package com.freeway.test;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TreeGridDataTest {
	@Test
	public void print()
			throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String str = "d:/fx//2/Error Messages Reference.pdf";
		
		System.out.println(str.matches(".*\\.pdf$"));
	}
}
