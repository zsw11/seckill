package com.dayup.seckil.service;

import java.util.List;

import com.dayup.seckil.model.Course;

public interface ICourseService {

	public List<Course> findAllCourses();

	public Course findCourseByCourseNo(String courseNo);
	
	public int reduceStockByCourseNo(String courseNo);
	
}
