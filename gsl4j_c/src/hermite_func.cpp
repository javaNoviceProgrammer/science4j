/*
 * hermite_func.cpp
 *
 *  Created on: Jun 11, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_hermite.h>
#include "../headers/org_gsl4j_special_Hermite.h"


/*
 * Class:     org_gsl4j_special_Hermite
 * Method:    hermiteFunc
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Hermite_hermiteFunc
  (JNIEnv *env, jclass Hermite, jint n, jdouble x) {
	return gsl_sf_hermite_func(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Hermite
 * Method:    hermiteFuncFast
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Hermite_hermiteFuncFast
  (JNIEnv *env, jclass Hermite, jint n, jdouble x) {
	return gsl_sf_hermite_func_fast(n, x) ;
}

/*
 * Class:     org_gsl4j_special_Hermite
 * Method:    hermiteFuncArray
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Hermite_hermiteFuncArray
  (JNIEnv *env, jclass Hermite, jint nmax, jdouble x) {
	jint len = nmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_func_array(nmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_Hermite
 * Method:    hermiteFuncSeries
 * Signature: (ID[D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Hermite_hermiteFuncSeries
  (JNIEnv *env, jclass Hermite, jint n, jdouble x, jdoubleArray a) {
	jint len = env -> GetArrayLength(a) ;
	jdouble *coeffs = new jdouble[len] ;
	env -> GetDoubleArrayRegion(a, 0, len, coeffs) ;
	jdouble jresult = gsl_sf_hermite_func_series(n, x, coeffs) ;
	delete[] coeffs ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_Hermite
 * Method:    hermiteFuncDeriv
 * Signature: (IID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Hermite_hermiteFuncDeriv
  (JNIEnv *env, jclass Hermite, jint m, jint n, jdouble x) {
	return gsl_sf_hermite_func_der(m, n, x) ;
}

/*
 * Class:     org_gsl4j_special_Hermite
 * Method:    hermiteFuncZero
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Hermite_hermiteFuncZero
  (JNIEnv *env, jclass Hermite, jint n, jint s) {
	return gsl_sf_hermite_func_zero(n, s) ;
}

