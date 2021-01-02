package visualizer4j.math;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import umontreal.iro.lecuyer.probdist.StudentDist;

public class StatFunctions {
	
	public static double getMean(int[] d) {
		double total = 0;
		for (int i = 0 ; i < d.length ; i++) {
			total += d[i];
		}
		return (total / (double)d.length);
	}	
	
	public static double getMean(double[] d) {
		double total = 0;
		for (int i = 0 ; i < d.length ; i++) {
			total += d[i];
		}
		return (total / (double)d.length);
	}

	public static float getMean(float[] f) {
		float total = 0;
		for (int i = 0 ; i < f.length ; i++) {
			total += f[i];
		}
		return (total / (float)f.length);
	}

	public static float getMeanF(Collection<Float> d) {
		float total = 0;
		for (float de : d) {
			total += de;
		}
		return (total / (float)d.size());
	}
	
	public static double getMean(Double[][] d) {
		double total = 0;
		int nb = 0;
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d.length ; j++) {
				if (d[i][j] != null) {
					total += d[i][j];
					nb++;
				}
			}
		}
		return (total / (double)nb);		
	}
	
	
	
	public static double getMean(double[][] d) {
		double total = 0;
		int nb = 0;
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d.length ; j++) {
				total += d[i][j];
				nb++;
			}
		}
		return (total / (double)nb);		
	}	

	public static double getMeanD(Collection<Double> d) {
		double total = 0;
		for (double de : d) {
			total += de;
		}
		return (total / (double)d.size());
	}

	public static float getMedian(float[] f) {
		Arrays.sort(f);
		if (f.length > 0) {
			return f[f.length/2];
		}
		return -1f;
	}

	public static double getMedianD(Collection<Double> d) {
		Double[] ddd = d.toArray(new Double[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return ddd[ddd.length/2];
		}
		return -1f;
	}

	public static float getMedianF(Collection<Float> d) {
		Float[] ddd = d.toArray(new Float[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return ddd[ddd.length/2];
		}
		return -1f;
	}

	public static float getMin(float[] f) {
		float aMin = Float.MAX_VALUE;
		for (int i = 0 ; i < f.length ; i++) {
			if (f[i] < aMin) {
				aMin = f[i];
			}
		}
		return aMin;
	}

	public static double getMin(Collection<Double> d) {
		return Collections.min(d);
	}

	public static float getMax(float[] f) {
		float aMax = Float.MIN_VALUE;
		for (int i = 0 ; i < f.length ; i++) {
			if (f[i] > aMax) {
				aMax = f[i];
			}
		}
		return aMax;
	}

	public static double getMax(Collection<Double> d) {
		return Collections.max(d);
	}

	public static float get1Quantile(float[] f) {
		Arrays.sort(f);
		if (f.length > 0) {
			return f[f.length/4];
		}
		return -1f;
	}

	public static double get1Quantile(Collection<Double> d) {
		Double[] ddd = d.toArray(new Double[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return ddd[ddd.length/4];
		}
		return -1f;
	}

	public static float get3Quantile(float[] f) {
		Arrays.sort(f);
		if (f.length > 0) {
			return f[f.length/4];
		}
		return -1f;
	}

	public static double get3Quantile(Collection<Double> d) {
		Double[] ddd = d.toArray(new Double[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return ddd[3*ddd.length/4];
		}
		return -1f;
	}

	public static float getVariance(float[] f) {
		float mean = getMean(f);
		float total = 0;
		for (int i = 0 ; i < f.length ; i++) {
			total += Math.pow(f[i] - mean, 2);
		}
		return total / (f.length - 1);
	}

	public static double getVarianceD(Collection<Double> d) {
		return getVarianceInternalD(d, getMeanD(d));
	}

	public static float getVarianceF(Collection<Float> f) {
		return getVarianceInternalF(f, getMeanF(f));
	}

	public static float getStandardDeviation(float[] f) {
		return (float)Math.pow(getVariance(f), 0.5);
	}

	public static double getStandardDeviation(Collection<Double> d) {
		return Math.pow(getVarianceD(d), 0.5);
	}

	public static float[] getConfidenceInterval(float[] f, float confidenceLevel) {
		double studT = -StudentDist.inverseF(f.length-1, (1-confidenceLevel)/2);
		double term1 = Math.pow((getVariance(f)/f.length), 0.5);
		float mean = getMean(f);
		return new float[]{(float)(mean - (studT*term1)), (float)(mean + (studT*term1))};
	}

	public static double[] getConfidenceInterval(Collection<Double> d, double confidenceLevel) {
		double mean = getMeanD(d);
		return getConfidenceIntervalInternal(d, mean, getVarianceInternalD(d, mean), confidenceLevel);
	}
	
	public static double getNormalisedConfidenceInterval(Collection<Double> d, double confidenceLevel) {
		double mean = getMeanD(d);
		return mean - getConfidenceIntervalInternal(d, mean, getVarianceInternalD(d, mean), confidenceLevel)[0];
	}	

	public static float[] getConfidenceInterval(Collection<Float> f, float confidenceLevel) {
		float mean = getMeanF(f);
		return getConfidenceIntervalInternal(f, mean, getVarianceInternalF(f, mean), confidenceLevel);
	}

	public static float getRelativeConfidenceInterval(Collection<Float> f, float confidenceLevel) {
		if (f.size() < 2) {
			return Float.POSITIVE_INFINITY;
		}
		double studT = -StudentDist.inverseF(f.size()-1, (1-confidenceLevel)/2);
		double term1 = Math.pow((getVarianceF(f)/f.size()), 0.5);
		double mean = getMeanF(f);
		if (mean < 10*Float.MIN_VALUE) {
			return 0;
		}
		return (float)(mean + (studT*term1))/(float)mean;
	}

	public static double[] getConfidenceIntervalAndMean(Collection<Double> d, float confidenceLevel) {
		double studT = -StudentDist.inverseF(d.size()-1, (1-confidenceLevel)/2);
		double term1 = Math.pow((getVarianceD(d)/d.size()), 0.5);
		double mean = getMeanD(d);
		return new double[]{(mean - (studT*term1)),mean, (mean + (studT*term1))};
	}

	public static float[] getAll(Collection<Float> f, float confidence) {
		float mean = getMeanF(f);
		float var = getVarianceInternalF(f, mean);
		float[] confInter;
		if (f.size() > 1) {
			confInter = getConfidenceIntervalInternal(f, mean, var, confidence);
		} else {
			float dou = f.iterator().next();
			confInter = new float[]{dou, dou};
		}
		float[] quartiles = getQuartilesInternalF(f);
		float[] dcop = new float[]{mean, getMedianF(f), confInter[0], confInter[1],
				quartiles[0], quartiles[3], quartiles[1],
				quartiles[2]};
		//	 System.out.println(java.util.Arrays.toString(dcop));
		return dcop;
	}
	
	public static float[] getAll95(Collection<Float> f, float confidence) {
		float mean = getMeanF(f);
		float var = getVarianceInternalF(f, mean);
		float[] confInter;
		if (f.size() > 1) {
			confInter = getConfidenceIntervalInternal(f, mean, var, confidence);
		} else {
			float dou = f.iterator().next();
			confInter = new float[]{dou, dou};
		}
		float[] quartiles = get95InternalF(f);
		float[] dcop = new float[]{mean, getMedianF(f), confInter[0], confInter[1],
				quartiles[0], quartiles[3], quartiles[1],
				quartiles[2]};
		//	 System.out.println(java.util.Arrays.toString(dcop));
		return dcop;
	}	

	public static double[] getAll(Collection<Double> d, double confidence) {
		double mean = getMeanD(d);
		double var = getVarianceInternalD(d, mean);
		double[] confInter;
		if (d.size() > 1) {
			confInter = getConfidenceIntervalInternal(d, mean, var, confidence);
		} else {
			double dou = d.iterator().next();
			confInter = new double[]{dou, dou};
		}
		double[] quartiles = getQuartilesInternalD(d);
		double[] dcop = new double[]{mean, getMedianD(d), confInter[0], confInter[1],
				quartiles[0], quartiles[3], quartiles[1],
				quartiles[2]};
		//	 System.out.println(java.util.Arrays.toString(dcop));
		return dcop;
	}
	
	public static double[] getAll95(Collection<Double> d, double confidence) {
		double mean = getMeanD(d);
		double var = getVarianceInternalD(d, mean);
		double[] confInter;
		if (d.size() > 1) {
			confInter = getConfidenceIntervalInternal(d, mean, var, confidence);
		} else {
			double dou = d.iterator().next();
			confInter = new double[]{dou, dou};
		}
		double[] quartiles = get95InternalD(d);
		double[] dcop = new double[]{mean, getMedianD(d), confInter[0], confInter[1],
				quartiles[0], quartiles[3], quartiles[1],
				quartiles[2]};
		//	 System.out.println(java.util.Arrays.toString(dcop));
		return dcop;
	}	



	private static double[] getConfidenceIntervalInternal(Collection<Double> d,
			double mean, double var, double confidenceLevel) {
		double studT = -StudentDist.inverseF(d.size()-1, (1-confidenceLevel)/2);
		double term1 = Math.pow((var/d.size()), 0.5);
		return new double[]{(mean - (studT*term1)), (mean + (studT*term1))};
	}

	private static float[] getConfidenceIntervalInternal(Collection<Float> d,
			float mean, float var, float confidenceLevel) {
		float studT = (float)-StudentDist.inverseF(d.size()-1, (1-confidenceLevel)/2);
		float term1 = (float)Math.pow((var/d.size()), 0.5);
		return new float[]{(mean - (studT*term1)), (mean + (studT*term1))};
	}

	private static double getVarianceInternalD(Collection<Double> d, double mean) {
		double total = 0;
		for (double de : d) {
			total += Math.pow(de - mean, 2);
		}
		return total / (d.size()-1);
	}

	private static float getVarianceInternalF(Collection<Float> d, double mean) {
		float total = 0;
		for (float de : d) {
			total += Math.pow(de - mean, 2);
		}
		return total / (d.size()-1);
	}

	private static double[] getQuartilesInternalD(Collection<Double> d) {
		Double[] ddd = d.toArray(new Double[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return new double[]{ddd[0], ddd[ddd.length/4], ddd[ddd.length*3/4], ddd[ddd.length-1]};
		}
		return new double[]{-1,-1,-1,-1};
	}
	
	private static double[] get95InternalD(Collection<Double> d) {
		Double[] ddd = d.toArray(new Double[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return new double[]{ddd[0], ddd[ddd.length/20], ddd[ddd.length*19/20], ddd[ddd.length-1]};
		}
		return new double[]{-1,-1,-1,-1};
	}	

	private static float[] getQuartilesInternalF(Collection<Float> d) {
		Float[] ddd = d.toArray(new Float[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return new float[]{ddd[0], ddd[ddd.length/4], ddd[ddd.length*3/4], ddd[ddd.length-1]};
		}
		return new float[]{-1,-1,-1,-1};
	}
	
	private static float[] get95InternalF(Collection<Float> d) {
		Float[] ddd = d.toArray(new Float[d.size()]);
		Arrays.sort(ddd);
		if (ddd.length > 0) {
			return new float[]{ddd[0], ddd[ddd.length/20], ddd[ddd.length*19/20], ddd[ddd.length-1]};
		}
		return new float[]{-1,-1,-1,-1};
	}	

//	@Test
//	public static void testStats(String args[]) {
//		int numberOfSamples = (int)(Math.random() * 100);
//		float[] tab = new float[numberOfSamples];
//		for (int i = 0 ; i < tab.length ; i++) {
//			tab[i] = (int)(Math.random() * 100);
//		}
//		System.out.println("Number of samples : " + tab.length);
//		System.out.println("Mean " + getMean(tab));
//		System.out.println("Var " + getVariance(tab));
//		float[] confi = getConfidenceInterval(tab, 0.95f);
//		System.out.println("95% CI [" + confi[0] + " ; " + confi[1] + "]");
//	}





}
