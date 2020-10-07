package org.gsl4j.util;

import java.util.ArrayList;

public class MathUtils {

	private MathUtils() {

	}

	public static int[] range(int start, int end) {
		int len = end-start+1 ;
		int[] values = new int[len] ;
		for(int i=0; i<len; i++)
			values[i] = start+i ;
		return values ;
	}

	public static int[] rangeExcludeEnd(int start, int end) {
		int len = end-start+1 ;
		int[] values = new int[len] ;
		for(int i=0; i<len-1; i++)
			values[i] = start+i ;
		return values ;
	}

	public static int[] rangeExcludeBeginning(int start, int end) {
		int len = end-start+1 ;
		int[] values = new int[len] ;
		for(int i=0; i<len-1; i++)
			values[i] = start+1+i ;
		return values ;
	}

	public static long[] range(long start, long end) {
		long len = end-start+1 ;
		long[] values = new long[(int)len] ;
		for(int i=0; i<len; i++)
			values[i] = start+i ;
		return values ;
	}

	public static long[] rangeExcludeEnd(long start, long end) {
		long len = end-start+1 ;
		long[] values = new long[(int)len] ;
		for(int i=0; i<len-1; i++)
			values[i] = start+i ;
		return values ;
	}

	public static long[] rangeExcludeBeginning(long start, long end) {
		long len = end-start+1 ;
		long[] values = new long[(int)len] ;
		for(int i=0; i<len-1; i++)
			values[i] = start+1+i ;
		return values ;
	}

	public static double[] linspace(double start, double end, int numPoints) {
		double[] Values = new double[numPoints];
		double Delta;
		if (numPoints == 1) {
			Delta = 0;
		} else {
			Delta = (end - start) / (numPoints - 1);
		}
		for (int i = 0; i < numPoints; i++) {
			Values[i] = start + i * Delta;
		}
		return Values;
	}

	public static double[] linspace(double start, double end, double stepSize) {
		ArrayList<Double> values = new ArrayList<Double>();
		for (double x = start; x <= end; x += stepSize) {
			values.add(x);
		}
		double[] Values = new double[values.size()];
		for (int i = 0; i < values.size(); i++) {
			Values[i] = values.get(i);
		}
		return Values;
	}

	public static double deltaKronecker(int i, int j) {
		if (i == j)
			return 1;
		else
			return 0;
	}

	public static double deltaKronecker(int i, int j, int k) {
		if (i == j && i == k)
			return 1;
		else
			return 0;
	}

	public static double deltaKronecker(int i, int j, int k, int l) {
		if (i == j && i == k && i == l)
			return 1;
		else
			return 0;
	}


	public static String formatNumber(double number, int decimal) {
		String st = number + "" ;
		switch (decimal) {
		case 1:
			st = String.format("%.1f", number) ;
			break;
		case 2:
			st = String.format("%.2f", number) ;
			break;
		case 3:
			st = String.format("%.3f", number) ;
			break;
		case 4:
			st = String.format("%.4f", number) ;
			break;
		case 5:
			st = String.format("%.5f", number) ;
			break;
		case 6:
			st = String.format("%.6f", number) ;
			break;
		case 7:
			st = String.format("%.7f", number) ;
			break;
		case 8:
			st = String.format("%.8f", number) ;
			break;
		case 9:
			st = String.format("%.9f", number) ;
			break;
		case 10:
			st = String.format("%.10f", number) ;
			break;
		default:
			st = String.format("%.5f", number) ;
			break;
		}
		return st ;
	}

}
