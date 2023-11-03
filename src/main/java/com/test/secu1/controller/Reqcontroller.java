package com.test.secu1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Reqcontroller {
	@PostMapping("/req/post")
	public String reqPost(String id,String pwd) {
		log.info("id=>{}","pwd=>{}",id,pwd);
		return "";
	}
	
	@PostMapping("/req/file")
	public String reqFile(@RequestParam MultipartFile file) {
		log.info("file name=>{}",file.getOriginalFilename());
		return "ok";
	}
}
