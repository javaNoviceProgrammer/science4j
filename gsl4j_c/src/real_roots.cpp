/*
 * real_roots.cpp
 *
 *  Created on: Jun 7, 2020
 *      Author: meisam
 */

#include <gsl/gsl_roots.h>
#include <gsl/gsl_errno.h>
#include "../headers/org_gsl4j_roots_RealRoot.h"


jclass realroot1d_func_class ;
jclass realroot1d_derivfunc_class ;
jmethodID realroot1d_func_value_id ;
jmethodID realroot1d_derivfunc_value_id ;
jfieldID realroot1d_func_id ;
jfieldID realroot1d_derivfunc_id ;
jfieldID realroot1d_abserr_id ;
jfieldID realroot1d_relerr_id ;
jfieldID max_num_iterations_id ;

struct jparams {
	JNIEnv *env ;
	jobject RealRoot ;
};

jdouble f_realroot1d (jdouble x, void *params) {
	jparams jp = *(jparams *) params ;
	jobject realroot1d_func_obj = jp.env -> GetObjectField(jp.RealRoot, realroot1d_func_id) ;
	return jp.env -> CallDoubleMethod(realroot1d_func_obj, realroot1d_func_value_id, x) ;
}

jdouble f_deriv_realroot1d (jdouble x, void *params) {
	jparams jp = *(jparams *) params ;
	jobject realroot1d_derivfunc_obj = jp.env -> GetObjectField(jp.RealRoot, realroot1d_derivfunc_id) ;
	jobject val_result = jp.env -> CallObjectMethod(realroot1d_derivfunc_obj, realroot1d_derivfunc_value_id, x) ;
	jdoubleArray val_array = (jdoubleArray)val_result ;
	jdouble buf[2] ;
	jp.env -> GetDoubleArrayRegion(val_array, 0, 2, buf) ;
	return buf[0] ;
}

jdouble df_deriv_realroot1d (jdouble x, void *params) {
	jparams jp = *(jparams *) params ;
	jobject realroot1d_derivfunc_obj = jp.env -> GetObjectField(jp.RealRoot, realroot1d_derivfunc_id) ;
	jobject val_result = jp.env -> CallObjectMethod(realroot1d_derivfunc_obj, realroot1d_derivfunc_value_id, x) ;
	jdoubleArray val_array = (jdoubleArray)val_result ;
	jdouble buf[2] ;
	jp.env -> GetDoubleArrayRegion(val_array, 0, 2, buf) ;
	return buf[1] ;
}

void fdf_deriv_realroot1d (jdouble x, void *params, double *f, double *df) {
	jparams jp = *(jparams *) params ;
	jobject realroot1d_derivfunc_obj = jp.env -> GetObjectField(jp.RealRoot, realroot1d_derivfunc_id) ;
	jobject val_result = jp.env -> CallObjectMethod(realroot1d_derivfunc_obj, realroot1d_derivfunc_value_id, x) ;
	jdoubleArray val_array = (jdoubleArray)val_result ;
	jdouble buf[2] ;
	jp.env -> GetDoubleArrayRegion(val_array, 0, 2, buf) ;
	*f = buf[0] ;
	*df = buf[1] ;
}


