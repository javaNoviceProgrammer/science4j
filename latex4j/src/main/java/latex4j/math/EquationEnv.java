package latex4j.math;

import latex4j.base.Environment;

public class EquationEnv extends Environment {
	
	/*
	 * inline equatoins: $\beta = f(\zeta)$
	 * 
	 * display:
	 * 			\begin{equation}
	 * 
	 * 			\end{equation}
	 * 
	 */
	
	boolean isInline = false ;
	boolean isNumbered = false ;

	public EquationEnv() {
		super("equation");
	}
	
	public void inline(boolean flag) {
		this.isInline = flag ;
		this.isFloating = false ;
		this.isNumbered = false ;
	}

	@Override
	public String toString() {
		if(isInline) {
			StringBuilder env = new StringBuilder() ;
			env.append("$") ;
			for(String s : text) {
				env.append(s) ;
			}
			env.append("$") ;
			return env.toString() ;
		}
		else {
			return super.toString();
		}
	}
	
	

}
