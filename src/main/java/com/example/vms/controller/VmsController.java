package com.example.vms.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vms.entity.VendorDetails;
import com.example.vms.model.StringModel;
import com.example.vms.respository.EmployeeRespository;
import com.example.vms.service.VisitorService;
import com.example.vms.util.AsymetricKeyUtils;
import com.example.vms.util.DigitalSignatureUtil;

@RestController
@CrossOrigin
public class VmsController {

	@Autowired
	AsymetricKeyUtils asymetricKeyUtils;

	@Autowired
	DigitalSignatureUtil DigitalSignatureUtil;

	@Autowired
	VisitorService visitorService;

	@Autowired
	EmployeeRespository employeeRespository;

	@GetMapping("/test")
	public boolean getPath() {
		asymetricKeyUtils.getKeyValuePair();
		byte[] a = DigitalSignatureUtil.getSign("VIS4", asymetricKeyUtils.getKeyValuePair());
		String a1 = "VIS4-"+Base64.getEncoder().encodeToString(a);
		System.out.println(a1);
		try {
			String[] qrValues = a1.split("-");
			System.out.println(qrValues[0]);
			System.out.println(qrValues[1]);
			 return DigitalSignatureUtil.getSignVerified(qrValues[0], Base64.getDecoder().decode(qrValues[1]),
					asymetricKeyUtils.getKeyValuePair());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * @PostMapping("/visitors") public String createVisitor(@RequestBody Visitor
	 * vendor) { visitorService.createVisitor(vendor); return "Visitor is created ";
	 * }
	 */

	@PostMapping("/getPDF")
	public String retrieveVisitorDetailsWithLaptop(@RequestBody StringModel data) {
		return visitorService.returnAsPdf(data.getQrCode(), data.getLaptopCode());
	}

	

	@PostMapping("/qrValidate")
	public boolean validateQrCode(@RequestBody StringModel data) {
		if (visitorService.checkStatus(data.getQrCode())) {
			String[] qrValues = data.getQrCode().split("-");
			try {
				
				 return DigitalSignatureUtil.getSignVerified(qrValues[0], Base64.getDecoder().decode(qrValues[1]),
						asymetricKeyUtils.getKeyValuePair());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		return false;
	}
	
	@GetMapping("/getDetailsAdmin")
	public Map<String,List<VendorDetails>> retriveDetails() {
		Map<String,List<VendorDetails>> value = new HashMap<>();
		value.put("visitorList", visitorService.retrieveForAdmin());
		return value;
	}

}
