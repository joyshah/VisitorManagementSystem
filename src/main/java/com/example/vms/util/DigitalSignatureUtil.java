package com.example.vms.util;

import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.Signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DigitalSignatureUtil {

	@Autowired
	SecureRandom secureRandom;

	public byte[] getSign(String data, KeyPair keyPair) {
		byte[] digitalSignature = null;
		try {
			Signature signature = Signature.getInstance("SHA256WithDSA");
			signature.initSign(keyPair.getPrivate(), secureRandom);
			signature.update(data.getBytes("UTF-8"));
			digitalSignature = signature.sign();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return digitalSignature;
	}

	public boolean getSignVerified(String data, byte[] incomingSignature, KeyPair keyPair) {
		try {
			Signature signature = Signature.getInstance("SHA256WithDSA");
			signature.initVerify(keyPair.getPublic());
			signature.update(data.getBytes("UTF-8"));
			System.out.println(data+" "+incomingSignature);
			
			boolean verified = signature.verify(incomingSignature);
			return verified;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