/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    initFieldIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_roots_RealRoot_initFieldIDs
  (JNIEnv *env, jclass RealRoot) {
	realroot1d_func_class = env -> FindClass("org/gsl4j/roots/RealRootFunction") ;
	realroot1d_derivfunc_class = env -> FindClass("org/gsl4j/roots/RealRootDerivFunction") ;
	realroot1d_func_value_id = env -> GetMethodID(realroot1d_func_class, "value", "(D)D") ;
	realroot1d_derivfunc_value_id = env -> GetMethodID(realroot1d_derivfunc_class, "value", "(D)[D") ;
	realroot1d_func_id = env -> GetFieldID(RealRoot, "func", "Lorg/gsl4j/roots/RealRootFunction;") ;
	realroot1d_derivfunc_id = env -> GetFieldID(RealRoot, "derivFunc", "Lorg/gsl4j/roots/RealRootDerivFunction;") ;
	realroot1d_abserr_id = env -> GetFieldID(RealRoot, "absErr", "D") ;
	realroot1d_relerr_id = env -> GetFieldID(RealRoot, "relErr", "D") ;
	max_num_iterations_id = env -> GetFieldID(RealRoot, "maxNumberOfIterations", "I") ;
}

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    bisection
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_bisection
  (JNIEnv *env, jobject RealRoot, jdouble a, jdouble b) {
	jdouble epsabs = env -> GetDoubleField(RealRoot, realroot1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(RealRoot, realroot1d_relerr_id) ;
	jint max_num_iterations = env -> GetIntField(RealRoot, max_num_iterations_id) ;
	size_t max_iter = (size_t) max_num_iterations ;
	jint iter = 0, status ;
	jdouble x_lo, x_hi, result ;
	jparams p = {env, RealRoot} ;
	gsl_function F ;
	F.function = &f_realroot1d ;
	F.params = &p ;
	gsl_root_fsolver *s = gsl_root_fsolver_alloc(gsl_root_fsolver_bisection);
	gsl_root_fsolver_set(s, &F, a, b) ;
	do {
		iter++ ;
		status = gsl_root_fsolver_iterate(s);
		result = gsl_root_fsolver_root(s);
		x_lo = gsl_root_fsolver_x_lower(s);
		x_hi = gsl_root_fsolver_x_upper (s);
		status = gsl_root_test_interval(x_lo, x_hi, epsabs, epsrel);
	}
	while(status == GSL_CONTINUE && iter < max_iter) ;
	gsl_root_fsolver_free(s);
	return result ;
}

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    falsepos
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_falsepos
  (JNIEnv *env, jobject RealRoot, jdouble a, jdouble b) {
	jdouble epsabs = env -> GetDoubleField(RealRoot, realroot1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(RealRoot, realroot1d_relerr_id) ;
	jint max_num_iterations = env -> GetIntField(RealRoot, max_num_iterations_id) ;
	size_t max_iter = (size_t) max_num_iterations ;
	jint iter = 0, status ;
	jdouble x_lo, x_hi, result ;
	jparams p = {env, RealRoot} ;
	gsl_function F ;
	F.function = &f_realroot1d ;
	F.params = &p ;
	gsl_root_fsolver *s = gsl_root_fsolver_alloc(gsl_root_fsolver_falsepos);
	gsl_root_fsolver_set(s, &F, a, b) ;
	do {
		iter++ ;
		status = gsl_root_fsolver_iterate(s);
		result = gsl_root_fsolver_root(s);
		x_lo = gsl_root_fsolver_x_lower(s);
		x_hi = gsl_root_fsolver_x_upper (s);
		status = gsl_root_test_interval(x_lo, x_hi, epsabs, epsrel);
	}
	while(status == GSL_CONTINUE && iter < max_iter) ;
	gsl_root_fsolver_free(s);
	return result ;
}

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    brent
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_brent
  (JNIEnv *env, jobject RealRoot, jdouble a, jdouble b) {
	jdouble epsabs = env -> GetDoubleField(RealRoot, realroot1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(RealRoot, realroot1d_relerr_id) ;
	jint max_num_iterations = env -> GetIntField(RealRoot, max_num_iterations_id) ;
	size_t max_iter = (size_t) max_num_iterations ;
	jint iter = 0, status ;
	jdouble x_lo, x_hi, result ;
	jparams p = {env, RealRoot} ;
	gsl_function F ;
	F.function = &f_realroot1d ;
	F.params = &p ;
	gsl_root_fsolver *s = gsl_root_fsolver_alloc(gsl_root_fsolver_brent);
	gsl_root_fsolver_set(s, &F, a, b) ;
	do {
		iter++ ;
		status = gsl_root_fsolver_iterate(s);
		result = gsl_root_fsolver_root(s);
		x_lo = gsl_root_fsolver_x_lower(s);
		x_hi = gsl_root_fsolver_x_upper (s);
		status = gsl_root_test_interval(x_lo, x_hi, epsabs, epsrel);
	}
	while(status == GSL_CONTINUE && iter < max_iter) ;
	gsl_root_fsolver_free(s);
	return result ;
}

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    newton
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_newton
  (JNIEnv *env, jobject RealRoot, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(RealRoot, realroot1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(RealRoot, realroot1d_relerr_id) ;
	jint max_num_iterations = env -> GetIntField(RealRoot, max_num_iterations_id) ;
	size_t max_iter = (size_t) max_num_iterations ;
	jint iter = 0, status ;
	jdouble x0 ;
	jparams p = {env, RealRoot} ;
	gsl_function_fdf F ;
	F.f = &f_deriv_realroot1d ;
	F.df = &df_deriv_realroot1d ;
	F.fdf = &fdf_deriv_realroot1d ;
	F.params = &p ;
	gsl_root_fdfsolver *s = gsl_root_fdfsolver_alloc(gsl_root_fdfsolver_newton);
	gsl_root_fdfsolver_set(s, &F, x) ;
	do {
		iter++ ;
		status = gsl_root_fdfsolver_iterate(s);
		x0 = x ;
		x = gsl_root_fdfsolver_root(s);
		status = gsl_root_test_delta(x, x0, epsabs, epsrel);
	}
	while(status == GSL_CONTINUE && iter < max_iter) ;
	gsl_root_fdfsolver_free(s);
	return x ;
}

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    secant
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_secant
  (JNIEnv *env, jobject RealRoot, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(RealRoot, realroot1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(RealRoot, realroot1d_relerr_id) ;
	jint max_num_iterations = env -> GetIntField(RealRoot, max_num_iterations_id) ;
	size_t max_iter = (size_t) max_num_iterations ;
	jint iter = 0, status ;
	jdouble x0 ;
	jparams p = {env, RealRoot} ;
	gsl_function_fdf F ;
	F.f = &f_deriv_realroot1d ;
	F.df = &df_deriv_realroot1d ;
	F.fdf = &fdf_deriv_realroot1d ;
	F.params = &p ;
	gsl_root_fdfsolver *s = gsl_root_fdfsolver_alloc(gsl_root_fdfsolver_secant);
	gsl_root_fdfsolver_set(s, &F, x) ;
	do {
		iter++ ;
		status = gsl_root_fdfsolver_iterate(s);
		x0 = x ;
		x = gsl_root_fdfsolver_root(s);
		status = gsl_root_test_delta(x, x0, epsabs, epsrel);
	}
	while(status == GSL_CONTINUE && iter < max_iter) ;
	gsl_root_fdfsolver_free(s);
	return x ;
}

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    steffenson
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_steffenson
  (JNIEnv *env, jobject RealRoot, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(RealRoot, realroot1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(RealRoot, realroot1d_relerr_id) ;
	jint max_num_iterations = env -> GetIntField(RealRoot, max_num_iterations_id) ;
	size_t max_iter = (size_t) max_num_iterations ;
	jint iter = 0, status ;
	jdouble x0 ;
	jparams p = {env, RealRoot} ;
	gsl_function_fdf F ;
	F.f = &f_deriv_realroot1d ;
	F.df = &df_deriv_realroot1d ;
	F.fdf = &fdf_deriv_realroot1d ;
	F.params = &p ;
	gsl_root_fdfsolver *s = gsl_root_fdfsolver_alloc(gsl_root_fdfsolver_steffenson);
	gsl_root_fdfsolver_set(s, &F, x) ;
	do {
		iter++ ;
		status = gsl_root_fdfsolver_iterate(s);
		x0 = x ;
		x = gsl_root_fdfsolver_root(s);
		status = gsl_root_test_delta(x, x0, epsabs, epsrel);
	}
	while(status == GSL_CONTINUE && iter < max_iter) ;
	gsl_root_fdfsolver_free(s);
	return x ;
}


