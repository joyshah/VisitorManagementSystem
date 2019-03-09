package com.example.vms.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.vms.entity.VendorDetails;

@Repository
public interface VendorDetailsRespository extends JpaRepository<VendorDetails, Integer> {
	public VendorDetails findByQrCode(String qrCode);
		
	@Query("update VendorDetails set laptopCode = :laptopCode where qrCode = :qrCode")
	@Modifying(clearAutomatically = true)
	@Transactional
	public void updateLaptop(@Param("laptopCode") String laptopCode,@Param("qrCode") String qrCode);
	
	@Query("select status from VendorDetails where qrCode = :qrCode")
	public String checkStatus(@Param("qrCode") String qrCode);
	
	@Query("from VendorDetails")
	public List<VendorDetails> retrieveDetails();
	
}
