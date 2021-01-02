package visualizer4j.math;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;

public class PrimeNumbers {
	
	private static boolean[] primes = new boolean[10000];
	private static boolean[] primePower = new boolean[228];
	
	static {
		Arrays.fill(primes,true);        // assume all integers are prime.
	    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
	    for (int i=2;i<primes.length;i++) {
	        //if the number is prime, 
	        //then go through all its multiples and make their values false.
	        if(primes[i]) {
	            for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	            }
	        }
	    }
	    
	    int[] trues = new int[]{1, 2, 3, 4, 5, 7, 8, 9, 11, 13, 16, 17, 19, 23, 25, 27, 29, 31, 32, 37, 41, 43, 47, 49, 53, 59, 61, 64, 67, 71, 73, 79, 81, 83, 89, 97, 101, 103, 107, 109, 113, 121, 125, 127, 128, 131, 137, 139, 149, 151, 157, 163, 167, 169, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227};
	    Arrays.fill(primePower,false); 
	    for (int i : trues) {
	    	primePower[i] = true;
	    }
	}
	
	public static boolean isPrime(int n) {
		if (n <= 10000)
			return primes[n];
		throw new IllegalStateException("PrimeNumbers goes up to 10000");
	}
	
	public static boolean isPrimePower(int n) {
		if (n > 227)
			throw new IllegalStateException("PrimeNumbers goes up to 10000");
		return primePower[n];
	}
	
	private static int[] generators = new int[]{-1, 1,1,2,3, // 0 - 4
												2,5,3,-1,2, // 5 - 9
												3,2,-1,2,3, // 10 - 14
												-1,-1,3,5,2, // 15- 19
												-1,-1,7,5,-1, // 20 - 24
												2,7,2,-1,2, // 25 - 29
												-1, 3, -1, -1, 3, // 30 - 34
												-1, -1, 2,3,-1 // 35 - 39
												-1, 6, -1, 3, -1, // 40 - 44
												-1, 5, 5, -1, 3, // 45 - 49
												3, -1, -1, 2, 5, // 50 - 54
												-1, -1, -1, 3, 2, // 55 - 59
												-1, 2, 3, -1, -1}; // 60 - 64
												
	
	public static int findGenerator(int max) {
		if (max < generators.length)
			return generators[max];
		
		int genTest = 2;
		BigInteger maxx = new BigInteger(max+"");
		while (genTest < max) {
			BigInteger prim = new BigInteger(genTest+"");
			HashSet<Integer> generated = new HashSet<Integer>();
			generated.add(genTest % max);
			BigInteger actual = new BigInteger(genTest+"");
			int ite = 5000;
			while(true) {
				actual = actual.multiply(prim);
				if (ite == 0) break;
				ite--;
				int mod = actual.mod(maxx).intValue();
			//	int mod = (int)(actual % max);
				if (!generated.contains(mod)) {
					generated.add(mod);
				}
			}
			if (generated.size() == max-1) return genTest;
			genTest++;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		for (int i = 0 ; i < primes.length ; i++) {
			if (primes[i]) {
				System.out.println("Prime " + i + " - generator " + findGenerator(i));
			}
		}
	}

}
