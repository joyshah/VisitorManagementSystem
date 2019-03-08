package com.example.vms.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vms.model.VendorDetails;

@Repository
public interface VendorDetailsRespository extends JpaRepository<VendorDetails, Integer> {

}
