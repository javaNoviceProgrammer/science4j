package visualizer4j.clazzes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamName {
	String name();
	String default_() default "";
	Class defaultClass_() default Object.class;
	Class abstractClass() default Object.class;
	Class requireInterface() default Object.class;
}
