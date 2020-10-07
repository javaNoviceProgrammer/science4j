/*
 * complex_math.cpp
 *
 *  Created on: Jun 2, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_complex_ComplexMath.h"
#include <gsl/gsl_complex.h>
#include <gsl/gsl_complex_math.h>

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arg
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_complex_ComplexMath_arg
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	return gsl_complex_arg(z) ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    abs
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_complex_ComplexMath_abs
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	return gsl_complex_abs(z) ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    abs2
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_complex_ComplexMath_abs2
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	return gsl_complex_abs2(z) ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    logabs
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_complex_ComplexMath_logabs
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	return gsl_complex_logabs(z) ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    add
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_add
  (JNIEnv *env, jclass complexMathClass, jdouble re1, jdouble im1, jdouble re2, jdouble im2) {
	gsl_complex z1 {{re1, im1}} ;
	gsl_complex z2 {{re2, im2}} ;
	gsl_complex result = gsl_complex_add(z1, z2) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sub
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sub
  (JNIEnv *env, jclass complexMathClass, jdouble re1, jdouble im1, jdouble re2, jdouble im2) {
	gsl_complex z1 {{re1, im1}} ;
	gsl_complex z2 {{re2, im2}} ;
	gsl_complex result = gsl_complex_sub(z1, z2) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    mul
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_mul
  (JNIEnv *env, jclass complexMathClass, jdouble re1, jdouble im1, jdouble re2, jdouble im2) {
	gsl_complex z1 {{re1, im1}} ;
	gsl_complex z2 {{re2, im2}} ;
	gsl_complex result = gsl_complex_mul(z1, z2) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    div
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_div
  (JNIEnv *env, jclass complexMathClass, jdouble re1, jdouble im1, jdouble re2, jdouble im2) {
	gsl_complex z1 {{re1, im1}} ;
	gsl_complex z2 {{re2, im2}} ;
	gsl_complex result = gsl_complex_div(z1, z2) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    addReal
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_addReal
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_add_real(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    subReal
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_subReal
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_sub_real(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    mulReal
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_mulReal
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_mul_real(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    divReal
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_divReal
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_div_real(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    addImag
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_addImag
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_add_imag(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    subImag
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_subImag
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_sub_imag(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    mulImag
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_mulImag
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_mul_imag(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    divImag
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_divImag
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z1 {{re, im}} ;
	gsl_complex result = gsl_complex_div_imag(z1, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    conjugate
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_conjugate
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_conjugate(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    inverse
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_inverse
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_inverse(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    negative
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_negative
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_negative(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sqrt
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sqrt
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_sqrt(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sqrtReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sqrtReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_sqrt_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    pow
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_pow
  (JNIEnv *env, jclass complexMathClass, jdouble re1, jdouble im1, jdouble re2, jdouble im2) {
	gsl_complex z1 {{re1, im1}} ;
	gsl_complex z2 {{re2, im2}} ;
	gsl_complex result = gsl_complex_pow(z1, z2) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    powReal
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_powReal
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im, jdouble x) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_pow_real(z, x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    exp
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_exp
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_exp(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    log
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_log
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_log(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    log10
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_log10
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_log10(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    logb
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_logb
  (JNIEnv *env, jclass complexMathClass, jdouble re1, jdouble im1, jdouble re2, jdouble im2) {
	gsl_complex z1 {{re1, im1}} ;
	gsl_complex z2 {{re2, im2}} ;
	gsl_complex result = gsl_complex_log_b(z1, z2) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sin
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sin
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_sin(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    cos
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_cos
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_cos(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    tan
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_tan
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_tan(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sec
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sec
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_sec(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    csc
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_csc
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_csc(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    cot
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_cot
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_cot(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arcsin
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arcsin
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arcsin(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arcsinReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arcsinReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_arcsin_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccos
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccos
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arccos(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccosReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccosReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_arccos_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arctan
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arctan
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arctan(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arcsec
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arcsec
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arcsec(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arcsecReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arcsecReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_arcsec_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccsc
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccsc
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arccsc(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccscReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccscReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_arccsc_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccot
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccot
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arccot(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sinh
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sinh
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_sinh(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    cosh
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_cosh
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_cosh(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    tanh
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_tanh
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_tanh(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    sech
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_sech
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_sech(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    csch
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_csch
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_csch(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    coth
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_coth
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_coth(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arcsinh
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arcsinh
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arcsinh(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccosh
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccosh
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arccosh(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccoshReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccoshReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_arccosh_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arctanh
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arctanh
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arctanh(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arctanhReal
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arctanhReal
  (JNIEnv *env, jclass complexMathClass, jdouble x) {
	gsl_complex result = gsl_complex_arctanh_real(x) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arcsech
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arcsech
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arcsech(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccsch
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccsch
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arccsch(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_complex_ComplexMath
 * Method:    arccoth
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_complex_ComplexMath_arccoth
  (JNIEnv *env, jclass complexMathClass, jdouble re, jdouble im) {
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_arccoth(z) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result.dat) ;
	return jresult ;
}



