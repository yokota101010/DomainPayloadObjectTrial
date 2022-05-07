package com.example.fw;

import java.util.Map;

import org.springframework.ui.Model;

public class ModelPlus {

	public static void addAttribute(String attributeName, Object attributeValue, Model model) {
		model.addAttribute(attributeName, attributeValue);

		FieldsToMap ftm = new FieldsToMap();
        Map<String, String> fieldsToMapItems = ftm.outputFieldsToMap(attributeValue);
		model.addAttribute("fieldsToMapItems", fieldsToMapItems);
	}
}
