package org.gsl4j.util;

public class Timer {

	long startTime, endTime, elapseTime ;

	public void start() {
		startTime = System.currentTimeMillis() ;
	}

	public void stop() {
		endTime = System.currentTimeMillis() ;
		elapseTime = endTime - startTime ;
	}

	public void reset() {
		startTime = 0 ;
		endTime = 0 ;
		elapseTime = 0 ;
	}

	public void show() {
		System.out.println("Timer duration = " + elapseTime + " ms");
	}


	@Override
	public String toString() {
		return "Timer duration = " + elapseTime + " ms";
	}

}
