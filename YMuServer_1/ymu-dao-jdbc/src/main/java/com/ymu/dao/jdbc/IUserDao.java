package com.ymu.dao.jdbc;

import com.ymu.model.User;
import com.ymu.vo.UserVo;

/**
 * Created by mutou on 16-12-14.
 */
public interface IUserDao {

    /**
     * 根据会员名获取会员用户信息。
     * @param userName
     * @return
     */
    User getByUserName(String userName) throws Exception;

    /**
     * 获取用户所有关联表信息
     * @param userId
     * @return
     */
    UserVo getUserAllInfoByUserId(long userId) throws Exception;
}
