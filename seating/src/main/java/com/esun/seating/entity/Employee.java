package com.esun.seating.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@JoinColumn(name = "floor_seat_seq")
	@OneToOne
	private SeatingChart floorSeatSeq;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SeatingChart getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(SeatingChart floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}
	
	

}
