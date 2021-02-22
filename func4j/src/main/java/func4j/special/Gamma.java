package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class Gamma {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Gamma() {

	}

	// Gamma Functions --> form GSL
	public static native double gamma(double x) ;
	public static native double lngamma(double x) ;
	public static native double[] lngammaSign(double x) ;
	public static native double gammaStar(double x) ;
	public static native double gammaInv(double x) ;
	public static native double[] lngammaComplex(double re, double im) ;

	// Factorials --> form GSL
	public static native double factorial(int n) ;
	public static native double doubleFactorial(int n) ;
	public static native double lnfactorial(int n) ;
	public static native double lnDoubleFactorial(int n) ;
	public static native double choose(int n, int m) ;
	public static native double lnchoose(int n, int m) ;
	public static native double taylorCoeff(int n, double x) ;

	// Pochhammer Symbol --> form GSL
	public static native double poch(double a, double x) ;
	public static native double lnpoch(double a, double x) ;
	public static native double[] lnpochSign(double a, double x) ;
	public static native double pochrel(double a, double x) ;

	// Incomplete Gamma Functions --> form GSL
	public static native double gammaIncomplete(double a, double x) ;
	public static native double gammaIncompleteQ(double a, double x) ;
	public static native double gammaIncompleteP(double a, double x) ;

	// Beta Functions --> form GSL
	public static native double beta(double a, double b) ;
	public static native double lnbeta(double a, double b) ;


	// Incomplete Beta Function --> form GSL
	public static native double betaIncomplete(double a, double b, double x) ;
	
	// for scipy.special
	public static native double[] lngamma(double re, double im) ;
	public static native double[] gamma(double re, double im) ;

}
