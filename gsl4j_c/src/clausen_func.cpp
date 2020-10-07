/*
 * clausen_func.cpp
 *
 *  Created on: Jun 4, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_special_Clausen.h"
#include <gsl/gsl_sf_clausen.h>

/*
 * Class:     org_gsl4j_special_Clausen
 * Method:    clausen
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Clausen_clausen
  (JNIEnv *env, jclass clausenClass, jdouble x) {
	return gsl_sf_clausen(x) ;
}



