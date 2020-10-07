package org.gsl4j.polynom;

import java.util.ArrayList;
import org.gsl4j.complex.Complex;
import static org.gsl4j.polynom.Polynomial.* ;


public class Rational {

	private Polynomial p, q;

	// Rational R(x) = p(x)/q(x)

	public Rational(Polynomial numerator, Polynomial denominator) {
		this.p = numerator;
		this.q = denominator;
	}

	// Rational R(x) = p(x)/1

	public Rational(Polynomial numerator) {
		this.q = 0 * x + 1;
		this.p = numerator;
	}

	public boolean isPolynomial() {
		if(q.degree() < 1)
			return true ;
		else
			return false ;
	}

	public static boolean isPolynomial(Rational r) {
		return r.isPolynomial() ;
	}

	public double evaluate(double x) {
		return p.evaluate(x)/q.evaluate(x) ;
	}

	public Rational LHopital() {
		return new Rational(p.diff(), q.diff()) ;
	}

	public Polynomial numerator() {
		return p ;
	}

	public Polynomial denominator() {
		return q ;
	}

	public Rational plus(Rational r) {
		Rational a = this ;
		Polynomial num = a.p * r.q + a.q * r.p ;
		Polynomial denom = a.q * r.q ;
		return new Rational(num, denom) ;
	}

	public Rational plus(double a) {
		return new Rational(p+q*a, q) ;
	}

	public Rational minus(Rational r) {
		Rational a = this ;
		Polynomial num = a.p * r.q - a.q * r.p ;
		Polynomial denom = a.q * r.q ;
		return new Rational(num, denom) ;
	}

	public Rational minus(double a) {
		return new Rational(p-q*a, q) ;
	}

	public Rational times(Rational r) {
		Rational a = this ;
		Polynomial num = a.p * r.p ;
		Polynomial denom = a.q * r.q ;
		return new Rational(num, denom) ;
	}

	public Rational times(double a) {
		return new Rational(p*a, q) ;
	}

	public Rational divides(Rational r) {
		Rational a = this ;
		Polynomial num = a.p * r.q ;
		Polynomial denom = a.q * r.p ;
		return new Rational(num, denom) ;
	}

	public Rational divides(double a) {
		return new Rational(p, q*a) ;
	}

	public Rational diff() {
		Polynomial num = p.diff() * q - p * q.diff() ;
		Polynomial denom = q * q ;
		return new Rational(num, denom) ;
	}

	public Rational diff(int n) {
		Rational a = this ;
		for(int i=0; i<n; i++)
			a = a.diff() ;
		return a ;
	}

	public Rational simplify() {
		// leading coefficients of p(x) and q(x)
		double pcoef = p.coeffs[p.degree()] ;
		double qcoef = q.coeffs[q.degree()] ;
		// find common factors of of p(x) and q(x)
		ArrayList<Polynomial> factorsOfP = p.getFactors() ;
		ArrayList<Polynomial> factorsOfQ = q.getFactors() ;
		// remove the common factors
		factorsOfP.removeAll(getCommonFactors(p, q)) ;
		factorsOfQ.removeAll(getCommonFactors(q, p)) ;
		// reconstruct p(x) and q(x)
		Polynomial pSimp = 0*x+1 ;
		for(Polynomial w : factorsOfP)
			pSimp = pSimp * w ;
		Polynomial qSimp = 0*x+1 ;
		for(Polynomial z : factorsOfQ)
			qSimp = qSimp * z ;

		return new Rational(pSimp * pcoef, qSimp * qcoef) ;
	}

	public static Rational toRational(Polynomial p) {
		return new Rational(p, 0*x+1) ;
	}

	public ArrayList<Complex> zeroes() {
		Rational a = this.simplify() ;
		return a.p.getRootsAsList() ;
	}

	public ArrayList<Complex> poles() {
		Rational a = this.simplify() ;
		return a.q.getRootsAsList() ;
	}

	public double limit(double x0) {
		return this.simplify().evaluate(x0) ;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder() ;
		st.append(p) ;
		st.append("\n") ;
		int m = Math.max(p.toString().length(), q.toString().length()) ;
		for(int i=0; i<m; i++)
			st.append("-") ;
		st.append("\n") ;
		st.append(q) ;
		st.append("\n") ;
		return st ;
	}

	// ************ operator overloading **********************

	public static Rational valueOf(double v) {
		return new Rational(0*x+v);
	}

	public static Rational valueOf(Polynomial v) {
		return new Rational(v, 0*x+1) ;
	}

	public Rational add(Polynomial v) {
		return this.plus(new Rational(v)) ;
	}

	public Rational addRev(Polynomial v) {
		return this.plus(new Rational(v)) ;
	}

	public Rational add(Rational v) {
		return this.plus(v) ;
	}

	public Rational addRev(Rational v) {
		return this.plus(v) ;
	}

	public Rational add(double v) {
		return this.plus(v) ;
	}

	public Rational addRev(double v) {
		return this.plus(v) ;
	}

	public Rational subtract(Polynomial v) {
		return this.minus(new Rational(v)) ;
	}

	public Rational subtractRev(Polynomial v) {
		return this.times(-1).plus(new Rational(v)) ;
	}

	public Rational subtract(Rational v) {
		return this.minus(v) ;
	}

	public Rational subtractRev(Rational v) {
		return this.times(-1).plus(v) ;
	}

	public Rational subtract(double v) {
		return this.minus(v) ;
	}

	public Rational subtractRev(double v) {
		return this.times(-1).plus(v) ;
	}

	public Rational multiply(Polynomial v) {
		return this.times(new Rational(v));
	}

	public Rational multiplyRev(Polynomial v) {
		return (new Rational(v)).times(this);
	}

	public Rational multiply(Rational v) {
		return this.times(v);
	}

	public Rational multiplyRev(Rational v) {
		return v.times(this);
	}

	public Rational multiply(double v) {
		return this.times(v);
	}

	public Rational multiplyRev(double v) {
		return this.times(v);
	}

	public Rational divide(Polynomial v) {
		return this.divides(new Rational(v));
	}

	public Rational divideRev(Polynomial v) {
		return (new Rational(v)).divides(this);
	}

	public Rational divide(Rational v) {
		return this.divides(v);
	}

	public Rational divideRev(Rational v) {
		return v.divides(this);
	}

	public Rational divide(double v) {
		return this.divides(v) ;
	}

	public Rational divideRev(double v) {
		return (new Rational(0*x+v)).divides(this);
	}

	public Rational negate() {
		return this.times(-1) ;
	}


}
