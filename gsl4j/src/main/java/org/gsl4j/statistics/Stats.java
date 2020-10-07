package org.gsl4j.statistics;

import org.gsl4j.util.NativeLibraryLoader;

public class Stats {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Stats() {

	}

	// Mean, Standard Deviation and Variance

	public static native double mean(double[] data) ;
	public static native double variance(double[] data) ;
	public static native double varianceMean(double[] data, double mean) ;
	public static native double sd(double[] data) ;
	public static native double sdMean(double[] data, double mean) ;
	public static native double totSumSquares(double[] data) ;
	public static native double totSumSquaresMean(double[] data, double mean) ;
	public static native double varianceWithFixedMean(double[] data, double mean) ;
	public static native double sdWithFixedMean(double[] data, double mean) ;

	// Absolute deviation

	public static native double absdev(double[] data) ;
	public static native double absdevMean(double[] data, double mean) ;

	// Higher moments (skewness and kurtosis)

	public static native double skew(double[] data) ;
	public static native double skewMeanSd(double[] data, double mean, double sd) ;
	public static native double kurtosis(double[] data) ;
	public static native double kurtosisMeanSd(double[] data, double mean, double sd) ;

	// Autocorrelation

	public static native double lag1Autocorrelation(double[] data) ;
	public static native double lag1AutocorrelationMean(double[] data, double mean) ;

	// Covariance

	public static native double covariance(double[] data1, double[] data2) ;
	public static native double covarianceMean(double[] data1, double[] data2, double mean1, double mean2) ;

	// Correlation

	public static native double correlation(double[] data1, double[] data2) ;
	public static native double spearman(double[] data1, double[] data2, double[] work) ;

	// Weighted Samples --> weights must be zero or positive

	public static native double weightedMean(double[] data, double[] weights) ;
	public static native double weightedVariance(double[] data, double[] weights) ;
	public static native double weightedVarianceMean(double[] data, double[] weights, double weightedMean) ;
	public static native double weightedSd(double[] data, double[] weights) ;
	public static native double weightedSdMean(double[] data, double[] weights, double weightedMean) ;
	public static native double weightedVarianceWithFixedMean(double[] data, double[] weights, double mean) ;
	public static native double weightedSdWithFixedMean(double[] data, double[] weights, double mean) ;
	public static native double weightedTotSumOfSquares(double[] data, double[] weights) ;
	public static native double weightedTotSumOfSquaresMean(double[] data, double[] weights, double mean) ;
	public static native double weightedAbsoluteSd(double[] data, double[] weights) ;
	public static native double weightedAbsoluteSdMean(double[] dadta, double[] weights, double mean) ;
	public static native double weightedSkew(double[] data, double[] weights) ;
	public static native double weightedSkewMeanSd(double[] data, double[] weights, double mean, double sd) ;
	public static native double weightedKurtosis(double[] data, double[] weights) ;
	public static native double weightedKurtosisMeanSd(double[] data, double[] weights, double mean, double sd) ;

	// Maximum and Minimum values

	public static native double max(double[] data) ;
	public static native double min(double[] data) ;
	public static native double[] minmax(double[] data) ;
	public static native long maxIndex(double[] data) ;
	public static native long minIndex(double[] data) ;
	public static native long[] minmaxIndex(double[] data) ;

	// Median and Percentiles

	public static native double medianFromSortedData(double[] sortedData) ;
	public static native double median(double[] data) ;
	public static native double quantileFromSortedData(double[] sortedData, double f) ;

	// Order Statistics

	public static native double select(double[] data, int k) ;

	// Robust Location Estimates

	// Trimmed Mean
	public static native double trmeanFromSortedData(double[] sortedData, double alpha) ;

	// Gastwirth Estimator
	public static native double gastwirthFromSortedData(double[] sortedData) ;

	// Robust Scale Estimates

	// Median Absolute Deviation (MAD)
	public static native double mad0(double[] data, double[] work) ;
	public static native double mad(double[] data, double[] work) ;

	// S_n Statistic
	public static native double sn0FromSortedData(double[] sortedData, double[] work) ;
	public static native double snFromSortedData(double[] sortedData, double[] work) ;

	// Q_n Statistic
	public static native double qn0FromSortedData(double[] sortedData, double[] work, int[] workInt) ;
	public static native double qnFromSortedData(double[] sortedData, double[] work, int[] workInt) ;


}
