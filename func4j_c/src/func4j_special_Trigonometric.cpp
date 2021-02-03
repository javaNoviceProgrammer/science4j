/*
 * func4j_special_Trigonometric.cpp
 *
 *  Created on: Feb 3, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Trigonometric.h"
#include <gsl/gsl_sf_result.h>
#include <gsl/gsl_sf_trig.h>

/*
 * Class:     func4j_special_Trigonometric
 * Method:    sin
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_sin__D
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_sin(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    cos
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_cos__D
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_cos(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    hypot
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_hypot
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x, jdouble y) {
	return gsl_sf_hypot(x, y) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    sinc
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_sinc
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_sinc(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    sinc2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_sinc2
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_sinc(x) * gsl_sf_sinc(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    sin
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_sin__DD
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble re, jdouble im) {
	gsl_sf_result szr, szi ;
	gsl_sf_complex_sin_e(re, im, &szr, &szi) ;
	jdouble buf[2] = {szr.val, szi.val} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    cos
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_cos__DD
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble re, jdouble im) {
	gsl_sf_result szr, szi ;
	gsl_sf_complex_cos_e(re, im, &szr, &szi) ;
	jdouble buf[2] = {szr.val, szi.val} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    logsin
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_logsin
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble re, jdouble im) {
	gsl_sf_result szr, szi ;
	gsl_sf_complex_logsin_e(re, im, &szr, &szi) ;
	jdouble buf[2] = {szr.val, szi.val} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    lnsinh
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_lnsinh
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_lnsinh(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    lncosh
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_lncosh
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_lncosh(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    polarToRect
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_polarToRect
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble r, jdouble phi) {
	gsl_sf_result szr, szi ;
	gsl_sf_polar_to_rect(r, phi, &szr, &szi) ;
	jdouble buf[2] = {szr.val, szi.val} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    rectToPolar
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_rectToPolar
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x, jdouble y) {
	gsl_sf_result szr, szi ;
	gsl_sf_rect_to_polar(x, y, &szr, &szi) ;
	jdouble buf[2] = {szr.val, szi.val} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    angleRestrictSymm
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_angleRestrictSymm
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_angle_restrict_symm(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    angleRestrictPos
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Trigonometric_angleRestrictPos
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x) {
	return gsl_sf_angle_restrict_pos(x) ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    sinErr
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_sinErr
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x, jdouble dx) {
	gsl_sf_result szr ;
	gsl_sf_sin_err_e(x, dx, &szr) ;
	jdouble buf[2] = {szr.val, szr.err} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Trigonometric
 * Method:    cosErr
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Trigonometric_cosErr
  (JNIEnv* jvm, jclass Trigonometric_class, jdouble x, jdouble dx) {
	gsl_sf_result szr ;
	gsl_sf_cos_err_e(x, dx, &szr) ;
	jdouble buf[2] = {szr.val, szr.err} ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

