package com.ymu.api;


import com.ymu.model.UserDetails;

public interface IUserDetailsService extends IBaseService<UserDetails,Long>  {

    UserDetails getById(long id) throws Exception;
}
