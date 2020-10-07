package org.gsl4j.tests;

import org.gsl4j.special.Transport;

class TestTransport {

	public static void test1() {
		double x = 1.1 ;
		double y = Transport.transport2(x) ;
		System.out.println(y);
	}

	public static void test2() {
		double x = 1.1 ;
		double y = Transport.transport3(x) ;
		System.out.println(y);
	}

	public static void test3() {
		double x = 1.1 ;
		double y = Transport.transport4(x) ;
		System.out.println(y);
	}

	public static void test4() {
		double x = 1.1 ;
		double y = Transport.transport5(x) ;
		System.out.println(y);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
	}

}
