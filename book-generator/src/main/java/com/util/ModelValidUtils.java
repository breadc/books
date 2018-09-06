package com.util;

import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * 校验字段值是否为空
 * 
 * @author chenghui
 *
 */
public class ModelValidUtils {

	public static void validModel(Object object)
			throws GeneralSecurityException {
		List<String> errors = new ArrayList<>();
		if (object == null) {
			errors.add("对象不能为空");
		}
		validFiledValue(object, errors);
		if (errors.size() > 0) {
			throw new GeneralSecurityException(errors.get(0));
		}
	}

	private static void validFiledValue(Object object, List<String> errors) {
		Field[] fileds = object.getClass().getDeclaredFields();
		if (fileds == null || fileds.length == 0) {
			errors.add("对象不能为空");
			return;
		}
		for (Field field : fileds) {
			field.setAccessible(true);
			try {
				String type = field.getType().getName();
				if (type.equals("java.lang.String")) {
					Object value = field.get(field.getName());
					if (value == null) {
						errors.add("值为空!");
						break;
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
