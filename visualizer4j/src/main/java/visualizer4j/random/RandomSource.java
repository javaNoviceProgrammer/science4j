package visualizer4j.random;

import java.util.Random;

public interface RandomSource {
	public double nextGaussian();
	public double nextRandom();
	public Random toRandom();
	public double peekNextRandom();
	public double state();
	public int getIndex();
	public int generationStep();
	public void incGenStep();
}
