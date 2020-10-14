package org.gsl4j.complex;

import java.io.Serializable;
import org.gsl4j.AlgebraicEntity;
import org.gsl4j.util.NativeLibraryLoader;
import org.gsl4j.util.StringUtils;

/**
 * Complex numbers are represented using the type {@code gsl_complex}.
 * The internal representation of this type may vary across platforms
 * and should not be accessed directly. The functions and macros described
 * below allow complex numbers to be manipulated in a portable way.
 *
 * @author Meisam
 * @since 1.0
 */
public final class Complex implements Serializable, AlgebraicEntity<Complex> {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private static final long serialVersionUID = -5423139577060070132L;

	/**
	 * ZERO = 0.0+j0.0: represents the origin of the complex plane
	 */
	public static final Complex ZERO = new Complex(0.0, 0.0) ;

	/**
	 * j = 0.0+j1.0: represents the imaginary number
	 */
	public static final Complex j = new Complex(0.0, 1.0) ;

	/**
	 * DISPLAY_ACCURACY: decimal accuracy when calling {@code toString()} method.
	 */
	static int DISPLAY_ACCURACY = 4 ;

	/**
	 * epsilon: accuracy for comparing complex numbers when calling {@code equals()}
	 * method.
	 */
	static double epsilon = 1e-10 ;

	private double re ;
	private double im ;

	private Complex(double re, double im) {
		this.re = re ;
		this.im = im ;
	}

	// copy constructor
	private Complex(Complex z) {
		this.re = z.re ;
		this.im = z.im ;
	}

	/**
	 * The real and imaginary part are stored in contiguous elements of a two element array. This eliminates any padding between the real and imaginary parts, dat[0] and dat[1],
	 * allowing the struct to be mapped correctly onto packed complex arrays.
	 * @param z : complex number to be copied
	 * @return {@code Complex} : a new complex number
	 */
	public static Complex of(Complex z) {
		return ofRect(z.re, z.im) ;
	}

	/**
	 * Constructs a complex number from a real number
	 * @param re : the real part of the complex number. Imaginary part is set to zero.
	 * @return a complex number
	 */
	public static Complex of(double re) {
		return ofRect(re, 0.0) ;
	}

	/**
	 * This function uses the rectangular Cartesian components (x,y) to return the complex number z = x + i y.
	 * @param x : real part
	 * @param y : imaginary part
	 * @return {@code Complex} : a new complex number
	 */
	public static Complex ofRect(double x, double y) {
		return new Complex(x, y) ;
	}

	/**
	 * This function returns the complex number z = r \exp(i \theta) = r (\cos(\theta) + i \sin(\theta)) from the polar representation (r, theta).
	 * @param r : magnitude of the complex number |z|
	 * @param phiRad : phase (arg) of the complex number arg(z)
	 * @return {@code Complex} : a new complex number
	 */
	public static Complex ofPolar(double r, double phiRad) {
		return new Complex(r*Math.cos(phiRad), r*Math.sin(phiRad)) ;
	}

	/**
	 * This function returns the complex number z = r \exp(i \theta) = r (\cos(\theta) + i \sin(\theta)) from the polar representation (r, theta).
	 * @param r : magnitude of the complex number |z|
	 * @param phiDegree : phase (arg) of the complex number arg(z)
	 * @return {@code Complex} : a new complex number
	 */
	public static Complex ofPolarDegree(double r, double phiDegree) {
		return ofPolar(r, Math.toRadians(phiDegree)) ;
	}

	public static Complex ofArray(double...realAndImag) {
		if(realAndImag.length != 2)
			throw new IllegalArgumentException("Number of array elements must be exactly 2") ;
		return ofRect(realAndImag[0], realAndImag[1]) ;
	}

	public static Complex ofArrayRect(double...realAndImag) {
		if(realAndImag.length != 2)
			throw new IllegalArgumentException("Number of array elements must be exactly 2") ;
		return ofRect(realAndImag[0], realAndImag[1]) ;
	}

	public static Complex ofArrayPolar(double...magAndPhase) {
		if(magAndPhase.length != 2)
			throw new IllegalArgumentException("Number of array elements must be exactly 2") ;
		return ofPolar(magAndPhase[0], magAndPhase[1]) ;
	}

	public static Complex ofArrayPolarDegree(double...magAndPhaseDegree) {
		if(magAndPhaseDegree.length != 2)
			throw new IllegalArgumentException("Number of array elements must be exactly 2") ;
		return ofPolarDegree(magAndPhaseDegree[0], magAndPhaseDegree[1]) ;
	}

	/**
	 * Real part of z: Re(z)
	 * @return real part of a complex number.
	 */
	public double re() {
		return re ;
	}

	/**
	 * Imaginary part of z: Im(z)
	 * @return imaginary part of a complex number.
	 */
	public double im() {
		return im ;
	}

	/**
	 * This function sets the accuracy of comparing two {@code Complex} numbers
	 * when calling {@code equals()} method.
	 * @param tolerance : tolerance of comparison. Default value is 1e-10.
	 */
	public void setComplexTolerance(double tolerance) {
		epsilon = tolerance ;
	}

