package com.ymu.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymu.rpc.mqsender.DirectSender;

@Controller
public class IndexController {
	
	@Autowired
	private DirectSender directSender;
	
	@Value("${index.str}")
	private String indexStr;
	
//	@Value("${my.name}")
//	private String myName;
	
	@RequestMapping(value="/")  
	@ResponseBody
	public String index() {
		return indexStr;
	}
	
	/**
	 * 测试：ｗeb每次请求都
	 */
	@RequestMapping(value="/demo")  
	@ResponseBody
	public String index(String str) throws InterruptedException {
		System.out.println("----ｓtr:" + str + "---" + str.hashCode());
//		System.out.println("-----object:" + this.toString() + "----" + this.getClass().getSimpleName());
		Thread.sleep(5000);
		System.out.println("=======ｓtr:" + str);
		return str;
	}
	
	@RequestMapping(value="/directSend")  
	@ResponseBody
	public String directSend(String send) {
		System.err.println("------参数send:" + send);
		directSender.sendToMainＱ(send);
		return send;
	}

}
