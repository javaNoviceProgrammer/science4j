/*
 * specfunc.h
 *
 *  Created on: Jan 23, 2021
 *      Author: meisam
 */

extern "C" void cjylv_(double& V, double* Z, double* CBJV, double* CDJV, double* CBYV, double* CDYV) ;

// complex error function erf(z) & erf'(z)
extern "C" void cerf_(double* Z, double* CER, double* CDER) ;

// double precision NaN
extern "C" double dnan_() ;

// double precision inf
extern "C" double dinf_() ;

// Compute complex parabolic cylinder function Dn(z) for small argument
extern "C" void cpdsa_(int& N, double* Z, double* CDN) ;

// Compute complex Fresnel Integral S(z) and S'(z)
extern "C" void cfs_(double* Z, double* ZF, double* ZD) ;

// Compute the associated Legendre functions of the second kind, Qmn(x) and Qmn'(x)
extern "C" void lqmn_(int& MM, int& M, int& N, double& X, double& QM, double& QD) ;

// Compute the associated Legendre functions Pmn(z) and their derivatives Pmn'(z) for a complex argument
extern "C" void clpmn_(int& MM, int& M, int& N, double& X, double& Y, int& NTYPE, double* CPM, double* CPD) ;

// Compute parabolic cylinder function Vv(x) for small argument
extern "C" void vvsa_(int& VA, double& X, double& PV) ;

// Compute the zeros of Bessel functions Jn(x) and Jn'(x), and arrange them in the order of their magnitudes
extern "C" void jdz0_(int& NT, int* N, int* M, int* P, double* ZO) ;

// Compute the gamma function Г(z) or ln[Г(z)] for a complex argument
extern "C" void cgama_(double& X, double& Y, int& KF, double& GR, double& GI) ;


