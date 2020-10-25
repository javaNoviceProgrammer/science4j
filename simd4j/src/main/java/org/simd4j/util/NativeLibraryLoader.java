package org.simd4j.util;

import java.io.IOException;


public class NativeLibraryLoader {

	private static boolean isLoaded = false ;
	public static boolean showInfo = false ;

	private NativeLibraryLoader() {

	}

	public static void loadLibraries() {
		if (isLoaded) {
			return;
		} else {
			OSUtils.initCpuInfo();
			if (OSUtils.isMac())
				loadMacLibraries();
			else if (OSUtils.isLinux())
				loadLinuxLibraries();
			else if (OSUtils.isWindows())
				loadWindowsLibraries();
			else
				System.out.println("Operating System NOT supported.");

			isLoaded = true;
		}
	}

	private static void loadMacLibraries() {
		// copy gsl libs and load gsl4j library
		try {
			if(OSUtils.supportsAVX2())
				NativeUtils.loadLibraryFromJar("/lib_mac/libsimd4j_avx2.dylib");
			else if(OSUtils.supportsAVX1())
				NativeUtils.loadLibraryFromJar("/lib_mac/libsimd4j_avx.dylib");
			else if(OSUtils.supportsSSE4p2())
				NativeUtils.loadLibraryFromJar("/lib_mac/libsimd4j_sse4p2.dylib");
		} catch (IOException e) {
			System.err.println("Could not load the dynamic library");
		}
	}

	// linux
	private static void loadLinuxLibraries() {
		// copy gsl libs and load gsl4j library
		try {
			if(OSUtils.supportsAVX2())
				NativeUtils.loadLibraryFromJar("/lib_mac/libgsl4j_avx2.so");
			else if(OSUtils.supportsAVX1())
				NativeUtils.loadLibraryFromJar("/lib_mac/libgsl4j_avx.so");
			else if(OSUtils.supportsSSE4p2())
				NativeUtils.loadLibraryFromJar("/lib_mac/libgsl4j_sse4p2.so");
		} catch (IOException e) {
			System.err.println("Could not load the dynamic library");
		}
	}

	// windows
	private static void loadWindowsLibraries() {

	}

//	private static void copyToLocation(String path, String libName) {
//		// mac: /usr/local/lib
//		// linux: /usr/local/lib
//		File temp = new File(path, libName);
//		if (!temp.exists()) {
//			try (InputStream is = NativeUtils.class.getResourceAsStream("/gsl_lib/"+libName)) {
//				Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (NullPointerException e) {
//				e.printStackTrace();
//			}
//			if(showInfo)
//				System.out.println(libName + " successfully copied to " + path);
//		}
//		else {
//			if(showInfo)
//				System.out.println(libName + " already exists at " + path);
//		}
//	}

}
