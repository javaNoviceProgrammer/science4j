package org.gsl4j.tests;

import org.gsl4j.special.FermiDirac;

class TetsFermiDirac {

	private static void test1() {
		double x = 1.1 ;
		double y = FermiDirac.fermiDiracInt(2, x) ;
		System.out.println(y);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
