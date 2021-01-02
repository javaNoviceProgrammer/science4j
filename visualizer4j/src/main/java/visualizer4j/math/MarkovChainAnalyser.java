package visualizer4j.math;

public class MarkovChainAnalyser {
	
	private double[][] transitions;
	
	public MarkovChainAnalyser(double[][] transition) {
		this.transitions = transition;

		for (int i = 0 ; i < transitions.length ; i++) {
			double accum = 0;			
			for (int j = 0 ; j < transitions[0].length ; j++) {
				accum += transitions[i][j];
			}
			for (int j = 0 ; j < transitions[0].length ; j++) {
				transitions[i][j] /= accum;
			}
		}
	}
	
	public double[] compute() {
		double p[] = new double[transitions[0].length];
		p[0] = 1;
		double[] nextP = p;
		do {
			p = nextP;
			nextP = MoreMaths.matricialProduct(p, transitions);
		}
		while (Math.abs(p[0] - nextP[0]) > 0.00001);
		return p;
	}
	
	
	
	public static void main(String[] args) {
		double mu = 10000000;
		
	/*	double[] l = new double[]{0.024390243902439025,
				0.047619047619047616,
				0.06976744186046513,
				0.09090909090909091,
				0.1111111111111111,
				0.13043478260869568,
				0.14893617021276598,
				0.16666666666666666,
				0.1836734693877551,
				0.2,
				0.21568627450980393,
				0.23076923076923078,
				0.24528301886792453,
				0.25925925925925924,
				0.2727272727272727,
				0.2857142857142857,
				0.29824561403508776,
				0.3103448275862069,
				0.3220338983050848,
				0.33333333333333337};*/
		
		for (int i = 1 ; i <= 20 ; i++) {
		//	double a = l[i]/2;
			double lam = 0.05*i;
		//	double gamma = (a*mu);///(1 - a);
			
		//	double[][] trans = new double[][]{{2*mu, 4*gamma},{2*mu, 2*gamma+2*mu}};
			double[][] trans = new double[][]{{0, lam, 0},{mu, 0, lam/2},{0, 3*mu/2, 0}};
			
			System.out.print((new MarkovChainAnalyser(trans)).compute()[1] + "\t");
		}
		
	}

}
