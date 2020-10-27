/*
 * numerical_diff.cpp
 *
 *  Created on: Jun 22, 2020
 *      Author: meisam
 */

#include <gsl/gsl_deriv.h>
#include "../headers/org_gsl4j_diff_NumericalDiff.h"

/*************Initialize and Cache IDs*******************/

namespace { // create an anonymous namespace to limit the scope

	jclass MathFunction_class ;
	jmethodID jvalueMethod ;

	struct my_f_params {
		JNIEnv* jvm ;
		jobject MathFunction_obj ;
	};

	jdouble math_func(jdouble x, void* p) {
		my_f_params* params = (my_f_params*) p;
		return (*params).jvm -> CallDoubleMethod(params->MathFunction_obj, jvalueMethod, x) ;
	}

}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_diff_NumericalDiff_initIDs
  (JNIEnv *env, jclass NumericalDiff) {
	MathFunction_class = env -> FindClass("org/gsl4j/function/MathFunction") ;
	jvalueMethod = env -> GetMethodID(MathFunction_class, "value", "(D)D") ;
}

/*******************************************************/

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    central
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_central__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {jvm, MathFunction};
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
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdoubleArray x, jdouble h) {
	my_f_params params {jvm, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble absErr {0.0} ;
	// create array and get direct access (no copying)
	jint len = jvm -> GetArrayLength(x) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(len) ;
	jdouble* jresult_array = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, nullptr) ;
	jdouble* x_array = (jdouble*) jvm -> GetPrimitiveArrayCritical(x, nullptr) ;
	for(int i=0; i<len; i++) {
		gsl_deriv_central(&F, x_array[i], h, (jresult_array+i), &absErr) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(x, x_array, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(jresult, jresult_array, 0) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forward
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_forward__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {jvm, MathFunction};
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
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdoubleArray x, jdouble h) {
	my_f_params params {jvm, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble absErr {0.0} ;
	// create array and get direct access (no copying)
	jint len = jvm -> GetArrayLength(x) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(len) ;
	jdouble* jresult_array = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, nullptr) ;
	jdouble* x_array = (jdouble*) jvm -> GetPrimitiveArrayCritical(x, nullptr) ;
	for(int i=0; i<len; i++) {
		gsl_deriv_forward(&F, x_array[i], h, (jresult_array+i), &absErr) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(x, x_array, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(jresult, jresult_array, 0) ;
	return jresult ;
}


/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backward
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_backward__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {jvm, MathFunction};
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
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdoubleArray x, jdouble h) {
	my_f_params params {jvm, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble absErr {0.0} ;
	// create array and get direct access (no copying)
	jint len = jvm -> GetArrayLength(x) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(len) ;
	jdouble* jresult_array = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, nullptr) ;
	jdouble* x_array = (jdouble*) jvm -> GetPrimitiveArrayCritical(x, nullptr) ;
	for(int i=0; i<len; i++) {
		gsl_deriv_backward(&F, x_array[i], h, (jresult_array+i), &absErr) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(x, x_array, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(jresult, jresult_array, 0) ;
	return jresult ;
}


/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    centralWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_centralWithError
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {jvm, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_central(&F, x, h, &jresult, &absErr) ;
	jdoubleArray jarray = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {jresult, absErr} ;
	jvm -> SetDoubleArrayRegion(jarray, 0, 2, buf) ;
	return jarray ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forwardWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_forwardWithError
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {jvm, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_forward(&F, x, h, &jresult, &absErr) ;
	jdoubleArray jarray = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {jresult, absErr} ;
	jvm -> SetDoubleArrayRegion(jarray, 0, 2, buf) ;
	return jarray ;
}

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backwardWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_backwardWithError
  (JNIEnv *jvm, jclass NumericalDiff, jobject MathFunction, jdouble x, jdouble h) {
	my_f_params params {jvm, MathFunction};
	gsl_function F ;
	F.function = &math_func ;
	F.params = &params ;
	jdouble jresult {0.0} ;
	jdouble absErr {0.0} ;
	gsl_deriv_backward(&F, x, h, &jresult, &absErr) ;
	jdoubleArray jarray = jvm -> NewDoubleArray(2) ;
	jdouble buf[] = {jresult, absErr} ;
	jvm -> SetDoubleArrayRegion(jarray, 0, 2, buf) ;
	return jarray ;
}



