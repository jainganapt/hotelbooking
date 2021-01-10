package com.example.hotelbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotelbooking.entity.HotelRoom;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Integer> {
	
	@Query("SELECT p FROM HotelRoom p WHERE p.roomType=:roomType")
	public HotelRoom findByRoomType(@Param("roomType") String roomType);

}
