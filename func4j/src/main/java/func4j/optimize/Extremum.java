package func4j.optimize;

public class Extremum {
	
	double value ;
	boolean isMinimum ;
	boolean isMaximum ;
	boolean isInflation ;
	
	public Extremum(double value, boolean isMinimum, boolean isMaximum) {
		this.value = value ;
		this.isMinimum = isMinimum ;
		this.isMaximum = isMaximum ;
		this.isInflation = !(isMaximum || isMinimum) ;
	}
	
	public double get() {
		return value ;
	}
	
	public boolean isMinimum() {
		return isMinimum ;
	}
	
	public boolean isMaximum() {
		return isMaximum ;
	}
	
	public boolean isInflation() {
		return isInflation ;
	}

	@Override
	public String toString() {
		if(isMinimum)
			return "Extremum [value=" + value + ", isMinimum=" + isMinimum + "]";
		else if(isMaximum)
			return "Extremum [value=" + value + ", isMaximum=" + isMaximum + "]";
		else
			return "Extremum [value=" + value + ", isInflation=" + isInflation + "]";
	}

}
