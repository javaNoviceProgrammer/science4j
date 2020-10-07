package org.gsl4j.tests;

import org.gsl4j.statistics.Stats;

class TestStats {

	public static void test1() {
		double[] data = {1.0, 2.0, -1.0} ;
		double mean = Stats.mean(data) ;
		System.out.println("mean = " + mean);
	}

	public static void test2() {
		double[] data = {1.0, 1.0, 1.0} ;
		double mean = Stats.variance(data) ;
		System.out.println("variance = " + mean);
	}

	public static void test3() {
		double[] data1 = {1.0, -1.0, 1.0} ;
		double[] data2 = {-1.0, 1.0, 2.0, 4.0} ;
		double covar = Stats.covariance(data1, data2) ;
		System.out.println("covariance = " + covar);
	}

	public static void test4() {
		double[] data    = {1.0, 2.0, 1.0} ;
		double[] weights = {1.0, 2.0, 1.0} ;
		double mean = Stats.weightedMean(data, weights) ;
		System.out.println("weighted mean = " + mean);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
	}

}
