package org.gsl4j.polynom;

import java.io.Serializable;
import java.util.ArrayList;
import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexMath;
import org.gsl4j.util.ArrayUtils;
import org.gsl4j.util.NativeLibraryLoader;
import static org.gsl4j.polynom.PolynomialMath.* ;


public class Polynomial implements Serializable {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private static final long serialVersionUID = 1L;

	public static final Polynomial x = new Polynomial(0.0, 1.0);
	public static final Polynomial ZERO = new Polynomial();
	private static double coeffEps = 1e-10;

	double[] coeffs; // coefficients (length = degree + 1)
	int deg; // degree of polynomial (0 for the zero polynomial)

	// a0 + a1*x + a2*x^2 + ... an * x^n
	private Polynomial(double... coeffs) {
		this.coeffs = coeffs;
		this.deg = degree();
		reduce() ;
	}

	// a * x^b
	private Polynomial(double a, int n) {
		coeffs = new double[n + 1];
		coeffs[n] = a;
		deg = degree();
		reduce() ;
	}

	// zero polynomial
	private Polynomial() {
		this(0.0);
	}

	// constant polynomial
	private Polynomial(double c) {
		this(c, 0);
	}

	public static Polynomial of(Polynomial p) {
		return new Polynomial(p.coeffs);
	}

	// a0 + a1*x + a2*x^2 + ... an * x^n
	public static Polynomial ofCoeffs(double... coeffs) {
		return new Polynomial(coeffs);
	}

	// a * x^b
	public static Polynomial ofSingleTerm(double a, int n) {
		return new Polynomial(a, n);
	}

	public static Polynomial zeroPolynom() {
		return new Polynomial();
	}

	// return the degree of this polynomial (0 for the zero polynomial)
	public int degree() {
		for (int i = coeffs.length - 1; i >= 0; i--)
			if (Math.abs(coeffs[i])>coeffEps)
				return i;
		return 0;
	}

	public static Polynomial reduce(Polynomial p) {
		int index = p.coeffs.length-1 ;
		// remove zeros from higher order terms
		if(Math.abs(p.coeffs[index])<coeffEps) {
			while(Math.abs(p.coeffs[index])<coeffEps) {
				index-- ;
				// check for p(x) = 0
				if(index == -1)
					return new Polynomial() ;
			}
			double[] reducedCoeffs = new double[index+1] ;
			for(int i=0; i<index+1; i++)
				reducedCoeffs[i] = p.coeffs[i] ;
			return of(p) ;
		}
		else {
			return p ;
		}
	}

	public void reduce() {
		int index = coeffs.length-1 ;
		// remove zeros from higher order terms
		if(Math.abs(coeffs[index])<coeffEps) {
			while(Math.abs(coeffs[index])<coeffEps) {
				index-- ;
				// check for p(x) = 0
				if(index == -1)
					return ;
			}
			double[] reducedCoeffs = new double[index+1] ;
			for(int i=0; i<index+1; i++)
				reducedCoeffs[i] = coeffs[i] ;
			this.coeffs = reducedCoeffs ;
			this.deg = index ;
		}
	}

	public double[] coeffs() {
		return coeffs ;
	}

	public void setCoeffTolerance(double epsilon) {
		coeffEps = epsilon;
	}

	public double limit(double a) {
		return evaluate(a);
	}

//	public static Polynomial parsePolynom(String s) {
//		return null ;
//	}

	public Polynomial pow(int m) {
		Polynomial a = this;
		Polynomial p = new Polynomial(new double[] { 1.0 });
		for (int i = 0; i < m; i++) {
			p = p.multiply(a);
		}
		return p;
	}

