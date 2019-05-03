package com.weifan.ferrier.springboot.security.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * todo: 如果更好的以编译的方式获取其他验证器？如: NotBlankValidator, MinValidator 之类的
 * @author dong
 *
 */
@Slf4j
@Component
public class UsernameValidator implements ConstraintValidator<Username, String> {
	
	private static ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
			.buildValidatorFactory();

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		var validator = factory.getConstraintValidatorFactory().getInstance(NotBlankValidator.class);
		validator.isValid(value, context);
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@" + validator);
		return 	false;
	}

}
