package com.example.fw;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.service.RepositoryService;

@Aspect
@Component
public class FieldsToMapAspect {

	@Autowired
	private RepositoryService repositoryService;

	@AfterReturning("@within(org.springframework.stereotype.Controller)")
	public void outputFieldsToMap(JoinPoint joinPoint) {

		Object[] methodArgValues = joinPoint.getArgs();
		Object dpo = null;
		Model model = null;

		for (int i = 0; i < methodArgValues.length; i++) {
			if(methodArgValues[i].getClass().getAnnotation(DomainPayloadObject.class) != null) {
				dpo = methodArgValues[i];
			};
			if(methodArgValues[i] instanceof Model) {
				model = (Model)methodArgValues[i];
			}
		}

		if((dpo != null) && (model != null)) {
			FieldsToMap ftm = new FieldsToMap();
			Map<String, String> fieldsToMapItems = ftm.outputFieldsToMap(dpo);
			model.addAttribute("fieldsToMapItems", fieldsToMapItems);
		}

		if(model != null) {
			model.addAttribute("repositoryService", repositoryService);
		}
	}
}
