package com.ymu.rpc.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by mutou on 16-12-14.
 */
public class BaseService {

	@Autowired
	public StringRedisTemplate stringRedisTemplate;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public RedisTemplate redisTemplate;
}
