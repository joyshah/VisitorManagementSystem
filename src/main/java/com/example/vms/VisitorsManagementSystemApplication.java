package com.example.vms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.vms.service.AsymetricKeyUtils;

@SpringBootApplication
public class VisitorsManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorsManagementSystemApplication.class, args);
	}
}
