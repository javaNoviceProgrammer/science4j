/*
 * amos.h
 *
 *  Created on: Jan 21, 2021
 *      Author: meisam
 */

// Airy function (zairy.f)
extern "C" void zairy_(double& ZR, double& ZI, int& ID, int& KODE, double& AIR, double& AII, int& NZ, int& IERR) ;

// Airy function (zbiry.f)
extern "C" void zbiry_(double& ZR, double& ZI, int& ID, int& KODE, double& BIR, double& BII, int& IERR) ;

// BesselJ function (zbesj.f)
extern "C" void zbesj_(double& ZR, double& ZI, double& FNU, int& KODE, int& N, double& CYR, double& CYI, int& NZ, int& IERR) ;

// BesselY function (zbesy.f)
extern "C" void zbesy_(double& ZR, double& ZI, double& FNU, int& KODE, int& N, double& CYR, double& CYI, int& NZ, double* CWRKR, double* CWRKI, int& IERR) ;
