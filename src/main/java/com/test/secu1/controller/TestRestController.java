package com.test.secu1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
	@GetMapping("/test/test")
	
	public String test() {
		return "index";
	}
	@GetMapping("/test/list")
	@ResponseBody
	public List<String> list(){
		List<String> list =new ArrayList<>();
		list.add("1");
		return list;
	}
}
