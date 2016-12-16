package com.freeway.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.fxtech.pfatwebsite.models.EMcategory;

public class TreeGridDataTest {
	@Test
	public void print()
			throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		EMcategory bean = new EMcategory();
		bean.setId(1);
		bean.setCateName("ajaxfan");

		Map db = new HashMap();

		for (Field field : bean.getClass().getDeclaredFields()) {
			BeanUtils.setProperty(db, field.getName(), BeanUtils.getProperty(bean, field.getName()));
		}
		
		System.out.println(new ObjectMapper().writeValueAsString(db));
	}
}
