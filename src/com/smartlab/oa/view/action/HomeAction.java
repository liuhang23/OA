package com.smartlab.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	// 主页面
	public String index() throws Exception {
		return "index";
	}

	// 下页面
	public String bottom() throws Exception {
		return "bottom";
	}

	// 上页面
	public String top() throws Exception {
		return "top";
	}

	// 左页面
	public String left() throws Exception {
		return "left";
	}

	// 右页面
	public String right() throws Exception {
		return "right";
	}

}
