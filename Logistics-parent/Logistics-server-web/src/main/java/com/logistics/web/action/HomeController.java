package com.logistics.web.action;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/**
	 * 主页
	 * 
	 * @param sitePreference
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(SitePreference sitePreference, Model model) {
		model.addAttribute("slogan", "与您携手，共创未来！");
		model.addAttribute(
				"company_intro",
				"楹木科技发展有限公司，是一家初创科技公司。 其立足于物流行业，以整合社会物流资源，志在不断构建起一个物流交易的信息平台，并与千千万万的司机、货主等物流相关人员共享科技带来的便利成果。");

		model.addAttribute("app_01", "物流人");
		model.addAttribute("app_01_details", "一款集找车找货，物流社交，物流工具一体的综合性物流应用app……");

		model.addAttribute("app_02", "物流人-司机");
		model.addAttribute("app_02_details",
				"一款供个人车主，物流公司在线查找货源并达成交易的app软件。可以查看附近货源并下单请求交易，也可以主动的向附近的匹配货主发出广播，告诉他们你在寻求业务……");

		model.addAttribute("app_03", "物流人-货主");
		model.addAttribute("app_03_details",
				"一款供货主查找附近车源并下单请求交易，或者主动向附近匹配车主发出请求交易的物流应用app……");

		return "index";
	}
}
