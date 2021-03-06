/*
 * func4j_special_Bessel.cpp
 *
 *  Created on: Jan 9, 2021
 *      Author: meisam
 */


#include "../headers/func4j_special_Bessel.h"
#include "../amos/amos_functions.h"
#include "../scipy/specfunc.h"
#include <gsl/gsl_sf_bessel.h>


/*
 * Class:     func4j_special_Bessel
 * Method:    J0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_J0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_J0(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    J1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_J1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_J1(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Jn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Jn__ID
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Jn(n, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    JnArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_JnArray
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
 * Class:     func4j_special_Bessel
 * Method:    Y0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Y0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_Y0(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Y1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Y1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_Y1(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Yn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Yn__ID
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Yn(n, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    YnArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_YnArray
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
 * Class:     func4j_special_Bessel
 * Method:    I0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_I0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I0(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    I1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_I1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I1(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    In
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_In__ID
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_In(n, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    InArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_InArray
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
 * Class:     func4j_special_Bessel
 * Method:    I0Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_I0Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I0_scaled(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    I1Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_I1Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_I1_scaled(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    InScaled
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_InScaled
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_In_scaled(n, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    InScaledArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_InScaledArray
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
 * Class:     func4j_special_Bessel
 * Method:    K0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_K0
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_K0(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    K1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_K1
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_K1(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Kn
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Kn__ID
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Kn(n, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    KnArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_KnArray
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
 * Class:     func4j_special_Bessel
 * Method:    K0Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_K0Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_K0_scaled(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    K1Scaled
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_K1Scaled
  (JNIEnv *env, jclass besselClass, jdouble x) {
	return gsl_sf_bessel_k1_scaled(x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    KnScaled
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_KnScaled
  (JNIEnv *env, jclass besselClass, jint n, jdouble x) {
	return gsl_sf_bessel_Kn_scaled(n, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    KnScaledArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_KnScaledArray
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
 * Class:     func4j_special_Bessel
 * Method:    Jv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Jv__DD
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Jnu(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Jv
 * Signature: (D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Jv__D_3D
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
 * Class:     func4j_special_Bessel
 * Method:    Yv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Yv__DD
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Ynu(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Iv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Iv__DD
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Inu(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    IvScaled
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_IvScaled
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Inu_scaled(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Kv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_Kv__DD
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Knu(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    lnKv
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_lnKv
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_lnKnu(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    KvScaled
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_KvScaled
  (JNIEnv *env, jclass besselClass, jdouble nu, jdouble x) {
	return gsl_sf_bessel_Knu_scaled(nu, x) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    zeroJ0
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_zeroJ0
  (JNIEnv *env, jclass besselClass, jint n) {
	return gsl_sf_bessel_zero_J0(n) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    zeroJ1
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_zeroJ1
  (JNIEnv *env, jclass besselClass, jint n) {
	return gsl_sf_bessel_zero_J1(n) ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    zeroJv
 * Signature: (DI)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Bessel_zeroJv
  (JNIEnv *env, jclass besselClass, jdouble nu, jint n) {
	return gsl_sf_bessel_zero_Jnu(nu, n) ;
}


/*
 * Class:     func4j_special_Bessel
 * Method:    Jn
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Jn__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	zbesj_(re, im, order, KODE, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Jv
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Jv__DDD
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	zbesj_(re, im, v, KODE, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    JvAndJvDeriv
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_JvAndJvDeriv
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble Z[] = {re, im} ;
	jdouble CBJV[2] ;
	jdouble CDJV[2] ;
	jdouble CBYV[2] ;
	jdouble CDYV[2] ;
	cjylv_(v, Z, CBJV, CDJV, CBYV, CDYV) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(4) ;
	jdouble buf[4] = {CBJV[0], CBJV[1], CDJV[0], CDJV[1]} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 4, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    YvAndYvDeriv
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_YvAndYvDeriv
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble Z[] = {re, im} ;
	jdouble CBJV[2] ;
	jdouble CDJV[2] ;
	jdouble CBYV[2] ;
	jdouble CDYV[2] ;
	cjylv_(v, Z, CBJV, CDJV, CBYV, CDYV) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(4) ;
	jdouble buf[4] = {CBYV[0], CBYV[1], CDYV[0], CDYV[1]} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 4, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Yn
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Yn__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	jdouble CWRKR[N], CWRKI[N] ;
	zbesy_(re, im, order, KODE, N, result_re, result_im, NZ, CWRKR, CWRKI, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Yv
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Yv__DDD
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble CWRKR[N], CWRKI[N] ;
	zbesy_(re, im, v, KODE, N, result_re, result_im, NZ, CWRKR, CWRKI, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    In
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_In__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	zbesi_(re, im, order, KODE, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Iv
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Iv__DDD
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	zbesi_(re, im, v, KODE, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Kn
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Kn__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	zbesk_(re, im, order, KODE, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Kv
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Kv__DDD
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	zbesk_(re, im, v, KODE, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Hn
 * Signature: (IIDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Hn__IIDD
  (JNIEnv * jvm, jclass Bessel_class, jint kind, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Hv
 * Signature: (IDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Hv__IDDD
  (JNIEnv *jvm, jclass Bessel_class, jint kind, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	zbesh_(re, im, v, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H1n
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H1n__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	jint kind = 1 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H1v
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H1v__DDD
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jint kind = 1 ;
	zbesh_(re, im, v, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H2n
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H2n__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	jint kind = 2 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H2v
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H2v__DDD
  (JNIEnv *jvm, jclass Bessel_class, jdouble v, jdouble re, jdouble im) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jint kind = 2 ;
	zbesh_(re, im, v, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Hn
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Hn__IID
  (JNIEnv *jvm, jclass Bessel_class, jint kind, jint n, jdouble x) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	jdouble re = x, im = 0.0 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    Hv
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_Hv__IDD
  (JNIEnv *jvm, jclass Bessel_class, jint kind, jdouble v, jdouble x) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = v ;
	jdouble re = x, im = 0.0 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H1n
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H1n__ID
  (JNIEnv *jvm, jclass Bessel_class, jint n, jdouble x) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	jint kind = 1 ;
	jdouble re = x, im = 0.0 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H1v
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H1v__DD
  (JNIEnv * jvm, jclass Bessel_class, jdouble v, jdouble x) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = v ;
	jint kind = 1 ;
	jdouble re = x, im = 0.0 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H2n
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H2n__ID
  (JNIEnv * jvm, jclass Bessel_class, jint n, jdouble x) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = n ;
	jint kind = 2 ;
	jdouble re = x, im = 0.0 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_special_Bessel
 * Method:    H2v
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Bessel_H2v__DD
  (JNIEnv * jvm, jclass Bessel_class, jdouble v, jdouble x) {
	jdouble result_re, result_im ;
	jint KODE {1}, N {1} ;
	jint NZ, IERR ;
	jdouble order = v ;
	jint kind = 2 ;
	jdouble re = x, im = 0.0 ;
	zbesh_(re, im, order, KODE, kind, N, result_re, result_im, NZ, IERR) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[2] = {result_re, result_im} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

