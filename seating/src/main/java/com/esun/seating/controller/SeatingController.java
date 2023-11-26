package com.esun.seating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.esun.seating.entity.SeatingChart;
import com.esun.seating.service.SeatingService;

@RestController
@RequestMapping("/seat")
public class SeatingController {

	@Autowired
	private SeatingService seatingService;

	@PostMapping("/generateSeats")
	public String generateSeats() {
		seatingService.generateSeatingChartDate();
		return "座位建立完成";

	}
	
	// 所有座位
	@GetMapping("/showAllSeats")
	public ResponseEntity<?> getAllSeats(){
		List<SeatingChart> seats = seatingService.getAllSeats();
		return ResponseEntity.ok(seats);
		
	}

}
