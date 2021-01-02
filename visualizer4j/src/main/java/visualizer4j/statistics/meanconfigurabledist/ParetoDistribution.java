package visualizer4j.statistics.meanconfigurabledist;

import umontreal.iro.lecuyer.probdist.ParetoDist;
import visualizer4j.clazzes.ParamName;

public class ParetoDistribution extends AbstractSSJBasedMeanConfigurableDistribution {
	
	boolean isAlpha = false;
	double param;
	boolean nullMean = false;
	
	public ParetoDistribution(double a, @ParamName(name="Value is alpha = true") boolean b) {
		isAlpha = b;
		param = a;
	}
	
	public ParetoDistribution(double hurst) {
		isAlpha = true;
		param = 3 - (2*hurst);
	}
	
	public void setMean(double f) {
		if (f == 0) {
			nullMean = true;
		} else {
			if (isAlpha) {
				double beta = (f*(param-1))/param;
				dist = new ParetoDist(param, beta);
			} else {
				double alpha = f/(f+param);
				dist = new ParetoDist(alpha, param);
			}
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
	
	public ParetoDistribution duplicate() {
		ParetoDistribution copy = new ParetoDistribution(param, isAlpha);
		if (dist != null) {
			copy.setMean(dist.getMean());
		}
		return copy;
	}
	
	public String toString() {
		if (dist != null) {
			return "ParetoDist with mean " + dist.getMean();
		} else {
			if (nullMean) {
				return "ParetoDist with null mean";
			} else {
				return "ParetoDist not configured yet";
			}
		}
	}	
	
}
