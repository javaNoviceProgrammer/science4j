package org.gsl4j.integration;

import org.gsl4j.util.NativeLibraryLoader;

public class IntegralND {

	static {
		NativeLibraryLoader.loadLibraries();
		initFieldIDs();
	}

	private static native void initFieldIDs();

	IntegralFunctionND func;
	double absErr = 1e-10;
	double relErr = 1e-10;
	int maxNumberOfIntervals = 100;
	int dim = 1 ;

	public IntegralND(IntegralFunctionND func, int dim) {
		this.func = func ;
		this.dim = dim ;
	}

	public IntegralND(int dim) {
		this.func = null ;
		this.dim = dim ;
	}

	public void setIntegralFunction(IntegralFunctionND func) {
		this.func = func ;
	}

	public void setDimension(int dim) {
		this.dim = dim ;
	}







}
