/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mu.logistics.action.controller.brower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mu.common.utils.Log;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Handles requests for the Home page.
 * 
 * @author Roy Clarkson
 */
@Controller
public class HomeController {

	/**
	 * Show the home page to the user. Declares a {@link SitePreference}
	 * parameter to show how you can resolve the user's site preference. This
	 * controller renders a different version of the home view if the user has a
	 * mobile site preference.
	 */
	@RequestMapping("/")
	public String index(String name, SitePreference sitePreference, Model model) {
		model.addAttribute("aa", "dsfdsf");
		System.out.println("----name=" + name);
		if (Integer.valueOf(name) >= 0) {
			return 	"redirect:/index";
		}  
		return "index";
	}

	@RequestMapping("/app")
	public String appIndex(SitePreference sitePreference, Model model) {
		return "index-2";
	}

	@RequestMapping("/index")
	public String index2(SitePreference sitePreference, Model model) {
		Log.println("////////////index2");

		String site = sitePreference.name();
		model.addAttribute("sitePreference", sitePreference.name());
		model.addAttribute("aa", "dsfdsf");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kk", "你好");
		map.put("sitePreference", site);
		map.put("bb", true);
		map.put("dd", 1);
		map.put("cc", map);
		model.addAllAttributes(map);

		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map);
		list.add(map);
		model.addAttribute("list", list);

		return "index";
	}

	@RequestMapping("/home")
	public String home(SitePreference sitePreference, Model model) {
		return "home";
	}

}
