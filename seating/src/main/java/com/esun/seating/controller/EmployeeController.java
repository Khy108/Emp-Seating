package com.esun.seating.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esun.seating.dto.SeatDTO;
import com.esun.seating.entity.Employee;
import com.esun.seating.entity.SeatingChart;
import com.esun.seating.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// 所有員工
	@GetMapping("/allEmp")
	public ResponseEntity<List<Employee>> findAll(){
		List<Employee> emps = employeeService.findAll();
		return ResponseEntity.ok(emps);
	}

	// 員工選擇座位
	@PostMapping("/choose")
	public ResponseEntity<?> chooseEmpSeat(@RequestBody SeatDTO seatDTO) {

		Optional<Employee> selectSeat = employeeService.chooseSeat(seatDTO.getEmpId(), seatDTO.getFloorSeatSeq());

		if (!selectSeat.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("座位選擇失敗");
		} 
		
		Employee emp = selectSeat.get();
		return ResponseEntity.ok(emp);
	}
}
