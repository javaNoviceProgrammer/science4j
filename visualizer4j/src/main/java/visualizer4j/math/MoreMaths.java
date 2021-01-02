package visualizer4j.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;


public class MoreMaths {
	
	private static final double LOG10_2 = Math.log10(2);
	
	/**
	 * This methods returns a list of factors such that
	 * 1) the product of the factor is greater or equal to total
	 * 2) the difference between factors is at most 1
	 * @param total
	 * @param nbFactors
	 * @return
	 */
	public static int[] factorize(int total, int nbFactors) {
		int max = (int)Math.ceil(Math.pow(total, 1/(double)nbFactors));
		int[] factors = new int[nbFactors];
		Arrays.fill(factors, max);
		for (int i = 0 ; i < factors.length ; i++) {
			factors[i]--;
			if (MoreMaths.product(factors) < total) {
				factors[i]++;
				return factors;
			}
		}
		return factors;		
	}
	
	public static int[] factorize(int total, int nbFactors,
			ArrayList<Integer> imposedFactors) {
		int totBis = total;
		int[] facs = new int[nbFactors];
		int index = 0;
		for (int f : imposedFactors) {
			totBis = totBis/f;
			facs[index] = f;
			index++;
		}
		int[] altFactors = factorize(totBis, nbFactors - index);
		for (int i = 0 ; i < altFactors.length ; i++) {
			facs[index+i] = altFactors[i];
		}
		return facs;
	}	
	
	public static double log2(int x){
		return Math.log10(x)/ LOG10_2;
	}
	
	
	public static double log2(double x){
		return Math.log10(x)/ LOG10_2;
	}
	
	public static double logN(double x, double N) {
		return Math.log10(x)/Math.log10(N);
	}
	
	public static long comb(int n, int r) {
        long t = 1;
        
        int m = n - r; // r = Math.max(r, n - r);
        if (r < m) {
            r = m;
        }
        
        for (int i = n, j = 1; i > r; i--, j++) {
            t = t * i / j;
        }
        
        return t;		
	}
	
	public static long factorial(int n) {
		if (n == 0) return 1;
		if (n > 20)
			throw new IllegalArgumentException("Cannot compute for n greater than 20. Use with double for more");
		if(n <= 1)
			return 1;
		else
			return n * factorial(n - 1);
	}
	
	public static double factorial(double d) {
		if (d - Math.ceil(d) > 0)
			throw new IllegalArgumentException("Factorial must be applied on integer numbers");
		if (d == 0) return 1;
		if (d > 170)
			throw new IllegalArgumentException("Cannot compute for n greater than 170. Use with double for more");  
		 if(d <= 1)
			return 1d;
		else
			return d * factorial(d - 1);   	
	}
	
	public static void arrayDivide(double[] t, double fac) {
		for (int i = 0 ; i < t.length ; i++) {
			t[i] = t[i]/fac;
		}
	}
	
	public static double[] arrayDivide(int[] t, double fac) {
		double[] arr = new double[t.length];
		for (int i = 0 ; i < t.length ; i++) {
			arr[i] = (double)t[i]/fac;
		}
		return arr;
	}
	
	public static double[] arrayPow(double element, double[] powers) {
		for (int i = 0 ; i < powers.length ; i++) {
			powers[i] = Math.pow(element, powers[i]);
		}	
		return powers;	
	}
	
	public static double[] arrayPow(double[] elements, double pow) {
		for (int i = 0 ; i < elements.length ; i++) {
			elements[i] = Math.pow(elements[i], pow);
		}
		return elements;		
	}
	
	public static BigInteger bigIntegerPow(int i, int j) {
		BigInteger n = new BigInteger(""+i);
		return n.pow(j);
	}
	
	public static BigInteger bigIntegerTimes(BigInteger b, int t) {
		BigInteger tt = new BigInteger(""+t);
		return b.multiply(tt);
	}
	
	public static BigDecimal bigIntegerTimes(BigInteger b, double t) {
		BigDecimal tt = new BigDecimal(""+t);
		return new BigDecimal(b).multiply(tt);
	}	
	
	public static BigInteger bigIntegerPlus(BigInteger b, int op) {
		BigInteger o = new BigInteger(op+"");
		return b.add(o);
	}
	
	public static double bigIntegerDivideDouble(BigInteger b1, BigInteger b2) {
		BigDecimal bc1 = new BigDecimal(b1);
		BigDecimal bc2 = new BigDecimal(b2);
		try {
			return bc1.divide(bc2).doubleValue();
		}
		catch (ArithmeticException e) {
			return bc1.doubleValue()/bc2.doubleValue();
		}
	}
	
	public static double[] matricialProduct(double[] a, double[][] b) {
		double[][] c = new double[][]{a};
		return matricialProduct(c, b)[0];
	}
	
