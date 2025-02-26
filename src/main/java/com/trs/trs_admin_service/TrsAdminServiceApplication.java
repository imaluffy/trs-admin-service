package com.trs.trs_admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrsAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrsAdminServiceApplication.class, args);
		System.out.println("Hello admin service");
	}

}
