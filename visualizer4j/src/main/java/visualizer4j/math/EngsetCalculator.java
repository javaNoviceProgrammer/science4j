package visualizer4j.math;

public class EngsetCalculator {


	public static void main(String[] args) {
		for (int i = 0 ; i < 20 ; i++) {
			double traf = (i+1)*0.05;
			System.out.print(engsetBlockingViaCarriedTraffic(traf, 2, 1) + "\t");
		}
	}
	
	/*public static double engsetBlocking(double beta, int sources, int outputs) {
		double up = part(beta, sources-1, outputs);
		double down = 0;
		for (int i = 0 ; i <= outputs ; i++) {
			down += part(beta, sources-1, i);
		}
		return up/down;
	}*/
	
	/*public static double engset(double beta, int sources, int outputs) {
		double up = part(beta, sources, outputs);
		double down = 0;
		for (int i = 0 ; i <= outputs ; i++) {
			down += part(beta, sources, i);
		}
		return up/down;		
	}*/
	
	public static double timeCongestionE(double beta, int sources, int outputs) {
		double up = part(beta, sources, outputs);
		double down = 0;
		for (int i = 0 ; i <= outputs ; i++) {
			down += part(beta, sources, i);
		}
		return up/down;			
	}
	
	public static double carriedTraffic(double totalTraffic, int sources, int outputs) {
		double beta = totalTraffic / (sources - totalTraffic);
		
		double t = beta/(1+beta);
		t *= sources - (sources - outputs) * timeCongestionE(beta, sources, outputs);
		return t;
	}
	
	public static double engsetBlockingViaCarriedTraffic(double totalTraffic, int sources, int outputs) {
		return 1 - carriedTraffic(totalTraffic, sources, outputs)/totalTraffic;
	}
	
	private static double part(double beta, int sources, int outputs) {
		return MoreMaths.comb(sources, outputs) * Math.pow(beta, outputs);
	}


}
