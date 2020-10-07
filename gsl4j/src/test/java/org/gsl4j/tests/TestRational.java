package org.gsl4j.tests;

import static org.gsl4j.polynom.Polynomial.x;

import org.gsl4j.polynom.Polynomial;
import org.gsl4j.polynom.Rational;

class TestRational {

	public static void test1() {
		Rational r = new Rational(x*x-1, x.pow(3)+1) ;
		Rational s = new Rational((x-1)*(x+3), x+3) ;
		System.out.println(r);
		System.out.println(s);
		Rational m = r/s ;
		System.out.println(m);
		System.out.println(m.simplify());
		System.out.println(m.zeroes());
		System.out.println(m.poles());
		System.out.println(s.evaluate(-3));
		System.out.println(s.limit(-3));
	}

	public static void test2() {
		Rational r = new Rational(x*x-1, x.pow(3)+1) ;
		Polynomial s = (x-1)*(x+3) ;
		Rational sum = r + s ;
		System.out.println(sum);
		System.out.println(sum.simplify());
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
