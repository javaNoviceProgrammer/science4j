package func4j.integration;

import func4j.function.MultiVariateMathFunction;
import func4j.integration.domain.IntegralDomain2D;

public class Integral2D {
	
	IntegralFunction2D func2d ;
	IntegralDomain2D domain ;
	
	public Integral2D(IntegralFunction2D func, IntegralDomain2D domain) {
		this.func2d = func ;
		this.domain = domain ;
	}
	
	public Integral2D(MultiVariateMathFunction func, IntegralDomain2D domain) {
		this.func2d = (x, y) -> func.value(x, y) ;
		this.domain = domain ;
	}
	
	public void setFunction(IntegralFunction2D func) {
		this.func2d = func ;
	}
	
	public void setFunction(MultiVariateMathFunction func) {
		this.func2d = (x, y) -> func.value(x, y) ; ;
	}
	
	public void setDomain(IntegralDomain2D domain) {
		this.domain = domain ;
	}
	
	public double qng() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qng(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qng(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qng(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qng(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qng(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss15() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss15(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss15(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss15(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss15(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss15(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss21() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss21(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss21(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss21(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss21(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss21(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss31() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss31(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss31(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss31(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss31(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss31(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss41() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss41(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss41(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss41(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss41(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss41(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss51() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss51(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss51(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss51(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss51(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss51(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss61() {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss61(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss61(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss61(IntegralDomain2D domain) {
		IntegralFunction1D func1 = var1 -> {
			IntegralFunction1D func2 = var2 -> func2d.value(var1, var2) ;
			Integral1D integrator = new Integral1D(func2) ;
			return integrator.qagGauss61(domain.var2Min(var1), domain.var2Max(var1)) ;
		} ;
		Integral1D integrator = new Integral1D(func1) ;
		return integrator.qagGauss61(domain.var1Min(), domain.var1Max()) ;
	}
	
}
