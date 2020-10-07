/*
 * dawson_func.cpp
 *
 *  Created on: Jun 4, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_special_Dawson.h"
#include <gsl/gsl_sf_dawson.h>

/*
 * Class:     org_gsl4j_special_Dawson
 * Method:    dawson
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Dawson_dawson
  (JNIEnv *env, jclass dawsonClass, jdouble x) {
	return gsl_sf_dawson(x) ;
}
