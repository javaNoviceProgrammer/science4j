/*
 * gamma_func.cpp
 *
 *  Created on: Jun 9, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_gamma.h>
#include "../headers/org_gsl4j_special_Gamma.h"


/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    gamma
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_gamma
  (JNIEnv *env, jclass Gamma, jdouble x) {
	return gsl_sf_gamma(x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lngamma
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_lngamma
  (JNIEnv *env, jclass Gamma, jdouble x) {
	return gsl_sf_lngamma(x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lngammaSign
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Gamma_lngammaSign
  (JNIEnv *env, jclass Gamma, jdouble x) {
	gsl_sf_result result = {0.0, 0.0} ;
	jdouble sign = 0.0 ;
	gsl_sf_lngamma_sgn_e(x, &result, &sign) ;
	jdouble buffer[2] = {result.val, sign} ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    gammaStar
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_gammaStar
  (JNIEnv *env, jclass Gamma, jdouble x) {
	return gsl_sf_gammastar(x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    gammaInv
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_gammaInv
  (JNIEnv *env, jclass Gamma, jdouble x) {
	return gsl_sf_gammainv(x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lngammaComplex
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Gamma_lngammaComplex
  (JNIEnv *env, jclass Gamma, jdouble re, jdouble im) {
	gsl_sf_result lnr = {0.0, 0.0} ;
	gsl_sf_result arg = {0.0, 0.0} ;
	gsl_sf_lngamma_complex_e(re, im, &lnr, &arg) ;
	jdouble buffer[2] = {lnr.val, arg.val} ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    factorial
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_factorial
  (JNIEnv *env, jclass Gamma, jint n) {
	return gsl_sf_fact(n) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    doubleFactorial
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_doubleFactorial
  (JNIEnv *env, jclass Gamma, jint n) {
	return gsl_sf_doublefact(n) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lnfactorial
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_lnfactorial
  (JNIEnv *env, jclass Gamma, jint n) {
	return gsl_sf_lnfact(n) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lnDoubleFactorial
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_lnDoubleFactorial
  (JNIEnv *env, jclass Gamma, jint n) {
	return gsl_sf_lndoublefact(n) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    choose
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_choose
  (JNIEnv *env, jclass Gamma, jint n, jint m) {
	return gsl_sf_choose(n, m) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lnchoose
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_lnchoose
  (JNIEnv *env, jclass Gamma, jint n, jint m) {
	return gsl_sf_lnchoose(n, m) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    taylorCoeff
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_taylorCoeff
  (JNIEnv *env, jclass Gamma, jint n, jdouble x) {
	return gsl_sf_taylorcoeff(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    poch
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_poch
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	return gsl_sf_poch(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lnpoch
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_lnpoch
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	return gsl_sf_lnpoch(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lnpochSign
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Gamma_lnpochSign
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	gsl_sf_result result = {0.0, 0.0} ;
	jdouble sign = 0.0 ;
	gsl_sf_lnpoch_sgn_e(a, x, &result, &sign) ;
	jdouble buffer[2] = {result.val, sign} ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    pochrel
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_pochrel
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	return gsl_sf_pochrel(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    gammaIncomplete
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_gammaIncomplete
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	return gsl_sf_gamma_inc(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    gammaIncompleteQ
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_gammaIncompleteQ
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	return gsl_sf_gamma_inc_Q(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    gammaIncompleteP
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_gammaIncompleteP
(JNIEnv *env, jclass Gamma, jdouble a, jdouble x) {
	return gsl_sf_gamma_inc_P(a, x) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    beta
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_beta
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble b) {
	return gsl_sf_beta(a, b) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    lnbeta
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_lnbeta
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble b) {
	return gsl_sf_lnbeta(a, b) ;
}

/*
 * Class:     org_gsl4j_special_Gamma
 * Method:    betaIncomplete
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gamma_betaIncomplete
  (JNIEnv *env, jclass Gamma, jdouble a, jdouble b, jdouble x) {
	return gsl_sf_beta_inc(a, b, x) ;
}


