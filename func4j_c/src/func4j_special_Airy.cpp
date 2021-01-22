/*
 * func4j_special_Airy.cpp
 *
 *  Created on: Jan 9, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Airy.h"
#include <gsl/gsl_sf_airy.h>
#include "../amos/amos_functions.h"


/*
 * Class:     func4j_special_Airy
 * Method:    ai
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_ai__D
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiScaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiScaled__D
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_scaled(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDeriv
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiDeriv__D
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_deriv(x, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDerivScaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Airy_aiDerivScaled__D
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


/*
 * Class:     func4j_special_Airy
 * Method:    ai
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Airy_ai__DD
  (JNIEnv *jvm, jclass Airy_class, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint ID {0} ;
	jint KODE {1} ;
	jint NZ, IERR ;
	zairy_(re, im, ID, KODE, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiScaled
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Airy_aiScaled__DD
  (JNIEnv *jvm, jclass Airy_class, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint ID {0} ;
	jint KODE {2} ;
	jint NZ, IERR ;
	zairy_(re, im, ID, KODE, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDeriv
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Airy_aiDeriv__DD
  (JNIEnv *jvm, jclass Airy_class, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint ID {1} ;
	jint KODE {1} ;
	jint NZ, IERR ;
	zairy_(re, im, ID, KODE, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Airy
 * Method:    aiDerivScaled
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Airy_aiDerivScaled__DD
  (JNIEnv *jvm, jclass Airy_class, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint ID {1} ;
	jint KODE {2} ;
	jint NZ, IERR ;
	zairy_(re, im, ID, KODE, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

