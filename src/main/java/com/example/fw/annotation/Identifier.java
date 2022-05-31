package com.example.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.TYPE
})
@Constraint(validatedBy = {IdValidator.class})

public @interface Identifier {
	String message() default "空文字はNGです。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
