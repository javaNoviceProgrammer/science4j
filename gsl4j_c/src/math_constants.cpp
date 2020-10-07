/*
 * math_constants.cpp
 *
 *  Created on: Jun 2, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_MathConstants.h"
#include <gsl/gsl_math.h>

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    me
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_me
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_E ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mlog2e
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mlog2e
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_LOG2E ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mlog10e
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mlog10e
(JNIEnv *env, jclass mathConstantsClass) {
	return M_LOG10E ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    msqrt2
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_msqrt2
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_SQRT2 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    msqrt12
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_msqrt12
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_SQRT1_2 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    msqrt3
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_msqrt3
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_SQRT3 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mpi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mpi
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_PI ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mpi2
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mpi2
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_PI_2 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mpi4
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mpi4
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_PI_4 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    msqrtpi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_msqrtpi
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_SQRTPI ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    m2sqrtpi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_m2sqrtpi
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_2_SQRTPI ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    m1pi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_m1pi
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_1_PI ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    m2pi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_m2pi
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_2_PI ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mln10
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mln10
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_LN10 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mln2
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mln2
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_LN2 ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    mlnpi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_mlnpi
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_LNPI ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    meuler
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_meuler
  (JNIEnv *env, jclass mathConstantsClass) {
	return M_EULER ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    gslposinf
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_gslposinf
  (JNIEnv *env, jclass mathConstantsClass) {
	return GSL_POSINF ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    gslneginf
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_gslneginf
  (JNIEnv *env, jclass mathConstantsClass) {
	return GSL_NEGINF ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    gslnan
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_MathConstants_gslnan
  (JNIEnv *env, jclass mathConstantsClass) {
	return GSL_NAN ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    isNaN
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_MathConstants_isNaN
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isnan(x) ;
	if(value==1)
		return true ;
	else
		return false ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    isInf
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_MathConstants_isInf
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isinf(x) ;
	if(value==0)
		return false ;
	else
		return true ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    isPositiveInf
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_MathConstants_isPositiveInf
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isinf(x) ;
	if(value==1)
		return true ;
	else
		return false ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    isNegativeInf
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_MathConstants_isNegativeInf
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isinf(x) ;
	if(value==-1)
		return true ;
	else
		return false ;
}

/*
 * Class:     org_gsl4j_MathConstants
 * Method:    isFinite
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_MathConstants_isFinite
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	return gsl_finite(x) ;
}



