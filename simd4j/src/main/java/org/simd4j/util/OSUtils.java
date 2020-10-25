package org.simd4j.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OSUtils {

	private static String OS ; // = System.getProperty("os.name") ;
	private static String arch ; // = System.getProperty("os.arch") ;

	static {
		OS = System.getProperty("os.name") ;
		arch = System.getProperty("os.arch") ;
		cpuInfo = new ArrayList<>() ;
		initCpuInfo() ;
	}



	/*------- Check the OS type ---------*/

	public static String getName() {
		return OS ;
	}

	public static boolean isMac() {
		return OS.toLowerCase().contains("mac") ;
	}

	public static boolean isWindows() {
		return OS.toLowerCase().contains("win") ;
	}

	public static boolean isLinux() {
		return OS.toLowerCase().contains("nux") ;
	}

	/*------- Check the OS Architecture ---------*/

	public static String getArch() {
		return arch ;
	}

	public static boolean is32bit() {
		return !arch.toLowerCase().contains("64") ;
	}

	public static boolean is64bit() {
		return arch.toLowerCase().contains("64") ; // amd64 or x86_64
	}

	/*------- Check the CPU Vectorization ---------*/

	private static String cpu = null ; // lazy initialization
	private static ArrayList<String> cpuInfo ; // = new ArrayList<>() ;
	public static String[] cpuVecStrings =
		   {"MMX", "SSE", "SSE2", "SSE3", "SSSE3", "SSE4",
			"SSE4A", "SSE4.1", "SSE4.2", "AVX", "AVX1.0", "AVX2",
			"AVX512F", "AVX512PF", "AVX512ER", "AVX512CD", "AVX512VL", "AVX512BW",
			"AVX512DQ", "AVX512IFMA", "AVX512VBMI"} ;

	private static void initCpuInfo() {
		if(cpu != null) {
			return ;
		}
		else if (isMac()) {
			initCpuInfoMac() ;
		}
		else if(isLinux()) {
			initCpuInfoLinux() ;
		}
	}

	private static void initCpuInfoMac() {
		// check avx
		String command1 = "sysctl -a | grep machdep.cpu.features" ;
		Scanner scanner1 = null ;
		String response1 = null ;
		// check avx2
		String command2 = "sysctl -a | grep machdep.cpu.leaf7_features" ;
		Scanner scanner2 = null ;
		String response2 = null ;
		// exec via runtime
		Process process1, process2 ;
		try {
			// first command
			process1 = Runtime.getRuntime().exec(command1) ;
			scanner1 = new Scanner(process1.getInputStream()) ;
			response1 = scanner1.nextLine() ;
			cpu = response1 ;
			for(String x : response1.split(" ")) {
				cpuInfo.add(x) ;
			}
			// second command
			process2 = Runtime.getRuntime().exec(command2) ;
			scanner2 = new Scanner(process2.getInputStream()) ;
			response2 = scanner2.nextLine() ;
			cpu += "\n" + response2 ;
			for(String x : response2.split(" ")) {
				cpuInfo.add(x) ;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void initCpuInfoLinux() {
		// check avx
		String command1 = "cat /proc/cpuinfo  | egrep \"(flags|model name|vendor)\" | sort | uniq -c" ;
		Scanner scanner1 = null ;
		String response1 = null ;
		// exec via runtime
		Process process1 ;
		try {
			// first command
			process1 = Runtime.getRuntime().exec(command1) ;
			scanner1 = new Scanner(process1.getInputStream()) ;
			response1 = scanner1.nextLine() ;
			cpu = response1.toUpperCase() ;
			for(String x : response1.split(" ")) {
				cpuInfo.add(x.toUpperCase()) ;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean supportsMMX() {
		return cpuInfo.contains("MMX") ;
	}

	public static boolean supportsSSE() {
		return cpuInfo.contains("SSE") ;
	}

	public static boolean supportsSSE2() {
		return cpuInfo.contains("SSE2") ;
	}

	public static boolean supportsSSE3() {
		return cpuInfo.contains("SSE3") ;
	}

	public static boolean supportsSSSE3() {
		return cpuInfo.contains("SSSE3") ;
	}

	public static boolean supportsSSE4p1() {
		return cpuInfo.contains("SSE4.1") ;
	}

	public static boolean supportsSSE4p2() {
		return cpuInfo.contains("SSE4.2") ;
	}

	public static boolean supportsAVX1() {
		return cpu.contains("AVX1") || cpuInfo.contains("AVX") ;
	}

	public static boolean supportsAVX2() {
		return cpuInfo.contains("AVX2") ;
	}

	public static boolean supportsFMA() {
		return cpuInfo.contains("FMA") ;
	}

	public static boolean supportsAVX512() {
		return cpu.contains("AVX512") ;
	}

	public static boolean supportsKNC() {
		return cpuInfo.contains("KNC") ;
	}

	public static boolean supportsAMX() {
		return cpuInfo.contains("AMX") ;
	}

	public static boolean supportsSVML() {
		return cpuInfo.contains("SVML") ;
	}













	public static void main(String[] args) {
		System.out.println(OS);
		System.out.println("is Mac     : " + isMac()) ;
		System.out.println("is Windows : " + isWindows()) ;
		System.out.println("is Linux   : " + isLinux()) ;
		System.out.println("is 64bit   : " + is64bit());
		System.out.println("is 32bit   : " + is32bit());
		System.out.println(getArch());
		initCpuInfoMac();
		System.out.println(cpu);
		System.out.println(supportsAVX512());
		System.out.println(supportsAVX2());
		System.out.println(supportsAVX1());
		System.out.println(supportsFMA());
	}

}