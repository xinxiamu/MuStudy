package com.ymu.web.www.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymu.api.IUserDetailsService;
import com.ymu.api.IUserService;
import com.ymu.model.User;
import com.ymu.model.UserDetails;
import com.ymu.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

//    @Autowired
    @Resource
    private IUserDetailsService userDetailsService;


    @RequestMapping(value = "/get-by-id.do")
    @ResponseBody
    public User getUserById(long id) throws Exception {
        return userService.getById(id);
    }

    @RequestMapping(value = "/get-by-name.do")
    @ResponseBody
    public User getUserByName(String  name) throws Exception {
        return userService.getByUserName(name);
    }

    @RequestMapping(value = "/get.do")
    @ResponseBody
    public UserVo getUserInfo(long id) throws Exception {
        return userService.getUserAllInfoByUserId(id);
    }

    @RequestMapping(value = "/save.do")
    @ResponseBody
    public User save() throws Exception {
        User user = new User();
        user.setUserName("樟木头");
        user.setAge(19);
        user.setSex(1);
        return userService.save(user);
    }

    @RequestMapping(value = "/save-all.do")
    @ResponseBody
    public UserVo saveUserAllInfo() throws Exception {
        User user = new User();
        user.setUserName("ymu");
        user.setAge(30);
        user.setSex(2);

        UserDetails userDetails = new UserDetails();
        userDetails.setMobile("13543495276");
        userDetails.setRealName("莺木");

        UserVo vo = new UserVo();
        vo.setUser(user);
        vo.setUserDetails(userDetails);

        vo = userService.saveUserAllInfo(vo);
        return vo;
    }

}
