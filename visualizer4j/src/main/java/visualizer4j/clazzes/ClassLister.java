package visualizer4j.clazzes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;


public class ClassLister<T> {

	private HashSet<Class<T>> result;

	JavaClass model;
	private boolean interface_ = false;	

	public ClassLister(Class<T> targetClass) {
		internalConstructor(new String[0], targetClass);
	}

	public ClassLister(String prefix, Class<T> targetClass) {
		internalConstructor(new String[]{prefix}, targetClass);
	}
	
	public ClassLister(String[] prefixes, Class<T> targetClass) {
		internalConstructor(prefixes, targetClass);
	}

	public ClassLister(List<String> prefixes, Class<T> targetClass) {
		internalConstructor(prefixes.toArray(new String[0]), targetClass);
	}

	private void internalConstructor(String[] prefixes, Class<T> targetClass) {
		try {
			model = Repository.lookupClass(targetClass);
			if (targetClass.isInterface()) {
				interface_ = true;
			}
			result = new HashSet<Class<T>>();
			ClasspathClassesEnumerator.enumerateClasses(new ClasspathClassesEnumerator.Processor() {
				@SuppressWarnings("unchecked")
				public void process(String className) {
					try {				
						if (interface_) {
							if (implementsInterface(className, model)) {
								result.add((Class<T>) Class.forName(className));
							}
						} else {
							if (implementsClass(className, model)) {
								result.add((Class<T>) Class.forName(className));
							}
						}
					} catch (Throwable t) {
						throw new IllegalStateException(t);
					}	
				}
			}, prefixes);			
			
			
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}
	
	public Collection<Class<T>> getClasses() {
		return result;
	}
	
	public Collection<Class<T>> getSortedClasses() {
		ArrayList<Class<T>> al = new ArrayList<Class<T>>(result);
		Collections.sort(al, new Comparator<Class>() {

			@Override
			public int compare(Class o1, Class o2) {
				return o1.getSimpleName().compareTo(o2.getSimpleName());
			}
			
		});
		return al;
	}

	private boolean implementsInterface(String className, JavaClass model) throws Exception {
		JavaClass jv = Repository.lookupClass(className);
		if (jv.isAbstract() == false) {
			if (Repository.implementationOf(className, model)) {
				return true;
			}
		}
		return false;
	}

	private boolean implementsClass(String className, JavaClass model) throws Exception {
		JavaClass jv = Repository.lookupClass(className);
		if (jv.isAbstract() == false) {
			if (Repository.instanceOf(className, model)) {
				return true;
			}
		}
		return false;
	}

}
