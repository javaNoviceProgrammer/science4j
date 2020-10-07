/*
 * gegenbauer_func.cpp
 *
 *  Created on: Jun 10, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_gegenbauer.h>
#include "../headers/org_gsl4j_special_Gegenbauer.h"

/*
 * Class:     org_gsl4j_special_Gegenbauer
 * Method:    gegenpoly1
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gegenbauer_gegenpoly1
  (JNIEnv *env, jclass Gegenbauer, jdouble lambda, jdouble x) {
	return gsl_sf_gegenpoly_1(lambda, x) ;
}

/*
 * Class:     org_gsl4j_special_Gegenbauer
 * Method:    gegenpoly2
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gegenbauer_gegenpoly2
  (JNIEnv *env, jclass Gegenbauer, jdouble lambda, jdouble x) {
	return gsl_sf_gegenpoly_2(lambda, x) ;
}

/*
 * Class:     org_gsl4j_special_Gegenbauer
 * Method:    gegenpoly3
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gegenbauer_gegenpoly3
  (JNIEnv *env, jclass Gegenbauer, jdouble lambda, jdouble x) {
	return gsl_sf_gegenpoly_3(lambda, x) ;
}

/*
 * Class:     org_gsl4j_special_Gegenbauer
 * Method:    gegenpolyN
 * Signature: (IDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Gegenbauer_gegenpolyN
  (JNIEnv *env, jclass Gegenbauer, jint n, jdouble lambda, jdouble x) {
	return gsl_sf_gegenpoly_n(n, lambda, x) ;
}

/*
 * Class:     org_gsl4j_special_Gegenbauer
 * Method:    gegenpolyArray
 * Signature: (IDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Gegenbauer_gegenpolyArray
  (JNIEnv *env, jclass Gegenbauer, jint nmax, jdouble lambda, jdouble x) {
	jdouble result[nmax+1] ;
	gsl_sf_gegenpoly_array(nmax, lambda, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(nmax+1) ;
	env -> SetDoubleArrayRegion(jresult, 0, nmax+1, result) ;
	return jresult ;
}


