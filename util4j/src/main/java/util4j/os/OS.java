package util4j.os;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OS {
	
	Runtime runtime ;
	String workingDir ;
	boolean logCommands = false ;
	
	public OS(String workingDir) {
		this.workingDir = System.getProperty("user.dir") ;
		this.runtime = Runtime.getRuntime() ;
		
		// change to working directory
		try {
			cd(workingDir);
		} catch (IOException e) {
			System.err.println("Could not access the working directory...");
		}
	}
	
	public String formCommand(String command, String... options) {
		return command ;
	}
	
	public List<String> getStdout(Process process) {
		List<String> lines = new ArrayList<>() ;
		try(Scanner scanner = new Scanner(process.getInputStream())) {
			while(scanner.hasNext()) {
				lines.add(scanner.nextLine()) ;
			}
		}
		return lines ;
	}
	
	public List<String> getStderr(Process process) {
		List<String> lines = new ArrayList<>() ;
		try(Scanner scanner = new Scanner(process.getErrorStream())) {
			while(scanner.hasNext()) {
				lines.add(scanner.nextLine()) ;
			}
		}
		return lines ;
	}
	
	public void logCommands(boolean flag) {
		this.logCommands = flag ;
	}
	
	private void logCommand(String command) {
		if(this.logCommands)
			System.out.println("command = " + command);
	}
	
	private String checkDir(String directory) {
		if(directory.charAt(0)=='.') {
			return workingDir + File.separator + directory.substring(2) ;
		}
		else if(directory.charAt(0)=='~') {
			return System.getProperty("user.home") + File.separator + directory.substring(2) ;
		}
		else if(directory.charAt(0)=='/') {
			return directory ;
		}
		else {
			return workingDir + File.separator + directory ;
		}
	}
	
	public void exec(String command) throws IOException {
		runtime.exec(command) ;
	}
	
	public String mkdir(String directory) throws IOException {
		String command = "mkdir " + checkDir(directory) ;
		runtime.exec(command) ;
		return (workingDir+File.separator+directory) ;
	}
	
	public List<String> ls(String... options) throws IOException {
		String command = formCommand("ls", options) ;
		logCommand(command);
		Process process = runtime.exec(command) ;
		return getStdout(process) ;
	}
	
	public String pwd() throws IOException {
		String command = formCommand("pwd") ;
		logCommand(command);
//		Process process = runtime.exec(command) ;
//		return getStdout(process).get(0) ;
		return workingDir ;
	}
	
	public void cd(String directory) throws IOException {
		if(directory.charAt(0)=='.') {
			workingDir = workingDir + File.separator + directory.substring(2) ;
		}
		else if(directory.charAt(0)=='~') {
			workingDir = System.getProperty("user.home") + File.separator + directory.substring(2) ;
		}
		else {
			workingDir = directory ;
		}
	}
	
//	public List<String> getAllCommandsStartingWith(String start) throws IOException {
//		String command = start + "\t\t" ;
//		Process process = runtime.exec(command) ;
//		return getStdout(process) ;
//	}
	
	public List<String> history() throws IOException {
		String command = "brew --help" ;
		logCommand(command);
		Process process = runtime.exec(command) ;
		return getStdout(process) ;
	}

}
