package visualizer4j.statistics.meanconfigurabledist;

public class NormalDist extends AbstractSSJBasedMeanConfigurableDistribution {
	
	private double sigma;
	
	public NormalDist(double sigma) {
		this.sigma = sigma;
	}

	@Override
	public void setMean(double d) {
		dist = new umontreal.iro.lecuyer.probdist.NormalDist(d, sigma);
	}

	@Override
	public AbstractMeanConfigurableDistribution duplicate() {
		NormalDist d = new NormalDist(sigma);
		if (dist != null) {
			d.dist = new umontreal.iro.lecuyer.probdist.NormalDist(dist.getMean(), sigma);
		}
		return d;
	}

}
