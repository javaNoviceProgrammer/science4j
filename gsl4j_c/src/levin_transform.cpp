/*
 * levin_transform.cpp
 *
 *  Created on: Jun 14, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sum.h>
#include "../headers/org_gsl4j_sequence_LevinTransform.h"


/*
 * Class:     org_gsl4j_sequence_LevinTransform
 * Method:    sumLevinUaccel
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_sequence_LevinTransform_sumLevinUaccel
  (JNIEnv *env, jclass LevinTransform, jdoubleArray jarray) {
	jint len = env -> GetArrayLength(jarray) ;
	jdouble *array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jarray, 0, len, array) ;
	gsl_sum_levin_u_workspace *w = gsl_sum_levin_u_alloc(len) ;
	jdouble sum_accel, abserr ;
	gsl_sum_levin_u_accel(array, len, w, &sum_accel, &abserr) ;
	delete[] array ;
	jdouble buffer[2] = {sum_accel, abserr} ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	gsl_sum_levin_u_free(w) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_sequence_LevinTransform
 * Method:    sumLevinUtruncAccel
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_sequence_LevinTransform_sumLevinUtruncAccel
  (JNIEnv *env, jclass LevinTransform, jdoubleArray jarray) {
	jint len = env -> GetArrayLength(jarray) ;
	jdouble *array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jarray, 0, len, array) ;
	gsl_sum_levin_utrunc_workspace *w = gsl_sum_levin_utrunc_alloc(len) ;
	jdouble sum_accel, abserr_trunc ;
	gsl_sum_levin_utrunc_accel(array, len, w, &sum_accel, &abserr_trunc) ;
	delete[] array ;
	jdouble buffer[2] = {sum_accel, abserr_trunc} ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	gsl_sum_levin_utrunc_free(w) ;
	return jresult ;
}

