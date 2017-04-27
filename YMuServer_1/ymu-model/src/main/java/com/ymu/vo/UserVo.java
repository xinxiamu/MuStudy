package com.ymu.vo;

import com.ymu.model.User;
import com.ymu.model.UserDetails;

import java.io.Serializable;

public class UserVo implements Serializable {

    private User user;
    private UserDetails userDetails;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
