/*
 * synchrotron_func.cpp
 *
 *  Created on: Jun 19, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_synchrotron.h>
#include "../headers/org_gsl4j_special_Synchrotron.h"

/*
 * Class:     org_gsl4j_special_Synchrotron
 * Method:    synchrotron1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Synchrotron_synchrotron1
  (JNIEnv *env, jclass Synchrotron, jdouble x) {
	return gsl_sf_synchrotron_1(x) ;
}

/*
 * Class:     org_gsl4j_special_Synchrotron
 * Method:    synchrotron2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Synchrotron_synchrotron2
  (JNIEnv *env, jclass Synchrotron, jdouble x) {
	return gsl_sf_synchrotron_2(x) ;
}

