
#include "../headers/org_simd4j_ArrayMath.h"


 /*
 * Class:     org_simd4j_ArrayMath
 * Method:    add
 * Signature: ([I[I[I)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_add___3I_3I_3I
  (JNIEnv *jvm, jclass ArrayMath_class, jintArray vec1, jintArray vec2, jintArray result) {
	jint len = jvm -> GetArrayLength(vec1) ;
	jint* v1 = (jint*) jvm -> GetPrimitiveArrayCritical(vec1, nullptr) ;
	jint* v2 = (jint*) jvm -> GetPrimitiveArrayCritical(vec2, nullptr) ;
	jint* v3 = (jint*) jvm -> GetPrimitiveArrayCritical(result, nullptr) ;
	for(jint i=0; i<len; i++) {
		v3[i] = v1[i] + v2[i] ;
	}
	jvm -> ReleasePrimitiveArrayCritical(vec1, v1, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(vec2, v2, 0) ;
	jvm -> ReleasePrimitiveArrayCritical(result, v3, 0) ;
}

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    sub
 * Signature: ([I[I[I)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_sub___3I_3I_3I
  (JNIEnv *jvm, jclass ArrayMath_class, jintArray, jintArray, jintArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    mul
 * Signature: ([I[I[I)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_mul___3I_3I_3I
  (JNIEnv *jvm, jclass ArrayMath_class, jintArray, jintArray, jintArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    div
 * Signature: ([I[I[I)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_div___3I_3I_3I
  (JNIEnv *jvm, jclass ArrayMath_class, jintArray, jintArray, jintArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    add
 * Signature: ([F[F[F)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_add___3F_3F_3F
  (JNIEnv *jvm, jclass ArrayMath_class, jfloatArray, jfloatArray, jfloatArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    sub
 * Signature: ([F[F[F)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_sub___3F_3F_3F
  (JNIEnv *jvm, jclass ArrayMath_class, jfloatArray, jfloatArray, jfloatArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    mul
 * Signature: ([F[F[F)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_mul___3F_3F_3F
  (JNIEnv *jvm, jclass ArrayMath_class, jfloatArray, jfloatArray, jfloatArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    div
 * Signature: ([F[F[F)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_div___3F_3F_3F
  (JNIEnv *jvm, jclass ArrayMath_class, jfloatArray, jfloatArray, jfloatArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    add
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_add___3D_3D_3D
  (JNIEnv *jvm, jclass ArrayMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    sub
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_sub___3D_3D_3D
  (JNIEnv *jvm, jclass ArrayMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    mul
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_mul___3D_3D_3D
  (JNIEnv *jvm, jclass ArrayMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

/*
 * Class:     org_simd4j_ArrayMath
 * Method:    div
 * Signature: ([D[D[D)V
 */
JNIEXPORT void JNICALL Java_org_simd4j_ArrayMath_div___3D_3D_3D
  (JNIEnv *jvm, jclass ArrayMath_class, jdoubleArray, jdoubleArray, jdoubleArray);

