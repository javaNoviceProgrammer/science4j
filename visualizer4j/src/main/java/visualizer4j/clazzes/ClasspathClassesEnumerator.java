package visualizer4j.clazzes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipException;

import ch.epfl.general_libraries.logging.Logger;

public class ClasspathClassesEnumerator {
	
	private static final Logger logger = new Logger(ClasspathClassesEnumerator.class);	
	
	public static interface Processor {
		public void process(String className) throws Exception;
	}
	
	private Vector<List<String>> includePrefixes;	
	private List<String> prefixes;
	private Processor p;
	
	// UNIQUE ENTRY POINT FOR THIS CLASS
	public static void enumerateClasses(Processor p, String[] prefixes) {
		new ClasspathClassesEnumerator(p, prefixes);
	}
	
	private ClasspathClassesEnumerator(Processor p, String[] prefixes) {
		super();
		this.p = p;
		processPrefixes(prefixes);
		try {
			String s = System.getProperty("java.class.path");
			
		//	System.out.println(s);

			String[] ss = s.split(System.getProperty("path.separator"));
			for (String c : ss) {
				File f = new File(c);
				if (f.isDirectory()) {
					processDir(f, "", 0);
				} else {
					if (f.getName().endsWith(".jar")) {
						try {
							JarFile jf = new JarFile(f);
							processJar(jf);
						} catch (ZipException e) {
						} catch (Exception e) {
							System.out.println("ERROR in file "
									+ f.getAbsoluteFile());
							//	throw e;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Cannot process jar " + e);
		}		
	}
	
	private void processPrefixes(String[] prefixes) {
		processPrefixes(Arrays.asList(prefixes));
	}
	
	private void processPrefixes(List<String> prefixes) {
		this.includePrefixes = new Vector<List<String>>();
		this.prefixes = new ArrayList<String>();
		for (String pref : prefixes) {
			int i = pref.split("\\.").length;
			includePrefixes.setSize(Math.max(includePrefixes.size(), i));
			for (int j = 0; j < i; j++) {
				if (includePrefixes.get(j) == null) {
					includePrefixes.setElementAt(new ArrayList<String>(), j);
				}
			}
			includePrefixes.get(i - 1).add(pref);
			this.prefixes.add(pref.replaceAll("\\.", "/"));
		}
	}	
	
	private void processDir(File f, String prefix, int prefixN)
	throws Exception {
		for (File sf : f.listFiles()) {
			if (sf.isDirectory()) {
				if (prefix.equals("")) {
					boolean processed = false;
					if (includePrefixes.size() == 0) {
						processGrantedDir(sf, sf.getName());
					} else {
						for (String s : includePrefixes.get(prefixN)) {
							if (sf.getName().startsWith(s)) {
								processGrantedDir(sf, sf.getName());
								processed = true;
								break;
							}
						}
						if (processed == false) {
							if (includePrefixes.size() > 1) {
								processDir(sf, sf.getName(), 1);
							}
						}
					}
				} else {
					boolean processed = false;
					for (String s : includePrefixes.get(prefixN)) {
						if ((prefix + "." + sf.getName()).startsWith(s)) {
							processGrantedDir(sf, prefix + "." + sf.getName());
							break;
						}
					}
					if (processed == false) {
						if (includePrefixes.size() > prefixN + 1) {
							processDir(sf, prefix + "." + sf.getName(),
									prefixN + 1);
						}
					}
				}
			}
		}
	}

	private void processGrantedDir(File f, String prefix) throws Exception {
		for (File sf : f.listFiles()) {
			if (sf.isDirectory()) {
				if (prefix.equals("")) {
					processGrantedDir(sf, sf.getName());
				} else {
					processGrantedDir(sf, prefix + "." + sf.getName());
				}
			} else {
				if (sf.getName().endsWith(".class")) {
					p.process(prefix + "."
							+ sf.getName().replaceAll(".class", ""));
				}
			}
		}
	}

	private void processJar(JarFile jf) throws Exception {
	//	logger.info("Processing jar file " + jf);	
		for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements();) {
			JarEntry entry = e.nextElement();
			String s = entry.getName();
			if (s.endsWith(".class")) {
				for (String pref : prefixes) {
					if (s.startsWith(pref)) {
						s = s.replaceAll(".class", "").replaceAll("/", ".");
						try {
							p.process(s);
						} catch (Exception ex) {
							System.out.println("ERROR processing class " + s
									+ " in " + jf.getName());
							System.out.println(entry);
							throw ex;
						}
						break;
					}
				}
			}

		}
	}	
	
	
}
