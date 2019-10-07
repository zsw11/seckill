package com.dayup.seckil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>{

	@Modifying
	@Query("update Course c set stockQuantity = stockQuantity - 1 where courseNo = :courseNo and stockQuantity > 0")
	public int reduceStockByCourseNo(@Param(value = "courseNo")String courseNo);

}
