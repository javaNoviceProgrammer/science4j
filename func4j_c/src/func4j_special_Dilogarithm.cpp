/*
 * func4j_special_Dilogarithm.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Dilogarithm.h"

#include <gsl/gsl_sf_dilog.h>


/*
 * Class:     func4j_special_Dilogarithm
 * Method:    dilog
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Dilogarithm_dilog__D
  (JNIEnv *env, jclass dilogarithm, jdouble x) {
	return gsl_sf_dilog(x) ;
}


/*
 * Class:     func4j_special_Dilogarithm
 * Method:    dilog
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Dilogarithm_dilog__DD
  (JNIEnv *env, jclass dilogarithm, jdouble x, jdouble y) {
	gsl_sf_result re = {0.0, 0.0} ;
	gsl_sf_result im = {0.0, 0.0} ;
	gsl_sf_complex_dilog_xy_e(x, y, &re, &im) ;
	jdouble result_re = re.val ;
	jdouble result_im = im.val ;
	jdoubleArray result = env -> NewDoubleArray(2) ;
	double buf[2] = {result_re, result_im} ;
	env -> SetDoubleArrayRegion(result, 0, 2, buf) ;
	return result ;
}


/*
 * Class:     func4j_special_Dilogarithm
 * Method:    dilogPolar
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Dilogarithm_dilogPolar
  (JNIEnv *env, jclass dilogarithm, jdouble r, jdouble theta) {
	gsl_sf_result re = {0.0, 0.0} ;
	gsl_sf_result im = {0.0, 0.0} ;
	gsl_sf_complex_dilog_e(r, theta, &re, &im) ;
	jdouble result_re = re.val ;
	jdouble result_im = im.val ;
	jdoubleArray result = env -> NewDoubleArray(2) ;
	double buf[2] = {result_re, result_im} ;
	env -> SetDoubleArrayRegion(result, 0, 2, buf) ;
	return result ;
}



