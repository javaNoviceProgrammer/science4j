package org.gsl4j.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestProcess {

	public static void test1() {
		String command = "ls -a ./" ;
		Runtime runtime = Runtime.getRuntime() ;
		try {
			Process process = runtime.exec(command) ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())) ;
			String line ;
			while((line=reader.readLine())!=null)
				System.out.println(line);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		String command = "python /Users/meisam/Desktop/untitled.txt" ;
		Runtime runtime = Runtime.getRuntime() ;
		try {
			Process process = runtime.exec(command) ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())) ;
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream())) ;
			String line ;
			while((line=reader.readLine())!=null)
				System.out.println(line);
			while((line=error.readLine())!=null)
				System.out.println(line);
			reader.close();
			error.close();
//			process.waitFor() ;

			runtime.exec(command) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		test1() ;
		test2() ;
	}

}
