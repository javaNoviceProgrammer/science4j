/*
 * func4j_special_Clausen.cpp
 *
 *  Created on: Jan 10, 2021
 *      Author: meisam
 */


#include "../headers/func4j_special_Clausen.h"

#include <gsl/gsl_sf_clausen.h>

/*
 * Class:     func4j_special_Clausen
 * Method:    clausen
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Clausen_clausen
  (JNIEnv *env, jclass clausenClass, jdouble x) {
	return gsl_sf_clausen(x) ;
}

