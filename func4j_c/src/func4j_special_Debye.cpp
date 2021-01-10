/*
 * func4j_special_Debye.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Debye.h"

#include <gsl/gsl_sf_debye.h>

/*
 * Class:     func4j_special_Debye
 * Method:    debye1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Debye_debye1
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_1(x) ;
}

/*
 * Class:     func4j_special_Debye
 * Method:    debye2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Debye_debye2
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_2(x) ;
}

/*
 * Class:     func4j_special_Debye
 * Method:    debye3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Debye_debye3
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_3(x) ;
}

/*
 * Class:     func4j_special_Debye
 * Method:    debye4
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Debye_debye4
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_4(x) ;
}

/*
 * Class:     func4j_special_Debye
 * Method:    debye5
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Debye_debye5
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_5(x) ;
}

/*
 * Class:     func4j_special_Debye
 * Method:    debye6
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Debye_debye6
  (JNIEnv *env, jclass debye, jdouble x) {
	return gsl_sf_debye_6(x) ;
}


