/*
 * elementary_functions.cpp
 *
 *  Created on: Jun 2, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_ElementaryFunctions.h"
#include <gsl/gsl_math.h>
#include <gsl/gsl_sf_elementary.h>

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    log1p
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_log1p
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_log1p(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    expm1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_expm1
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_expm1(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    hypot
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_hypot
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x, jdouble y) {
	return gsl_hypot(x, y) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    hypot3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_hypot3
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x, jdouble y, jdouble z) {
	return gsl_hypot3(x, y, z) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    acosh
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_acosh
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_acosh(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    asinh
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_asinh
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_asinh(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    atanh
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_atanh
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_atanh(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    ldexp
 * Signature: (DI)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_ldexp
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x, jint n) {
	return gsl_ldexp(x, n) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    frexp
 * Signature: (DI)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ElementaryFunctions_frexp
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x, jint n) {
	jint m = n ;
	jdoubleArray f_and_n = env -> NewDoubleArray(2) ;
	jdouble result[2] ;
	jdouble f = gsl_frexp(x, &m) ;
	result[0] = f ;
	result[1] = m ;
	env -> SetDoubleArrayRegion(f_and_n, 0, 2, result) ;
	return f_and_n ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    powInt
 * Signature: (DI)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_powInt
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x, jint n) {
	return gsl_pow_int(x, n) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow2
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_2(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow3
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_3(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow4
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow4
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_4(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow5
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow5
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_5(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow6
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow6
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_6(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow7
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow7
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_7(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow8
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow8
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_8(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    pow9
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_pow9
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return gsl_pow_9(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    sign
 * Signature: (D)I
 */
JNIEXPORT jint JNICALL Java_org_gsl4j_ElementaryFunctions_sign
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x) {
	return GSL_SIGN(x) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    isOdd
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_ElementaryFunctions_isOdd
  (JNIEnv *env, jclass elementaryFunctionsClass, jint n) {
	return GSL_IS_ODD(n) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    isEven
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_ElementaryFunctions_isEven
  (JNIEnv *env, jclass elementaryFunctionsClass, jint n) {
	return GSL_IS_EVEN(n) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    max
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_max
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble a, jdouble b) {
	return GSL_MAX_DBL(a, b) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    min
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_min
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble a, jdouble b) {
	return GSL_MIN_DBL(a, b) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    fcmp
 * Signature: (DDD)I
 */
JNIEXPORT jint JNICALL Java_org_gsl4j_ElementaryFunctions_fcmp
  (JNIEnv *env, jclass elementaryFunctionsClass, jdouble x, jdouble y, jdouble epsilon) {
	return gsl_fcmp(x, y, epsilon) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    multiply
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_multiply
  (JNIEnv *env, jclass elementaryFunctions, jdouble x, jdouble y) {
	return gsl_sf_multiply(x, y) ;
}

/*
 * Class:     org_gsl4j_ElementaryFunctions
 * Method:    multiplyError
 * Signature: (DDDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ElementaryFunctions_multiplyError
  (JNIEnv *env, jclass elementaryFunctions, jdouble x, jdouble dx, jdouble y, jdouble dy) {
	gsl_sf_result *result {NULL} ;
	gsl_sf_multiply_err_e(x, dx, y, dy, result) ;
	return result -> val ;
}




