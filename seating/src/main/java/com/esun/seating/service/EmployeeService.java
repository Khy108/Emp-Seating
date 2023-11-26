package com.esun.seating.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.seating.dao.EmployeeRepository;
import com.esun.seating.dao.SeatingRepository;
import com.esun.seating.entity.Employee;
import com.esun.seating.entity.SeatingChart;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final SeatingRepository seatingRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, SeatingRepository seatingRepository) {
		this.employeeRepository = employeeRepository;
		this.seatingRepository = seatingRepository;
	}
	
	// 找所有員工
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}

	// 員工訂位
	public Optional<Employee> chooseSeat(String empId, Integer floorSeatSeq) {

		 seatingRepository.seatState(floorSeatSeq, "selected", "available");

		    // 检查座位状态是否已经更新
		    Optional<SeatingChart> updatedSeat = seatingRepository.findByFloorSeatSeq(floorSeatSeq);
		    if (!updatedSeat.isPresent() || !"selected".equals(updatedSeat.get().getSeatStatus())) {
		        return Optional.empty(); // 座位状态没有更新，可能是因为已被占用
		    }

		    // 查找员工
		    Optional<Employee> employee = employeeRepository.findById(empId);
		    if (!employee.isPresent()) {
		        return Optional.empty(); // 员工不存在
		    }

		    // 更新员工座位
		    Employee emp = employee.get();
		    SeatingChart seatingChart = updatedSeat.get();
		    emp.setFloorSeatSeq(seatingChart);
		    employeeRepository.save(emp);

		    return Optional.of(emp);
		}

}
