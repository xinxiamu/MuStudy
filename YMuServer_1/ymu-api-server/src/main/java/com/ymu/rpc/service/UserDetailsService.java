package com.ymu.rpc.service;

import org.springframework.stereotype.Service;

import com.ymu.api.IUserDetailsService;
import com.ymu.dao.jpa.repository.UserDetailsRepository;
import com.ymu.model.UserDetails;
import com.ymu.rpc.base.BaseService;

@Service("userDetailsService")
public class UserDetailsService extends BaseService implements IUserDetailsService {

	private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails getById(long id) throws Exception {
        return userDetailsRepository.getOne(id);
    }

    @Override
    public UserDetails save(UserDetails entity) throws Exception {
        return userDetailsRepository.save(entity);
    }
}
