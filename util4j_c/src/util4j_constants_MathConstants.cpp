/*
 * util4j_constants_MathConstants.cpp
 *
 *  Created on: Jan 14, 2021
 *      Author: meisam
 */

#include "../headers/util4j_constants_MathConstants.h"

#include <gsl/gsl_math.h>


/*
 * Class:     util4j_constants_MathConstants
 * Method:    gslposinf
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_util4j_constants_MathConstants_gslposinf
  (JNIEnv *env, jclass mathConstantsClass) {
	return GSL_POSINF ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    gslneginf
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_util4j_constants_MathConstants_gslneginf
  (JNIEnv *env, jclass mathConstantsClass) {
	return GSL_NEGINF ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    gslnan
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_util4j_constants_MathConstants_gslnan
  (JNIEnv *env, jclass mathConstantsClass) {
	return GSL_NAN ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    isNaN
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_util4j_constants_MathConstants_isNaN
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isnan(x) ;
	if(value==1)
		return true ;
	else
		return false ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    isInf
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_util4j_constants_MathConstants_isInf
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isinf(x) ;
	if(value==0)
		return false ;
	else
		return true ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    isPositiveInf
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_util4j_constants_MathConstants_isPositiveInf
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isinf(x) ;
	if(value==1)
		return true ;
	else
		return false ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    isNegativeInf
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_util4j_constants_MathConstants_isNegativeInf
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	jint value = gsl_isinf(x) ;
	if(value==-1)
		return true ;
	else
		return false ;
}

/*
 * Class:     util4j_constants_MathConstants
 * Method:    isFinite
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_util4j_constants_MathConstants_isFinite
  (JNIEnv *env, jclass mathConstantsClass, jdouble x) {
	return gsl_finite(x) ;
}
