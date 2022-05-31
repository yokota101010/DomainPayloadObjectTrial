package com.example.fw;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.fw.annotation.Identifier;

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

		Field[] declaredAndInheritedFields = getDeclaredAndInheritedFields(obj.getClass());

		try {
			for(Field field : declaredAndInheritedFields) {
				field.setAccessible(true);

				if(Arrays.asList(primitiveTypes).contains(field.getGenericType().getTypeName())){

					//プリミティブ型（⇒出力）
					fieldsMap.put(incompleteName + field.getName(), field.get(obj).toString());

				} else if(field.getType().getAnnotation(Identifier.class) != null) {

					//識別子型（⇒出力）
					fieldsMap.put(incompleteName + field.getName(), field.get(obj).toString());

				} else if(field.getGenericType().getTypeName().startsWith("java.util.List")) {

					//List型（⇒要素毎に再帰呼び出し）
					List<?> list = (List<?>)field.get(obj);
					for(int i=0; i<list.size(); i++) {
						outputFieldsToMap(
								list.get(i),
								incompleteName + field.getName() + "[" + i + "].");
					}

				} else if(field.getGenericType().getTypeName().startsWith("java.util.Map")) {

					//Map型（⇒要素毎に再帰呼び出し）
					Map<?, ?> map = (Map<?, ?>)field.get(obj);
					for(Object key : map.keySet()) {
						outputFieldsToMap(
								map.get(key),
								incompleteName + field.getName() + "[" + key + "].");
					}
				} else {

					//その他のクラス型（⇒再帰呼び出し）
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

	//継承したフィールドも含めてクラスの持つフィールド一覧を取得
	private Field[] getDeclaredAndInheritedFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<>();
		getDeclaredAndInheritedFields(clazz, fieldList);
		return fieldList.toArray(new Field[fieldList.size()]);
	}

	private void getDeclaredAndInheritedFields(Class<?> clazz, List<Field> fieldList) {

		if(clazz.getSuperclass() != null) {
			getDeclaredAndInheritedFields(clazz.getSuperclass(), fieldList);
		}

		fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
	}
}
