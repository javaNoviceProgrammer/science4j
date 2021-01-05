package util4j.natives;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import util4j.os.OSUtils;

public class NativeLibraryLoader {

	private static boolean isLoaded = false ;
	public static boolean showInfo = false ;

	private NativeLibraryLoader() {

	}

	public static void loadLibraries(String... lib) {
		if (isLoaded) {
			return;
		} else {
			if (OSUtils.isMac())
				loadMacLibraries(lib);
			else if (OSUtils.isLinux())
				loadLinuxLibraries(lib);
			else if (OSUtils.isWindows())
				loadWindowsLibraries(lib);
			else
				System.out.println("Operating System NOT supported.");

			isLoaded = true;
		}
	}

	private static void loadMacLibraries(String... lib) {
		// copy gsl libs and load gsl4j library
		try {
			copyToLocation("/usr/local/lib", "libgsl.25.dylib");
			copyToLocation("/usr/local/lib", "libgslcblas.0.dylib");
			NativeUtils.loadLibraryFromJar("/libgsl4j_c.dylib");
		} catch (IOException e) {
			System.err.println("Could not load GSL4j dynamic library");
		}
	}

	// linux
	private static void loadLinuxLibraries(String... lib) {
		// copy gsl libs and load gsl4j library
		try {
			copyToLocation("/usr/local/lib", "libgsl.so.25.0.0");
			copyToLocation("/usr/local/lib", "libgslcblas.so.0.0.0");
			NativeUtils.loadLibraryFromJar("/libgsl4j_c.so");
		} catch (IOException e) {
			System.err.println("Could not load GSL4j dynamic library");
		}
	}

	// windows
	private static void loadWindowsLibraries(String... lib) {

	}

	private static void copyToLocation(String path, String libName) {
		// mac: /usr/local/lib
		// linux: /usr/local/lib
		File temp = new File(path, libName);
		if (!temp.exists()) {
			try (InputStream is = NativeUtils.class.getResourceAsStream("/gsl_lib/"+libName)) {
				Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			if(showInfo)
				System.out.println(libName + " successfully copied to " + path);
		}
		else {
			if(showInfo)
				System.out.println(libName + " already exists at " + path);
		}
	}

}
