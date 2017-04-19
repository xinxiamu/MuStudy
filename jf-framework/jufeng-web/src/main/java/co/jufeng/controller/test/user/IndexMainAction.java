package co.jufeng.controller.test.user;


import co.jufeng.BaseAction;
import co.jufeng.logger.LoggerUtil;
import co.jufeng.web.servlet.ModelAndView;
import co.jufeng.web.servlet.bind.annotation.DefaultAction;
import co.jufeng.web.servlet.bind.annotation.RestController;

@RestController
public class IndexMainAction extends BaseAction{

	/**
	 * http://127.0.0.1:8080/jufeng-web/user/indexMain.do
	 * Default execution index method
	 * @return String 
	 */
	@DefaultAction
	public String index(){
		LoggerUtil.info(getClass(), this.getParameter());
		System.out.println("http://127.0.0.1:8080/jufeng-web/user/index.do");
		return "test/common/top.html";
	}
	//http://www.jufeng.co:8080/jufeng-web/user/indexMain!aa.do
	public String aa(){
		LoggerUtil.info(getClass(), this.getParameter());
		return "user.do";
	}
	
	//http://www.jufeng.co:8080/jufeng-web/user/indexMain!aa2.do
	public ModelAndView aa2(ModelAndView modelAndView){
		LoggerUtil.info(getClass(), this.getParameter());
		modelAndView.addViewName("index.do");
		return modelAndView;
	}

}