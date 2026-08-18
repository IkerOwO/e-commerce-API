package com.iker.ecommerce_api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 
 * Checks if any of the fields provided are empty
 * In case that any of them are empty, it shows a message
 * <p>
 * Equivalent to {@code @NotBlank}
 * <p>
 * 
 * @see NotBlank
 */
@Constraint(validatedBy = RequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsRequired {
    String message() default "Es requerido!";

    Class<?>[] groups() default { };
    
    Class<? extends Payload>[] payload() default { };
}
