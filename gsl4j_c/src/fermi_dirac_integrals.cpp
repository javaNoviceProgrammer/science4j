/*
 * fermi_dirac_integrals.cpp
 *
 *  Created on: Jun 9, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_fermi_dirac.h>
#include "../headers/org_gsl4j_special_FermiDirac.h"


/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDiracM1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDiracM1
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_m1(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDriac0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDriac0
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_0(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDirac1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDirac1
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_1(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDirac2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDirac2
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_2(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDiracInt
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDiracInt
  (JNIEnv *env, jclass FermiDirac, jint n, jdouble x) {
	return gsl_sf_fermi_dirac_int(n, x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDiracMhalf
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDiracMhalf
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_mhalf(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDiractHalf
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDiractHalf
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_half(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDirac3half
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDirac3half
  (JNIEnv *env, jclass FermiDirac, jdouble x) {
	return gsl_sf_fermi_dirac_3half(x) ;
}

/*
 * Class:     org_gsl4j_special_FermiDirac
 * Method:    fermiDiractIncomplete0
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_FermiDirac_fermiDiractIncomplete0
  (JNIEnv *env, jclass FermiDirac, jdouble x, jdouble b) {
	return gsl_sf_fermi_dirac_inc_0(x, b) ;
}
