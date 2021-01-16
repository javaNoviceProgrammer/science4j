package func4j.natives;

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
		try {
			for(int i=0; i<lib.length; i++) {
				NativeUtils.loadLibraryFromJar(lib[i]+".dylib") ;
			}
		} catch (IOException e) {
			System.err.println("Could not load GSL4j dynamic library");
		}
	}

	// linux
	private static void loadLinuxLibraries(String... lib) {
		try {
			for(int i=0; i<lib.length; i++) {
				NativeUtils.loadLibraryFromJar(lib[i]+".so") ;
			}
		} catch (IOException e) {
			System.err.println("Could not load GSL4j dynamic library");
		}
	}

	// windows
	private static void loadWindowsLibraries(String... lib) {
		try {
			for(int i=0; i<lib.length; i++) {
				NativeUtils.loadLibraryFromJar(lib[i]+".dll") ;
			}
		} catch (IOException e) {
			System.err.println("Could not load the dynamic library");
		}
	}

	public static void copyToLocation(String path, String libName) {
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
