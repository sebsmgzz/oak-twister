package annotations;

import database.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    boolean primaryKey() default false;

    String name();

    Type type();

    int size() default 0;

    boolean allowNull() default false;

}
