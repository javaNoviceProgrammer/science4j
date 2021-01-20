package func4j.integration;

import func4j.integration.domain.IntegralDomain2D;

public class Integral2D {
	
	IntegralFunction2D func2d ;
	IntegralDomain2D domain ;
	
	public Integral2D(IntegralFunction2D func, IntegralDomain2D domain) {
		this.func2d = func ;
		this.domain = domain ;
	}
	
	public void setFunction(IntegralFunction2D func) {
		this.func2d = func ;
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
	
	
	
	
	
}
