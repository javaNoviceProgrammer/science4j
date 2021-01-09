/*
 * func4j_special_Airy.cpp
 *
 *  Created on: Jan 9, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Airy.h"

#include <gsl/gsl_sf_airy.h>


/*
 * Class:     func4j_special_Airy
 * Method:    ai
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_ai
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiScaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_scaled(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDeriv
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiDeriv
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_deriv(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDerivScaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiDerivScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_deriv_scaled(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiZero
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Ai(n) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDerivZero
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiDerivZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Ai_deriv(n) ;
}


/*
 * Class:     func4j_special_Airy
 * Method:    bi
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_bi
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    biScaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_biScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi_scaled(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    biDeriv
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_biDeriv
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi_deriv(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    biDerivScaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_biDerivScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi_deriv_scaled(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    biZero
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_biZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Bi(n) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    biDerivZero
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_biDerivZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Bi_deriv(n) ;
}

