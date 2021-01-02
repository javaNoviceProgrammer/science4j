package visualizer4j.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class DynamicInstancier<T> {

	private Class<? extends T> class_;

	public DynamicInstancier(Class<? extends T> class_) {
		this.class_ = class_;
	}

	public String getInstanciatedClassName() {
		return class_.getSimpleName();
	}

	public T getInstance() {
		try {
			T new_ = class_.getConstructor(new Class[]{}).newInstance(new Object[]{});
			return new_;
		}
		catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}
		catch (InstantiationException e) {
			throw new IllegalStateException(e);
		}
		catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		catch (java.lang.reflect.InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}


	public T getInstance(Object... params) {
		return (T)getInstance(class_, params);
	}

	public static Object getIntance(Class classI) {
		return getInstance(classI, new Object[0], 0);
	}


	public static Object getInstance(Class classI, Object... params) {
		return getInstance(classI, params, 0);
	}

	private static Object getInstance(Class classI, Object[] params, int dummy) {
		Class[] classTab = new Class[params.length];
		Object[] paramTab = new Object[params.length];
		int i = 0;
		for (Object param : params) {
			classTab[i] = param.getClass();
			paramTab[i] = param;
			i++;
		}
		try {
			Constructor[] cons = classI.getConstructors();
			Object new_ = findConstructor(cons, paramTab, classTab).newInstance(paramTab);
			return new_;
		}
		catch (InstantiationException e) {
			throw new IllegalStateException(e);
		}
		catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		catch (java.lang.reflect.InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalStateException("Error constructing object of class " + classI.getSimpleName(), e);
		}
	}


	public static Constructor findConstructor(Constructor[] cons, Object[] paramTab, Class[] classTab) {
		Constructor elected = null;
		for (Constructor ite : cons) {
			Constructor ct = ite;
			int i = 0;
			boolean valid = true;
			if (ct.getParameterTypes().length != paramTab.length) {
				continue;
			}
			for (Class c : ct.getParameterTypes()) {
				if (!c.isAssignableFrom(classTab[i]) && !test(c, classTab[i])) {
					valid = false;
					break;
				}
				i++;
			}
			if (valid) {
				elected = ct;
				break;
			}
		}
		if (elected == null) {
			throw new IllegalStateException("No constructor found, perhaps due to wrong int/Integer choices");
		}
		return elected;
	}


	public static Method findMethod(String name, Method[] cons, Object[] paramTab, Class[] classTab) {
		Method elected = null;
		for (Method ct : cons) {
			if (!ct.getName().equals(name)) {
				continue;
			}
			if (paramTab == null || paramTab.length == 0) {
				elected = ct;
				break;
			}
			int i = 0;
			boolean valid = true;
			if (ct.getParameterTypes().length != paramTab.length) {
				continue;
			}
			for (Class c : ct.getParameterTypes()) {
				if (!c.isAssignableFrom(classTab[i]) && !test(c, classTab[i])) {
					valid = false;
					break;
				}
				i++;
			}
			if (valid) {
				elected = ct;
				break;
			}
		}
		if (elected == null) {
			throw new IllegalStateException("No constructor found, perhaps due to wrong int/Integer choices");
		}
		return elected;
	}

	public static boolean test(Class c, Class c2) {
		if (c.equals(Integer.TYPE) && c2.equals(Integer.class)) {
			return true;
		}
		if (c.equals(Double.TYPE) && c2.equals(Double.class)) {
			return true;
		}
		if (c.equals(Float.TYPE) && c2.equals(Float.class)) {
			return true;
		}
		if (c.equals(Byte.TYPE) && c2.equals(Byte.class)) {
			return true;
		}
		if (c.equals(Character.TYPE) && c2.equals(Character.class)) {
			return true;
		}
		if (c.equals(Long.TYPE) && c2.equals(Long.class)) {
			return true;
		}
		if (c.equals(Short.TYPE) && c2.equals(Short.class)) {
			return true;
		}
		if (c.equals(Boolean.TYPE) && c2.equals(Boolean.class)) {
			return true;
		}
		return false;
	}


}
