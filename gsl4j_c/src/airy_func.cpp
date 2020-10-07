/*
 * special_funcs.cpp
 *
 *  Created on: Jun 1, 2020
 *      Author: meisam
 */


#include "../headers/org_gsl4j_special_Airy.h"
#include <gsl/gsl_sf_airy.h>


// Ai(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_ai
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai(x, GSL_PREC_DOUBLE) ;
}

// scaled Ai(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_aiScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_scaled(x, GSL_PREC_DOUBLE) ;
}

// Ai'(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_aiDeriv
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_deriv(x, GSL_PREC_DOUBLE) ;
}

// scaled Ai'(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_aiDerivScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Ai_deriv_scaled(x, GSL_PREC_DOUBLE) ;
}

// n-th zero of Ai(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_aiZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Ai(n) ;
}

// n-th zero of Ai'(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_aiDerivZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Ai_deriv(n) ;
}


// Bi(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_bi
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi(x, GSL_PREC_DOUBLE) ;
}

// scaled Bi(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_biScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi_scaled(x, GSL_PREC_DOUBLE) ;
}

// Bi'(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_biDeriv
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi_deriv(x, GSL_PREC_DOUBLE) ;
}

// scaled Bi'(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_biDerivScaled
  (JNIEnv *env, jclass airyClass, jdouble x) {
	return gsl_sf_airy_Bi_deriv_scaled(x, GSL_PREC_DOUBLE) ;
}

// n-th zero of Bi(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_biZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Bi(n) ;
}

// n-th zero of Bi'(x)
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Airy_biDerivZero
  (JNIEnv *env, jclass airyClass, jint n) {
	return gsl_sf_airy_zero_Bi_deriv(n) ;
}

