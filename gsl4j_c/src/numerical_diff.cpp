/*
 * numerical_diff.cpp
 *
 *  Created on: Jun 22, 2020
 *      Author: meisam
 */

#include <gsl/gsl_deriv.h>
#include "../headers/org_gsl4j_diff_NumericalDiff.h"

/*************Initialize and Cache IDs*******************/

static jclass jMathFunction ;
static jmethodID jvalueMethod ;

struct my_f_params {
	JNIEnv *env ;
	jobject MathFunction ;
};

static jdouble math_func(jdouble x, void *p) {
	my_f_params *params = (my_f_params *)p;
	return (*params).env -> CallDoubleMethod(params->MathFunction, jvalueMethod, x) ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_diff_NumericalDiff_initIDs
  (JNIEnv *env, jclass NumericalDiff) {
	jMathFunction = env -> FindClass("org/gsl4j/function/MathFunction") ;
	jvalueMethod = env -> GetMethodID(jMathFunction, "value", "(D)D") ;
}

/*******************************************************/

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    central
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_central__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_central(&F, x, h, &jresult, &absErr) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    central
 * Signature: (Lorg/gsl4j/function/MathFunction;[DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_central__Lorg_gsl4j_function_MathFunction_2_3DD
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdoubleArray x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble absErr {0.0} ;
	jint len = env -> GetArrayLength(x) ;
	jdouble *x_array = new jdouble[len] ;
	jdouble *result = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	for(int i=0; i<len; i++) {
		gsl_deriv_central(&F, x_array[i], h, (result+i), &absErr) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] x_array ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forward
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_forward__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_forward(&F, x, h, &jresult, &absErr) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forward
 * Signature: (Lorg/gsl4j/function/MathFunction;[DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_forward__Lorg_gsl4j_function_MathFunction_2_3DD
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdoubleArray x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble absErr {0.0} ;
	jint len = env -> GetArrayLength(x) ;
	jdouble *x_array = new jdouble[len] ;
	jdouble *result = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	for(int i=0; i<len; i++) {
		gsl_deriv_forward(&F, x_array[i], h, (result+i), &absErr) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] x_array ;
	delete[] result ;
	return jresult ;
}


/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backward
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_backward__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_backward(&F, x, h, &jresult, &absErr) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backward
 * Signature: (Lorg/gsl4j/function/MathFunction;[DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_backward__Lorg_gsl4j_function_MathFunction_2_3DD
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdoubleArray x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble absErr {0.0} ;
	jint len = env -> GetArrayLength(x) ;
	jdouble *x_array = new jdouble[len] ;
	jdouble *result = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	for(int i=0; i<len; i++) {
		gsl_deriv_backward(&F, x_array[i], h, (result+i), &absErr) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] x_array ;
	delete[] result ;
	return jresult ;
}


/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    centralWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_centralWithError
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_central(&F, x, h, &jresult, &absErr) ;
	jdoubleArray jarray = env -> NewDoubleArray(2) ;
	jdouble buf[] = {jresult, absErr} ;
	env -> SetDoubleArrayRegion(jarray, 0, 2, buf) ;
	return jarray ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forwardWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_forwardWithError
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_forward(&F, x, h, &jresult, &absErr) ;
	jdoubleArray jarray = env -> NewDoubleArray(2) ;
	jdouble buf[] = {jresult, absErr} ;
	env -> SetDoubleArrayRegion(jarray, 0, 2, buf) ;
	return jarray ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backwardWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_backwardWithError
  (JNIEnv *env, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {env, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_backward(&F, x, h, &jresult, &absErr) ;
	jdoubleArray jarray = env -> NewDoubleArray(2) ;
	jdouble buf[] = {jresult, absErr} ;
	env -> SetDoubleArrayRegion(jarray, 0, 2, buf) ;
	return jarray ;
}



