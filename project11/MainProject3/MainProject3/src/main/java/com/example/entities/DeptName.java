package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DeptName")
public class DeptName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dept_no")
	private int deptno;
	
	
	@Column(name="dept_name")
	private String pincode;
	
	@Column(name="status")
	private String adharId;
	
	@Column(name="pincode")
	private String pin;
	

	public DeptName() {
		
	}

	
	public DeptName(int deptno, String pincode, String adharId, String pin) {
		this.deptno = deptno;
		this.pincode = pincode;
		this.adharId = adharId;
		this.pin = pin;
	
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAdharId() {
		return adharId;
	}

	public void setAdharId(String adharId) {
		this.adharId = adharId;
	}

	
	
	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public DeptName(String adharId, String pin) {
		this.adharId = adharId;
		this.pin = pin;
		
	}


	
	
	

}
