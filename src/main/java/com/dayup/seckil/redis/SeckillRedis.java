package com.dayup.seckil.redis;

import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.Orders;

@Repository
public class SeckillRedis extends BaseRedis<Orders>{
	private static final String REDIS_KEY = "com.dayup.seckil.redis.SeckillRedis";
	
	@Override
	protected String getRedisKey() {
		return REDIS_KEY;
	}

}
