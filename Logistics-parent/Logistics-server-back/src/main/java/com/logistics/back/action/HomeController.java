package com.logistics.back.action;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the Home page.
 * @author Roy Clarkson
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(SitePreference sitePreference, Model model) {
		return "ligerUI";
	}

}
