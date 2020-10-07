package org.gsl4j.plot.util;

import java.io.IOException;


public class TerminalExecutor {

	private TerminalExecutor() {

	}

	private static void executeCommand(String toolname, String filename, String... args) {
		// build the command
		StringBuilder sb = new StringBuilder() ;
		sb.append(toolname).append(" ") ;
		if(args != null && args.length > 0) {
			for(int i=0; i<args.length; i++)
				sb.append(args[i] + " ") ;
		}
		sb.append(filename) ;
		// execute on the terminal (command line)
		Runtime rt = Runtime.getRuntime() ;
		try {
			Process process = rt.exec(sb.toString()) ;
			// wait for process
			process.waitFor() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void execute(String toolname, String filename, String... args) {
		executeCommand(toolname, filename, args);
	}

}
