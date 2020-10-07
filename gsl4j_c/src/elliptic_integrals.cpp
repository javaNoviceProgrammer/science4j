/*
 * elliptic_integrals.cpp
 *
 *  Created on: Jun 5, 2020
 *      Author: meisam
 */

#include "../headers/org_gsl4j_special_EllipticIntegrals.h"
#include <gsl/gsl_sf_ellint.h>
#include <gsl/gsl_sf_elljac.h>


/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintKcomp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintKcomp
  (JNIEnv *env, jclass ellipticIntegrals, jdouble k) {
	return gsl_sf_ellint_Kcomp(k, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintEcomp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintEcomp
  (JNIEnv *env, jclass ellipticIntegrals, jdouble k) {
	return gsl_sf_ellint_Ecomp(k, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintPcomp
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintPcomp
  (JNIEnv *env, jclass ellipticIntegrals, jdouble k, jdouble n) {
	return gsl_sf_ellint_Pcomp(k, n, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintF
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintF
  (JNIEnv *env, jclass ellipticIntegrals, jdouble phi, jdouble k) {
	return gsl_sf_ellint_F(phi, k, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintE
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintE
  (JNIEnv *env, jclass ellipticIntegrals, jdouble phi, jdouble k) {
	return gsl_sf_ellint_E(phi, k, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintP
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintP
  (JNIEnv *env, jclass ellipticIntegrals, jdouble phi, jdouble k, jdouble n) {
	return gsl_sf_ellint_P(phi, k, n, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintD
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintD
  (JNIEnv *env, jclass ellipticIntegrals, jdouble phi, jdouble k) {
	return gsl_sf_ellint_D(phi, k, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintRC
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintRC
  (JNIEnv *env, jclass ellipticIntegrals, jdouble x, jdouble y) {
	return gsl_sf_ellint_RC(x, y, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintRD
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintRD
  (JNIEnv *env, jclass ellipticIntegrals, jdouble x, jdouble y, jdouble z) {
	return gsl_sf_ellint_RD(x, y, z, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintRF
 * Signature: (DDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintRF
  (JNIEnv *env, jclass ellipticIntegrals, jdouble x, jdouble y, jdouble z) {
	return gsl_sf_ellint_RF(x, y, z, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    ellintRJ
 * Signature: (DDDD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_ellintRJ
  (JNIEnv *env, jclass ellipticIntegrals, jdouble x, jdouble y, jdouble z, jdouble p) {
	return gsl_sf_ellint_RJ(x, y, z, p, GSL_PREC_DOUBLE) ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    elljacSn
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_elljacSn
  (JNIEnv *env, jclass ellipticIntegrals, jdouble u, jdouble m) {
	jdouble sn, cn, dn ;
	gsl_sf_elljac_e(u, m, &sn, &cn, &dn) ;
	return sn ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    elljacCn
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_elljacCn
  (JNIEnv *env, jclass ellipticIntegrals, jdouble u, jdouble m) {
	jdouble sn, cn, dn ;
	gsl_sf_elljac_e(u, m, &sn, &cn, &dn) ;
	return cn ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    elljacDn
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_special_EllipticIntegrals_elljacDn
  (JNIEnv *env, jclass ellipticIntegrals, jdouble u, jdouble m) {
	jdouble sn, cn, dn ;
	gsl_sf_elljac_e(u, m, &sn, &cn, &dn) ;
	return dn ;
}

/*
 * Class:     org_gsl4j_special_EllipticIntegrals
 * Method:    elljac
 * Signature: (DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_special_EllipticIntegrals_elljac
  (JNIEnv *env, jclass ellipticIntegrals, jdouble u, jdouble m) {
	jdouble sn, cn, dn ;
	gsl_sf_elljac_e(u, m, &sn, &cn, &dn) ;
	jdouble buf[3] = {sn, cn, dn} ;
	jdoubleArray result = env -> NewDoubleArray(3) ;
	env -> SetDoubleArrayRegion(result, 0, 3, buf) ;
	return result ;
}



