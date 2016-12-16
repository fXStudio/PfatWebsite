package com.freeway.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TreeGridDataTest {
	@Test
	public void print() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text", ".");
		map.put("children", getChilds());

		System.out.println(om.writeValueAsString(map));
	}

	private List getChilds() {
		List list = new ArrayList();

		for (int i = 0; i < 5; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("task", i + "_txt");
			map.put("duration", i);
			map.put("leaf", false);
			map.put("user", i + "_user");
			map.put("iconCls", "task");
			
			list.add(map);
		}
		return list;
	}
}
