package com.didispace.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.didispace.hystrix.ComputeClientHystrix;

/**
 * 
 * 绑定springcloud-compute-service服务。
 * 断路回调：ComputeClientHystrix
 * 
 * @author mutou
 *
 */
@FeignClient(value="service-A",fallback = ComputeClientHystrix.class)	//绑定接口对应service-A服务
public interface ComputeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);

}	