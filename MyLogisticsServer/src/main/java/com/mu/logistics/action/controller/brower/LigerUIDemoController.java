package com.mu.logistics.action.controller.brower;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LigerUIDemoController extends WebBaseController {

	@RequestMapping("/ligerUI")
	public String home(SitePreference sitePreference, Model model) {
		return "ligerUI";
	}
}
