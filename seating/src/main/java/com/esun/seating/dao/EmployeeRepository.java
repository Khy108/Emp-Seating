package com.esun.seating.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esun.seating.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	@Query("SELECT emp FROM Employee emp WHERE emp.floorSeatSeq = :floorSeatSeq")
	Optional<Employee> chooseSeatBySeq(@Param("floorSeatSeq") Integer floorSeatSeq);
	
}
