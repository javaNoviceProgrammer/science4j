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
	boolean isBoxed = false ;

	public EquationEnv() {
		super("equation");
	}
	
	public EquationEnv inline(boolean flag) {
		isInline = flag ;
		isFloating = false ;
		isNumbered = false ;
		isBoxed = false ;
		return this ;
	}
	
	public EquationEnv boxed(boolean flag) {
		isBoxed = true ;
		isInline = false ;
		return this ;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public EquationEnv addText(String text) {
		if(isBoxed) {
			// add extra text: \boxed{text}
			this.text.add("\\boxed{" + text + "}") ;
			return this ;
		}
		else
			return super.addText(text);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EquationEnv addText(Object text) {
		if(isBoxed) {
			this.text.add("\\boxed{" + text + "}") ;
			return this ;
		}
		else
			return super.addText(text);
	}

	@Override
	public String toString() {
		if(isInline) {
			StringBuilder env = new StringBuilder() ;
			env.append("$") ;
			for(Object s : text) {
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
