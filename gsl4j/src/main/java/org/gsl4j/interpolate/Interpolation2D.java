package org.gsl4j.interpolate;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * Given a set of x coordinates x_1,...,x_m and a set of y coordinates y_1,...,y_n, each in increasing order, plus a set of function values z_{ij} for each grid point (x_i,y_j), the routines described in this section compute a continuous interpolation function z(x,y) such that z(x_i,y_j) = z_{ij}.
 *
 * @author Meisam
 *
 */
public class Interpolation2D {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	/**
	 * Bilinear interpolation. This interpolation method does not require any additional memory.
	 */
	public static final int BILINEAR = 0 ;

	/**
	 * Bicubic interpolation.
	 */
	public static final int BICUBIC = 1 ;

	double[] x ;
	double[] y ;
	double[] z ;
	int type ;

	/**
	 * The 2D interpolation routines access the function values z_{ij} with the following ordering:
	 * z_{ij} = za[j*xsize + i]
	 * with i = 0,...,xsize-1 and j = 0,...,ysize-1. However, for ease of use, the following functions are provided to add and retrieve elements from the function grid without requiring knowledge of the internal ordering.
	 * @param x
	 * @param y
	 * @param z
	 * @param type
	 */
	public Interpolation2D(double[] x, double[] y, double[] z, int type) {
		this.x = x ;
		this.y = y ;
		this.z = z ;
		this.type = type ;
		cacheData(type) ;
	}

	public Interpolation2D(double[] x, double[] y, double[][] z, int type) {
		this.x = x ;
		this.y = y ;
		this.z = new double[x.length*y.length] ;
		for(int i=0; i<x.length; i++)
			for(int j=0; j<y.length; j++) {
				this.z[i*y.length+j] = z[i][j] ;
			}
		this.type = type ;
		cacheData(type) ;
	}

	private native void cacheData(int type) ;

	/**
	 * This function returns the name of the interpolation type used by interp
	 * @return
	 */
	public native String name() ;

	/**
	 * These functions return the minimum number of points required by the interpolation object interp or interpolation type T. For example, bicubic interpolation requires a minimum of 4 points.
	 * @return
	 */
	public native int minSize() ;

	/**
	 * These functions return the interpolated value of z for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double eval(double x, double y) ;

	/**
	 * These functions return the interpolated value d = \partial z / \partial x for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double derivX(double x, double y) ;

	/**
	 * These functions return the interpolated value d = \partial z / \partial y for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double derivY(double x, double y) ;

	/**
	 * These functions return the interpolated value d = \partial^2 z / \partial x^2 for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double derivXX(double x, double y) ;

	/**
	 * These functions return the interpolated value d = \partial^2 z / \partial y^2 for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double derivYY(double x, double y) ;

	/**
	 * These functions return the interpolated value d = \partial^2 z / \partial x \partial y for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double derivXY(double x, double y) ;

	/**
	 * These functions return the interpolated value d = \partial^2 z / \partial y \partial x for a given point (x, y), using the interpolation object interp, data arrays xa, ya, and za and the accelerators xacc and yacc. When x is outside the range of xa or y is outside the range of ya, the error code GSL_EDOM is returned.
	 * @param x
	 * @param y
	 * @return
	 */
	public native double derivYX(double x, double y) ;

	@Override
	public String toString() {
		return name() ;
	}

}
