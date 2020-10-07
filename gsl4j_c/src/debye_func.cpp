/*
 * debye_func.cpp
 *
 *  Created on: Jun 4, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_special_Debye.h"
#include <gsl/gsl_sf_debye.h>

/*
 * Class:     org_gsl4j_special_Debye
 * Method:    debye1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Debye_debye1
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_1(x) ;
}

/*
 * Class:     org_gsl4j_special_Debye
 * Method:    debye2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Debye_debye2
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_2(x) ;
}

/*
 * Class:     org_gsl4j_special_Debye
 * Method:    debye3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Debye_debye3
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_3(x) ;
}

/*
 * Class:     org_gsl4j_special_Debye
 * Method:    debye4
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Debye_debye4
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_4(x) ;
}

/*
 * Class:     org_gsl4j_special_Debye
 * Method:    debye5
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Debye_debye5
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_5(x) ;
}

/*
 * Class:     org_gsl4j_special_Debye
 * Method:    debye6
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Debye_debye6
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_6(x) ;
}

