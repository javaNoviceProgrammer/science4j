package visualizer4j.statistics.meanconfigurabledist;



public class ConstantDistribution extends AbstractMeanConfigurableDistribution {
	
	public double val;
	
	public ConstantDistribution() {
	}
	
	public void setMean(double f) {
		val = f;
	}	
	
	public double inverseF(double rand) {
		return val;
	}
	
	public ConstantDistribution duplicate() {
		ConstantDistribution dis = new ConstantDistribution();
		dis.setMean(val);
		return dis;
	}
	
	public String toString() {
		return "Constant value = " + val;
	}	
}
