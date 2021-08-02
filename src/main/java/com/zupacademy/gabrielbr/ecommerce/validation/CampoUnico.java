package com.zupacademy.gabrielbr.ecommerce.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidadorCampoUnico.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface CampoUnico {
    public String message() default "{br.com.zupacademy.gabrielbrandao.casadocodigo.beanvalidation.campounico}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};

    String fieldName();

    Class<?> domainClass();
}
