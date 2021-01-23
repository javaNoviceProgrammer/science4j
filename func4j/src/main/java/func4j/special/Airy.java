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
	
	public static native double[] bi(double re, double im) ;
	public static native double[] biScaled(double re, double im) ;
	
	public static native double[] biDeriv(double re, double im) ;
	public static native double[] biDerivScaled(double re, double im) ;
	
	
	public static Complex ai(Complex z) {
		return Complex.ofArray(Airy.ai(z.re(), z.im())) ;
	}
	
	public static Complex aiDeriv(Complex z) {
		return Complex.ofArray(Airy.aiDeriv(z.re(), z.im())) ;
	}
	
	public static Complex aiScaled(Complex z) {
		return Complex.ofArray(Airy.aiScaled(z.re(), z.im())) ;
	}
	
	public static Complex aiDerivScaled(Complex z) {
		return Complex.ofArray(Airy.aiDerivScaled(z.re(), z.im())) ;
	}
	
	public static Complex bi(Complex z) {
		return Complex.ofArray(Airy.bi(z.re(), z.im())) ;
	}
	
	public static Complex biDeriv(Complex z) {
		return Complex.ofArray(Airy.biDeriv(z.re(), z.im())) ;
	}
	
	public static Complex biScaled(Complex z) {
		return Complex.ofArray(Airy.biScaled(z.re(), z.im())) ;
	}
	
	public static Complex biDerivScaled(Complex z) {
		return Complex.ofArray(Airy.biDerivScaled(z.re(), z.im())) ;
	}
	
}
