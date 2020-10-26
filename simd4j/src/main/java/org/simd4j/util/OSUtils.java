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

	static void initCpuInfo() {
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
//		String command1 = "sysctl -a | grep machdep.cpu.features" ;
		String command1 = "sysctl -a" ;
		Scanner scanner1 = null;
		String response1 = null;
		String response2 = null;

		try {
			Process process1 = Runtime.getRuntime().exec(command1);
			scanner1 = new Scanner(process1.getInputStream());
			String line = null ;
			while(scanner1.hasNextLine()) {
				line = scanner1.nextLine() ;
				if(line.contains("machdep.cpu.features")) {
					response1 = line ;
				}
				else if(line.contains("machdep.cpu.leaf7_features")) {
					response2 = line ;
				}
			}
			cpu = response1 + "\n" + response2 ;
			for(String x : response1.split(" "))
				cpuInfo.add(x.toUpperCase()) ;
			for(String x : response2.split(" "))
				cpuInfo.add(x.toUpperCase()) ;

		} catch (IOException var12) {
			var12.printStackTrace();
		}
	}

	private static void initCpuInfoLinux() {
		// check avx
		String command1 = "cat /proc/cpuinfo" ;
		Scanner scanner1 = null ;
		String response1 = null ;
		// exec via runtime
		Process process1 ;
		try {
			// first command
			process1 = Runtime.getRuntime().exec(command1) ;
			scanner1 = new Scanner(process1.getInputStream()) ;
			while(scanner1.hasNextLine()) {
				response1 = scanner1.nextLine() ;
				if(response1.contains("flags")) {
					break ;
				}
			}
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
		return cpuInfo.contains("SSE4.1") || cpuInfo.contains("SSE4_1") ;
	}

	public static boolean supportsSSE4p2() {
		return cpuInfo.contains("SSE4.2") || cpuInfo.contains("SSE4_2") ;
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


	public static void printInfo() {
		System.out.println("OS name    : " + OS);
		System.out.println("is Mac     : " + isMac()) ;
		System.out.println("is Windows : " + isWindows()) ;
		System.out.println("is Linux   : " + isLinux()) ;
		System.out.println("is 64bit   : " + is64bit());
		System.out.println("is 32bit   : " + is32bit());
		System.out.println("OS arch    : " + getArch());
		if (cpu == null) {
			initCpuInfo();
		}
		System.out.println(OSUtils.cpu);
		System.out.println("support AVX512 : " + supportsAVX512());
		System.out.println("support AVX2   : " + supportsAVX2());
		System.out.println("support AVX1   : " + supportsAVX1());
		System.out.println("support SSE4.2 : " + supportsSSE4p2());
		System.out.println("support SSE4.1 : " + supportsSSE4p1());
		System.out.println("support SSE3   : " + supportsSSE3());
		System.out.println("support SSSE3  : " + supportsSSSE3());
		System.out.println("support SSE2   : " + supportsSSE2());
		System.out.println("support SSE    : " + supportsSSE());
	}





	public static void main(String[] args) {
		printInfo();
	}

}
