/*
 * hyper_geometric_func.cpp
 *
 *  Created on: Jun 18, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_hyperg.h>
#include "../headers/org_gsl4j_special_HyperGeometric.h"

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg0F1
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg0F1
  (JNIEnv *env, jclass HyperGeometric, jdouble c, jdouble x) {
	return gsl_sf_hyperg_0F1(c, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg1F1int
 * Signature: (IID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg1F1int
  (JNIEnv *env, jclass HyperGeometric, jint m, jint n, jdouble x) {
	return gsl_sf_hyperg_1F1_int(m, n, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg1F1
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg1F1
  (JNIEnv *env, jclass HyperGeometric, jdouble a, jdouble b, jdouble x) {
	return gsl_sf_hyperg_1F1(a, b, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hypergUint
 * Signature: (IID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hypergUint
  (JNIEnv *env, jclass HyperGeometric, jint m, jint n, jdouble x) {
	return gsl_sf_hyperg_U_int(m, n, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hypergUintE10
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_HyperGeometric_hypergUintE10
  (JNIEnv *env, jclass HyperGeometric, jint m, jint n, jdouble x) {
	gsl_sf_result_e10 result ;
	gsl_sf_hyperg_U_int_e10_e(m, n, x, &result) ;
	jdouble buf[] = {result.val, (jdouble)result.e10, result.err} ;
	jdoubleArray jresult = env -> NewDoubleArray(3) ;
	env -> SetDoubleArrayRegion(jresult, 0, 3, buf) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hypergU
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hypergU
  (JNIEnv *env, jclass HyperGeometric, jdouble a, jdouble b, jdouble x) {
	return gsl_sf_hyperg_U(a, b, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hypergUe10
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_HyperGeometric_hypergUe10
  (JNIEnv *env, jclass HyperGeometric, jdouble a, jdouble b, jdouble x) {
	gsl_sf_result_e10 result ;
	gsl_sf_hyperg_U_e10_e(a, b, x, &result) ;
	jdouble buf[] = {result.val, (jdouble)result.e10, result.err} ;
	jdoubleArray jresult = env -> NewDoubleArray(3) ;
	env -> SetDoubleArrayRegion(jresult, 0, 3, buf) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg2F1
 * Signature: (DDDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg2F1
  (JNIEnv *env, jclass HyperGeometric, jdouble a, jdouble b, jdouble c, jdouble x) {
	return gsl_sf_hyperg_2F1(a, b, c, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg2F1conj
 * Signature: (DDDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg2F1conj
  (JNIEnv *env, jclass HyperGeometric, jdouble aR, jdouble aI, jdouble c, jdouble x) {
	return gsl_sf_hyperg_2F1_conj(aR, aI, c, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg2F1renorm
 * Signature: (DDDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg2F1renorm
  (JNIEnv *env, jclass HyperGeometric, jdouble a, jdouble b, jdouble c, jdouble x) {
	return gsl_sf_hyperg_2F1_renorm(a, b, c, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg2F1conjRenorm
 * Signature: (DDDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg2F1conjRenorm
  (JNIEnv *env, jclass HyperGeometric, jdouble aR, jdouble aI, jdouble c, jdouble x) {
	return gsl_sf_hyperg_2F1_conj_renorm(aR, aI, c, x) ;
}

/*
 * Class:     org_gsl4j_special_HyperGeometric
 * Method:    hyperg2F0
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_HyperGeometric_hyperg2F0
  (JNIEnv *env, jclass HyperGeometric, jdouble a, jdouble b, jdouble x) {
	return gsl_sf_hyperg_2F0(a, b, x) ;
}




