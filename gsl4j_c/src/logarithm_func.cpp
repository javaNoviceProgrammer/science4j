/*
 * logarithm_func.cpp
 *
 *  Created on: Jun 13, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_log.h>
#include "../headers/org_gsl4j_special_Logarithm.h"

/*
 * Class:     org_gsl4j_special_Logarithm
 * Method:    log
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Logarithm_log
  (JNIEnv *env, jclass Logarithm, jdouble x) {
	return gsl_sf_log(x) ;
}

/*
 * Class:     org_gsl4j_special_Logarithm
 * Method:    logAbs
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Logarithm_logAbs
  (JNIEnv *env, jclass Logarithm, jdouble x) {
	return gsl_sf_log_abs(x) ;
}

/*
 * Class:     org_gsl4j_special_Logarithm
 * Method:    complexLog
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_Logarithm_complexLog
  (JNIEnv *env, jclass Logarithm, jdouble re, jdouble im) {
	gsl_sf_result lnr = {0.0, 0.0} ;
	gsl_sf_result theta = {0.0, 0.0} ;
	gsl_sf_complex_log_e(re, im, &lnr, &theta) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	jdouble result[2] {lnr.val, theta.val} ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_special_Logarithm
 * Method:    log1plusx
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Logarithm_log1plusx
  (JNIEnv *env, jclass Logarithm, jdouble x) {
	return gsl_sf_log_1plusx(x) ;
}

/*
 * Class:     org_gsl4j_special_Logarithm
 * Method:    log1plusxMx
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Logarithm_log1plusxMx
  (JNIEnv *env, jclass Logarithm, jdouble x) {
	return gsl_sf_log_1plusx_mx(x) ;
}

