package com.example.fw;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FieldsToMap {

	private Map<String, String> fieldsMap = new LinkedHashMap<>();

	private static String[] primitiveTypes = {
				"java.lang.String",
				"java.util.Date",
				"java.lang.Integer",
				"int",
				"java.lang.Long",
				"long",
				"java.lang.Float",
				"float",
				"java.lang.Double",
				"double"};

	public Map<String, String> outputFieldsToMap(Object obj) {
		outputFieldsToMap(obj, "");
		return fieldsMap;
	}

	private void outputFieldsToMap(Object obj, String incompleteName) {

		try {
			for(Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);

				if(Arrays.asList(primitiveTypes).contains(field.getGenericType().getTypeName())){

					//プリミティブ型
					fieldsMap.put(incompleteName + field.getName(), field.get(obj).toString());

				} else if(field.getGenericType().getTypeName().startsWith("java.util.List")) {

					//List型
					List<?> list = (List<?>)field.get(obj);
					for(int i=0; i<list.size(); i++) {
						outputFieldsToMap(
								list.get(i),
								incompleteName + field.getName() + "[" + i + "].");
					}

				} else if(field.getGenericType().getTypeName().startsWith("java.util.Map")) {

					//Map型
					Map<?, ?> map = (Map<?, ?>)field.get(obj);
					for(Object key : map.keySet()) {
						outputFieldsToMap(
								map.get(key),
								incompleteName + field.getName() + "[" + key + "].");
					}

				} else {

					//その他のクラス型
					outputFieldsToMap(
							field.get(obj),
							incompleteName + field.getName() + ".");

				}
			}
		} catch(IllegalAccessException iae) {
			System.out.println(iae.toString());
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
