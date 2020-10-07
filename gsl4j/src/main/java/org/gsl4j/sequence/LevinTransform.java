package org.gsl4j.sequence;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * The functions described in this class accelerate the convergence of a
 * series using the Levin u-transform. This method takes a small number of
 * terms from the start of a series and uses a systematic approximation to
 * compute an extrapolated value and an estimate of its error. The u-transform
 * works for both convergent and divergent series, including asymptotic series.
 * <br>
 * These functions are declared in the header file {@code gsl_sum.h}. <br>
 * The following functions compute the full Levin u-transform of a series with
 * its error estimate. The error estimate is computed by propagating rounding
 * errors from each term through to the final extrapolation.
 * <br>These functions are intended for summing analytic series where each term is known to high
 * accuracy, and the rounding errors are assumed to originate from finite
 * precision. They are taken to be relative errors of order GSL_DBL_EPSILON
 * for each term. <br>
 * The calculation of the error in the extrapolated value is
 * an O(N^2) process, which is expensive in time and memory. A faster but less
 * reliable method which estimates the error from the convergence of the
 * extrapolated value is described in the next section. For the method
 * described here a full table of intermediate values and derivatives through
 * to O(N) must be computed and stored, but this does give a reliable error
 * estimate.
 *
 * @author Meisam
 *
 */
public class LevinTransform {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private LevinTransform() {

	}

	// Acceleration functions

	/**
	 * This function takes the terms of a series in array of size array_size and computes the extrapolated limit of the series using a Levin u-transform. Additional working space must be provided in w. The extrapolated sum is stored in sum_accel, with an estimate of the absolute error stored in abserr. The actual term-by-term sum is returned in w->sum_plain. The algorithm calculates the truncation error (the difference between two successive extrapolations) and round-off error (propagated from the individual terms) to choose an optimal number of terms for the extrapolation. All the terms of the series passed in through array should be non-zero.
	 *
	 * @param array values of the sequence terms of the series
	 * @return an array containing sum_accel, abs_err
	 */
	public static native double[] sumLevinUaccel(double[] array) ;

	// Acceleration functions without error estimation

	/**
	 * This function takes the terms of a series in array of size array_size and computes the extrapolated limit of the series using a Levin u-transform. Additional working space must be provided in w. The extrapolated sum is stored in sum_accel. The actual term-by-term sum is returned in w->sum_plain. The algorithm terminates when the difference between two successive extrapolations reaches a minimum or is sufficiently small. The difference between these two values is used as estimate of the error and is stored in abserr_trunc. To improve the reliability of the algorithm the extrapolated values are replaced by moving averages when calculating the truncation error, smoothing out any fluctuations.
	 *
	 * @param array Array values of the sequence terms of the series
	 * @return An array containing sum_accel, abs_err_turnc
	 */
	public static native double[] sumLevinUtruncAccel(double[] array) ;


}
