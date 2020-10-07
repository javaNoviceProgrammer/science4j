/*
 * hermite_polynom.cpp
 *
 *  Created on: Jun 10, 2020
 *      Author: meisam
 */

#include <gsl/gsl_sf_hermite.h>
#include "../headers/org_gsl4j_polynom_HermitePolynom.h"

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermite
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermite
  (JNIEnv *env, jclass HermitePolynom, jint n, jdouble x) {
	return gsl_sf_hermite(n, x) ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteArray
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteArray
  (JNIEnv *env, jclass HermitePolynom, jint nmax, jdouble x) {
	jint len = nmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_array(nmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteSeries
 * Signature: (ID[D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteSeries
  (JNIEnv *env, jclass HermitePolynom, jint n, jdouble x, jdoubleArray a) {
	jint len = env -> GetArrayLength(a) ;
	jdouble *coeffs = new jdouble[len] ;
	env -> GetDoubleArrayRegion(a, 0, len, coeffs) ;
	jdouble jresult = gsl_sf_hermite_series(n, x, coeffs) ;
	delete[] coeffs ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProb
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProb
  (JNIEnv *env, jclass HermitePolynom, jint n, jdouble x) {
	return gsl_sf_hermite_prob(n, x) ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProbArray
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProbArray
  (JNIEnv *env, jclass HermitePolynom, jint nmax, jdouble x) {
	jint len = nmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_prob_array(nmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProbSeries
 * Signature: (ID[D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProbSeries
  (JNIEnv *env, jclass HermitePolynom, jint n, jdouble x, jdoubleArray a) {
	jint len = env -> GetArrayLength(a) ;
	jdouble *coeffs = new jdouble[len] ;
	env -> GetDoubleArrayRegion(a, 0, len, coeffs) ;
	jdouble jresult = gsl_sf_hermite_series(n, x, coeffs) ;
	delete[] coeffs ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteDeriv
 * Signature: (IID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteDeriv
  (JNIEnv *env, jclass HermitePolynom, jint m, jint n, jdouble x) {
	return gsl_sf_hermite_deriv(m, n, x) ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteArrayDeriv
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteArrayDeriv
  (JNIEnv *env, jclass HermitePolynom, jint m, jint nmax, jdouble x) {
	jint len = nmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_array_deriv(m, nmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteDerivArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteDerivArray
  (JNIEnv *env, jclass HermitePolynom, jint mmax, jint n, jdouble x) {
	jint len = mmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_deriv_array(mmax, n, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProbDeriv
 * Signature: (IID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProbDeriv
  (JNIEnv *env, jclass HermitePolynom, jint m, jint n, jdouble x) {
	return gsl_sf_hermite_prob_deriv(m, n, x) ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProbArrayDeriv
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProbArrayDeriv
  (JNIEnv *env, jclass HermitePolynom, jint m, jint nmax, jdouble x) {
	jint len = nmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_prob_array_deriv(m, nmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProbDerivArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProbDerivArray
  (JNIEnv *env, jclass HermitePolynom, jint mmax, jint n, jdouble x) {
	jint len = mmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_hermite_prob_deriv_array(mmax, n, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteZero
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteZero
  (JNIEnv *env, jclass HermitePolynom, jint n, jint s) {
	return gsl_sf_hermite_zero(n, s) ;
}

/*
 * Class:     org_gsl4j_polynom_HermitePolynom
 * Method:    hermiteProbZero
 * Signature: (II)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_HermitePolynom_hermiteProbZero
  (JNIEnv *env, jclass HermitePolynom, jint n, jint s) {
	return gsl_sf_hermite_prob_zero(n, s) ;
}



