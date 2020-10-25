package org.simd4j;

import java.util.Arrays;

public class Test1 {

	public static void main(String[] args) {
		int[] v1 = {1, 2, 3, 4} ;
		int[] v2 = {-7, -10, 5, 11} ;
		int[] sum = new int[v1.length] ;
		ArrayMath.add(v1, v2, sum);
		System.out.println(Arrays.toString(sum));

//		OSUtils.printInfo();
	}

}
