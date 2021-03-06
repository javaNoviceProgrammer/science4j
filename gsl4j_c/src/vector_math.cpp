/*
 * vector_math.cpp
 *
 *  Created on: Oct 21, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_vector_VectorMath.h"

#include <iostream>

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    add
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_add___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v1[i] + v2[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    sub
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_sub___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v1[i] - v2[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mul
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mul___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v1[i] * v2[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    div
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_div___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v1[i] / v2[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    addRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_addRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v2[i] + v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    subRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_subRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v2[i] - v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mulRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mulRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v2[i] * v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    divRev
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_divRev___3D_3D_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v2[i] / v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    add
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_add___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = v1[i] + x ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    sub
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_sub___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = v1[i] - x ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mul
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mul___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = v1[i] * x ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    div
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_div___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = v1[i] / x ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    addRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_addRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = x + v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    subRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_subRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = x - v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    mulRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_mulRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = x * v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    divRev
 * Signature: ([DD[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_divRev___3DD_3D
  (JNIEnv *jvm, jclass VectorMath_class, jdoubleArray vec1, jdouble x, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v2[i] = x / v1[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v2, 0) ;
}

/*
 * Class:     org_gsl4j_vector_VectorMath
 * Method:    axpby
 * Signature: (D[DD[D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_vector_VectorMath_axpby
  (JNIEnv *jvm, jclass VectorMath_class, jdouble a, jdoubleArray vec1, jdouble b, jdoubleArray vec2, jdoubleArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	// get a direct pointer --> NO copying by JVM
	jdouble* v1 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jdouble* v2 = (jdouble*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jdouble* v3 =  (jdouble*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = a * v1[i] + b * v2[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;

}



