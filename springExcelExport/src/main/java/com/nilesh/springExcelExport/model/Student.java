package com.nilesh.springExcelExport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pid;
	private String pname;
	
	
	
	public Student(long pid, String pname) {
		super();
		this.pid = pid;
		
		this.pname = pname;
	
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
}
