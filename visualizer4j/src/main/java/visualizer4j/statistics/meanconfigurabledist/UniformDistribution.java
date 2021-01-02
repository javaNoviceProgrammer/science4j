package visualizer4j.statistics.meanconfigurabledist;

import visualizer4j.clazzes.ConstructorDef;

public class UniformDistribution extends
		AbstractSSJBasedMeanConfigurableDistribution {
	
	double a;
	double b;
	boolean isA = false;
	
	@ConstructorDef(def="Fix lower number")
	public UniformDistribution(double a) {
		this.a = a;
		isA = true;
	}
	
	@ConstructorDef(def="Fix higher number")
	public UniformDistribution(float b) {
		this.b = (int)b;
	}

	@Override
	public void setMean(double mu) {
		if (isA) {
			b = (2*mu - a);
		} else {
			a = (2*mu - b);
		}
		dist = new umontreal.iro.lecuyer.probdist.UniformDist(a, b);
	}

	@Override
	public AbstractMeanConfigurableDistribution duplicate() {
		if (isA) {
			return new UniformDistribution(a);
		} else {
			return new UniformDistribution((float)b);
		}	
	}

}
