package util4j.array;

import util4j.complex.ComplexMath;
import util4j.complex.ComplexNumber;
import util4j.container.Pair;
import util4j.container.Tuples;


public class ArrayMath {
	

	// make the constructor private
	private ArrayMath() {}
	
	
	//############### Sin #######################
	
	public static double[] sin(double[] x) {
		int len = x.length ;
		double[] y = new double[len] ;
		for(int i=0; i<len; i++) {
			y[i] = Math.sin(x[i]) ;
		}
		return y ;
	}
	
	public static NdArray sin(NdArray x) {
		return new NdArray(sin(x.data), x.shape) ;
	}
	

	public static Pair<double[], double[]> sin(double[] xRe, double[] xIm) {
		int len = xRe.length ;
		double[] yRe = new double[len] ;
		double[] yIm = new double[len] ;
		for(int i=0; i<len; i++) {
			double[] a = ComplexMath.sin(xRe[i], xIm[i]) ;
			yRe[i] = a[0] ;
			yIm[i] = a[1] ;
		}
		return Tuples.makePair(yRe, yIm) ;
	}
	
	public static ComplexNumber[] sin(ComplexNumber[] x) {
		int len = x.length ;
		ComplexNumber[] y = new ComplexNumber[len] ;
		for(int i=0; i<len; i++) {
			y[i] = ComplexMath.sin(x[i]) ;
		}
		return y ;
	}
	
	public static ComplexNdArray sin(ComplexNdArray x) {
		Pair<double[], double[]> result = sin(x.dataRe, x.dataIm);
		return new ComplexNdArray(result.first, result.second, x.shape) ;
	}
	
	
	//############### Cos #######################
	
	public static double[] cos(double[] x) {
		int len = x.length ;
		double[] y = new double[len] ;
		for(int i=0; i<len; i++) {
			y[i] = Math.cos(x[i]) ;
		}
		return y ;
	}
	
	public static NdArray cos(NdArray x) {
		return new NdArray(cos(x.data), x.shape) ;
	}
	
	public static Pair<double[], double[]> cos(double[] xRe, double[] xIm) {
		int len = xRe.length ;
		double[] yRe = new double[len] ;
		double[] yIm = new double[len] ;
		for(int i=0; i<len; i++) {
			double[] a = ComplexMath.cos(xRe[i], xIm[i]) ;
			yRe[i] = a[0] ;
			yIm[i] = a[1] ;
		}
		return Tuples.makePair(yRe, yIm) ;
	}
	
	public static ComplexNumber[] cos(ComplexNumber[] x) {
		int len = x.length ;
		ComplexNumber[] y = new ComplexNumber[len] ;
		for(int i=0; i<len; i++) {
			y[i] = ComplexMath.cos(x[i]) ;
		}
		return y ;
	}
	
	public static ComplexNdArray cos(ComplexNdArray x) {
		Pair<double[], double[]> result = cos(x.dataRe, x.dataIm);
		return new ComplexNdArray(result.first, result.second, x.shape) ;
	}
	
	//############### tan #######################
	
	public static double[] tan(double[] x) {
		int len = x.length ;
		double[] y = new double[len] ;
		for(int i=0; i<len; i++) {
			y[i] = Math.tan(x[i]) ;
		}
		return y ;
	}
	
	public static NdArray tan(NdArray x) {
		return new NdArray(tan(x.data), x.shape) ;
	}
	
	public static Pair<double[], double[]> tan(double[] xRe, double[] xIm) {
		int len = xRe.length ;
		double[] yRe = new double[len] ;
		double[] yIm = new double[len] ;
		for(int i=0; i<len; i++) {
			double[] a = ComplexMath.tan(xRe[i], xIm[i]) ;
			yRe[i] = a[0] ;
			yIm[i] = a[1] ;
		}
		return Tuples.makePair(yRe, yIm) ;
	}
	
	public static ComplexNumber[] tan(ComplexNumber[] x) {
		int len = x.length ;
		ComplexNumber[] y = new ComplexNumber[len] ;
		for(int i=0; i<len; i++) {
			y[i] = ComplexMath.tan(x[i]) ;
		}
		return y ;
	}
	
	public static ComplexNdArray tan(ComplexNdArray x) {
		Pair<double[], double[]> result = tan(x.dataRe, x.dataIm);
		return new ComplexNdArray(result.first, result.second, x.shape) ;
	}
	
	//############### cot #######################
	
	public static double[] cot(double[] x) {
		int len = x.length ;
		double[] y = new double[len] ;
		for(int i=0; i<len; i++) {
			y[i] = 1.0/Math.tan(x[i]) ;
		}
		return y ;
	}
	
	public static NdArray cot(NdArray x) {
		return new NdArray(cot(x.data), x.shape) ;
	}

}
