package com.dayup.seckil.service;


import javax.servlet.http.HttpServletRequest;

import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.model.Course;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;

public interface ISeckillService {

	Result<Orders> seckillFlow(User user, String courseNo);

	void cacheAllCourse();

	public Orders seckill(User user, Course course);

	Result<Orders> seckillResult(User user, String courseNo);

	String getPath(User user, String courseNo);

	Result<Orders> seckillFlow(User user, String courseNo, String path);

	Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request);

}
