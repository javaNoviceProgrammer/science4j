package org.gsl4j.util;

public class OSUtils {

	private static String OS = System.getProperty("os.name") ;

	public static boolean isMac() {
		return OS.toLowerCase().contains("mac") ;
	}

	public static boolean isWindows() {
		return OS.toLowerCase().contains("win") ;
	}

	public static boolean isLinux() {
		return OS.toLowerCase().contains("nux") ;
	}

	public static void main(String[] args) {
		System.out.println(OS);
		System.out.println("is Mac     : " + isMac());
		System.out.println("is Windows : " +isWindows()); ;
		System.out.println("is Linux   : " +isLinux()); ;
	}

}
