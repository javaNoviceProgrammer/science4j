package func4j.special;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class SpecialFuncs {
	
	
	// open PDF documentation
	public static void help() {
		try {
	        String inputPdf = "doc/gsl_special_functions.pdf";
	        Path tempOutput = Files.createTempFile("gsl_special_functions", ".pdf");
	        tempOutput.toFile().deleteOnExit();
	        try (InputStream is = SpecialFuncs.class.getClassLoader().getResourceAsStream(inputPdf)) {
	            Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
	        }
	        Desktop.getDesktop().open(tempOutput.toFile());
			
		} catch (IOException e) {
			System.err.println("could not open PDF document...");
		}
	}
	
}
