package com.logistics.web.action;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @类描述：视频
 *
 * @创建人：mt
 * @创建时间：2014年11月13日下午6:05:52
 * @修改人：Administrator
 * @修改时间：2014年11月13日下午6:05:52
 * @修改备注：
 * @version v1.0
 * @Copyright 
 * @mail 932852117@qq.com
 */
@Controller
public class VideoPlay {

	@RequestMapping("/app-logistics-video")
	public String sewisePlayerTest(SitePreference sitePreference, Model model) {
		return "app-logistics-video";
	}
	
}
