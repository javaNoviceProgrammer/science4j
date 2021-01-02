package visualizer4j.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextField;



public class MoreArrays {
	
	public static int max(int[] t) {
		int max = -Integer.MAX_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			if (t[i] > max) max = t[i];
		}
		return max;
	}
	
	public static double max(double[] t) {
		double max = -Double.MAX_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			if (t[i] > max) max = t[i];
		}
		return max;
	}
	
	public static int min(int[] t) {
		if (t.length == 0) {
			return Integer.MIN_VALUE;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			if (t[i] < min) min = t[i];
		}
		return min;
	}
	
	public static double min(double[] t) {
		if (t.length == 0) {
			return Double.MIN_VALUE;
		}		
		double min = Double.MAX_VALUE;
		for (int i = 0 ; i < t.length ; i++) {
			if (t[i] < min) min = t[i];
		}
		return min;
	}	
	
	
	
	public static Pair<Double, Integer> maxAndIndex(double[] d) {
		double max = -Double.MAX_VALUE;
		int index = -1;
		for (int i = 0 ; i < d.length ; i++) {
			if (d[i] > max) {
				max = d[i];
				index = i;
			}
		}
		return new Pair<Double, Integer>(max, index);		
	}
	
	public static Pair<Double, Integer> minAndIndex(double[] d) {
		int index = -1;
		double min = Double.MAX_VALUE;
		for (int i = 0 ; i < d.length ; i++) {
			if (d[i] < min) {
				min = d[i];
				index = i;
			}
		}
		return new Pair<Double, Integer>(min, index);
	}
	
	public static double[] todoubleArray(Double[] doubles) {
		double[] d = new double[doubles.length];
		for (int i = 0 ; i < doubles.length ; i++) {
			d[i] = doubles[i];
		}
		return d;
	}
	
	public static Integer[] toIntegerArray(int[] a) {
		Integer[] target = new Integer[a.length];
		for (int i = 0 ; i < a.length; i++) {
			target[i] = a[i];
		}
		return target;
	}
	
	public static Double[] toDoubleArray(double ... u) {
		Double[] target = new Double[u.length];
		for (int i = 0 ; i < u.length; i++) {
			target[i] = u[i];
		}
		return target;
	}
	
	public static Float[] toFloatArray(float[] a) {
		Float[] target = new Float[a.length];
		for (int i = 0 ; i < a.length; i++) {
			target[i] = a[i];
		}
		return target;
	}	
	
	public static double[] normalize(double[] d) {
		double accumOrig = MoreArrays.sum(d);
		double[] d2 = product(d, 1/accumOrig);
		double accum = MoreArrays.sum(d2);
		while (accum > 1) {
			accumOrig += 0.00000000001;
			d2 = product(d, 1/accumOrig);
			accum = MoreArrays.sum(d2);
		}
		return d2;
	}
	
	public static double[] normalize(double[] d, double targetSum) {
		double accumOrig = MoreArrays.sum(d);
		double[] d2 = product(d, targetSum/accumOrig);
		double accum = MoreArrays.sum(d2);
		while (accum > targetSum) {
			accumOrig += 0.000000000001;
			d2 = product(d, targetSum/accumOrig);
			accum = MoreArrays.sum(d2);
		}
		return d2;
	}	
	
	public static int sum(List<Integer> list) {
		int accum = 0;
		for (int i = 0 ; i < list.size() ; i++) {
			accum += list.get(i);
		}
		return accum;
	}	
	
	public static double sum(double[] t, int startIndex, int length) {
		double sum = 0;
		int minI = Math.min(t.length, startIndex + length);
		for (int i = startIndex ; i < minI ; i++) {
			sum += t[i];
		}
		return sum;			
	}
	
	public static double sum(double[] t, int length) {
		return sum(t, 0, length);		
	}	
	
	public static double sum(double[] t) {
		double sum = 0;
		for (int i = 0 ; i < t.length ; i++) {
			sum += t[i];
		}
		return sum;		
	}
	
	public static float sum(float[] t) {
		float sum = 0;
		for (int i = 0 ; i < t.length ; i++) {
			sum += t[i];
		}
		return sum;		
	}
	
	public static long sum(long[] t) {
		long sum = 0;
		for (int i = 0 ; i < t.length ; i++) {
			sum += t[i];
		}
		return sum;	
	}
	
	public static int sum(int[] t) {
		return sum(t, t.length);
	}
	
	public static int sum(int[] t, int length) {	
		return sum(t, 0, length);
	}
	
	public static int sum(int[] t, int startIndex, int length) {
		int sum = 0;
		int minI = Math.min(t.length, startIndex + length);
		for (int i = startIndex ; i < minI ; i++) {
			sum += t[i];
		}
		return sum;	
	}	
	
	public static int sum_(boolean ... bs) {
		return sum(bs);
	}
	
	public static int sum(boolean[] t) {
		return sum(t, t.length);
	}
	
	public static int sum(boolean[] t, int length) {	
		return sum(t, 0, length);
	}
	
	public static int sum(boolean[] t, int startIndex, int length) {
		int sum = 0;
		int minI = Math.min(t.length, startIndex + length);
		for (int i = startIndex ; i < minI ; i++) {
			sum += t[i] ? 1 : 0;
		}
		return sum;	
	}	
	
	
	
	public static double[] concat(double v1[], double v2[]){
		int len = v1.length + v2.length;
		double[] newv = new double[len];
		for(int i=0; i < len; i++){
			if(i < v1.length){
				newv[i] = v1[i];
			}else{
				newv[i] = v2[i-v1.length];
			}
		}
		return newv;
	}
	
	public static int[] concat(int v1[], int v2[]){
		int len = v1.length + v2.length;
		int[] newv = new int[len];
		for(int i=0; i < len; i++){
			if(i < v1.length){
				newv[i] = v1[i];
			}else{
				newv[i] = v2[i-v1.length];
			}
		}
		return newv;
	}	
	
	public static double[] product(double[] t, double d) {
		double[] n = new double[t.length];
		for (int i = 0 ; i < t.length ; i++) {
			n[i] = d*t[i];
		}
		return n;
	}
	
	public static void productInSameArray(double[] t, double d) {
		for (int i = 0 ; i < t.length ; i++) {
			t[i] = d*t[i];
		}
	}	
	
	public static double[] itemProduct(double[] t, double[] d) {
		double[] n = new double[t.length];
		for (int i = 0 ; i < t.length ; i++) {
			n[i] = d[i]*t[i];
		}
		return n;
	}	
	
	public static double[] itemProduct(double[] t, int[] d) {
		double[] n = new double[t.length];
		for (int i = 0 ; i < t.length ; i++) {
			n[i] = d[i]*t[i];
		}
		return n;
	}	
	
	public static List<Integer> asList(int[] ints) {
		List<Integer> l = new ArrayList<Integer>(ints.length);
		for (int i = 0 ; i < ints.length ; i++) {
			l.add(ints[i]);
		}
		return l;
	}
	
	public static int[] subsetOfN(int start, int end) {
		int[] ints = new int[end-start+1];
		for (int i = 0 ; i < ints.length ; i++) {
			ints[i] = start + i;
		}
		return ints;
	}

	public static int[] range(int start, int end) {
		return range(start, end, Integer.MAX_VALUE);
	}
	
	public static int[] range(int start, int end, int mod) {
		if (end < start) return new int[0];
		int[] r = new int[end - start + 1];
		for (int i = 0 ; i < r.length ; i++) {
			r[i] = (start + i) % mod;
		}
		return r;
	}
	
	
	
	

	public static int product(ArrayList<Integer> clientsPerLevel) {
		int prod = 1;
		for (int i = 0 ; i < clientsPerLevel.size() ; i++) {
			prod *= clientsPerLevel.get(i);
		}
		return prod;
	}

	public static boolean hasThisValue(int[] radixes, int v) {
		for (int i = 0 ; i < radixes.length ; i++) {
			if (radixes[i] == v) return true;
		}
		return false;
	}

	public static int[][] arrayCopy(int[][] repartitions) {
		int[][] copy = new int[repartitions.length][];
		for (int i = 0 ; i < repartitions.length ; i++) {
			if (repartitions[i] != null) {
				copy[i] = new int[repartitions[i].length];
				System.arraycopy(repartitions[i], 0, copy[i], 0, copy[i].length);
			}
		}
		return copy;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] reorganize(T[] objects, Class<T> c) {
		T[] newTab;
		if (objects == null) {
			newTab = (T[])Array.newInstance(c, 0);
		} else {
			int count = 0;
			for (T a : objects) {
				if (a != null) 
					count++;
			}
			newTab = (T[])Array.newInstance(c, count);
			count = 0;
			for (T a : objects) {
				if (a != null) {
					newTab[count] = a;
					count++;
				}
			}		
		}
		return newTab;
	}

	public static double scalarProduct(double[] from0, int[] is) {
		if (from0.length != is.length) throw new IllegalStateException("Array sizes must match");
		double accum = 0;
		for (int i = 0 ; i < from0.length ; i++) {
			accum += from0[i]*is[i];
		}
		return accum;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(exclude(new int[]{1, 2, 3 ,4 ,5,6,7,8}, 4)));
	}

	public static int[] exclude(int[] dimensions, int exception) {
		int[] newT = new int[dimensions.length-1];
		int index = 0;
		for (int i = 0 ; i < dimensions.length ; i++) {
			if (i == exception) continue;
			newT[index] = dimensions[i];
			index++;
		}
		return newT;
	}

	public static int[] getDecreasinglySortedCopy(int[] dimensions) {
		int len = dimensions.length;
		int[] dim = new int[len];
		System.arraycopy(dimensions, 0, dim, 0, len);
		Arrays.sort(dim);
		int[] toRet = new int[len];
		for (int i = 0 ; i < len ; i++) {
			toRet[i] = dim[len-1-i];
		}
		return toRet;
	}

	public static int[] getIncreasinglySortedCopy(int[] dimensions) {
		int[] dim = new int[dimensions.length];
		System.arraycopy(dimensions, 0, dim, 0, dimensions.length);
		Arrays.sort(dim);	
		return dim;
	}

	public static int[] arrayCopy(int[] sizes) {
		int l = sizes.length;
		int[] cop = new int[l];
		System.arraycopy(sizes, 0, cop, 0, l);
		return cop;
	}



	@SuppressWarnings("all")
	public static <T> ArrayList<T> getArrayList(T... t ) {
		int l = t.length;
		ArrayList<T> al = new ArrayList<T>(l);
		for (int i = 0 ; i < l ; i++) {
			al.add(t[i]);
		}
		return al;
	}

	public static int[] toIntArray(List<Integer> ff) {
		int[] a = new int[ff.size()];
		int index = 0;
		for (Integer g : ff) {
			a[index] = g;
			index++;
		}
		return a;
	}

	public static int[] toIntArray(Integer[] ff) {
		int[] a = new int[ff.length];
		int index = 0;
		for (Integer g : ff) {
			a[index] = g;
			index++;
		}
		return a;
	}

	public static double mean(int[] factors) {
		int accum = 0 ;
		for (int i = 0 ; i < factors.length ; i++) {
			accum += factors[i];
		}
		return (double)accum/factors.length;
	}

	/**
	 * This method looks for the INDEX of number in values that has the smallest difference
	 * with val
	 * @param val
	 * @param values
	 * @return
	 */
	public static int findClosest(double val, int[] values) {
		int bestIndexSoFar = -1;
		double bestDistSoFar = Double.POSITIVE_INFINITY;
		for (int i = 0 ; i < values.length ; i++) {
			double d = Math.abs(val - values[i]);
			if (d < bestDistSoFar) {
				bestDistSoFar = d;
				bestIndexSoFar = i;
			}
		}
		return bestIndexSoFar;
	}

	public static int findSmallerLargerThan(double val, int[] values) {
		int bestIndexSoFar = -1;
		double bestDistSoFar = Double.POSITIVE_INFINITY;
		for (int i = 0 ; i < values.length ; i++) {
			double d = values[i] - val;
			if (d < 0) continue;
			if (d < bestDistSoFar) {
				bestDistSoFar = d;
				bestIndexSoFar = i;
			}
		}
		return bestIndexSoFar;
	}
	/**
	 * This is a special method that simply checkts if number is very close to an integer,
	 * and if yes, rounds it. 
	 * @param trafFrom
	 */
	public static void cleanUncert(double[] trafFrom) {
		for (int i = 0 ; i < trafFrom.length ; i++) {
			double diff = trafFrom[i] - Math.round(trafFrom[i]);
			if (diff < 0.0000000000095) {
				trafFrom[i] = Math.round(trafFrom[i]);
			}
		}
		// TODO Auto-generated method stub
		
	}

	public static int lastIndexOf(Object[] allFields, Object f) {
		for (int i = 0 ; i < allFields.length ; i++) {
			if (f == allFields[i]) {
				return i;
			}
		}
		return -1;
	}

	public static boolean contains(int[] tab, int index) {
		for (int i = 0 ; i < tab.length ; i++) {
			if (tab[i] == index) return true;
		}
		return false;
	}

	public static int countsOfValue(int[] vals, int trigger) {
		int count = 0;
		int le = vals.length;
		for (int i = 0 ; i < le ; i++) {
			if (vals[i] == trigger) count++;
		}
		return count;
	}










}
