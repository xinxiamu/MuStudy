package com.ymu.web.www.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.ymu.infrastructure.web.json.AjaxResponseBody;
import com.ymu.infrastructure.web.json.jsonview.Views;
import com.ymu.vo.par.LoginFormVo;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @RequestMapping(value = "/index.do",method = RequestMethod.GET)
    public String index() throws Exception {
        model.addAttribute("loginFormVo",new LoginFormVo());
        return "login/index";
    }

    @RequestMapping(value = "/index.do",method = RequestMethod.POST)
    public String login(@Valid LoginFormVo loginFormVo, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            //在代码中添加错误信息
            result.rejectValue("username","noUsername","该用户名不存在");

            return "login/index";
        }
        return "redirect:/index.do";
    }

    @RequestMapping("/login-ajax.do")
    @ResponseBody
    @JsonView(Views.Public.class)
    public AjaxResponseBody loginAjax(@Valid LoginFormVo loginParVo, BindingResult result) {
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        //有错误信息.
        model.addAttribute("loginParVo",loginParVo);
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError error:list){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }

            ajaxResponseBody.setCode(200);
            ajaxResponseBody.setData(list);
            ajaxResponseBody.setDescription("成功");

        }

        return ajaxResponseBody;
    }
}
