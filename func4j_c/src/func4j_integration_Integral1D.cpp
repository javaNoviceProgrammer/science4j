/*
 * func4j_integration_Integral1D.cpp
 *
 *  Created on: Jan 11, 2021
 *      Author: meisam
 */

#include "../headers/func4j_integration_Integral1D.h"

#include <gsl/gsl_integration.h>

namespace { // create an anonymous namespace (global)

	jclass integral1d_func_class ;
	jmethodID integral1d_func_value_id ;
	jfieldID integral1d_func_id ;
	jfieldID abserr_id ;
	jfieldID relerr_id ;
	jfieldID max_num_interval_id ;

	struct jparams {
		JNIEnv* jvm ;
		jobject integral_func_1d_obj ;
	};

	jdouble f (jdouble x, void *params) {
		jparams jp = *((jparams*) params) ;
		return jp.jvm -> CallDoubleMethod(jp.integral_func_1d_obj, integral1d_func_value_id, x) ;
	}

}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    initFieldIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_func4j_integration_Integral1D_initFieldIDs
  (JNIEnv *jvm, jclass Integral1D_class) {
	integral1d_func_class = jvm -> FindClass("func4j/integration/IntegralFunction1D") ;
	integral1d_func_value_id = jvm -> GetMethodID(integral1d_func_class, "value", "(D)D") ;
	integral1d_func_id = jvm -> GetFieldID(Integral1D_class, "func", "Lfunc4j/integration/IntegralFunction1D;") ;
	abserr_id = jvm -> GetFieldID(Integral1D_class, "absErr", "D") ;
	relerr_id = jvm -> GetFieldID(Integral1D_class, "relErr", "D") ;
	max_num_interval_id = jvm -> GetFieldID(Integral1D_class, "maxNumberOfIntervals", "I") ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qng
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qng
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jdouble result {} ;
	jdouble abserr {} ;
	size_t neval {} ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	jparams p {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_qng(&F, a, b, epsabs, epsrel, &result, &abserr, &neval) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qngDetailed
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qngDetailed
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jdouble result {} ;
	jdouble abserr {} ;
	size_t neval {} ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	jparams p {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_qng(&F, a, b, epsabs, epsrel, &result, &abserr, &neval) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(3) ;
	jdouble buf[] = {result, abserr, (jdouble)neval} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 3, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss15
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagGauss15
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 1, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss15withError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagGauss15withError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 1, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss21
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagGauss21
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 2, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss21withError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagGauss21withError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 2, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss31
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagGauss31
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 3, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss31withError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagGauss31withError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 3, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss41
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagGauss41
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 4, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss41withError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagGauss41withError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 4, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss51
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagGauss51
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 5, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss51withError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagGauss51withError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 5, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}


/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss61
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagGauss61
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 6, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagGauss61withError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagGauss61withError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qag(&F, a, b, epsabs, epsrel, limit, 6, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}


/*
 * Class:     func4j_integration_Integral1D
 * Method:    qags
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qags
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qags(&F, a, b, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagsWithError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagsWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qags(&F, a, b, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagp
 * Signature: (DD[D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagp
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jdoubleArray poitns) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	jint npts = jvm -> GetArrayLength(poitns) ;
	jdouble temp[npts] ;
	jdouble pts[npts+2] ;
	jvm -> GetDoubleArrayRegion(poitns, 0, npts, temp) ;
	pts[0] = a ;
	for(int i=0; i<npts; i++)
		pts[i+1] = temp[i] ;
	pts[npts+1] = b ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagp(&F, pts, npts+2, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagpWithError
 * Signature: (DD[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagpWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jdoubleArray poitns) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	jint npts = jvm -> GetArrayLength(poitns) ;
	jdouble temp[npts] ;
	jdouble pts[npts+2] ;
	jvm -> GetDoubleArrayRegion(poitns, 0, npts, temp) ;
	pts[0] = a ;
	for(int i=0; i<npts; i++)
		pts[i+1] = temp[i] ;
	pts[npts+1] = b ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagp(&F, pts, npts+2, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagi
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagi
  (JNIEnv *jvm, jobject Integral1D_obj) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagi(&F, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagiWithError
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagiWithError
  (JNIEnv *jvm, jobject Integral1D_obj) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagi(&F, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagiu
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagiu
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagiu(&F, a, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagiuWithError
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagiuWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagiu(&F, a, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagil
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qagil
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagil(&F, b, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qagilWithError
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qagilWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qagil(&F, b, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawc
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qawc
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jdouble c) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qawc(&F, a, b, c, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawcWithError
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qawcWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jdouble c) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qawc(&F, a, b, c, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qaws
 * Signature: (DDIDD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qaws
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint type, jdouble alpha, jdouble beta) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	jint mu, nu ;
	if(type == func4j_integration_Integral1D_WEIGHT_FUNC_TYPE_I) {
		mu = 0 ;
		nu = 0 ;
	}
	else if(type == func4j_integration_Integral1D_WEIGHT_FUNC_TYPE_II) {
		mu = 1 ;
		nu = 0 ;
	}
	else if(type == func4j_integration_Integral1D_WEIGHT_FUNC_TYPE_III) {
		mu = 0 ;
		nu = 1 ;
	}
	else {
		mu = 1 ;
		nu = 1 ;
	}
	gsl_integration_qaws_table *table = gsl_integration_qaws_table_alloc(alpha, beta, mu, nu) ;
	gsl_integration_qaws(&F, a, b, table, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	gsl_integration_qaws_table_free(table) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawsWithError
 * Signature: (DDIDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qawsWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint type, jdouble alpha, jdouble beta) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	jint mu, nu ;
	if(type == func4j_integration_Integral1D_WEIGHT_FUNC_TYPE_I) {
		mu = 0 ;
		nu = 0 ;
	}
	else if(type == func4j_integration_Integral1D_WEIGHT_FUNC_TYPE_II) {
		mu = 1 ;
		nu = 0 ;
	}
	else if(type == func4j_integration_Integral1D_WEIGHT_FUNC_TYPE_III) {
		mu = 0 ;
		nu = 1 ;
	}
	else {
		mu = 1 ;
		nu = 1 ;
	}
	gsl_integration_qaws_table *table = gsl_integration_qaws_table_alloc(alpha, beta, mu, nu) ;
	gsl_integration_qaws(&F, a, b, table, epsabs, epsrel, limit, w, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	gsl_integration_qaws_table_free(table) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawo
 * Signature: (DDID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qawo
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint choice, jdouble omega) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qawo_enum choice_enum ;
	if(choice == func4j_integration_Integral1D_GSL_INTEG_SINE) {
		choice_enum = GSL_INTEG_SINE ;
	}
	else {
		choice_enum = GSL_INTEG_COSINE ;
	}
	gsl_integration_qawo_table *table = gsl_integration_qawo_table_alloc(omega, b-a, choice_enum, limit) ;
	gsl_integration_qawo(&F, a, epsabs, epsrel, limit, w, table, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	gsl_integration_qawo_table_free(table) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawoWithError
 * Signature: (DDID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qawoWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint choice, jdouble omega) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qawo_enum choice_enum ;
	if(choice == func4j_integration_Integral1D_GSL_INTEG_SINE) {
		choice_enum = GSL_INTEG_SINE ;
	}
	else {
		choice_enum = GSL_INTEG_COSINE ;
	}
	gsl_integration_qawo_table *table = gsl_integration_qawo_table_alloc(omega, b-a, choice_enum, limit) ;
	gsl_integration_qawo(&F, a, epsabs, epsrel, limit, w, table, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	gsl_integration_qawo_table_free(table) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawf
 * Signature: (DID)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qawf
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jint choice, jdouble omega) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_workspace *cycle_w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qawo_enum choice_enum ;
	if(choice == func4j_integration_Integral1D_GSL_INTEG_SINE) {
		choice_enum = GSL_INTEG_SINE ;
	}
	else {
		choice_enum = GSL_INTEG_COSINE ;
	}
	gsl_integration_qawo_table *table = gsl_integration_qawo_table_alloc(omega, a, choice_enum, limit) ;
	gsl_integration_qawf(&F, a, epsabs, limit, w, cycle_w, table, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	gsl_integration_workspace_free(cycle_w) ;
	gsl_integration_qawo_table_free(table) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qawfWithError
 * Signature: (DID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qawfWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jint choice, jdouble omega) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_workspace *w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_workspace *cycle_w = gsl_integration_workspace_alloc(limit) ;
	gsl_integration_qawo_enum choice_enum ;
	if(choice == func4j_integration_Integral1D_GSL_INTEG_SINE) {
		choice_enum = GSL_INTEG_SINE ;
	}
	else {
		choice_enum = GSL_INTEG_COSINE ;
	}
	gsl_integration_qawo_table *table = gsl_integration_qawo_table_alloc(omega, a, choice_enum, limit) ;
	gsl_integration_qawf(&F, a, epsabs, limit, w, cycle_w, table, &result, &abserr) ;
	gsl_integration_workspace_free(w) ;
	gsl_integration_workspace_free(cycle_w) ;
	gsl_integration_qawo_table_free(table) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, abserr} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    cquad
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_cquad
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	size_t nevals ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_cquad_workspace *w = gsl_integration_cquad_workspace_alloc(limit) ;
	gsl_integration_cquad(&F, a, b, epsabs, epsrel, w, &result, &abserr, &nevals) ;
	gsl_integration_cquad_workspace_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    cquadWithError
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_cquadWithError
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	jdouble result ;
	jdouble abserr ;
	size_t nevals ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_cquad_workspace *w = gsl_integration_cquad_workspace_alloc(limit) ;
	gsl_integration_cquad(&F, a, b, epsabs, epsrel, w, &result, &abserr, &nevals) ;
	gsl_integration_cquad_workspace_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(3) ;
	jdouble buf[] = {result, abserr, (jdouble)nevals} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 3, buf) ;
	return jresult ;
}


/*
 * Class:     func4j_integration_Integral1D
 * Method:    romberg
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_romberg
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	if(limit>20)
		limit = 20 ;
	jdouble result ;
	size_t nevals ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_romberg_workspace *w = gsl_integration_romberg_alloc(limit) ;
	gsl_integration_romberg(&F, a, b, epsabs, epsrel, &result, &nevals, w) ;
	gsl_integration_romberg_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    rombergDetailed
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_rombergDetailed
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b) {
	jdouble epsabs = jvm -> GetDoubleField(Integral1D_obj, abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Integral1D_obj, relerr_id) ;
	jint max_num_intervals = jvm -> GetIntField(Integral1D_obj, max_num_interval_id) ;
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) max_num_intervals ;
	if(limit>20)
		limit = 20 ;
	jdouble result ;
	size_t nevals ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_romberg_workspace *w = gsl_integration_romberg_alloc(limit) ;
	gsl_integration_romberg(&F, a, b, epsabs, epsrel, &result, &nevals, w) ;
	gsl_integration_romberg_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {result, (jdouble)nevals} ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}



/*
 * Class:     func4j_integration_Integral1D
 * Method:    glfixed
 * Signature: (DDI)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_glfixed
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint numPoints) {
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) numPoints ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_glfixed_table *table = gsl_integration_glfixed_table_alloc(limit) ;
	jdouble result = gsl_integration_glfixed(&F, a, b, table) ;
	gsl_integration_glfixed_table_free(table) ;
	return result ;
}


/*
 * Class:     func4j_integration_Integral1D
 * Method:    glfixedPointAndWeight
 * Signature: (DDII)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_glfixedPointAndWeight
  (JNIEnv *env, jobject Integral1D, jdouble a, jdouble b, jint numPoints, jint jindex) {
	size_t limit = (size_t) numPoints ;
	size_t index = (size_t) jindex ;
	gsl_integration_glfixed_table *table = gsl_integration_glfixed_table_alloc(limit) ;
	jdouble xi, wi ;
	gsl_integration_glfixed_point(a, b, index, &xi, &wi, table) ;
	gsl_integration_glfixed_table_free(table) ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	jdouble buf[] = {xi, wi} ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buf) ;
	return jresult ;
}


/*
 * Class:     func4j_integration_Integral1D
 * Method:    qfixed
 * Signature: (DDIIDD)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_integration_Integral1D_qfixed
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint numPoints, jint jtype, jdouble alpha, jdouble beta) {
	jobject integral_func_1d_obj = jvm -> GetObjectField(Integral1D_obj, integral1d_func_id) ;
	size_t limit = (size_t) numPoints ;
	jdouble result ;
	jparams p = {jvm, integral_func_1d_obj} ;
	gsl_function F ;
	F.function = &f ;
	F.params = &p ;
	gsl_integration_fixed_workspace *w ;
	if(jtype == func4j_integration_Integral1D_FIXED_LEGENDRE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_legendre, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_CHEBYSHEV)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_chebyshev, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_GEGENBAUER)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_gegenbauer, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_JACOBI)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_jacobi, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_LAGUERRE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_laguerre, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_HERMITE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_hermite, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_EXPONENTIAL)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_exponential, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_RATIONAL)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_rational, limit, a, b, alpha, beta) ;
	else
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_chebyshev2, limit, a, b, alpha, beta) ;
	gsl_integration_fixed(&F, &result, w) ;
	gsl_integration_fixed_free(w) ;
	return result ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qfixedPoints
 * Signature: (DDIIDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qfixedPoints
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint numPoints, jint jtype, jdouble alpha, jdouble beta) {
	size_t limit = (size_t) numPoints ;
	jdouble* result ;
	gsl_integration_fixed_workspace *w ;
	if(jtype == func4j_integration_Integral1D_FIXED_LEGENDRE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_legendre, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_CHEBYSHEV)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_chebyshev, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_GEGENBAUER)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_gegenbauer, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_JACOBI)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_jacobi, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_LAGUERRE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_laguerre, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_HERMITE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_hermite, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_EXPONENTIAL)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_exponential, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_RATIONAL)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_rational, limit, a, b, alpha, beta) ;
	else
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_chebyshev2, limit, a, b, alpha, beta) ;
	result = gsl_integration_fixed_nodes(w) ;
	gsl_integration_fixed_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(numPoints) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, numPoints, result) ;
	return jresult ;
}

/*
 * Class:     func4j_integration_Integral1D
 * Method:    qfixedWeights
 * Signature: (DDIIDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_integration_Integral1D_qfixedWeights
  (JNIEnv *jvm, jobject Integral1D_obj, jdouble a, jdouble b, jint numPoints, jint jtype, jdouble alpha, jdouble beta) {
	size_t limit = (size_t) numPoints ;
	jdouble* result ;
	gsl_integration_fixed_workspace *w ;
	if(jtype == func4j_integration_Integral1D_FIXED_LEGENDRE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_legendre, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_CHEBYSHEV)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_chebyshev, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_GEGENBAUER)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_gegenbauer, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_JACOBI)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_jacobi, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_LAGUERRE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_laguerre, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_HERMITE)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_hermite, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_EXPONENTIAL)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_exponential, limit, a, b, alpha, beta) ;
	else if(jtype == func4j_integration_Integral1D_FIXED_RATIONAL)
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_rational, limit, a, b, alpha, beta) ;
	else
		w = gsl_integration_fixed_alloc(gsl_integration_fixed_chebyshev2, limit, a, b, alpha, beta) ;
	result = gsl_integration_fixed_weights(w) ;
	gsl_integration_fixed_free(w) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(numPoints) ;
	jvm -> SetDoubleArrayRegion(jresult, 0, numPoints, result) ;
	return jresult ;
}





