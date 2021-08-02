package com.zupacademy.gabrielbr.ecommerce.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidadorCampoUnico implements ConstraintValidator<CampoUnico, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String atributo;
    private Class<?> klass;


    @Override
    public void initialize(CampoUnico constraintAnnotation) {
        atributo = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + atributo + " = :value");
        query.setParameter("value", value);
        List<?> result = query.getResultList();
        return result.isEmpty();
    }
}
