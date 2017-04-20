package com.logistics.app.action.controller;

import javax.annotation.Resource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mu.utils.email.EmailInfo;
import com.mu.utils.email.impl.EmailSenderImpl;
import com.mu.utils.logger.Log;

@Controller
public class TestController {
	
	@Resource
	private EmailSenderImpl emailSenderImpl;
	
	@Resource(name="javaMailSender")
	private JavaMailSender javaMailSender;

	@RequestMapping("/videoPlayer")
	public String videoPlayerShow(SitePreference sitePreference, Model model) {
		return "videoPlayer";
	}
	
	@RequestMapping("/videojsPlayer")
	public String videoJsShow(SitePreference sitePreference, Model model) {
		return "video-js";
	}
	
	/**
	 * 发送邮件
	 * @param sitePreference
	 * @param model
	 * @return
	 */
	@RequestMapping("/mail")
	public String emailSender(SitePreference sitePreference, Model model) {
		Log.println("发送email咯");
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setFrom("xinxiamu@163.com");
		emailInfo.setTo("932852117@qq.com");
		emailInfo.setSubject("妹子");
		emailInfo.setContent("一块钱一个妹子，买么？？");
		emailSenderImpl.setJavaMailSender(javaMailSender);
		emailSenderImpl.sendEmail(emailInfo);
		return "index";
	}
}
