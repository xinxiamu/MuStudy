package com.didispace;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import com.didispace.domain.Address;
import com.didispace.domain.BaseVo;
import com.didispace.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String,User> redisTemplate;

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate jedisTemplate;
	
	@Test
	public void templateTest() {
		User user = new User("张华", 18);
		ValueOperations<String, User> uop = jedisTemplate.opsForValue();
		uop.set("user", user);
		System.out.println("------user=" + uop.get("user").getUsername());
		
		Address adr = new Address();
		adr.setPrv("广东省");
		adr.setCity("茂名市");
		adr.setArea("滨海新区");
		adr.setZcode(123456l);
		ValueOperations<String,Address> ov = jedisTemplate.opsForValue();
		ov.set("adr", adr);
		System.out.println("==========adr:" + ov.get("adr").getPrv());
	}

	@Test
	public void stringRedisTemplateTest() throws Exception {
		// 简单的<string,string>
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set("ab", "ababababab");
		ops.set("c", "真的吗",50l);
//		stringRedisTemplate.delete("ab");
		System.out.println("------------ab:" + ops.get("ab"));
		System.out.println("------------c:" + ops.get("c"));
	}

	@Test
	public void test() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		User user = new User("超人", 20);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蝙蝠侠", 30);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蜘蛛侠", 40);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge()
				.longValue());
		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge()
				.longValue());
		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge()
				.longValue());

	}

}
