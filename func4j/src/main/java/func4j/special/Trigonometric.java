package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Trigonometric {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	// Trigonometric Functions

	private Trigonometric() {

	}

	// Circular Trigonometric Functions

	public static native double sin(double x) ;
	public static native double cos(double x) ;
	public static native double hypot(double x, double y) ;
	
	/**
	 * Sinc(x) = sin(pi x) / (pi x)
	 * @param x
	 * @return
	 */
	public static native double sinc(double x) ;
	
	/**
	 * Sinc^2(x) = sin^2(pi x) / (pi x)^2
	 * @param x
	 * @return
	 */
	public static native double sinc2(double x) ;

	// Trigonometric Functions for Complex Arguments

	public static native double[] sin(double re, double im) ;
	public static native double[] cos(double re, double im) ;
	public static native double[] logsin(double re, double im) ;

	// Hyperbolic Trigonometric Functions

	public static native double lnsinh(double x) ;
	public static native double lncosh(double x) ;

	// Conversion Functions

	/**
	 * Convert polar to rectlinear coordinates.
	 * @param r
	 * @param phiRad
	 * @return
	 */
	public static native double[] polarToRect(double r, double phiRad) ;
	
	/**
	 * Convert rectilinear to polar coordinates.
	 * @param r
	 * @param phiRad
	 * @return argument in range [-pi, pi]
	 */
	public static native double[] rectToPolar(double x, double y) ;

	// Restriction Functions

	/**
	 * Force an angle to lie in the range (-pi,pi].
	 * @param theta
	 * @return
	 */
	public static native double angleRestrictSymm(double theta) ;
	
	/**
	 * Force an angle to lie in the range [0, 2pi)
	 * @param theta
	 * @return
	 */
	public static native double angleRestrictPos(double theta) ;

	// Trigonometric Functions With Error Estimates

	/**
	 * Sin(x) for quantity with an associated error.
	 * @param x
	 * @param dx
	 * @return
	 */
	public static native double[] sinErr(double x, double dx) ;
	
	/**
	 * Cos(x) for quantity with an associated error.
	 * @param x
	 * @param dx
	 * @return
	 */
	public static native double[] cosErr(double x, double dx) ;

}
