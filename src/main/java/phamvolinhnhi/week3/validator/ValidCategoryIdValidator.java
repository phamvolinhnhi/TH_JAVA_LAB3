package phamvolinhnhi.week3.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import phamvolinhnhi.week3.entity.Category;
import phamvolinhnhi.week3.validator.annotation.ValidCategoryId;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return category != null && category.getId() != null;
    }
}
