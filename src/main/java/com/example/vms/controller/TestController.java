package com.example.vms.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vms.model.Employee;
import com.example.vms.respository.EmployeeRespository;
import com.example.vms.respository.VendorDetailsRespository;
import com.example.vms.service.AsymetricKeyUtils;
import com.example.vms.service.DigitalSignatureUtil;

@RestController
@CrossOrigin
public class TestController {

	@Autowired
	AsymetricKeyUtils asymetricKeyUtils;

	@Autowired
	DigitalSignatureUtil DigitalSignatureUtil;
	
	@Autowired
	VendorDetailsRespository vendorDetailsRespository;

	@Autowired
	EmployeeRespository employeeRespository; 
	
	@GetMapping("/test")
	public void getPath() {
		asymetricKeyUtils.getKeyValuePair();
		byte[] a = DigitalSignatureUtil.getSign("abcd2", asymetricKeyUtils.getKeyValuePair());
		String a1 = Base64.getEncoder().encodeToString(a);
		System.out.println(a1);
		try {
			System.out.print(DigitalSignatureUtil.getSignVerified("abcd",  Base64.getDecoder().decode(a1),
					asymetricKeyUtils.getKeyValuePair()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
