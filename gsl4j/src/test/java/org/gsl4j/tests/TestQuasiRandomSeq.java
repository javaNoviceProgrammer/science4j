package org.gsl4j.tests;

import java.util.Arrays;
import org.gsl4j.sequence.QuasiRandomSequence;
import org.gsl4j.util.Timer;

public class TestQuasiRandomSeq {

	public static void test1() {
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		double[] rand ;
		for(int i=0; i<10; i++) {
			rand = seq.niederreiter2(i) ;
			System.out.println(Arrays.toString(rand));
		}
	}

	public static void test2() {
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		double[] rand ;
		for(int i=0; i<10; i++) {
			rand = seq.sobol(i) ;
			System.out.println(Arrays.toString(rand));
		}
	}

	public static void test3() {
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		double[] rand ;
		for(int i=0; i<10; i++) {
			rand = seq.halton(i) ;
			System.out.println(Arrays.toString(rand));
		}
	}

	public static void test4() {
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		double[] rand ;
		for(int i=0; i<10; i++) {
			rand = seq.reverseHalton(i) ;
			System.out.println(Arrays.toString(rand));
		}
	}

	public static void test5() {
		Timer timer = new Timer() ;
		timer.start();
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		for(int i=0; i<1000; i++) {
			seq.reverseHalton(i) ;
		}
		timer.stop();
		timer.show();
	}

	public static void test6() {
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		int size = 10_000 ;
		double[][] rand = seq.niederreiter2All(size) ;
		for(int i=0; i<size; i++) {
			System.out.println(Arrays.toString(rand[i]));
		}
	}

	public static void test7() {
		QuasiRandomSequence seq = new QuasiRandomSequence(2) ;
		int size = 1000_000 ;
		Timer timer = new Timer() ;
		timer.start();
		double[][] rand = seq.niederreiter2All(size) ;
		timer.stop();
		timer.show();
		System.out.println(rand.length);
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test6() ;
		test7() ;
	}

}
