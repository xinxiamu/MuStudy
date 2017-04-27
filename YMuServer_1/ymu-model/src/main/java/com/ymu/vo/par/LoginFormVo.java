package com.ymu.vo.par;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 *  登录参数。
 */
public class LoginFormVo {

    @NotEmpty(message="{login.username.notnull}")
    private String username;

    //注意注解顺序，下往上显示提示
    @Length(min = 5, max = 20, message = "{login.pwd.length}")
    @NotEmpty(message="{login.pwd.notnull}")
//    @Pattern(regexp="[a-za-z0-9._%+-]+@[a-za-z0-9.-]+\\.[a-za-z]{2,4}", message="邮件格式错误")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
