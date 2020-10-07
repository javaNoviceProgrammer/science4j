/*
 * polynom_math.cpp
 *
 *  Created on: Jun 15, 2020
 *      Author: meisam
 */

#include <gsl/gsl_poly.h>
#include <gsl/gsl_math.h>
#include "../headers/org_gsl4j_polynom_PolynomialMath.h"


/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    eval
 * Signature: ([DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_PolynomialMath_eval___3DD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble x) {
	jint len = env -> GetArrayLength(coeffs) ;
	jdouble *c = new jdouble[len] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len, c) ;
	jdouble jresult = gsl_poly_eval(c, len, x) ;
	delete[] c ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    eval
 * Signature: ([DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_eval___3DDD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble re, jdouble im) {
	jint len = env -> GetArrayLength(coeffs) ;
	jdouble *c = new jdouble[len] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len, c) ;
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_poly_complex_eval(c, len, z) ;
	delete[] c ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	jdouble buffer[2] {result.dat[0], result.dat[1] } ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    eval
 * Signature: ([D[DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_eval___3D_3DDD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffsReal, jdoubleArray coeffsImag, jdouble re, jdouble im) {
	jint len = env -> GetArrayLength(coeffsReal) ;
	jdouble *c_real = new jdouble[len] ;
	jdouble *c_imag = new jdouble[len] ;
	env -> GetDoubleArrayRegion(coeffsReal, 0, len, c_real) ;
	env -> GetDoubleArrayRegion(coeffsImag, 0, len, c_imag) ;
	gsl_complex *c_complex = new gsl_complex[len] ;
	for(int i=0; i<len; i++) {
		c_complex[i].dat[0] = c_real[i] ;
		c_complex[i].dat[1] = c_imag[i] ;
	}
	gsl_complex z {{re, im}} ;
	gsl_complex result = gsl_complex_poly_complex_eval(c_complex, len, z) ;
	delete[] c_real ;
	delete[] c_imag ;
	delete[] c_complex ;
	jdoubleArray jresult = env -> NewDoubleArray(2) ;
	jdouble buffer[2] {result.dat[0], result.dat[1] } ;
	env -> SetDoubleArrayRegion(jresult, 0, 2, buffer) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    evalDerivs
 * Signature: ([DID)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_evalDerivs
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jint lenres, jdouble x) {
	jint len = env -> GetArrayLength(coeffs) ;
	jdouble *c = new jdouble[len] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len, c) ;
	jdouble *result = new jdouble[lenres] ;
	gsl_poly_eval_derivs(c, len, x, result, lenres) ;
	jdoubleArray jresult = env -> NewDoubleArray(lenres) ;
	env -> SetDoubleArrayRegion(jresult, 0, lenres, result) ;
	delete[] c ;
	delete[] result ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    solveQuadraticRealRoots
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_solveQuadraticRealRoots
  (JNIEnv *env, jclass PolynomialMath, jdouble a, jdouble b, jdouble c) {
	jdouble x0 = GSL_NAN ;
	jdouble x1 = GSL_NAN ;
	gsl_poly_solve_quadratic(a, b, c, &x0, &x1) ;
	jint len = 2 ;
	jdouble result[] = {x0, x1} ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    solveQuadraticComplexRoots
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_solveQuadraticComplexRoots
  (JNIEnv *env, jclass PolynomialMath, jdouble a, jdouble b, jdouble c) {
	gsl_complex z0 {{GSL_NAN, GSL_NAN}} ;
	gsl_complex z1 {{GSL_NAN, GSL_NAN}} ;
	gsl_poly_complex_solve_quadratic(a, b, c, &z0, &z1) ;
	jint len = 4 ;
	jdouble result[] = {z0.dat[0], z0.dat[1], z1.dat[0], z1.dat[1]} ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    solveCubicRealRoots
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_solveCubicRealRoots
  (JNIEnv *env, jclass PolynomialMath, jdouble a, jdouble b, jdouble c) {
	jdouble x0 = GSL_NAN ;
	jdouble x1 = GSL_NAN ;
	jdouble x2 = GSL_NAN ;
	gsl_poly_solve_cubic(a, b, c, &x0, &x1, &x2) ;
	jint len = 3 ;
	jdouble result[] = {x0, x1, x2} ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    solveCubicComplexRoots
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_solveCubicComplexRoots
  (JNIEnv *env, jclass PolynomialMath, jdouble a, jdouble b, jdouble c) {
	gsl_complex z0 {{GSL_NAN, GSL_NAN}} ;
	gsl_complex z1 {{GSL_NAN, GSL_NAN}} ;
	gsl_complex z2 {{GSL_NAN, GSL_NAN}} ;
	gsl_poly_complex_solve_cubic(a, b, c, &z0, &z1, &z2) ;
	jint len = 6 ;
	jdouble result[] = {z0.dat[0], z0.dat[1], z1.dat[0], z1.dat[1], z2.dat[0], z2.dat[1]} ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    solvePolynomialComplexRoots
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_solvePolynomialComplexRoots
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	gsl_poly_complex_workspace *w = gsl_poly_complex_workspace_alloc(len_coeffs) ;
	jint len = 2*(len_coeffs-1) ;
	jdouble *result = new jdouble[len] ;
	gsl_poly_complex_solve(coeffs_array, len_coeffs, w, result) ;
	gsl_poly_complex_workspace_free(w) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	delete[] coeffs_array ;
	return jresult ;
}


/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    plus
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_plus___3D_3D
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs1, jdoubleArray coeffs2) {
	jint len_coeffs1 = env -> GetArrayLength(coeffs1) ;
	jdouble *coeffs1_array = new jdouble[len_coeffs1] ;
	env -> GetDoubleArrayRegion(coeffs1, 0, len_coeffs1, coeffs1_array) ;
	jint len_coeffs2 = env -> GetArrayLength(coeffs2) ;
	jdouble *coeffs2_array = new jdouble[len_coeffs2] ;
	env -> GetDoubleArrayRegion(coeffs2, 0, len_coeffs2, coeffs2_array) ;
	jint len_coeffs_sum = GSL_MAX(len_coeffs1, len_coeffs2) ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = 0.0 ;
	for(jint i=0; i<len_coeffs1; i++)
		coeffs_sum[i] += coeffs1_array[i] ;
	for(jint i=0; i<len_coeffs2; i++)
		coeffs_sum[i] += coeffs2_array[i] ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs1_array ;
	delete[] coeffs2_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    minus
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_minus___3D_3D
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs1, jdoubleArray coeffs2) {
	jint len_coeffs1 = env -> GetArrayLength(coeffs1) ;
	jdouble *coeffs1_array = new jdouble[len_coeffs1] ;
	env -> GetDoubleArrayRegion(coeffs1, 0, len_coeffs1, coeffs1_array) ;
	jint len_coeffs2 = env -> GetArrayLength(coeffs2) ;
	jdouble *coeffs2_array = new jdouble[len_coeffs2] ;
	env -> GetDoubleArrayRegion(coeffs2, 0, len_coeffs2, coeffs2_array) ;
	jint len_coeffs_sum = GSL_MAX(len_coeffs1, len_coeffs2) ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = 0.0 ;
	for(jint i=0; i<len_coeffs1; i++)
		coeffs_sum[i] += coeffs1_array[i] ;
	for(jint i=0; i<len_coeffs2; i++)
		coeffs_sum[i] -= coeffs2_array[i] ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs1_array ;
	delete[] coeffs2_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    times
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_times___3D_3D
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs1, jdoubleArray coeffs2) {
	jint len_coeffs1 = env -> GetArrayLength(coeffs1) ;
	jdouble *coeffs1_array = new jdouble[len_coeffs1] ;
	env -> GetDoubleArrayRegion(coeffs1, 0, len_coeffs1, coeffs1_array) ;
	jint len_coeffs2 = env -> GetArrayLength(coeffs2) ;
	jdouble *coeffs2_array = new jdouble[len_coeffs2] ;
	env -> GetDoubleArrayRegion(coeffs2, 0, len_coeffs2, coeffs2_array) ;
	jint len_coeffs_sum = len_coeffs1 + len_coeffs2 ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = 0.0 ;
	for(jint i=0; i<len_coeffs1; i++)
		for(jint j=0; j<len_coeffs2; j++) {
			coeffs_sum[i+j] += coeffs1_array[i]*coeffs2_array[j] ;
		}
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs1_array ;
	delete[] coeffs2_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    compose
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_compose
  (JNIEnv *, jclass, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    plus
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_plus___3DD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble a) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_sum = len_coeffs ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = coeffs_array[i] ;
	coeffs_sum[0] += a ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    minus
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_minus___3DD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble a) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_sum = len_coeffs ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = coeffs_array[i] ;
	coeffs_sum[0] -= a ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    times
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_times___3DD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble a) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_sum = len_coeffs ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = a*coeffs_array[i] ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    divides
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_divides
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble a) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_sum = len_coeffs ;
	jdouble *coeffs_sum = new jdouble[len_coeffs_sum] ;
	for(jint i=0; i<len_coeffs_sum; i++)
		coeffs_sum[i] = coeffs_array[i]/a ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_sum) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_sum, coeffs_sum) ;
	delete[] coeffs_array ;
	delete[] coeffs_sum ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    ofRoots
 * Signature: ([D[D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_ofRoots
  (JNIEnv *, jclass, jdoubleArray, jdoubleArray);

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    pow
 * Signature: ([DI)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_pow
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jint m) {

}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    diff
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_diff___3D
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_diff = len_coeffs-1 ;
	jdouble *coeffs_diff = new jdouble[len_coeffs_diff] ;
	for(jint i=0; i<len_coeffs_diff; i++)
		coeffs_diff[i] = (i+1)*coeffs_array[i+1] ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_diff) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_diff, coeffs_diff) ;
	delete[] coeffs_array ;
	delete[] coeffs_diff ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    diff
 * Signature: ([DI)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_diff___3DI
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jint s) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_diff = len_coeffs-s ;
	jdouble *coeffs_diff = new jdouble[len_coeffs_diff] ;
	for(jint i=0; i<len_coeffs_diff; i++) {
		// find the coeff
		jdouble coeff_i = 1.0 ;
		for(jint j=0; j<s; j++) {
			coeff_i *= i+1+j ;
		}
		coeffs_diff[i] = coeff_i*coeffs_array[i+s] ;
	}
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_diff) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_diff, coeffs_diff) ;
	delete[] coeffs_array ;
	delete[] coeffs_diff ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    integrate
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_integrate___3D
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_diff = len_coeffs+1 ;
	jdouble *coeffs_diff = new jdouble[len_coeffs_diff] ;
	coeffs_diff[0] = 0.0 ;
	for(jint i=1; i<len_coeffs_diff; i++)
		coeffs_diff[i] = coeffs_array[i-1]/i ;
	jdoubleArray jresult = env -> NewDoubleArray(len_coeffs_diff) ;
	env -> SetDoubleArrayRegion(jresult, 0, len_coeffs_diff, coeffs_diff) ;
	delete[] coeffs_array ;
	delete[] coeffs_diff ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    integrate
 * Signature: ([DI)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_polynom_PolynomialMath_integrate___3DI
  (JNIEnv *, jclass, jdoubleArray, jint);

/*
 * Class:     org_gsl4j_polynom_PolynomialMath
 * Method:    integrate
 * Signature: ([DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_polynom_PolynomialMath_integrate___3DDD
  (JNIEnv *env, jclass PolynomialMath, jdoubleArray coeffs, jdouble x0, jdouble x1) {
	jint len_coeffs = env -> GetArrayLength(coeffs) ;
	jdouble *coeffs_array = new jdouble[len_coeffs] ;
	env -> GetDoubleArrayRegion(coeffs, 0, len_coeffs, coeffs_array) ;
	jint len_coeffs_diff = len_coeffs+1 ;
	jdouble *coeffs_diff = new jdouble[len_coeffs_diff] ;
	coeffs_diff[0] = 0.0 ;
	for(jint i=1; i<len_coeffs_diff; i++)
		coeffs_diff[i] = coeffs_array[i-1]/i ;
	jdouble result0 = gsl_poly_eval(coeffs_diff, len_coeffs_diff, x0) ;
	jdouble result1 = gsl_poly_eval(coeffs_diff, len_coeffs_diff, x1) ;
	jdouble jresult = result1 - result0 ;
	delete[] coeffs_array ;
	delete[] coeffs_diff ;
	return jresult ;
}








