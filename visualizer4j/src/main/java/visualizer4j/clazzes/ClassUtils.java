package visualizer4j.clazzes;

public class ClassUtils {
	
	public static boolean isTypableType(String name) {
		return name.equals("int") || name.equals("float") || name.equals("double") || name.equals("long") || name.equals("short")
				|| name.equals("char") || name.equals("java.lang.String")
			    || name.equals("java.lang.Double") || name.equals("java.lang.Float");
	}
	
	public static boolean isBooleanType(String name) {
		return name.equals("boolean");
	}
	
	public static boolean isTypableType(Class<?> c) {
		return isTypableType(c.getName());
	}
	
	public static boolean isBooleanType(Class<?> c) {
		return isBooleanType(c.getName());
	}
	
	public static boolean isHeritingFrom(Class<?> class_, Class<?> superClass) {
		boolean ret = false;
		while (!ret && !class_.getSuperclass().equals(Object.class)) {
			if (class_.getSuperclass().equals(superClass)) {
				ret = true;
			} else {
				for (Class<?> interfaces : class_.getInterfaces()) {
					if (interfaces.equals(superClass)) {
						ret = true;
					}
				}
				class_ = class_.getSuperclass();
			}
		}
		for (Class<?> interfaces : class_.getInterfaces()) {
			if (interfaces.equals(superClass)) {
				ret = true;
			}
		}
		return ret;
	}
	

}
