package com.example.vms.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vms.entity.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {

}
