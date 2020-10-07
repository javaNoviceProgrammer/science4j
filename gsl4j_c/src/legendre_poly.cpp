/*
 * legendre_poly.cpp
 *
 *  Created on: Jun 12, 2020
 *      Author: meisam
 */

#include <stdlib.h>
#include <gsl/gsl_sf_legendre.h>
#include "../headers/org_gsl4j_polynom_LegendrePolynom.h"

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreP1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreP1
  (JNIEnv *env, jclass LegendrePolynom, jdouble x) {
	return gsl_sf_legendre_P1(x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreP2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreP2
  (JNIEnv *env, jclass LegendrePolynom, jdouble x) {
	return gsl_sf_legendre_P2(x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreP3
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreP3
  (JNIEnv *env, jclass LegendrePolynom, jdouble x) {
	return gsl_sf_legendre_P3(x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendrePl
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendrePl
  (JNIEnv *env, jclass LegendrePolynom, jint l, jdouble x) {
	return gsl_sf_legendre_Pl(l, x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendrePlArray
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendrePlArray
  (JNIEnv *env, jclass LegendrePolynom, jint lmax, jdouble x) {
	jint len = lmax+1 ;
	jdouble *result = new jdouble[len] ;
	gsl_sf_legendre_Pl_array(lmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendrePlDerivArray
 * Signature: (ID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendrePlDerivArray
  (JNIEnv *env, jclass LegendrePolynom, jint lmax, jdouble x) {
	jint len = lmax+1 ;
	jdouble *result = new jdouble[len] ;
	jdouble *resultDeriv = new jdouble[len] ;
	gsl_sf_legendre_Pl_deriv_array(lmax, x, result, resultDeriv) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, resultDeriv) ;
	delete[] result ;
	delete[] resultDeriv ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreQ0
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreQ0
  (JNIEnv *env, jclass LegendrePolynom, jdouble x) {
	return gsl_sf_legendre_Q0(x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreQ1
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreQ1
  (JNIEnv *env, jclass LegendrePolynom, jdouble x) {
	return gsl_sf_legendre_Q1(x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreQl
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreQl
  (JNIEnv *env, jclass LegendrePolynom, jint l, jdouble x) {
	return gsl_sf_legendre_Ql(l, x) ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreArray
  (JNIEnv *env, jclass LegendrePolynom, jint norm, jint lmax, jdouble x) {
	jint len = gsl_sf_legendre_array_n(lmax) ;
	jdouble *result = new jdouble[len] ;
	if(norm == org_gsl4j_polynom_LegendrePolynom_GSL_SF_LEGENDRE_NONE)
		gsl_sf_legendre_array(GSL_SF_LEGENDRE_NONE, lmax, x, result) ;
	else if(norm == org_gsl4j_polynom_LegendrePolynom_GSL_SF_LEGENDRE_SCHMIDT)
		gsl_sf_legendre_array(GSL_SF_LEGENDRE_SCHMIDT, lmax, x, result) ;
	else if(norm == org_gsl4j_polynom_LegendrePolynom_GSL_SF_LEGENDRE_SPHARM)
		gsl_sf_legendre_array(GSL_SF_LEGENDRE_SPHARM, lmax, x, result) ;
	else
		gsl_sf_legendre_array(GSL_SF_LEGENDRE_FULL, lmax, x, result) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_LegendrePolynom
 * Method:    legendreDerivArray
 * Signature: (IID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_LegendrePolynom_legendreDerivArray
  (JNIEnv *env, jclass LegendrePolynom, jint norm, jint lmax, jdouble x) {
	jint len = gsl_sf_legendre_array_n(lmax) ;
	jdouble *result = new jdouble[len] ;
	jdouble *result_deriv = new jdouble[len] ;
	if(norm == org_gsl4j_polynom_LegendrePolynom_GSL_SF_LEGENDRE_NONE)
		gsl_sf_legendre_deriv_array(GSL_SF_LEGENDRE_NONE, lmax, x, result, result_deriv) ;
	else if(norm == org_gsl4j_polynom_LegendrePolynom_GSL_SF_LEGENDRE_SCHMIDT)
		gsl_sf_legendre_deriv_array(GSL_SF_LEGENDRE_SCHMIDT, lmax, x, result, result_deriv) ;
	else if(norm == org_gsl4j_polynom_LegendrePolynom_GSL_SF_LEGENDRE_SPHARM)
		gsl_sf_legendre_deriv_array(GSL_SF_LEGENDRE_SPHARM, lmax, x, result, result_deriv) ;
	else
		gsl_sf_legendre_deriv_array(GSL_SF_LEGENDRE_FULL, lmax, x, result, result_deriv) ;
	jdoubleArray jresult = env -> NewDoubleArray(2*len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	env -> SetDoubleArrayRegion(jresult, len, len, result_deriv) ;
	delete[] result ;
	delete[] result_deriv ;
	return jresult ;
}


