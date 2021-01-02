package visualizer4j.math;

import java.math.BigDecimal;
import java.math.MathContext;

import visualizer4j.utils.BigDecimalXX;

public class ErlangBCalculator {
	
	public static double erlangBacceptedRate(int output, double offeredRate) {
		double blo = erlangB(output, offeredRate);
		double inverseBlo = 1-blo;
		double accrate = inverseBlo*offeredRate;
		
		return accrate;
	}
	
	public static double erlangB(int outputs, double offeredRate) {
		double num = Math.pow(offeredRate, outputs);
		double fac = MoreMaths.factorial((double)outputs);
		num = num/fac;
		double den = 0;
		for (int i = 0 ; i <= outputs ; i++) {
			double d;
			if (i == 0) 
				d = 1;
			else
				d = Math.pow(offeredRate, i);
			d = d/MoreMaths.factorial((double)i);
			den += d;
		}
		return num/den;
	}
	
	public static BigDecimal erlangB(BigDecimal A, int n, int y, MathContext mc){
		BigDecimal facto = BigDecimalXX.factorial(y, mc);
		BigDecimal numerator  = A.pow(y, mc);
		numerator = numerator.divide(facto, mc);
		BigDecimal ac = new BigDecimal("0",mc);
		for(int i = 1; i <=n; i++){
			BigDecimal aux2 = BigDecimalXX.factorial(i,mc);
			ac = ac.add(A.pow(i, mc).divide(aux2, mc));
		}
		BigDecimal denominator = ac.add(new BigDecimal("1", mc));
		return numerator.divide(denominator, mc);
	}

	public static BigDecimal erlangB(BigDecimalXX A, int n, int y){
		return erlangB(A, n, y, A.getContext());
	}	
	
	public static double invErlangBApprox(double offeredRate, double lossProb) {
	//	System.out.println(-Math.log10(lossProb));
		double r = offeredRate + ((-Math.log10(lossProb))*Math.sqrt(offeredRate));
	//	System.out.println((-Math.log10(lossProb))*Math.sqrt(offeredRate));
		return r;
	}
	
	public static void main(String[] args) {
		
		double[] lambda1 = new double[]{0.048780488,	0.095238095,	0.139534884,	0.181818182,	0.222222222,
				0.260869565,	0.29787234,	0.333333333,	0.367346939,	0.4,	0.431372549,	0.461538462,
				0.490566038,	0.518518519,	0.545454545,	0.571428571,	0.596491228,	0.620689655,
				0.644067797,	0.666666667
		};

		
		
		for (int i = 0 ; i < 20 ; i++) {
		//	double traf = (i+1)*0.05;
			
			System.out.print(erlangB(1, lambda1[i]/2) + "\t");
			
		/*	double dd= erlangB(1, traf/2);
			
			double acc = (traf/2)*(1-dd);
			
			System.out.print(erlangB(1, traf/2) + "\t");*/
			
			
			
			//double acc = erlangBacceptedRate (1, traf/2);
			//double accafter = erlangBacceptedRate(1, 2*acc);
			//System.out.print(acc + "\t");
		//	double accafter = acc + acc* (1 - acc);
		//	System.out.println("Worstcase");
		//	System.out.print(1- (accafter/(traf*2)) + "\t");
			
		/*	double beta = acc;///(1-acc);
			
			double p1 = 2*beta / (1 + 2*beta);
			
			accafter = (1-p1)*acc*2;
			System.out.print(1- (accafter/(traf*2)) + "\t");
			*/
			
		/*	double dropSwi = 2*acc*(1-acc);
			
			accafter = (acc* (1-dropSwi));*/
			
		//	System.out.print(1- (accafter/(traf)) + "\t");		
			
			
		}
	}
}
