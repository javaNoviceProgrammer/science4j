/*
 * specfunc.h
 *
 *  Created on: Jan 23, 2021
 *      Author: meisam
 */


extern "C" void cjylv_(double& V, double* Z, double* CBJV, double* CDJV, double* CBYV, double* CDYV) ;

// complex error function erf(z) & erf'(z)
extern "C" void cerf_(double* Z, double* CER, double* CDER) ;

