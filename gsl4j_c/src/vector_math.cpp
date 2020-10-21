/*
 * vector_math.cpp
 *
 *  Created on: Oct 21, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_vector_VectorMath.h"

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    add
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_add___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// stack allocation: len < 100_000
	if(len <= 100000) {
		jdouble v1[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble v2[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v2[i] += v1[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v2) ;
	}
	// heap allocation: len > 100_000
	else {
		jdouble* v1 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble* v2 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] += v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
		delete[] v1 ;
		delete[] v2 ;
	}
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    sub
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_sub___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// stack allocation: len < 100_000
	if(len <= 100000) {
		jdouble v1[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble v2[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] -= v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
	}
	// heap allocation: len > 100_000
	else {
		jdouble* v1 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble* v2 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] -= v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
		delete[] v1 ;
		delete[] v2 ;
	}
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mul
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mul___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// stack allocation: len < 100_000
	if(len <= 100000) {
		jdouble v1[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble v2[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] *= v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
	}
	// heap allocation: len > 100_000
	else {
		jdouble* v1 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble* v2 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] *= v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
		delete[] v1 ;
		delete[] v2 ;
	}
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    div
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_div___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// stack allocation: len < 100_000
	if(len <= 100000) {
		jdouble v1[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble v2[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] /= v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
	}
	// heap allocation: len > 100_000
	else {
		jdouble* v1 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec1, 0, len, v1) ;
		jdouble* v2 = new jdouble[len] ;
		jvm -> GetDoubleArrayRegion(vec2, 0, len, v2) ;
		for(int i=0; i<len; i++) {
			v1[i] /= v2[i] ;
		}
		jvm -> SetDoubleArrayRegion(result, 0, len, v1) ;
		delete[] v1 ;
		delete[] v2 ;
	}
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    addRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_addRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    subRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_subRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mulRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mulRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    divRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_divRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    add
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_add___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    sub
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_sub___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mul
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mul___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    div
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_div___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    addRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_addRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    subRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_subRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mulRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mulRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    divRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_divRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    axpby
 * Signature: (D[DD[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_axpby
  (JNIEnv *jvm, jclass VectorMath_class, jdouble, jdoubleArray, jdouble, jdoubleArray, jdoubleArray);



