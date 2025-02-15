package com.trs.trs_admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrsAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrsAdminServiceApplication.class, args);
		System.out.println("Hello admin service");
	}

}
