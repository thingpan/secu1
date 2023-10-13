package com.test.secu1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan
public class Secu1Application {

	public static void main(String[] args) {
		SpringApplication.run(Secu1Application.class, args);
	}

}
