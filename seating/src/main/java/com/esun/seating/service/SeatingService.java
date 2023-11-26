package com.esun.seating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.seating.dao.SeatingRepository;
import com.esun.seating.entity.SeatingChart;

@Service
public class SeatingService {

	private final SeatingRepository seatingRepository;

	@Autowired
	public SeatingService(SeatingRepository seatingRepository) {
		this.seatingRepository = seatingRepository;
	}

	// 循環生成座位
	public void generateSeatingChartDate() {
		for (int floor = 1; floor <= 4; floor++) {
			for (int seat = 1; seat <= 4; seat++) {
				SeatingChart seatingChart = new SeatingChart();
				seatingChart.setFloorNo(floor);
				seatingChart.setSeatNo(seat);
				seatingChart.setSeatStatus("available");

				seatingRepository.save(seatingChart);
			}
		}
	}
	
	// 所有座位
	public List<SeatingChart> getAllSeats(){
		return seatingRepository.findAll();
	}

}
