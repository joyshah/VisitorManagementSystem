package com.example.vms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VENDER_DETAILS")
public class VendorDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "visitor_type")
	private String visitorType;

	@Column(name = "visitor_name")
	private int visitorName;

	@Column(name = "in_time")
	private String inTime;

	@Column(name = "out_time")
	private String outTime;

	@Column(name = "ref_emp_id")
	private int refEmpId;

	@Column(name = "ref_emp_mail")
	private String refEmpMail;

	@Column(name = "emp_status")
	private String empStatus;

	@Column(name = "last_modification_date")
	private int lastModificationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getVisitorType() {
		return visitorType;
	}

	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

	public int getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(int visitorName) {
		this.visitorName = visitorName;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getRefEmpId() {
		return refEmpId;
	}

	public void setRefEmpId(int refEmpId) {
		this.refEmpId = refEmpId;
	}

	public String getRefEmpMail() {
		return refEmpMail;
	}

	public void setRefEmpMail(String refEmpMail) {
		this.refEmpMail = refEmpMail;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public int getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(int lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

}
