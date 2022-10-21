package com.humanlearning.rentermatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.humanlearning.rentermatch.mapper")
@SpringBootApplication
public class RentermatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentermatchApplication.class, args);
	}

}
