/*
 * seq4j_LevinTransform.cpp
 *
 *  Created on: Jan 26, 2021
 *      Author: meisam
 */

#include "../headers/seq4j_LevinTransform.h"

#include <gsl/gsl_sum.h>


///*
// * Class:     seq4j_LevinTransform
// * Method:    sumLevinUaccel
// * Signature: ([D)[D
// */
//JNIEXPORT jdoubleArray JNICALL Java_seq4j_LevinTransform_sumLevinUaccel
//  (JNIEnv *env, jclass LevinTransform, jdoubleArray jarray) {
//	jint len = env -> GetArrayLength(jarray) ;
//	jdouble *array = new jdouble[len] ;
//	env -> GetDoubleArrayRegion(jarray, 0, len, array) ;
//	gsl_sum_levin_u_workspace *w = gsl_sum_levin_u_alloc(len) ;
//	jdouble sum_accel, abserr ;
//	gsl_sum_levin_u_accel(array, len, w, &sum_accel, &abserr) ;
//	delete[] array ;
//	jdouble buffer[2] = {sum_accel, abserr} ;
//	jdoubleArray jresult = env -> NewDoubleArray(2) ;
//	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
//	gsl_sum_levin_u_free(w) ;
//	return jresult ;
//}


/*
 * Class:     seq4j_LevinTransform
 * Method:    sumLevinUaccel
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_seq4j_LevinTransform_sumLevinUaccel
  (JNIEnv *jvm, jclass LevinTransform, jdoubleArray jarray) {
	jint len = jvm -> GetArrayLength(jarray) ;
	jdouble *array = (jdouble*) jvm -> GetPrimitiveArrayCritical(jarray, nullptr) ;
	gsl_sum_levin_u_workspace* w = gsl_sum_levin_u_alloc(len) ;
	jdouble result[2] ; // {sum_accel, abserr} ;
	gsl_sum_levin_u_accel(array, len, w, result, result+1) ;
	gsl_sum_levin_u_free(w) ;
	jvm -> ReleasePrimitiveArrayCritical(jarray, array, 0) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, result) ;
	return jresult ;
}



///*
// * Class:     seq4j_LevinTransform
// * Method:    sumLevinUtruncAccel
// * Signature: ([D)[D
// */
//JNIEXPORT jdoubleArray JNICALL Java_seq4j_LevinTransform_sumLevinUtruncAccel
//  (JNIEnv *env, jclass LevinTransform, jdoubleArray jarray) {
//	jint len = env -> GetArrayLength(jarray) ;
//	jdouble *array = new jdouble[len] ;
//	env -> GetDoubleArrayRegion(jarray, 0, len, array) ;
//	gsl_sum_levin_utrunc_workspace *w = gsl_sum_levin_utrunc_alloc(len) ;
//	jdouble sum_accel, abserr_trunc ;
//	gsl_sum_levin_utrunc_accel(array, len, w, &sum_accel, &abserr_trunc) ;
//	delete[] array ;
//	jdouble buffer[2] = {sum_accel, abserr_trunc} ;
//	jdoubleArray jresult = env -> NewDoubleArray(2) ;
//	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
//	gsl_sum_levin_utrunc_free(w) ;
//	return jresult ;
//}

/*
 * Class:     seq4j_LevinTransform
 * Method:    sumLevinUtruncAccel
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_seq4j_LevinTransform_sumLevinUtruncAccel
  (JNIEnv *jvm, jclass LevinTransform, jdoubleArray jarray) {
	jint len = jvm -> GetArrayLength(jarray) ;
	jdouble *array = (jdouble*) jvm -> GetPrimitiveArrayCritical(jarray, nullptr) ;
	gsl_sum_levin_utrunc_workspace* w = gsl_sum_levin_utrunc_alloc(len) ;
	jdouble result[2] ; // {sum_accel, abserr} ;
	gsl_sum_levin_utrunc_accel(array, len, w, result, result+1) ;
	gsl_sum_levin_utrunc_free(w) ;
	jvm -> ReleasePrimitiveArrayCritical(jarray, array, 0) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, result) ;
	return jresult ;
}

