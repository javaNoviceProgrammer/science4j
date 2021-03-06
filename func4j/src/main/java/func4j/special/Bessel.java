package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class Bessel {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Bessel() {

	}

	// Regular Cylindrical Bessel Functions --> from GSL
	public static native double J0(double x) ;
	public static native double J1(double x) ;
	public static native double Jn(int n, double x) ;
	public static native double[] JnArray(int nmin, int nmax, double x) ;

	// Irregular Cylindrical Bessel Functions --> from GSL
	public static native double Y0(double x) ;
	public static native double Y1(double x) ;
	public static native double Yn(int n, double x) ;
	public static native double[] YnArray(int nmin, int nmax, double x) ;

	// Regular Modified Cylindrical Bessel Functions --> from GSL
	public static native double I0(double x) ;
	public static native double I1(double x) ;
	public static native double In(int n, double x) ;
	public static native double[] InArray(int nmin, int nmax, double x) ;

	// Irregular Modified Cylindrical Bessel Functions --> from GSL
	public static native double I0Scaled(double x) ;
	public static native double I1Scaled(double x) ;
	public static native double InScaled(int n, double x) ;
	public static native double[] InScaledArray(int nmin, int nmax, double x) ;

	// Regular Spherical Bessel Functions --> from GSL
	public static native double K0(double x) ;
	public static native double K1(double x) ;
	public static native double Kn(int n, double x) ;
	public static native double[] KnArray(int nmin, int nmax, double x) ;

	// Irregular Spherical Bessel Functions --> from GSL
	public static native double K0Scaled(double x) ;
	public static native double K1Scaled(double x) ;
	public static native double KnScaled(int n, double x) ;
	public static native double[] KnScaledArray(int nmin, int nmax, double x) ;

	// Regular Bessel IntegralFunction1D—Fractional Order --> from GSL
	public static native double Jv(double v, double x) ;
	public static native double[] Jv(double v, double[] x) ;

	// Irregular Bessel Functions—Fractional Order --> from GSL
	public static native double Yv(double v, double x) ;

	// Regular Modified Bessel Functions—Fractional Order --> from GSL
	public static native double Iv(double v, double x) ;
	public static native double IvScaled(double v, double x) ;

	// Irregular Modified Bessel Functions—Fractional Order --> from GSL
	public static native double Kv(double v, double x) ;
	public static native double lnKv(double v, double x) ;
	public static native double KvScaled(double v, double x) ;

	// Zeros of Regular Bessel Functions --> from GSL
	public static native double zeroJ0(int n) ;
	public static native double zeroJ1(int n) ;
	public static native double zeroJv(double v, int n) ;
	
	
	/**
	 *  Purpose: Compute Bessel functions Jv(z) and its derivative with a complex argument and a large order <br>
	 *  Input:   v --- Order of Jv(z) , z --- Complex argument <br>
	 *  Output:  Jv(z), Jv'(z) <br>
	 *  
	 * @param v
	 * @param re
	 * @param im
	 * @return
	 */
	// --> from scipy
	public static native double[] JvAndJvDeriv(double v, double re, double im) ;
	
	/**
	 *  Purpose: Compute Bessel functions Yv(z) and its derivative with a complex argument and a large order <br>
	 *  Input:   v --- Order of Yv(z) , z --- Complex argument <br>
	 *  Output:  Yv(z) , Yv'(z) <br>
	 *  
	 * @param v
	 * @param re
	 * @param im
	 * @return
	 */
	// --> from scipy
	public static native double[] YvAndYvDeriv(double v, double re, double im) ;
	
	// Regular Cylindrical Bessel Functions --> from amos
	public static native double[] Jn(int n, double re, double im) ;
	public static native double[] Jv(double v, double re, double im) ;
	
	public static native double[] Yn(int n, double re, double im) ;
	public static native double[] Yv(double v, double re, double im) ;
	
	public static native double[] In(int n, double re, double im) ;
	public static native double[] Iv(double v, double re, double im) ;
	
	public static native double[] Kn(int n, double re, double im) ;
	public static native double[] Kv(double v, double re, double im) ;
	
	public static native double[] Hn(int kind, int n, double re, double im) ;
	public static native double[] Hv(int kind, double v, double re, double im) ;
	
	public static native double[] H1n(int n, double re, double im) ;
	public static native double[] H1v(double v, double re, double im) ;
	
	public static native double[] H2n(int n, double re, double im) ;
	public static native double[] H2v(double v, double re, double im) ;
	
	public static native double[] Hn(int kind, int n, double x) ;
	public static native double[] Hv(int kind, double v, double x) ;
	
	public static native double[] H1n(int n, double x) ;
	public static native double[] H1v(double v, double x) ;
	
	public static native double[] H2n(int n, double x) ;
	public static native double[] H2v(double v, double x) ;

}
