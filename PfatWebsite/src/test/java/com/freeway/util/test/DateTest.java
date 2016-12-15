package com.freeway.util.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DateTest {
	@Test
	public void datePrint() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String str = mapper.writeValueAsString(new Timestamp(System.currentTimeMillis()));
		System.out.println(str);
	}
}
