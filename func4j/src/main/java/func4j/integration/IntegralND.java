package func4j.integration;

import func4j.natives.NativeLibraryLoader;

public class IntegralND {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
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
