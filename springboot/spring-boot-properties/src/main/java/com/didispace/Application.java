package com.didispace;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.didispace.service.SendMessage;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("----启动成功");
	}
	
	@Autowired
    private SendMessage sendMessage;

	//创建bean前调用
    @PostConstruct
    public void init(){
        sendMessage.send();// 会根据profile指定的环境实例化对应的类
    }
    
    //销毁bean前调用
    @PreDestroy
    public void desty(){
       System.out.println("---------销毁bean前调用");
    }

}
