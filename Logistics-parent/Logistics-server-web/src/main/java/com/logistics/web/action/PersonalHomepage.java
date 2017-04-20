package com.logistics.web.action;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @类描述：个人主页
 *
 * @创建人：mt
 * @创建时间：2014年11月13日上午11:22:00
 * @修改人：Administrator
 * @修改时间：2014年11月13日上午11:22:00
 * @修改备注：
 * @version v1.0
 * @Copyright 
 * @mail 932852117@qq.com
 */
@Controller
public class PersonalHomepage {

	/**
	 * 我的个人主页
	 * @param sitePreference
	 * @param model
	 * @return
	 */
	@RequestMapping("/myHomepage")
	public String index(SitePreference sitePreference, Model model) {
		return "myHomepage";
	}
}
