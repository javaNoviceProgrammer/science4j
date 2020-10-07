/*
 * transport_func.cpp
 *
 *  Created on: Jun 19, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_transport.h>
#include "../headers/org_gsl4j_special_Transport.h"

/*
 * Class:     org_gsl4j_special_Transport
 * Method:    transport2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Transport_transport2
  (JNIEnv *env, jclass Transport, jdouble x) {
	return gsl_sf_transport_2(x) ;
}

/*
 * Class:     org_gsl4j_special_Transport
 * Method:    transport3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Transport_transport3
  (JNIEnv *env, jclass Transport, jdouble x) {
	return gsl_sf_transport_3(x) ;
}

/*
 * Class:     org_gsl4j_special_Transport
 * Method:    transport4
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Transport_transport4
  (JNIEnv *env, jclass Transport, jdouble x) {
	return gsl_sf_transport_4(x) ;
}

/*
 * Class:     org_gsl4j_special_Transport
 * Method:    transport5
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_Transport_transport5
  (JNIEnv *env, jclass Transport, jdouble x) {
	return gsl_sf_transport_5(x) ;
}


