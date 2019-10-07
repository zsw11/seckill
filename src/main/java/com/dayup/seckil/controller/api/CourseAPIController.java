 package com.dayup.seckil.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayup.seckil.VO.CourseVO;
import com.dayup.seckil.base.controller.BaseApiController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.model.Course;
import com.dayup.seckil.service.ICourseService;
import com.dayup.seckil.util.CourseUtil;

@RestController
public class CourseAPIController extends BaseApiController{

	@Autowired
	public ICourseService courseService;
	
	@RequestMapping(value="courseList",method=RequestMethod.GET)
	public Result<List<Course>> courseList(){
		return Result.success(courseService.findAllCourses());
	}
	
	@RequestMapping(value="courseDetail/{courseNo}",method=RequestMethod.GET)
	public Result<CourseVO> courseDetail(@PathVariable String courseNo){
		return Result.success(CourseUtil.courseToCourseVO(courseService.findCourseByCourseNo(courseNo)));
	}
}
