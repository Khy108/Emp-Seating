package com.esun.seating.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esun.seating.entity.SeatingChart;

import jakarta.transaction.Transactional;

@Repository
public interface SeatingRepository extends JpaRepository<SeatingChart, Integer> {

	Optional<SeatingChart> findByFloorSeatSeq(Integer floorSeatSeq);
	
	// 當座位狀態是open時才能點選座位轉換狀態
	@Modifying
	@Transactional
	@Query("UPDATE SeatingChart sc SET sc.seatStatus = :changeState WHERE sc.floorSeatSeq = :floorSeatSeq And sc.seatStatus = :openStatus" )
	void seatState(@Param("floorSeatSeq")Integer floorSeatSeq, @Param("changeState")String changeState, @Param("openStatus")String openStatus);
	
}
