package visualizer4j.statistics.meanconfigurabledist;

import umontreal.iro.lecuyer.probdist.UniformIntDist;
import visualizer4j.clazzes.ConstructorDef;

public class UniformIntDistribution extends AbstractSSJBasedMeanConfigurableDistribution {
	
	int a;
	int b;
	boolean isA = false;
	
	@ConstructorDef(def="Fix lower number")
	public UniformIntDistribution(int a) {
		this.a = a;
		isA = true;
	}
	
	@ConstructorDef(def="Fix higher number")
	public UniformIntDistribution(long b) {
		this.b = (int)b;
	}	

	@Override
	public void setMean(double mu) {
		if (isA) {
			b = (int)(2*mu - a);
		} else {
			a = (int)(2*mu - b);
		}
		dist = new UniformIntDist(a, b);
	}


	@Override
	public AbstractMeanConfigurableDistribution duplicate() {
			if (isA) {
				return new UniformIntDistribution(a);
			} else {
				return new UniformIntDistribution((long)b);
			}	
	}

}
