package org.gsl4j.tests;

import org.gsl4j.complex.Complex;
import org.gsl4j.io.FileInput;
import org.gsl4j.io.FileOutput;
import static org.gsl4j.complex.Complex.* ;

import java.util.ArrayList;

public class TestFileInputOutput {

	public static void test0() {
		String s = "-1.5+j2.0" ;
		Complex c = Complex.parseComplex(s) ;
		System.out.println(c);
	}

	public static void test1() {
		String[] lines = new String[2] ;
		lines[0] = "hi, this is the first line." ;
		lines[1] = "this is the second line".toUpperCase() ;
		FileOutput fo = new FileOutput("./tests/org/gsl4j/tests/test/file1.txt", 'w') ;
		fo.println(lines);
		fo.close();
	}

	public static void test2() {
		String[] lines = new String[2] ;
		lines[0] = "this is the third line." ;
		lines[1] = "THIS is the 4th line!!".toLowerCase() ;
		FileOutput fo = new FileOutput("./tests/org/gsl4j/tests/test/file1.txt", 'a') ;
		fo.println(lines);
		fo.close();
	}

	public static void test3() {
		Complex[] numbers = new Complex[15] ;
		for(int i=0; i<numbers.length; i++) {
			numbers[i] = (0.5*i)+j*(-2.1*i*i) ;
		}
		FileOutput fo = new FileOutput("./tests/org/gsl4j/tests/test/file1.txt", 'w') ;
		fo.println(numbers);
		fo.close();
	}

	public static void test4() {
		FileOutput.renameFile("./tests/org/gsl4j/tests/test/file1.txt", "./tests/org/gsl4j/tests/test/file2.txt") ;
	}

	public static void test5() {
		FileInput fi = new FileInput("./tests/org/gsl4j/tests/test/file1.txt") ;
		fi.setSupressMessageToTrue();
		ArrayList<Complex> numbers = new ArrayList<>() ;
		String s = null ;
		while(!fi.eof()) {
			if(!(s=fi.readLine()).isEmpty())
				numbers.add(parseComplex(s)) ;
		}
		System.out.println(fi.numberOfLines());
		fi.close();
		System.out.println(numbers);
	}

	public static void main(String[] args) {
//		test0() ;
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
		test5() ;
	}

}
