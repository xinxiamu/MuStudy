package com.didispace.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class HelloController {
    
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(String userName,String sex) {
        return "Hello World" + userName + "--" + sex;
    }

    //“/index”和“/”一样
    @RequestMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setAge(18);
        user.setName("木头");
        user.setSex("男");
        user.setFlg(false);
        user.setCreateTime(new Date());

        User user2 = new User();
        user2.setAge(20);
        user2.setName("猪头");
        user2.setSex("女");
        user2.setFlg(false);
        user2.setCreateTime(new Date());

        List userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);
        userList.add(user);

        model.addAttribute("host", "http://blog.didispace.com");
        model.addAttribute("userName","mutou");
        model.addAttribute(user);
        model.addAttribute(userList);
        model.addAttribute("num",83);
        model.addAttribute("num1",3);

        model.addAttribute("bol",true);

        model.addAttribute("path","/imgs/xyk.jpg");

        model.addAttribute("html","This is an &lt;em&gt;HTML&lt;/em&gt; text. &lt;b&gt;Enjoy yourself!&lt;/b&gt;");

        return "index";
    }

    /**
     * 请求某个thymeleaf片段
     * @return
     */
    @RequestMapping(path = {"/frg","/fragment"})
    public String fragment() {
        return "common/footer::footer";
    }

    class User {
        private String name;
        private int age;
        private String sex;
        private boolean flg;
        private Date createTime;

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public boolean isFlg() {
            return flg;
        }

        public void setFlg(boolean flg) {
            this.flg = flg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

}