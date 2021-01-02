package visualizer4j.statistics;

import umontreal.iro.lecuyer.probdist.ContinuousDistribution;

public class ConstantDist extends ContinuousDistribution {

	private double value;

	public ConstantDist(double value) {
		this.value = value;
	}

	public double[] getParams() {
		return new double[]{value};
	}

	public double getStandardDeviation() {
		return 0;
	}

	public double getVariance() {
		return 0;
	}

	public double getMean() {
		return value;
	}

	public double cdf(double d) {
		if (d < value) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public double density(double parm1) {
		if (Math.abs(parm1 - value) < 0.000001d) {
			return 1;
		}
		return 0;
	}

	@Override
	public double inverseF(double d) {
		return value;
	}


}
