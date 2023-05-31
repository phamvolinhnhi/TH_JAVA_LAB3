package phamvolinhnhi.week3.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import phamvolinhnhi.week3.validator.ValidUserIdValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidUserIdValidator.class)
@Documented
public @interface ValidUserId {
    String message() default "Invalid User ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
