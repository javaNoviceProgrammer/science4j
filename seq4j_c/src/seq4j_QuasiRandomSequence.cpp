/*
 * seq4j_QuasiRandomSequence.cpp
 *
 *  Created on: Feb 6, 2021
 *      Author: meisam
 */


#include "../headers/seq4j_QuasiRandomSequence.h"
#include <gsl/gsl_qrng.h>

namespace { // anonymous namespace

	jfieldID quasi_rand_seq_dim_id ;

}


/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_seq4j_QuasiRandomSequence_initIDs
  (JNIEnv *jvm, jclass QuasiRandomSequence) {
	quasi_rand_seq_dim_id = jvm -> GetFieldID(QuasiRandomSequence, "dim", "I") ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    niederreiter2
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_seq4j_QuasiRandomSequence_niederreiter2
  (JNIEnv* jvm, jobject QuasiRandomSequence_obj, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence_obj, quasi_rand_seq_dim_id) ;
	gsl_qrng* q = gsl_qrng_alloc(gsl_qrng_niederreiter_2, dim);
	gsl_qrng_init(q) ;

	jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
	jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	gsl_qrng_free(q);
	return jresult ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    sobol
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_seq4j_QuasiRandomSequence_sobol
  (JNIEnv *jvm, jobject QuasiRandomSequence_obj, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence_obj, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_sobol, dim);
	gsl_qrng_init(q) ;

	jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
	jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	gsl_qrng_free(q);
	return jresult ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    halton
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_seq4j_QuasiRandomSequence_halton
  (JNIEnv *jvm, jobject QuasiRandomSequence_obj, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence_obj, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_halton, dim);
	gsl_qrng_init(q) ;

	jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
	jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	gsl_qrng_free(q);
	return jresult ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    reverseHalton
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_seq4j_QuasiRandomSequence_reverseHalton
  (JNIEnv *jvm, jobject QuasiRandomSequence_obj, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence_obj, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_reversehalton, dim);
	gsl_qrng_init(q) ;

	jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
	jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	gsl_qrng_free(q);
	return jresult ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    niederreiter2All
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_seq4j_QuasiRandomSequence_niederreiter2All
  (JNIEnv *jvm, jobject QuasiRandomSequence_obj, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence_obj, quasi_rand_seq_dim_id) ;
	gsl_qrng* q = gsl_qrng_alloc(gsl_qrng_niederreiter_2, dim);
	gsl_qrng_init(q) ;

	jclass array_class = jvm -> FindClass("[D") ;
	jobjectArray obj_array = jvm -> NewObjectArray(index+1, array_class, NULL) ;

	for(int i=0; i<=index; i++) {
		jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
		jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
		gsl_qrng_get(q, buf) ;
		jvm -> SetObjectArrayElement(obj_array, i, jresult) ;
		jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	}

	gsl_qrng_free(q);
	return obj_array ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    sobolAll
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_seq4j_QuasiRandomSequence_sobolAll
  (JNIEnv *jvm, jobject QuasiRandomSequence_obj, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence_obj, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_sobol, dim);
	gsl_qrng_init(q) ;

	jclass array_class = jvm -> FindClass("[D") ;
	jobjectArray obj_array = jvm -> NewObjectArray(index+1, array_class, NULL) ;

	for(int i=0; i<=index; i++) {
		jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
		jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
		gsl_qrng_get(q, buf) ;
		jvm -> SetObjectArrayElement(obj_array, i, jresult) ;
		jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	}

	gsl_qrng_free(q);
	return obj_array ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    haltonAll
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_seq4j_QuasiRandomSequence_haltonAll
  (JNIEnv *jvm, jobject QuasiRandomSequence, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_halton, dim);
	gsl_qrng_init(q) ;

	jclass array_class = jvm -> FindClass("[D") ;
	jobjectArray obj_array = jvm -> NewObjectArray(index+1, array_class, NULL) ;

	for(int i=0; i<=index; i++) {
		jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
		jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
		gsl_qrng_get(q, buf) ;
		jvm -> SetObjectArrayElement(obj_array, i, jresult) ;
		jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	}

	gsl_qrng_free(q);
	return obj_array ;
}

/*
 * Class:     seq4j_QuasiRandomSequence
 * Method:    reverseHaltonAll
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_seq4j_QuasiRandomSequence_reverseHaltonAll
  (JNIEnv *jvm, jobject QuasiRandomSequence, jint index) {
	jint dim = jvm -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_reversehalton, dim);
	gsl_qrng_init(q) ;

	jclass array_class = jvm -> FindClass("[D") ;
	jobjectArray obj_array = jvm -> NewObjectArray(index+1, array_class, NULL) ;

	for(int i=0; i<=index; i++) {
		jdoubleArray jresult = jvm -> NewDoubleArray(dim) ;
		jdouble* buf = (jdouble*) jvm -> GetPrimitiveArrayCritical(jresult, 0) ;
		gsl_qrng_get(q, buf) ;
		jvm -> SetObjectArrayElement(obj_array, i, jresult) ;
		jvm -> ReleasePrimitiveArrayCritical(jresult, buf, 0) ;
	}

	gsl_qrng_free(q);
	return obj_array ;
}



