package org.gsl4j.tests;

import org.gsl4j.PhysicalConstants;

class TestPhysConstants {

	private static void test1() {
		System.out.println(PhysicalConstants.GSL_CONST_MKSA_SPEED_OF_LIGHT);
		System.out.println(PhysicalConstants.GSL_CONST_CGSM_SPEED_OF_LIGHT);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
