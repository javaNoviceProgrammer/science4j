package org.gsl4j.tests;

import org.gsl4j.interpolate.Interpolation2D;
import org.gsl4j.interpolate.twod.BilinearInterpolation;
import org.gsl4j.util.MathUtils;
import static org.gsl4j.interpolate.Interpolation2D.* ;
import static java.lang.Math.sin ;
import static java.lang.Math.cos ;

class TestInterpolation2D {

	public static void test1() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[] z = new double[x.length*y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				int index = i*y.length+j ;
				z[index] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new Interpolation2D(x, y, z, BICUBIC) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.eval(xv, yv) ;
		System.out.println("interp = " + zv);
		System.out.println("exact = " + sin(xv)*cos(yv));
	}

	public static void test2() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[][] z = new double[x.length][y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				z[i][j] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new Interpolation2D(x, y, z, BICUBIC) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.derivX(xv, yv) ;
		System.out.println("interp dx = " + zv);
		System.out.println("exact dx = " + cos(xv)*cos(yv));
	}

	public static void test3() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[][] z = new double[x.length][y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				z[i][j] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new Interpolation2D(x, y, z, BICUBIC) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.derivY(xv, yv) ;
		System.out.println("interp dy = " + zv);
		System.out.println("exact dy = " + (-sin(xv)*sin(yv)));
	}

	public static void test4() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[][] z = new double[x.length][y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				z[i][j] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new Interpolation2D(x, y, z, BICUBIC) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.derivXX(xv, yv) ;
		System.out.println("interp dxx = " + zv);
		System.out.println("exact dxx = " + (-Math.sin(xv)*Math.cos(yv)));
	}

	public static void test5() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[][] z = new double[x.length][y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				z[i][j] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new Interpolation2D(x, y, z, BICUBIC) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.derivYY(xv, yv) ;
		System.out.println("interp dyy = " + zv);
		System.out.println("exact dyy = " + (-Math.sin(xv)*Math.cos(yv)));
	}

	public static void test6() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[][] z = new double[x.length][y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				z[i][j] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new Interpolation2D(x, y, z, BICUBIC) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.derivXY(xv, yv) ;
		System.out.println("interp dxdy = " + zv);
		System.out.println("exact dxdy = " + (-Math.cos(xv)*Math.sin(yv)));
	}

	public static void test7() {
		double[] x = MathUtils.linspace(0.0, 1.0, 50) ;
		double[] y = MathUtils.linspace(-1.0, 1.0, 40) ;
		double[][] z = new double[x.length][y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				z[i][j] = sin(x[i])*cos(y[j]) ;
			}
		Interpolation2D interp = new BilinearInterpolation(x, y, z) ;
		System.out.println(interp.name());
		double xv = 0.2 ;
		double yv = 0.2 ;
		double zv = interp.derivXY(xv, yv) ;
		System.out.println("interp dxdy = " + zv);
		System.out.println("exact dxdy = " + (-cos(xv)*sin(yv)));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
		test5() ;
		test6() ;
		test7() ;
	}

}
