/*
 * func4j_special_Dawson.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */

#include "../headers/func4j_special_Dawson.h"

#include <gsl/gsl_sf_dawson.h>

/*
 * Class:     func4j_special_Dawson
 * Method:    dawson
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Dawson_dawson
  (JNIEnv *env, jclass dawsonClass, jdouble x) {
	return gsl_sf_dawson(x) ;
}
