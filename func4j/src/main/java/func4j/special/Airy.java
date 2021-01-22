package func4j.special;

import func4j.natives.NativeLibraryLoader;
import util4j.complex.Complex;

public class Airy {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Airy() {

	}

	// from GSL
	
	public static native double ai(double x) ;
	public static native double aiScaled(double x) ;
	public static native double aiDeriv(double x) ;
	public static native double aiDerivScaled(double x) ;
	public static native double aiZero(int n) ;
	public static native double aiDerivZero(int n) ;

	public static native double bi(double x) ;
	public static native double biScaled(double x) ;
	public static native double biDeriv(double x) ;
	public static native double biDerivScaled(double x) ;
	public static native double biZero(int n) ;
	public static native double biDerivZero(int n) ;
	
	
	// from amos
	public static native double[] ai(double re, double im) ;
	public static native double[] aiScaled(double re, double im) ;
	
	public static native double[] aiDeriv(double re, double im) ;
	public static native double[] aiDerivScaled(double re, double im) ;
	
	public static Complex ai(Complex z) {
		return Complex.ofArray(ai(z.re(), z.im())) ;
	}
	
	public static Complex aiDeriv(Complex z) {
		return Complex.ofArray(aiDeriv(z.re(), z.im())) ;
	}
	
	public static Complex aiScaled(Complex z) {
		return Complex.ofArray(aiScaled(z.re(), z.im())) ;
	}
	
	public static Complex aiDerivScaled(Complex z) {
		return Complex.ofArray(aiDerivScaled(z.re(), z.im())) ;
	}

}