	public double abs() {
		return ComplexMath.abs(re, im) ;
	}

	public double abs2() {
		return ComplexMath.abs2(re, im) ;
	}

	public boolean isReal() {
		return Math.abs(im)<epsilon ? true : false ;
	}

	public double[] toArray() {
		return new double[] {re, im} ;
	}

	public double[] toArrayPolar() {
		double r = ComplexMath.abs(re, im) ;
		double theta = ComplexMath.arg(re, im) ;
		return new double[] {r, theta} ;
	}

	public static void setDisplayAccuracy(int decimals) {
		DISPLAY_ACCURACY = decimals ;
	}

	@Override
	public String toString() {
		String formatter = "%." + DISPLAY_ACCURACY + "f" ;
		String stReal = String.format(formatter, re) ;
		String stImag = String.format(formatter, Math.abs(im)) ;
		if (im == 0)
			return stReal + "";
		if (re == 0 && im > 0)
			return "j" + stImag;
		if (re == 0 && im < 0)
			return "-j" + stImag;
		if (im < 0)
			return stReal + "-" + "j" + stImag;
		return stReal + "+" + "j" + stImag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(im);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(re);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Complex))
			return false;
		Complex other = (Complex) obj;
		if (Math.abs(re-other.re)>epsilon)
			return false;
		if (Math.abs(im-other.im)>epsilon)
			return false;
		return true;
	}

	/**
	 * A method for parsing {@code Complex} object from a String. The format must
	 * include "i" or "j" and be like "a + j b" or "a - j b".
	 *
	 * @param st : String representation of a complex number
	 * @return {@code Complex} : a new complex object
	 */
	public static Complex parseComplex(String st) {
		boolean hasJ = st.contains("j");
		boolean hasI = st.contains("i");
		st = st.trim();
		st = st.replaceAll("\\s+", "");
		if (/*isNumeric(st) && */ (hasI || hasJ)) {
			double realPart = 0;
			double imagPart = 0;
			String[] num = null;
			if (!hasJ) {
				num = st.split("i");
			} else {
				num = st.split("j");
			}
			String real = num[0];
			String imag = num[1];
			// the correct format is "a + j b" or " a + i b"
			if (real.charAt(real.length() - 1) == '+') {
				imagPart = Double.parseDouble(imag);
			} else {
				imagPart = -Double.parseDouble(imag);
			}
			if (real.charAt(0) == '-') {
				realPart = -Double.parseDouble(real.replaceAll("[+-]", ""));
			} else {
				realPart = Double.parseDouble(real.replaceAll("[+-]", ""));
			}
			return new Complex(realPart, imagPart);
		} else if (StringUtils.isNumeric(st)) {
			return new Complex(Double.parseDouble(st), 0);
		} else {
			return null;
		}
	}

	//*********** support for algebraic operations *************

	/**
	 * Static factory method for creating a complex number from a double primitive.
	 * See also {@link #of(double)}
	 * @param v
	 * @return
	 */
	public static Complex valueOf(double v) {
		return ofRect(v, 0.0) ;
	}

	@Override
	public Complex add(double v) {
		return ofRect(re+v, im) ;
	}

	@Override
	public Complex addRev(double v) {
		return ofRect(v+re, im) ;
	}

	@Override
	public Complex add(Complex v) {
		return ofRect(this.re+v.re, this.im+v.im) ;
	}

	@Override
	public Complex addRev(Complex v) {
		return ofRect(v.re+this.re, v.im+this.im) ;
	}

	@Override
	public Complex subtract(double v) {
		return ofRect(re-v, im) ;
	}

	@Override
	public Complex subtractRev(double v) {
		return ofRect(v-re, -im) ;
	}

	@Override
	public Complex subtract(Complex v) {
		return ofRect(this.re-v.re, this.im-v.im) ;
	}

	@Override
	public Complex subtractRev(Complex v) {
		return ofRect(v.re-this.re, v.im-this.im) ;
	}

	@Override
	public Complex multiply(double v) {
		return ofRect(v*re, v*im) ;
	}

	@Override
	public Complex multiplyRev(double v) {
		return ofRect(v*re, v*im) ;
	}

	@Override
	public Complex multiply(Complex v) {
		return ofArray(ComplexMath.mul(re, im, v.re, v.im)) ;
	}

	@Override
	public Complex multiplyRev(Complex v) {
		return ofArray(ComplexMath.mul(v.re, v.im, re, im)) ;
	}

	@Override
	public Complex divide(double v) {
		return ofRect(re/v, im/v) ;
	}

	@Override
	public Complex divideRev(double v) {
		return ofArray(ComplexMath.div(v, 0.0, re, im)) ;
	}

	@Override
	public Complex divide(Complex v) {
		return ofArray(ComplexMath.div(this.re, this.im, v.re, v.im)) ;
	}

	@Override
	public Complex divideRev(Complex v) {
		return ofArray(ComplexMath.div(v.re, v.im, this.re, this.im)) ;
	}

	@Override
	public Complex negate() {
		return ofRect(-re, -im) ;
	}


}
