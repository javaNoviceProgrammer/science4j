package org.gsl4j.tests;

import org.gsl4j.special.Synchrotron;

class TestSynchrotron {

	public static void test1() {
		double x = 1.1 ;
		double y = Synchrotron.synchrotron1(x) ;
		System.out.println(y);
	}

	public static void test2() {
		double x = 1.1 ;
		double y = Synchrotron.synchrotron2(x) ;
		System.out.println(y);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
