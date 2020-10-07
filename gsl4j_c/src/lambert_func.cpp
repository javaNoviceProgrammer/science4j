/*
 * lambert_func.cpp
 *
 *  Created on: Jun 11, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_lambert.h>
#include "../headers/org_gsl4j_special_Lambert.h"

/*
 * Class:     org_gsl4j_special_Lambert
 * Method:    lambertW0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Lambert_lambertW0
  (JNIEnv *env, jclass Lambert, jdouble x) {
	return gsl_sf_lambert_W0(x) ;
}

/*
 * Class:     org_gsl4j_special_Lambert
 * Method:    lambertWm1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Lambert_lambertWm1
  (JNIEnv *env, jclass Lambert, jdouble x) {
	return gsl_sf_lambert_Wm1(x) ;
}