	// return a(b(x)) - compute using Horner's method
	public Polynomial compose(Polynomial q) {
		Polynomial p = this;
		Polynomial c = new Polynomial();
		for (int i = p.deg; i >= 0; i--) {
			Polynomial term = new Polynomial(p.coeffs[i], 0);
			c = term.add(q.multiply(c));
		}
		return c;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Polynomial))
			return false ;
		Polynomial a = this;
		Polynomial b = (Polynomial) obj;
		if (a.deg != b.deg)
			return false;
		for (int i = a.deg; i >= 0; i--) {
			if(Math.abs(a.coeffs[i]-b.coeffs[i])>coeffEps)
				return false;
		}
		return true;
	}

	public double evaluate(double x) {
		return PolynomialMath.eval(coeffs, x) ;
	}

	public Polynomial diff() {
		if (deg == 0)
			return zeroPolynom() ;
		return ofCoeffs(PolynomialMath.diff(coeffs)) ;
	}

	public Polynomial diff(int order) {
		if (order == 0)
			return this;
		if (deg == 0)
			return zeroPolynom() ;
		return ofCoeffs(PolynomialMath.diff(coeffs, order)) ;
	}

	public Polynomial integrate() {
		return ofCoeffs(PolynomialMath.integrate(coeffs)) ;
	}

	public Polynomial integrate(int order) {
		return ofCoeffs(PolynomialMath.integrate(coeffs, order)) ;
	}

	public double integrate(double x0, double x1) {
		return PolynomialMath.integrate(coeffs, x0, x1) ;
	}

	public Complex[] getRoots() {
		if(deg==0)
			return new Complex[0] ;
		double[] complexroots = PolynomialMath.solvePolynomialComplexRoots(coeffs) ;
		return ArrayUtils.toComplexArray(complexroots) ;
	}

	public ArrayList<Complex> getRootsAsList() {
		ArrayList<Complex> roots = new ArrayList<>();
		if(deg==0)
			return roots ;
		Complex[] allRoots = getRoots();
		for (Complex r : allRoots)
			roots.add(r);
		return roots;
	}

	public static Polynomial ofFactors(ArrayList<Polynomial> factors) {
		Polynomial p = ofCoeffs(1.0) ;
		for (int i = 0; i < factors.size(); i++)
			p = p * factors.get(i);
		return p;
	}

	public static Polynomial ofFactors(Polynomial... factors) {
		Polynomial p = ofCoeffs(1.0) ;
		for (int i = 0; i < factors.length; i++)
			p = p * factors[i];
		return p;
	}

	public static Polynomial ofRoots(ArrayList<Complex> roots) {
		Polynomial p = ofCoeffs(1.0) ;
		for (Complex root : roots) {
			if (Math.abs(root.im()) < 1e-5) {
				p = p * (x - root.re());
			}
		}
		for (Complex root : roots) {
			if (root.im() > 1e-5) {
				p = p * (x * x - 2 * root.re() * x + ComplexMath.abs2(root.re(), root.im()));
			}
		}
		return p;
	}

	public static Polynomial ofRoots(Complex... roots) {
		Polynomial p = ofCoeffs(1.0) ;
		for (Complex root : roots) {
			if (Math.abs(root.im()) < 1e-5) {
				p = p * (x - root.re());
			}
		}
		for (Complex root : roots) {
			if (root.im() > 1e-5) {
				p = p * (x * x - 2 * root.re() * x + ComplexMath.abs2(root.re(), root.im()));
			}
		}
		return p;
	}

	public static Polynomial ofRoots(double... roots) {
		Polynomial p = ofCoeffs(1.0) ;
		for (double root : roots) {
			p = p * (x - root) ;
		}
		return p;
	}

	// convert to string representation
	public String toString() {
		if (deg == 0)
			return "" + format(coeffs[0]);
		if (deg == 1) {
			if (coeffs[0] > 0.0)
				return format(coeffs[1]) + " x + " + format(coeffs[0]);
			else if (coeffs[0] < 0.0)
				return format(coeffs[1]) + " x - " + format(Math.abs(coeffs[0]));
			else
				return format(coeffs[1]) + " x";
		}
		String s = format(coeffs[deg]) + " x^" + deg;
		for (int i = deg - 1; i >= 0; i--) {
			if (Math.abs(coeffs[i])<coeffEps)
				continue;
			else if (coeffs[i] > 0)
				s = s + " + " + (format(coeffs[i]));
			else if (coeffs[i] < 0)
				s = s + " - " + (format(-coeffs[i]));
			if (i == 1)
				s = s + " x";
			else if (i > 1)
				s = s + " x^" + i;
		}
		return s;
	}

	private String format(double c) {
		if (Math.abs(c) > 1e-3)
			return String.format("%.4f", c);
		else
			return String.format("%.8f", c);
	}

	public Polynomial copy() {
		return new Polynomial(coeffs);
	}

	public ArrayList<Polynomial> getFactors() {
		Complex[] roots = getRoots();
		ArrayList<Polynomial> factors = new ArrayList<>();
		for (Complex root : roots) {
			if (Math.abs(root.im()) < 1e-5) {
				factors.add(ofCoeffs(-root.re(), 1.0));
			}
		}
		for (Complex root : roots) {
			if (root.im() > 1e-5) {
				factors.add(ofCoeffs(root.abs2(), -2.0*root.re(), 1.0)) ;
			}
		}

		return factors;
	}

	public static ArrayList<Complex> getCommonRoots(Polynomial p, Polynomial q) {
		ArrayList<Polynomial> commonFactors = getCommonFactors(p, q);
		ArrayList<Complex> commonRoots = new ArrayList<>();
		for (Polynomial pp : commonFactors)
			commonRoots.addAll(pp.getRootsAsList());

		return commonRoots;
	}

	public static ArrayList<Polynomial> getCommonFactors(Polynomial p, Polynomial q) {
		ArrayList<Polynomial> factorsOfP = p.getFactors();
		ArrayList<Polynomial> factorsOfQ = q.getFactors();
		ArrayList<Polynomial> factors = new ArrayList<>();

		ArrayList<String> factorsP = new ArrayList<>();
		for (Polynomial pp : factorsOfP)
			factorsP.add(pp.toString());
		ArrayList<String> factorsQ = new ArrayList<>();
		for (Polynomial qq : factorsOfQ)
			factorsQ.add(qq.toString());

		ArrayList<String> commons = (ArrayList<String>) ArrayUtils.getCommonElements(factorsP, factorsQ);

		for (String s : commons) {
			for (Polynomial pp : factorsOfP) {
				if (s.equals(pp.toString())) {
					factors.add(pp);
					break;
				}
			}
		}
		return factors;
	}

	public ArrayList<Polynomial> getCommonFactors(Polynomial p) {
		return getCommonFactors(this, p);
	}

	// ************ operator overloading **********************

	public static Polynomial valueOf(double v) {
		return new Polynomial(v, 0);
	}

	public static Polynomial valueOf(double[] v) {
		return Polynomial.ofCoeffs(v) ;
	}

	public static Polynomial valueOf(Polynomial v) {
		return new Polynomial(v.coeffs);
	}

	public Polynomial add(Polynomial v) {
		return ofCoeffs(plus(this.coeffs, v.coeffs)) ;
	}

	public Polynomial addRev(Polynomial v) {
		return ofCoeffs(plus(v.coeffs, this.coeffs));
	}

	public Polynomial add(double v) {
		return ofCoeffs(plus(this.coeffs, v)) ;
	}

	public Polynomial addRev(double v) {
		return ofCoeffs(plus(this.coeffs, v)) ;
	}

	public Polynomial subtract(Polynomial v) {
		return ofCoeffs(minus(this.coeffs, v.coeffs)) ;
	}

	public Polynomial subtractRev(Polynomial v) {
		return ofCoeffs(minus(v.coeffs, this.coeffs)) ;
	}

	public Polynomial subtract(double v) {
		return ofCoeffs(minus(this.coeffs, v)) ;
	}

	public Polynomial subtractRev(double v) {
		return ofCoeffs(plus(times(this.coeffs, -1.0), v)) ;
	}

	public Polynomial multiply(Polynomial v) {
		return ofCoeffs(times(this.coeffs, v.coeffs)) ;
	}

	public Polynomial multiplyRev(Polynomial v) {
		return ofCoeffs(times(v.coeffs, this.coeffs)) ;
	}

	public Polynomial multiply(double v) {
		return ofCoeffs(times(this.coeffs, v)) ;
	}

	public Polynomial multiplyRev(double v) {
		return ofCoeffs(times(this.coeffs, v)) ;
	}

	public Rational divide(Polynomial v) {
		return new Rational(this, v);
	}

	public Rational divideRev(Polynomial v) {
		return new Rational(v, this);
	}

	public Polynomial divide(double v) {
		return ofCoeffs(divides(this.coeffs, v)) ;
	}

	public Rational divideRev(double v) {
		return Rational.valueOf(v) / this;
	}

	public Polynomial negate() {
		return ofCoeffs(times(this.coeffs, -1.0)) ;
	}

}
