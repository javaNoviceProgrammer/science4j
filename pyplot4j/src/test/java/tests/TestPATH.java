package tests;

import java.io.IOException;
import java.util.Scanner;

public class TestPATH {
	
	public static void test1() {
		var runtime = Runtime.getRuntime() ;
		try {
			var process = runtime.exec("python --version") ;
			var stdout = new Scanner(process.getInputStream()) ;
			while(stdout.hasNext()) {
				System.out.println(stdout.next());
			}
			var stderr = new Scanner(process.getErrorStream()) ;
			while(stderr.hasNext()) {
				System.out.println(stderr.next());
			}
			System.out.println(System.getenv("PATH"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		test1() ;
	}

}
