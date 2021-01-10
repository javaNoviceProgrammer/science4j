/*
 * func4j_special_Digamma.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Digamma.h"

#include <gsl/gsl_sf_psi.h>

/*
 * Class:     func4j_special_Digamma
 * Method:    psiInt
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_psiInt
  (JNIEnv *env, jclass Digamma, jint n) {
	return gsl_sf_psi_int(n) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    digamma
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_digamma__I
  (JNIEnv *env, jclass Digamma, jint n) {
	return gsl_sf_psi_int(n) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    psi
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_psi
  (JNIEnv *env, jclass Digamma, jdouble x) {
	return gsl_sf_psi(x) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    digamma
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_digamma__D
  (JNIEnv *env, jclass Digamma, jdouble x) {
	return gsl_sf_psi(x) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    psi1piy
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_psi1piy
  (JNIEnv *env, jclass Digamma, jdouble y) {
	return gsl_sf_psi_1piy(y) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    psi1int
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_psi1int
  (JNIEnv *env, jclass Digamma, jint n) {
	return gsl_sf_psi_1_int(n) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    psi1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_psi1
  (JNIEnv *env, jclass Digamma, jdouble x) {
	return gsl_sf_psi_1(x) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    trigamma
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_trigamma
  (JNIEnv *env, jclass Digamma, jdouble x) {
	return gsl_sf_psi_1(x) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    psiN
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_psiN
  (JNIEnv *env, jclass Digamma, jint n, jdouble x) {
	return gsl_sf_psi_n(n, x) ;
}

/*
 * Class:     func4j_special_Digamma
 * Method:    polygamma
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Digamma_polygamma
  (JNIEnv *env, jclass Digamma, jint n, jdouble x) {
	return gsl_sf_psi_n(n, x) ;
}

