/*
 * exp_func.cpp
 *
 *  Created on: Jun 7, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_exp.h>
#include "../headers/org_gsl4j_special_ExpFunction.h"


/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    exp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_exp
  (JNIEnv *env, jclass ExpFunction, jdouble x) {
	return gsl_sf_exp(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    exp10
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_exp10
  (JNIEnv *env, jclass ExpFunction, jdouble x) {
	gsl_sf_result_e10_struct gsl_result = {0.0, 0.0} ;
	gsl_sf_exp_e10_e(x, &gsl_result) ;
	return gsl_result.val ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    expMult
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_expMult
  (JNIEnv *env, jclass ExpFunction, jdouble x, jdouble y) {
	return gsl_sf_exp_mult(x, y) ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    multE10
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_multE10
  (JNIEnv *env, jclass ExpFunction, jdouble x, jdouble y) {
	gsl_sf_result_e10 result = {0.0, 0.0} ;
	gsl_sf_exp_mult_e10_e(x, y, &result) ;
	return (&result) -> val ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    expm1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_expm1
  (JNIEnv *env, jclass ExpFunction, jdouble x) {
	return gsl_sf_expm1(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    exprel
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_exprel
  (JNIEnv *env, jclass ExpFunction, jdouble x) {
	return gsl_sf_exprel(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    exprel2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_exprel2
  (JNIEnv *env, jclass ExpFunction, jdouble x) {
	return gsl_sf_exprel_2(x) ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    exprelN
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_ExpFunction_exprelN
  (JNIEnv *env, jclass ExpFunction, jint n, jdouble x) {
	return gsl_sf_exprel_n(n, x) ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    expErr
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_ExpFunction_expErr
  (JNIEnv *env, jclass ExpFunction, jdouble x, jdouble dx) {
	gsl_sf_result gsl_result ;
	gsl_sf_exp_err_e(x, dx, &gsl_result) ;
	jdouble buffer[2] = {gsl_result.val, gsl_result.err} ;
	jdoubleArray result = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(result, 0, 2, buffer) ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    expErrE10
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_ExpFunction_expErrE10
  (JNIEnv *env, jclass ExpFunction, jdouble x, jdouble dx) {
	gsl_sf_result_e10 gsl_result ;
	gsl_sf_exp_err_e10_e(x, dx, &gsl_result) ;
	jdouble buffer[2] = {gsl_result.val, gsl_result.err} ;
	jdoubleArray result = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(result, 0, 2, buffer) ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    expMultErr
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_ExpFunction_expMultErr
  (JNIEnv *env, jclass ExpFunction, jdouble x, jdouble dx, jdouble y, jdouble dy) {
	gsl_sf_result gsl_result ;
	gsl_sf_exp_mult_err_e(x, dx, y, dy, &gsl_result) ;
	jdouble buffer[2] = {gsl_result.val, gsl_result.err} ;
	jdoubleArray result = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(result, 0, 2, buffer) ;
	return result ;
}

/*
 * Class:     org_gsl4j_special_ExpFunction
 * Method:    expMultErrE10
 * Signature: (DDDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_ExpFunction_expMultErrE10
  (JNIEnv *env, jclass ExpFunction, jdouble x, jdouble dx, jdouble y, jdouble dy) {
	gsl_sf_result_e10 gsl_result ;
	gsl_sf_exp_mult_err_e10_e(x, dx, y, dy, &gsl_result) ;
	jdouble buffer[2] = {gsl_result.val, gsl_result.err} ;
	jdoubleArray result = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(result, 0, 2, buffer) ;
	return result ;
}


