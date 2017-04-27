package com.ymu.api;

import com.ymu.model.User;
import com.ymu.vo.UserVo;


public interface IUserService extends IBaseService<User,Long> {

    User getById(long usrId) throws Exception;

    User getByUserName(String userName) throws Exception;

    UserVo getUserAllInfoByUserId(long userId) throws Exception;

    UserVo saveUserAllInfo(UserVo userVo) throws Exception;
}
