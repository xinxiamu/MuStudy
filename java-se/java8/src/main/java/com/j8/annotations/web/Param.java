package com.j8.annotations.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mutou
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface Param {

	public String value();

	public boolean required() default true;

	public String defaultValue() default "";
}