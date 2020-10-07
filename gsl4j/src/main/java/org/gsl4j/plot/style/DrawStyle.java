package org.gsl4j.plot.style;

public enum DrawStyle {

	defaults("default") ,
	steps("steps") ,
	stepsPre("steps-pre") ,
	stepsMid("steps-mid") ,
	stepsPost("steps-post") ;

	private String value ;

	private DrawStyle(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
