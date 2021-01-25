/*
 * func4j_special_ErrorFunction.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_ErrorFunction.h"
#include "../scipy/specfunc.h"
#include <gsl/gsl_sf_erf.h>

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erf
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_ErrorFunction_erf__D
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

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erf
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_ErrorFunction_erf__DD
  (JNIEnv *jvm, jclass ErrorFunction_class, jdouble re, jdouble im) {
	jdouble Z[] = {re, im} ;
	jdouble CER[2] ;
	jdouble CDER[2] ;
	cerf_(Z, CER, CDER) ;
	jdoubleArray result = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(result, 0, 2, CER) ;
	return result ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erfDeriv
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_ErrorFunction_erfDeriv
  (JNIEnv *jvm, jclass ErrorFunction_class, jdouble re, jdouble im) {
	jdouble Z[] = {re, im} ;
	jdouble CER[2] ;
	jdouble CDER[2] ;
	cerf_(Z, CER, CDER) ;
	jdoubleArray result = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(result, 0, 2, CDER) ;
	return result ;
}

/*
 * Class:     func4j_special_ErrorFunction
 * Method:    erfAndDeriv
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_ErrorFunction_erfAndDeriv
  (JNIEnv *jvm, jclass ErrorFunction, jdouble re, jdouble im) {
	jdouble Z[] = {re, im} ;
	jdouble CER[4] ;
	cerf_(Z, CER, CER+2) ;
	jdoubleArray result = jvm -> NewDoubleArray(4) ;
	jvm -> SetDoubleArrayRegion(result, 0, 4, CER) ;
	return result ;
}


