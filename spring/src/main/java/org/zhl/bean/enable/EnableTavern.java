package org.zhl.bean.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(EnableBoss.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface EnableTavern {
}
