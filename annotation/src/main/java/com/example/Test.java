package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhairui on 2017/2/28.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface  Test {
    String value() default "";
}
