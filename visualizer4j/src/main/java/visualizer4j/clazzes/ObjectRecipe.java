package visualizer4j.clazzes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ObjectRecipe<T> {
	
	private Constructor<T> constructor;
	private Object[] parameters;
	private ObjectRecipe[] futureParameters;
	
	public ObjectRecipe(Constructor<T> c, Object[] param) {
		this.constructor = c;
		this.parameters = param;
	}
	
	public ObjectRecipe(Constructor<T> c, ObjectRecipe[] subs) {
		this.constructor = c;
		this.futureParameters = subs;
	}
	
	public T build() {
		try {
			if (parameters == null) {
				parameters = new Object[futureParameters.length];
				for (int i = 0 ; i < parameters.length ; i++) {
					parameters[i] = futureParameters[i].build();
				}
			}
			return constructor.newInstance(parameters);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException(e);
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

}
