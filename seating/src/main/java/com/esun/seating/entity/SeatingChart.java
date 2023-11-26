package com.esun.seating.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seatingChart")
public class SeatingChart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "floor_seat_seq")
	private Integer floorSeatSeq;

	@Column(name = "floor_no")
	private Integer floorNo;

	@Column(name = "seat_no")
	private Integer seatNo;

	@Column(name = "seat_status")
	private String seatStatus;

	public Integer getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(Integer floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	

}
