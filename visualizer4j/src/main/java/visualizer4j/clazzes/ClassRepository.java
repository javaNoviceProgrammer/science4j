package visualizer4j.clazzes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;

import ch.epfl.general_libraries.logging.Logger;

public class ClassRepository {
	
	private static final Logger logger = new Logger(ClassRepository.class);	
	
	private HashSet<JavaClass> cache;	


	public ClassRepository(List<String> prefixes) {
		this(prefixes.toArray(new String[prefixes.size()]));
	}
	
	public ClassRepository(String[] prefixes) {
		cache = new HashSet<JavaClass>();
		ClasspathClassesEnumerator.Processor p = new ClasspathClassesEnumerator.Processor() {
			@Override
			public void process(String className) throws Exception {
				try {
					JavaClass cl = Repository.lookupClass(className);
					cache.add(cl);
				} catch (Exception e) {
					System.out.println("Class name is : " + className);
					throw e;
				}
			}	
		};	
		ClasspathClassesEnumerator.enumerateClasses(p, prefixes);		
	}

	public Collection<Class<?>> getClasses(Class<?> mod) throws Exception {
		logger.debug("Getting classes of model " + mod);
		JavaClass model = Repository.lookupClass(mod);
		ArrayList<Class<?>> list = new ArrayList<Class<?>>();
		if (mod.isInterface()) {
			for (JavaClass c : cache) {
				//System.out.print(".");
				//logger.debug("Testing class " + c.getClassName());
				boolean flag = false;
				try {
					flag = Repository.implementationOf(c, model);
				}
				catch(ClassNotFoundException e) {
				/*	System.out.print("e");
					e.printStackTrace();*/
					continue;
				}
				if (flag) {
					if (c.isAbstract() == false) {
						logger.debug("Found class " + c.getClassName());						
						list.add((Class<?>)Class.forName(c.getClassName()));
					}
				} else {
					for (JavaClass superClass : c.getSuperClasses()) {
						flag = false;
						try {
							flag = Repository.implementationOf(superClass, model);
						}
						catch (ClassNotFoundException e) {
							continue;
						}
						if (flag) {
							if (c.isAbstract() == false) {
								list.add((Class<?>)Class.forName(c.getClassName()));
								break;
							}
						}
					}
				}
			}
		} else {
			for (Iterator<JavaClass> ite = cache.iterator() ; ite.hasNext() ; ) {
				JavaClass c = ite.next();
				try {
					if (Repository.instanceOf(c, model)) {
						if (c.isAbstract() == false) {
							list.add((Class<?>)Class.forName(c.getClassName()));
						}
					}
				}
				catch (Throwable e) {
					System.out.println("ERROR with class " + c.getClassName());
					System.out.println(e);
					ite.remove();
				}
			}
		}
		return list;
	}	
}
