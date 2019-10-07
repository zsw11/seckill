package com.dayup.seckil.redis;

import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.Course;

@Repository
public class CourseRedis extends BaseRedis<Course>{

	private static final String REDIS_KEY = "com.dayup.seckil.redis.CourseRedis";
	
	@Override
	protected String getRedisKey() {
		return REDIS_KEY;
	}

}
