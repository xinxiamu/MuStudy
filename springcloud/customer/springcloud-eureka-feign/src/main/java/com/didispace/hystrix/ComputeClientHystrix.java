package com.didispace.hystrix;

import org.springframework.stereotype.Component;

import com.didispace.service.ComputeClient;

/**
 * 当服务断路时候，客户端请求服务失败回调。
 * @author mutou
 *
 */
@Component
public class ComputeClientHystrix implements ComputeClient {

	@Override
	public Integer add(Integer a, Integer b) {
		return a + b + 3;
	}

}
