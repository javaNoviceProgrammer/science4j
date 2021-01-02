package visualizer4j.statistics.meanconfigurabledist;


public abstract class AbstractMeanConfigurableDistribution {
	
	public abstract void setMean(double d);
	
	public abstract double inverseF(double rand);
	
	public abstract AbstractMeanConfigurableDistribution duplicate();
}
