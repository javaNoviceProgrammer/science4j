/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_gsl4j_diff_NumericalDiff */

#ifndef _Included_org_gsl4j_diff_NumericalDiff
#define _Included_org_gsl4j_diff_NumericalDiff
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_diff_NumericalDiff_initIDs
  (JNIEnv *, jclass);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    central
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_central__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *, jclass, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    central
 * Signature: (Lorg/gsl4j/function/MathFunction;[DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_central__Lorg_gsl4j_function_MathFunction_2_3DD
  (JNIEnv *, jclass, jobject, jdoubleArray, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forward
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_forward__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *, jclass, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forward
 * Signature: (Lorg/gsl4j/function/MathFunction;[DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_forward__Lorg_gsl4j_function_MathFunction_2_3DD
  (JNIEnv *, jclass, jobject, jdoubleArray, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backward
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_diff_NumericalDiff_backward__Lorg_gsl4j_function_MathFunction_2DD
  (JNIEnv *, jclass, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backward
 * Signature: (Lorg/gsl4j/function/MathFunction;[DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_backward__Lorg_gsl4j_function_MathFunction_2_3DD
  (JNIEnv *, jclass, jobject, jdoubleArray, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    centralWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_centralWithError
  (JNIEnv *, jclass, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    forwardWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_forwardWithError
  (JNIEnv *, jclass, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_diff_NumericalDiff
 * Method:    backwardWithError
 * Signature: (Lorg/gsl4j/function/MathFunction;DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_diff_NumericalDiff_backwardWithError
  (JNIEnv *, jclass, jobject, jdouble, jdouble);

#ifdef __cplusplus
}
#endif
#endif