	/**
	 * First dimension represents the lines, second dim the columns
	 * a11 a12 a13
	 * a21 a22 a23
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[][] matricialProduct(double[][] a, double[][] b) {
		// say a is MxK and b is KxL
		int M = a.length;
		int L = b[0].length;
		// basic check
		if (a[0].length != b.length) throw new IllegalStateException("matrix mismatch");
		int K = a[0].length;
		
		
		double[][] res = new double[M][L];
		
		for (int i = 0 ; i < M ; i++) {
			for (int j = 0 ; j < L ; j++) {
				for (int k = 0 ; k < K ; k++) {
					res[i][j] += a[i][k]*b[k][j];
				}		
			}
 		}
		return res;
	}
	
	public static double[][] transpose(double[] d) {
		return transpose(new double[][]{d});
	}
	
	public static double[][] transpose(double[][] d) {
		double[][] res = new double[d[0].length][d.length];
		for (int i = 0 ; i < d.length ; i++) {
			for (int j = 0 ; j < d[0].length ; j++) {
				res[j][i] = d[i][j];
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		/*for (int i = 1 ; i < 700 ; i++) {
			double l = factorial((double)i);
			if ((Double.MAX_VALUE / l) < i+1) {
				System.out.println("limit " + i);
				System.out.println(Double.MAX_VALUE);
				System.out.println(factorial((double)i));
				return;
			}
		}*/
		System.out.println(Arrays.toString(factorize(10009, 5)));
	}
	
	public static int ceilDiv(double numerator, double denominator) {
		return (int)Math.ceil(numerator/denominator);
	}
	
	public static int ceilDiv(int numerator, double denominator) {
		return (int)Math.ceil((double)numerator/denominator);
	}
	
	public static int ceilDiv(double numerator, int denominator) {
		return (int)Math.ceil(numerator/(double)denominator);
	}	

	public static int ceilDiv(int numerator, int denominator) {
		return ceilDiv(numerator, (double)denominator);
	}

	public static long product(int[] array) {
		long h = 1;
		for (int i = 0 ; i < array.length ; i++) {
			h *= (long)array[i];
		}
		return h;
	}


	public static long product(int[] array, int exception) {
		long h = 1;
		for (int i = 0 ; i < array.length ; i++) {
			if (i == exception) continue;
			h *= (long)array[i];
		}
		return h;
	}

	public static int reverse(int src, int clients) {
		int bits = (int)Math.ceil(MoreMaths.log2(clients));
		int mask = 1;
		int mask2 = (int)Math.pow(2, bits-1);
		int result = 0;
		for (int i = 0 ; i < bits ; i++) {
			if ((src & mask) > 0)
				result += mask2;
			mask2 /=2;
			mask *= 2;
		}
		return result;
	}

	public static int[] getBits(int src, int bits) {
		int[] bitA = new int[bits];
		int mask = 1;
		for (int i = 0; i < bits ; i++) {
			bitA[i] = ((src & mask) > 0) ? 1 : 0;
			mask <<= 1;
		}
		return bitA;
	}

	public static int bitsToInt(int[] res) {
		int mask = 1;
		int result = 0;
		for (int i = 0 ; i < res.length ; i++) {
			if (res[i] > 0) {
				result += mask;
			}
			mask <<= 1;
		}
		return result;
	}

	public static int[] divide(int total, int bin) {
		int base = total/bin;
		int[] ret = new int[bin];
		int current = base*bin;
		Arrays.fill(ret, base);
		int index = 0;
		for (int i = 0 ; i < total-current ; i++) {
			ret[index]++;
			index = (index + 1) % bin;
		}
		return ret;
	}

	public static float max(Vector<Float> values) {
		float f = Float.MIN_VALUE;
		for (Float cand : values) {
			if (cand > f) {
				f = cand;
			}
		}
		return f;
	}
	
	public static float min(Vector<Float> values) {
		float f = Float.MAX_VALUE;
		for (Float cand : values) {
			if (cand < f) {
				f = cand;
			}
		}
		return f;	
	}

	public static double coth(double cothArg) {
		return (Math.exp(cothArg) + Math.exp(-cothArg)) / (Math.exp(cothArg) - Math.exp(-cothArg));
	}

	public static int[] factorize(int nodesAtFirstLevel) {
		int[] resp = new int[]{nodesAtFirstLevel};
		boolean cont = true;
		int factors = 2;
		while (cont) {
			int[] r = factorize(nodesAtFirstLevel, factors);
			cont = product(r) == nodesAtFirstLevel;
			if (cont) resp = r;
			factors++;
		}
		return resp;
	}

	public static int[] coordinates(int id, int[] space) {
		int[] coord = new int[space.length];
		for (int i = 0 ; i < space.length ; i++) {
			coord[i] = id % space[i];
			id = id  / space[i];
		}
		return coord;
	}



}
