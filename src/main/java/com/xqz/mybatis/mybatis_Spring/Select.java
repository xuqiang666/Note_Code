package com.xqz.mybatis.mybatis_Spring;

import java.lang.annotation.*;

/**
 * @Author: 许庆之 on 2020/10/23.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Select {

    String value() default "";  // sql语句

}
