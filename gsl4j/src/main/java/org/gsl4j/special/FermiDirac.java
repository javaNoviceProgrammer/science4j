package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class FermiDirac {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private FermiDirac() {

	}

	//Complete Fermi-Dirac Integrals
	public static native double fermiDiracM1(double x) ;
	public static native double fermiDriac0(double x) ;
	public static native double fermiDirac1(double x) ;
	public static native double fermiDirac2(double x) ;
	public static native double fermiDiracInt(int n, double x) ;
	public static native double fermiDiracMhalf(double x) ;
	public static native double fermiDiractHalf(double x) ;
	public static native double fermiDirac3half(double x) ;

	// Incomplete Fermi-Dirac Integrals
	public static native double fermiDiractIncomplete0(double x, double b) ;

}
