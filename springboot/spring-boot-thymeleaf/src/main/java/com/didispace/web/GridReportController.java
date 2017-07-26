package com.didispace.web;

import com.alibaba.fastjson.JSONObject;
import com.didispace.web.vo.VSignInfoRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class GridReportController {

	/**
	 * html,360，360极速浏览器全面支持。谷歌，火狐不支持
	 * @return
	 */
	@RequestMapping("/sign")
	public String sign() {
		return "grid-report/sign";
	}

	@RequestMapping("/sign-data")
	@ResponseBody
	public String signData () throws Exception {
		List<VSignInfoRes> list = new ArrayList<>();
		VSignInfoRes v1 = new VSignInfoRes();
		VSignInfoRes v2 = new VSignInfoRes();
		v1.setTmall_check_("天猫1");
		v2.setTmall_check_("天猫非");
		list.add(v1);
		list.add(v2);
		HashMap<String,Object> h = new HashMap<>();
		h.put("Table",list);
		return JSONObject.toJSONString(h);
	}

	/**
	 * h5形式
	 * @return
	 */
	@RequestMapping("/freeGrid")
	public String freeGrid() {
		return "grid-report/free-grid";
	}

}
