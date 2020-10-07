/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_gsl4j_roots_RealRoot */

#ifndef _Included_org_gsl4j_roots_RealRoot
#define _Included_org_gsl4j_roots_RealRoot
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    initFieldIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_roots_RealRoot_initFieldIDs
  (JNIEnv *, jclass);

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    bisection
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_bisection
  (JNIEnv *, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    falsepos
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_falsepos
  (JNIEnv *, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    brent
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_brent
  (JNIEnv *, jobject, jdouble, jdouble);

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    newton
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_newton
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    secant
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_secant
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     org_gsl4j_roots_RealRoot
 * Method:    steffenson
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_roots_RealRoot_steffenson
  (JNIEnv *, jobject, jdouble);

#ifdef __cplusplus
}
#endif
#endif
