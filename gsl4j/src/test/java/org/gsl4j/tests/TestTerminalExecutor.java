package org.gsl4j.tests;

import org.gsl4j.plot.util.TerminalExecutor;

class TestTerminalExecutor {

	public static void test1() {
		TerminalExecutor.execute("ls", "./", "-a");
	}

	public static void main(String[] args) {
		test1() ;
	}

}
