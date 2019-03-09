package com.example.vms.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vms.entity.VendorDetails;
import com.example.vms.model.Visitor;
import com.example.vms.respository.VendorDetailsRespository;
import com.example.vms.util.AsymetricKeyUtils;
import com.example.vms.util.DigitalSignatureUtil;
import com.example.vms.util.GeneratePdfUtility;
import com.example.vms.util.MailUtil;
import com.example.vms.util.QRCodeUtil;
import com.google.zxing.WriterException;

@Service
public class VisitorService {
	@Autowired
	VendorDetailsRespository vendorDetailsRespository;

	@Autowired
	QRCodeUtil qrCodeUtil;

	@Autowired
	GeneratePdfUtility pdfUtil;

	@Autowired
	DigitalSignatureUtil DigitalSignatureUtil;

	@Autowired
	AsymetricKeyUtils asymetricKeyUtils;

	public void createVisitor(Visitor vendor) {
		Visitor visitor = new Visitor();
		BufferedImage qrImg = null;
		VendorDetails vendorDetails = visitor.toVendorEntity(vendor);
		VendorDetails vendorEntity = vendorDetailsRespository.save(vendorDetails);
		String qrCode = "VIS" + vendorEntity.getId();
		try {
			byte[] digitalSignatureByte = DigitalSignatureUtil.getSign("abcd2", asymetricKeyUtils.getKeyValuePair());
			String digitalSignatureString = Base64.getEncoder().encodeToString(digitalSignatureByte);
			qrCode += "-" + digitalSignatureString;
			qrImg = qrCodeUtil.createQRImage(qrCode);
			vendorDetails.setQrCode(qrCode);
			vendorDetails.setStatus("Approved");
			vendorDetailsRespository.save(vendorDetails);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MailUtil mail = new MailUtil();

		if (vendor.getVisitorEmail() != null)
			mail.send("shahjoy831994@gmail.com", "Momai@1994", vendor.getVisitorEmail(), "Granted Temporary Access",
					"Hi, Please find the attach QR code. Kindly bring your photo ID for verification.  Thanks.", qrImg);
	}

	public String returnAsPdf(String qrCode, String laptopCode) {
		BufferedImage qrImg = null;
		try {
			qrImg = qrCodeUtil.createQRImage(qrCode);
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VendorDetails vendor = vendorDetailsRespository.findByQrCode(qrCode);
		vendorDetailsRespository.updateLaptop(laptopCode, qrCode);
		byte[] gatepass = null;
		try {
			gatepass = FileUtils.readFileToByteArray(pdfUtil.createPdf(vendor.getVisitorName(), vendor.getVisitorType(),
					vendor.getVisitDate(), vendor.getLaptopCode(), qrImg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qrCodeUtil.encodeToString(gatepass, "");
	}
	
	public List<VendorDetails> retrieveForAdmin() {
		return vendorDetailsRespository.retrieveDetails();
	}

	public boolean checkStatus(String qrCode) {
		if ("Approved".equals(vendorDetailsRespository.checkStatus(qrCode))) {
			return true;
		}
		return false;
	}
}
