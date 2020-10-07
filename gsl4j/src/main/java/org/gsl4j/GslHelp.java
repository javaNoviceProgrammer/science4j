package org.gsl4j;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GslHelp {

	private GslHelp() {

	}

	private static void openURL(String url) {
		try {
			URI doc = new URI(url) ;
			Desktop.getDesktop().browse(doc);
		} catch (IOException e) {
			System.out.println("Could not open the documentation");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void introduction() {
		openURL("https://www.gnu.org/software/gsl/doc/html/intro.html");
	}

	public static void complex() {
		openURL("https://www.gnu.org/software/gsl/doc/html/complex.html") ;
	}



}
