package func4j.optimize;

import java.util.ArrayList;
import java.util.List;

import func4j.domain.Interval;
import func4j.function.MathFunction;
import func4j.roots.RealRoot;


/**
 * A class for finding minima and maxima (extrema) of a "smooth" function using root finding algorithms.
 * 
 * @author Meisam
 *
 */
public class Extrema1D {
	
	MathFunction func, funcPrime, funcDoublePrime ;
	RealRoot rootFinder ;
	
	public Extrema1D(MathFunction func) {
		this.func = func ;
		this.funcPrime = func.deriv() ;
		this.funcDoublePrime = func.deriv2() ;
		this.rootFinder = new RealRoot(funcPrime) ;
	}
	
	public List<Double> brent(double start, double end, int subIntervals) {
		return rootFinder.brent(start, end, subIntervals) ;
	}
	
	public List<Double> brent(Interval interval, int subIntervals) {
		return rootFinder.brent(interval.var1Min(), interval.var1Max(), subIntervals) ;
	}
	
	public List<Extremum> brentAll(double start, double end, int subIntervals) {
		List<Double> roots = rootFinder.brent(start, end, subIntervals) ;
		List<Extremum> extrema = new ArrayList<>(roots.size()) ;
		double xi ;
		boolean isMin, isMax ;
		for(int i=0; i<roots.size(); i++) {
			xi = roots.get(i) ;
			isMin = funcDoublePrime.value(xi) > 1e-5 ;
			isMax = funcDoublePrime.value(xi) < -1e-5 ;
			extrema.add(new Extremum(xi, isMin, isMax)) ;
		}
		return extrema ;
	}
	
	public List<Extremum> brentAll(Interval interval, int subIntervals) {
		List<Double> roots = rootFinder.brent(interval.var1Min(), interval.var1Max(), subIntervals) ;
		List<Extremum> extrema = new ArrayList<>(roots.size()) ;
		double xi ;
		boolean isMin, isMax ;
		for(int i=0; i<roots.size(); i++) {
			xi = roots.get(i) ;
			isMin = funcDoublePrime.value(xi) > 1e-5 ;
			isMax = funcDoublePrime.value(xi) < -1e-5 ;
			extrema.add(new Extremum(xi, isMin, isMax)) ;
		}
		return extrema ;
	}
	
	public List<Double> newton(double start, double end, int subIntervals) {
		return rootFinder.newton(start, end, subIntervals) ;
	}
	
	public List<Double> newton(Interval interval, int subIntervals) {
		return rootFinder.newton(interval.var1Min(), interval.var1Max(), subIntervals) ;
	}
	
	public List<Extremum> newtonAll(double start, double end, int subIntervals) {
		List<Double> roots = rootFinder.newton(start, end, subIntervals) ;
		List<Extremum> extrema = new ArrayList<>(roots.size()) ;
		double xi ;
		boolean isMin, isMax ;
		for(int i=0; i<roots.size(); i++) {
			xi = roots.get(i) ;
			isMin = funcDoublePrime.value(xi) > 1e-5 ;
			isMax = funcDoublePrime.value(xi) < -1e-5 ;
			extrema.add(new Extremum(xi, isMin, isMax)) ;
		}
		return extrema ;
	}
	
	public List<Extremum> newtonAll(Interval interval, int subIntervals) {
		List<Double> roots = rootFinder.newton(interval.var1Min(), interval.var1Max(), subIntervals) ;
		List<Extremum> extrema = new ArrayList<>(roots.size()) ;
		double xi ;
		boolean isMin, isMax ;
		for(int i=0; i<roots.size(); i++) {
			xi = roots.get(i) ;
			isMin = funcDoublePrime.value(xi) > 1e-5 ;
			isMax = funcDoublePrime.value(xi) < -1e-5 ;
			extrema.add(new Extremum(xi, isMin, isMax)) ;
		}
		return extrema ;
	}
	

}
