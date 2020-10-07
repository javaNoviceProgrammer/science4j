/*
 * bessel_func.cpp
 *
 *  Created on: Jun 2, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_special_Bessel.h"
#include <gsl/gsl_sf_bessel.h>


/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    J0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_J0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_J0(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    J1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_J1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_J1(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Jn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Jn
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Jn(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    JnArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_JnArray
  (JNIEnv *env, jclass besselClass, jint nmin, jint nmax, jdouble x) {
	jint len = nmax-nmin+1 ;
	double *result_array = new double[len] ;
	gsl_sf_bessel_Jn_array(nmin, nmax, x, result_array) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, result_array) ;
	delete[] result_array ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Y0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Y0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_Y0(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Y1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Y1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_Y1(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Yn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Yn
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Yn(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    YnArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_YnArray
  (JNIEnv *env, jclass besselClass, jint nmin, jint nmax, jdouble x) {
	jint len = nmax-nmin+1 ;
	double *result_array = new double[len] ;
	gsl_sf_bessel_Yn_array(nmin, nmax, x, result_array) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, result_array) ;
	delete[] result_array ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    I0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_I0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I0(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    I1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_I1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I1(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    In
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_In
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_In(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    InArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_InArray
  (JNIEnv *env, jclass besselClass, jint nmin, jint nmax, jdouble x) {
	jint len = nmax-nmin+1 ;
	double *result_array = new double[len] ;
	gsl_sf_bessel_In_array(nmin, nmax, x, result_array) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, result_array) ;
	delete[] result_array ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    I0Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_I0Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I0_scaled(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    I1Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_I1Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I1_scaled(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    InScaled
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_InScaled
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_In_scaled(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    InScaledArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_InScaledArray
  (JNIEnv *env, jclass besselClass, jint nmin, jint nmax, jdouble x) {
	jint len = nmax-nmin+1 ;
	double *result_array = new double[len] ;
	gsl_sf_bessel_In_scaled_array(nmin, nmax, x, result_array) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, result_array) ;
	delete[] result_array ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    K0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_K0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_K0(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    K1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_K1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_K1(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Kn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Kn
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Kn(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    KnArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_KnArray
  (JNIEnv *env, jclass besselClass, jint nmin, jint nmax, jdouble x) {
	jint len = nmax-nmin+1 ;
	double *result_array = new double[len] ;
	gsl_sf_bessel_Kn_array(nmin, nmax, x, result_array) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, result_array) ;
	delete[] result_array ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    K0Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_K0Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_K0_scaled(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    K1Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_K1Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_k1_scaled(x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    KnScaled
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_KnScaled
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Kn_scaled(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    KnScaledArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_KnScaledArray
  (JNIEnv *env, jclass besselClass, jint nmin, jint nmax, jdouble x) {
	jint len = nmax-nmin+1 ;
	double *result_array = new double[len] ;
	gsl_sf_bessel_Kn_scaled_array(nmin, nmax, x, result_array) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, result_array) ;
	delete[] result_array ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Jv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Jv__DD
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Jnu(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Jv
 * Signature: (D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Bessel_Jv__D_3D
  (JNIEnv *env, jclass besselClass, jdouble nu, jdoubleArray x) {
	jint len = env -> GetArrayLength(x) ;
	jdouble *xvals = new double[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, xvals) ;
	gsl_sf_bessel_sequence_Jnu_e(nu, GSL_PREC_DOUBLE, len, xvals) ;
	jdoubleArray result = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(result, 0, len, xvals) ;
	delete[] xvals ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Yv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Yv
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Ynu(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Iv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Iv
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Inu(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    IvScaled
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_IvScaled
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Inu_scaled(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    Kv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_Kv
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Knu(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    lnKv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_lnKv
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_lnKnu(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    KvScaled
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_KvScaled
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Knu_scaled(nu, x) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    zeroJ0
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_zeroJ0
  (JNIEnv *env, jclass besselClass, jint n) {
	return gsl_sf_bessel_zero_J0(n) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    zeroJ1
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_zeroJ1
  (JNIEnv *env, jclass besselClass, jint n) {
	return gsl_sf_bessel_zero_J1(n) ;
}

/*
 * Class:     org_gsl4j_special_Bessel
 * Method:    zeroJv
 * Signature: (DI)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Bessel_zeroJv
  (JNIEnv *env, jclass besselClass, jdouble nu, jint n) {
	return gsl_sf_bessel_zero_Jnu(nu, n) ;
}













