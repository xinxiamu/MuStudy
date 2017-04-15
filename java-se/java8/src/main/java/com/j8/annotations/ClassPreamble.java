package com.j8.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declaring an Annotation Type。元数据
 * @author mutou
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)	//注解的存储范围。这里是运行时JVM.
@Target(value = { ElementType.FIELD ,ElementType.METHOD })	//可以应用于字段（域）、方法
@Documented	//可以用java工具生成文档
@Inherited	//表明该元注解是可以继承的
public @interface ClassPreamble {

	String author();

	String date();

	int currentRevision() default 1;

	String lastModified() default "N/A";

	String lastModifiedBy() default "N/A";

	// Note use of array
	String[] reviewers();
}

@interface ClassPreambles {
	ClassPreamble[] value();
}
