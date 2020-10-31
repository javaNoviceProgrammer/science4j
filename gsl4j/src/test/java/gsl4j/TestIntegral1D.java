package gsl4j;

import java.util.Arrays;

import org.gsl4j.integration.Integral1D;
import org.gsl4j.integration.IntegralFunction1D;

public class TestIntegral1D {

	public static void test1() {
		IntegralFunction1D func = t -> t*Math.sin(t) ;
		Integral1D integration = new Integral1D(func) ;
		double result = integration.qng(0.0, 1.0) ;
		System.out.println(result);
	}

	public static void test2() {
		IntegralFunction1D func = t -> t*Math.sin(t) ;
		Integral1D integration = new Integral1D(func) ;
		double[] result = integration.qngDetailed(0.0, 10.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void main(String[] args) {
//		test1() ;
		test2() ;
	}

}
