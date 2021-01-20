/*
 * func4j_special_Wright.cpp
 *
 *  Created on: Jan 20, 2021
 *      Author: meisam
 */

#include <complex.h>
#include "../headers/func4j_special_Wright.h"
#include "../scipy/wright.h"

using complex = std::complex<double> ;

/*
 * Class:     func4j_special_Wright
 * Method:    wrightOmega
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_func4j_special_Wright_wrightOmega__D
  (JNIEnv *jvm, jclass Wright_class, jdouble x) {
	return wright::wrightomega_real(x) ;
}

/*
 * Class:     func4j_special_Wright
 * Method:    wrightOmega
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_special_Wright_wrightOmega__DD
  (JNIEnv *jvm, jclass Wright_class, jdouble re, jdouble im) {
	complex z {re, im} ;
	complex result = wright::wrightomega(z) ;
	jdoubleArray jresult = jvm -> NewDoubleArray(2) ;
	jdouble carray[] = {result.real(), result.imag() } ;
	jvm -> SetDoubleArrayRegion(jresult, 0, 2, carray) ;
	return jresult ;
}

