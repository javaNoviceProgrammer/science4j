package util4j.time;

import java.util.concurrent.TimeUnit;

public class Timer {

	long startTime ;
	long endTime ;
	long elapseTime ;

	public void start() {
		startTime = System.nanoTime() ;
	}

	public void stop() {
		endTime = System.nanoTime() ;
		elapseTime = endTime - startTime ;
	}

	public void reset() {
		startTime = 0L ;
		endTime = 0L ;
		elapseTime = 0L ;
	}

	public void show() {
		System.out.println("Timer duration = " + (elapseTime/1e6) + " ms");
	}
	
	public void show(TimeUnit unit) {
		switch (unit) {
		case NANOSECONDS:
			System.out.println("Timer duration = " + elapseTime + " ns");
			break;
		case MICROSECONDS:
			System.out.println("Timer duration = " + (elapseTime/1e3) + " us");
			break;
		case MILLISECONDS:
			System.out.println("Timer duration = " + (elapseTime/1e6) + " ms");
			break;
		case SECONDS:
			System.out.println("Timer duration = " + (elapseTime/1e9) + " sec");
			break;
		case MINUTES:
			System.out.println("Timer duration = " + (elapseTime/1e9/60.0) + " sec");
			break;
		default:
			System.out.println("Timer duration = " + (elapseTime/1e6) + " ms");
			break;
		}
		
	}


	@Override
	public String toString() {
		return "Timer duration = " + (elapseTime/1e6) + " ms" ;
	}

}
