package latex4j.math;

import latex4j.base.Environment;

public class SubEquationsEnv extends Environment {

	public SubEquationsEnv() {
		super("subequations");
	}
	
	public SubEquationsEnv addEquation(EquationEnv eqn) {
		text.add(eqn) ;
		return this ;
	}

}
