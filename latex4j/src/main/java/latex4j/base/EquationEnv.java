package latex4j.base;

public class EquationEnv extends Environment {
	
	/*
	 * inline equatoins: $\beta = f(\zeta)$
	 * 
	 */
	
	boolean isInline = false ;

	public EquationEnv() {
		super("equation");
	}
	
	public void inline(boolean flag) {
		this.isInline = flag ;
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
