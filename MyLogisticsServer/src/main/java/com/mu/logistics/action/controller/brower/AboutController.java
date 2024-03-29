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

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the About page.
 * @author Roy Clarkson
 */
@Controller
public class AboutController extends WebBaseController {

	/**
	 * Show the About page to the user. Declares a {@link SitePreference} parameter to
	 * show how you can resolve the user's site preference. This controller renders a
	 * different version of the about view if the user has a mobile site preference.
	 */
	@RequestMapping("/about")
	public String home(SitePreference sitePreference, Model model) {
		return "about";
	}
	
	/**
	 * 视频播放器demo
	 * @return
	 */
	@RequestMapping("/video-player")
	public String html5Player() {
		return "videoPlayer";
	}
}
