/*
 * exp_integrals.cpp
 *
 *  Created on: Jun 8, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_expint.h>
#include "../headers/org_gsl4j_special_ExpIntegrals.h"

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    expIntE1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_expIntE1
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_expint_E1(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    expIntE2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_expIntE2
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_expint_E2(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    expIntEn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_expIntEn
  (JNIEnv *env, jclass ExpIntegrals, jint n, jdouble x) {
	return gsl_sf_expint_En(n, x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    expIntEi
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_expIntEi
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_expint_Ei(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    shi
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_shi
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_Shi(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    chi
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_chi
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_Chi(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    expInt3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_expInt3
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_expint_3(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    si
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_si
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_Si(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    ci
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_ci
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_Ci(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpIntegrals
 * Method:    atanInt
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpIntegrals_atanInt
  (JNIEnv *env, jclass ExpIntegrals, jdouble x) {
	return gsl_sf_atanint(x) ;
}


