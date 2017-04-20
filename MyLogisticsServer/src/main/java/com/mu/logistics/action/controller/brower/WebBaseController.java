package com.mu.logistics.action.controller.brower;

import org.springframework.stereotype.Controller;

import com.mu.common.action.BaseController;

@Controller
public class WebBaseController extends BaseController {

	@Override
	public boolean isCheckProtocol() {
		return false;
	}

}
