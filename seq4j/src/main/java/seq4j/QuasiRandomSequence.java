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
		if(this.dim<=0)
			throw new IllegalArgumentException("Dimension cannot be zero or negative") ;
	}

	/**
	 * This generator uses the algorithm described in Bratley, Fox, Niederreiter, ACM Trans. Model. Comp. Sim. 2, 195 (1992). It is valid up to 12 dimensions.
	 * @param index
	 * @return
	 */
	public native double[] niederreiter2(int index) ;
	
	public ArraySequence niederreiter2() {
		return n -> niederreiter2(n) ;
	}
	
	/**
	 * This generator uses the Sobol sequence described in Antonov, Saleev, USSR Comput. Maths. Math. Phys. 19, 252 (1980). It is valid up to 40 dimensions.
	 * @param index
	 * @return
	 */
	public native double[] sobol(int index) ;
	
	public ArraySequence sobol() {
		return n -> sobol(n) ;
	}
	
	/**
	 * These generators use the Halton and reverse Halton sequences described in J.H. Halton, Numerische Math- ematik, 2, 84-90 (1960) and B. Vandewoestyne and R. Cools Computational and Applied Mathematics, 189, 1&2, 341-361 (2006). They are valid up to 1229 dimensions.
	 * @param index
	 * @return
	 */
	public native double[] halton(int index) ;
	
	public ArraySequence halton() {
		return n -> halton(n) ;
	}
	
	/**
	 * These generators use the Halton and reverse Halton sequences described in J.H. Halton, Numerische Math- ematik, 2, 84-90 (1960) and B. Vandewoestyne and R. Cools Computational and Applied Mathematics, 189, 1&2, 341-361 (2006). They are valid up to 1229 dimensions.
	 * @param index
	 * @return
	 */
	public native double[] reverseHalton(int index) ;
	
	public ArraySequence reverseHalton() {
		return n -> reverseHalton(n) ;
	}

	public native double[][] niederreiter2All(int index) ;
	public native double[][] sobolAll(int index) ;
	public native double[][] haltonAll(int index) ;
	public native double[][] reverseHaltonAll(int index) ;
	
}
