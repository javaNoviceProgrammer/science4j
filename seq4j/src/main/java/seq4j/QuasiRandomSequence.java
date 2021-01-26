package seq4j;

import seq4j.natives.NativeLibraryLoader;

public class QuasiRandomSequence {

	static {
		NativeLibraryLoader.loadLibraries("/libseq4j_c");
		initIDs() ;
	}

	private static native void initIDs() ;

	int dim ;

	public QuasiRandomSequence(int dim) {
		this.dim = dim ;
		if(this.dim==0)
			throw new IllegalArgumentException("Dimension cannot be zero") ;
	}

	public native double[] niederreiter2(int index) ;
	public native double[] sobol(int index) ;
	public native double[] halton(int index) ;
	public native double[] reverseHalton(int index) ;

	public native double[][] niederreiter2All(int index) ;
	public native double[][] sobolAll(int index) ;
	public native double[][] haltonAll(int index) ;
	public native double[][] reverseHaltonAll(int index) ;

}
