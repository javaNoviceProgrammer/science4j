/*
 * vector_math.cpp
 *
 *  Created on: Jul 7, 2020
 *      Author: meisam
 */

#include <gsl/gsl_vector.h>
#include "../headers/org_gsl4j_matrix_VectorMath.h"

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    get
 * Signature: ([DI)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_matrix_VectorMath_get
  (JNIEnv *env, jclass VectorMath, jdoubleArray x, jint i) {
	jint len = env -> GetArrayLength(x) ;
	jdouble *x_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	jdouble elem = x_array[i] ;
	delete[] x_array ;
	return elem ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    set
 * Signature: ([DID)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_matrix_VectorMath_set
  (JNIEnv *env, jclass VectorMath, jdoubleArray vec, jint i, jdouble x) {
	jint len = env -> GetArrayLength(vec) ;
	jdouble *vec_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(vec, 0, len, vec_array) ;
	vec_array[i] = x ;
	env -> SetDoubleArrayRegion(vec, 0, len, vec_array) ;
	delete[] vec_array ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    setAll
 * Signature: ([DD)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_matrix_VectorMath_setAll
  (JNIEnv *env, jclass VectorMath, jdoubleArray vec, jdouble x) {
	jint len = env -> GetArrayLength(vec) ;
	jdouble *vec_array = new jdouble[len] ;
	for(int i=0; i<len; i++) {
		vec_array[i] = x ;
	}
	env -> SetDoubleArrayRegion(vec, 0, len, vec_array) ;
	delete[] vec_array ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    setZero
 * Signature: ([D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_matrix_VectorMath_setZero
  (JNIEnv *env, jclass VectorMath, jdoubleArray vec) {
	jint len = env -> GetArrayLength(vec) ;
	jdouble *vec_array = new jdouble[len] ;
	for(int i=0; i<len; i++) {
		vec_array[i] = 0.0 ;
	}
	env -> SetDoubleArrayRegion(vec, 0, len, vec_array) ;
	delete[] vec_array ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    setBasis
 * Signature: ([DI)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_matrix_VectorMath_setBasis
  (JNIEnv *env, jclass VectorMath, jdoubleArray vec, jint i) {
	jint len = env -> GetArrayLength(vec) ;
	jdouble *vec_array = new jdouble[len] ;
	for(int k=0; k<len; k++) {
		vec_array[k] = 0.0 ;
	}
	vec_array[i] = 1.0 ;
	env -> SetDoubleArrayRegion(vec, 0, len, vec_array) ;
	delete[] vec_array ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    subVector
 * Signature: ([DII)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_subVector
  (JNIEnv *env, jclass VectorMath, jdoubleArray vec, jint offset, jint n) {
	jint len = env -> GetArrayLength(vec) ;
	jdouble *vec_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(vec, offset, n, vec_array) ;
	jdoubleArray sub_vec = env -> NewDoubleArray(n) ;
	env -> SetDoubleArrayRegion(sub_vec, 0, n, vec_array) ;
	delete[] vec_array ;
	return sub_vec ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    subVectorWithStride
 * Signature: ([DIII)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_subVectorWithStride
  (JNIEnv *env, jclass VectorMath, jdoubleArray vec, jint offset, jint stride, jint n) {
	jint len = env -> GetArrayLength(vec) ;
	jdouble *vec_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(vec, 0, len, vec_array) ;
	jdoubleArray sub_vec = env -> NewDoubleArray(n) ;
	jdouble *sub_vec_array = new jdouble[n] ;
	jint index = 0 ;
	for(int i=0; i<n; i++) {
		if((index=offset+i*stride)>=len)
			throw -1 ;
		sub_vec_array[i] = vec_array[offset+i*stride] ;
	}
	env -> SetDoubleArrayRegion(sub_vec, 0, n, sub_vec_array) ;
	delete[] vec_array ;
	delete[] sub_vec_array ;
	return sub_vec ;
}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    memcpy
 * Signature: ([D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_matrix_VectorMath_memcpy
  (JNIEnv *env, jclass VectorMath, jdoubleArray dest, jdoubleArray src) {

}

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    swap
 * Signature: ([D[D)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_matrix_VectorMath_swap
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    swapElements
 * Signature: ([DII)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_swapElements
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jint, jint);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    reverse
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_reverse
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    add
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_add
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    sub
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_sub
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    mul
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_mul
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    div
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_div
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    scale
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_scale
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdouble);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    addConstant
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_addConstant
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdouble);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    axpby
 * Signature: (D[DD[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_axpby
  (JNIEnv *env, jclass VectorMath, jdouble, jdoubleArray, jdouble, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    max
 * Signature: ([D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_matrix_VectorMath_max
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    min
 * Signature: ([D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_matrix_VectorMath_min
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    minmax
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_matrix_VectorMath_minmax
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    maxIndex
 * Signature: ([D)I
 */
JNIEXPORT jint JNICALL Java_org_gsl4j_matrix_VectorMath_maxIndex
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    minIndex
 * Signature: ([D)I
 */
JNIEXPORT jint JNICALL Java_org_gsl4j_matrix_VectorMath_minIndex
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    minmaxIndex
 * Signature: ([D)[I
 */
JNIEXPORT jintArray JNICALL Java_org_gsl4j_matrix_VectorMath_minmaxIndex
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    isZero
 * Signature: ([D)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_matrix_VectorMath_isZero
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    isPositive
 * Signature: ([D)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_matrix_VectorMath_isPositive
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    isNegative
 * Signature: ([D)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_matrix_VectorMath_isNegative
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    isNonNegative
 * Signature: ([D)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_matrix_VectorMath_isNonNegative
  (JNIEnv *env, jclass VectorMath, jdoubleArray);

/*
 * Class:     org_gsl4j_matrix_VectorMath
 * Method:    equal
 * Signature: ([D[D)Z
 */
JNIEXPORT jboolean JNICALL Java_org_gsl4j_matrix_VectorMath_equal
  (JNIEnv *env, jclass VectorMath, jdoubleArray, jdoubleArray);


