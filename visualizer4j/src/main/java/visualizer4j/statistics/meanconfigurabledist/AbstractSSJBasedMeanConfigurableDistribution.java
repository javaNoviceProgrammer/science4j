package visualizer4j.statistics.meanconfigurabledist;

import umontreal.iro.lecuyer.probdist.Distribution;

public abstract class AbstractSSJBasedMeanConfigurableDistribution extends AbstractMeanConfigurableDistribution {

	Distribution dist;
	
	@Override
	public double inverseF(double rand) {
		return dist.inverseF(rand);
	}
	
}
