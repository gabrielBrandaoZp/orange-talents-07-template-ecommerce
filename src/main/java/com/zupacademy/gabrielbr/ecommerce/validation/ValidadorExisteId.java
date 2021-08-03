package com.zupacademy.gabrielbr.ecommerce.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidadorExisteId implements ConstraintValidator<ExisteId, Long> {

    private String atributo;
    private Class<?> klass;
    private boolean isFieldNull;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExisteId constraintAnnotation) {
        atributo = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
        isFieldNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(isFieldNull && value == null) return true;
        Query query = entityManager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + atributo + " = :value");
        query.setParameter("value", value);

        List<?> result = query.getResultList();
        return !result.isEmpty();
    }
}
