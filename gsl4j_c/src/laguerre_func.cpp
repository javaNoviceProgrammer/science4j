/*
 * laguerre_func.cpp
 *
 *  Created on: Jun 11, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_laguerre.h>
#include "../headers/org_gsl4j_special_Laguerre.h"

/*
 * Class:     org_gsl4j_special_Laguerre
 * Method:    laguerre1
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Laguerre_laguerre1
  (JNIEnv *env, jclass Laguerre, jdouble a, jdouble x) {
	return gsl_sf_laguerre_1(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Laguerre
 * Method:    laguerre2
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Laguerre_laguerre2
  (JNIEnv *env, jclass Laguerre, jdouble a, jdouble x) {
	return gsl_sf_laguerre_2(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Laguerre
 * Method:    laguerre3
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Laguerre_laguerre3
  (JNIEnv *env, jclass Laguerre, jdouble a, jdouble x) {
	return gsl_sf_laguerre_3(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Laguerre
 * Method:    laguerreN
 * Signature: (IDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Laguerre_laguerreN
  (JNIEnv *env, jclass Laguerre, jint n, jdouble a, jdouble x) {
	return gsl_sf_laguerre_n(n, a, x) ;
}


