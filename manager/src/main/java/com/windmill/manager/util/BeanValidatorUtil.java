package com.windmill.manager.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author z022921
 *
 *
 *         Request payload validator class
 */
public class BeanValidatorUtil {

	private BeanValidatorUtil() {

	}

	public static <T> List<String> validate(T obj, Class<T> clazz) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(obj);

		List<String> violationMsg = new ArrayList<>();

		for (ConstraintViolation<T> violation : constraintViolationSet) {
			violationMsg.add(violation.getMessage());
		}

		return violationMsg;
	}
}
