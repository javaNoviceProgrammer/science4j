package visualizer4j.statistics.meanconfigurabledist;

import umontreal.iro.lecuyer.probdist.ExponentialDist;

public class ExponentialDistribution extends AbstractSSJBasedMeanConfigurableDistribution {
	

	boolean nullMean = false;
	
	public ExponentialDistribution() {}
	
	public void setMean(double f) {
		if (f == 0) {
			nullMean = true;
		} else {
			dist = new ExponentialDist(1d/f);
		}
	}
	
	@Override
	public double inverseF(double rand) {
		if (nullMean) {
			return 0;
		} else {
			return dist.inverseF(rand);
		}
	}
	
	public ExponentialDistribution duplicate() {
		ExponentialDistribution copy = new ExponentialDistribution();
		if (dist != null) {
			copy.setMean(dist.getMean());
		}
		return copy;
	}
	
	public String toString() {
		if (dist != null) {
			return "ExpoDist with mean " + dist.getMean();
		} else {
			if (nullMean) {
				return "ExpoDist with null mean";
			} else {
				return "ExpoDist not configured yet";
			}
		}
	}
	
}
