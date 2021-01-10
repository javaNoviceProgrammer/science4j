/*
 * func4j_special_ErrorFunction.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_ErrorFunction.h"

#include <gsl/gsl_sf_erf.h>

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erf
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_erf
  (JNIEnv *env, jclass errorFunctionClass, jdouble x) {
	return gsl_sf_erf(x) ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erfc
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_erfc
  (JNIEnv *env, jclass errorFunctionClass, jdouble x) {
	return gsl_sf_erfc(x) ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    logErfc
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_logErfc
  (JNIEnv *env, jclass errorFunctionClass, jdouble x) {
	return gsl_sf_log_erfc(x) ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erfZ
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_erfZ
  (JNIEnv *env, jclass errorFunctionClass, jdouble x) {
	return gsl_sf_erf_Z(x) ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erfQ
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_erfQ
  (JNIEnv *env, jclass errorFunctionClass, jdouble x) {
	return gsl_sf_erf_Q(x) ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    hazard
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_hazard
  (JNIEnv *env, jclass errorFunctionClass, jdouble x) {
	return gsl_sf_hazard(x) ;
}


