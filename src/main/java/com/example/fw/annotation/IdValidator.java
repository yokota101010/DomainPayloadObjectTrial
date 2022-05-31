package com.example.fw.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.fw.IdObject;

public class IdValidator implements ConstraintValidator<Identifier, IdObject> {

	public void initialize(Identifier annotation) {
    }

	@Override
	public boolean isValid(IdObject value, ConstraintValidatorContext context) {

		return !isEmptyOrNull(value.getId());
	}

	private static boolean isEmptyOrNull(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
