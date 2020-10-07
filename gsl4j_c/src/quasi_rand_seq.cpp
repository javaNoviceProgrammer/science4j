/*
 * quasi_rand_seq.cpp
 *
 *  Created on: Jul 4, 2020
 *      Author: meisam
 */

#include <gsl/gsl_qrng.h>
#include "../headers/org_gsl4j_sequence_QuasiRandomSequence.h"

jfieldID quasi_rand_seq_dim_id ;

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_initIDs
  (JNIEnv *env, jclass QuasiRandomSequence) {
	quasi_rand_seq_dim_id = env -> GetFieldID(QuasiRandomSequence, "dim", "I") ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    niederreiter2
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_niederreiter2
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_niederreiter_2, dim);
	gsl_qrng_init(q) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(dim) ;
	env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
	gsl_qrng_free(q);
	delete[] buf ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    sobol
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_sobol
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_sobol, dim);
	gsl_qrng_init(q) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(dim) ;
	env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
	gsl_qrng_free(q);
	delete[] buf ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    halton
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_halton
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_halton, dim);
	gsl_qrng_init(q) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(dim) ;
	env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
	gsl_qrng_free(q);
	delete[] buf ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    reverseHalton
 * Signature: (I)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_reverseHalton
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_reversehalton, dim);
	gsl_qrng_init(q) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(dim) ;
	env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
	gsl_qrng_free(q);
	delete[] buf ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    niederreiter2All
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_niederreiter2All
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_niederreiter_2, dim);
	gsl_qrng_init(q) ;
	jclass array_class = env -> FindClass("[D") ;
	jobjectArray obj_array = env -> NewObjectArray(index+1, array_class, NULL) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
		jdoubleArray jresult = env -> NewDoubleArray(dim) ;
		env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
		env -> SetObjectArrayElement(obj_array, i, jresult) ;
	}
	gsl_qrng_free(q);
	delete[] buf ;
	return obj_array ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    sobolAll
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_sobolAll
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_sobol, dim);
	gsl_qrng_init(q) ;
	jclass array_class = env -> FindClass("[D") ;
	jobjectArray obj_array = env -> NewObjectArray(index+1, array_class, NULL) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
		jdoubleArray jresult = env -> NewDoubleArray(dim) ;
		env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
		env -> SetObjectArrayElement(obj_array, i, jresult) ;
	}
	gsl_qrng_free(q);
	delete[] buf ;
	return obj_array ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    haltonAll
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_haltonAll
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_halton, dim);
	gsl_qrng_init(q) ;
	jclass array_class = env -> FindClass("[D") ;
	jobjectArray obj_array = env -> NewObjectArray(index+1, array_class, NULL) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
		jdoubleArray jresult = env -> NewDoubleArray(dim) ;
		env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
		env -> SetObjectArrayElement(obj_array, i, jresult) ;
	}
	gsl_qrng_free(q);
	delete[] buf ;
	return obj_array ;
}

/*
 * Class:     org_gsl4j_sequence_QuasiRandomSequence
 * Method:    reverseHaltonAll
 * Signature: (I)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_sequence_QuasiRandomSequence_reverseHaltonAll
  (JNIEnv *env, jobject QuasiRandomSequence, jint index) {
	jint dim = env -> GetIntField(QuasiRandomSequence, quasi_rand_seq_dim_id) ;
	gsl_qrng *q = gsl_qrng_alloc(gsl_qrng_reversehalton, dim);
	gsl_qrng_init(q) ;
	jclass array_class = env -> FindClass("[D") ;
	jobjectArray obj_array = env -> NewObjectArray(index+1, array_class, NULL) ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<=index; i++) {
		gsl_qrng_get(q, buf) ;
		jdoubleArray jresult = env -> NewDoubleArray(dim) ;
		env -> SetDoubleArrayRegion(jresult, 0, dim, buf) ;
		env -> SetObjectArrayElement(obj_array, i, jresult) ;
	}
	gsl_qrng_free(q);
	delete[] buf ;
	return obj_array ;
}






