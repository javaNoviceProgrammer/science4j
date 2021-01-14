package func4j.special;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class SpecialFuncs {
	
	
	
	
	
	// open PDF documentation
	public static void help() {
		try {
			System.out.println("opening PDF document...");
			File pdf = new File(ClassLoader.getSystemResource("gsl_special_functions.pdf").getFile()) ;
			Desktop.getDesktop().open(pdf);
		} catch (IOException e) {
			System.err.println("could not open PDF document...");
		}
	}

}
