package com.example.hotelbooking.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotelbooking.entity.CustomerInfo;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo, Integer> {

	@Transactional
	@Modifying
	@Query("Update CustomerInfo set bonusPoint=:bonusPoint WHERE Id=:id")
	Integer updateById(@Param("id") Integer id, @Param("bonusPoint") Integer bonusPoint);

	@Transactional
	@Modifying
	@Query("Update CustomerInfo set bonusPoint=:bonusPoint ,lastbookingStatus=:bookingStatus WHERE Id=:id")
	Integer updateByIdforStatusBonus(@Param("id") Integer id, @Param("bonusPoint") Integer bonusPoint,
			@Param("bookingStatus") String bookingStatus);
	
	@Transactional
	@Modifying
	@Query("Update CustomerInfo set lastbookingStatus=:bookingStatus WHERE Id=:id")
	Integer updateByIdforStatus(@Param("id") Integer id, @Param("bookingStatus") String bookingStatus);

}
