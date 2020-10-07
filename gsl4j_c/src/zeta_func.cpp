/*
 * zeta_func.cpp
 *
 *  Created on: Jun 13, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_zeta.h>
#include "../headers/org_gsl4j_special_Zeta.h"

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    zetaInt
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_zetaInt
  (JNIEnv *env, jclass Zeta, jint n) {
	return gsl_sf_zeta_int(n) ;
}

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    zeta
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_zeta
  (JNIEnv *env, jclass Zeta, jdouble s) {
	return gsl_sf_zeta(s) ;
}

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    zetam1Int
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_zetam1Int
  (JNIEnv *env, jclass Zeta, jint n) {
	return gsl_sf_zetam1_int(n) ;
}

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    zetam1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_zetam1
  (JNIEnv *env, jclass Zeta, jdouble s) {
	return gsl_sf_zetam1(s) ;
}

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    hzeta
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_hzeta
  (JNIEnv *env, jclass Zeta, jdouble s, jdouble q) {
	return gsl_sf_hzeta(s, q) ;
}

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    etaInt
 * Signature: (I)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_etaInt
  (JNIEnv *env, jclass Zeta, jint n) {
	return gsl_sf_eta(n) ;
}

/*
 * Class:     org_gsl4j_special_Zeta
 * Method:    eta
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Zeta_eta
  (JNIEnv *env, jclass Zeta, jdouble s) {
	return gsl_sf_eta(s) ;
}


