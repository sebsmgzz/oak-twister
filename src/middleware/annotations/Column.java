package middleware.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    boolean primaryKey() default false;

    String name();

    int type();

    boolean notNull() default true;

    boolean unique() default false;

}
