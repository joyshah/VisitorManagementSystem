package com.example.vms.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vms.model.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {

}
