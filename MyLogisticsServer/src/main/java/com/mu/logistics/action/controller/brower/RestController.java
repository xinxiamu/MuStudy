package com.mu.logistics.action.controller.brower;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public RestController() {

	}

	@RequestMapping(value = "/login-brower/{userName}", method = RequestMethod.GET)
	public ModelAndView myMethod(HttpServletRequest req,
			HttpServletResponse res, @PathVariable("userName") String u,
			ModelMap mm) throws Exception {
		mm.put("loginUser", u);
		return new ModelAndView("/login/logined", mm);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String registPost() {
		return "/home";
	}

	@RequestMapping(value = "/detect-device",method = RequestMethod.GET)
	public @ResponseBody
	String detectDevice(Device device,Model model) {
		String deviceType = "unknown";
		if (device.isNormal()) {
			deviceType = "normal";
		} else if (device.isMobile()) {
			deviceType = "mobile";
		} else if (device.isTablet()) {
			deviceType = "tablet";
		}
		return "Hello " + deviceType + " browser!";
	}
	
	@RequestMapping("/detect-device2")
	public String home(Device device, Model model) {
		if (device == null) {
			logger.info("no device detected");
		} else if (device.isNormal()) {
			logger.info("Device is normal");
		} else if (device.isMobile()) {
			logger.info("Device is mobile");
		} else if (device.isTablet()) {
			logger.info("Device is tablet");
		}
		return "home";
	}
}
