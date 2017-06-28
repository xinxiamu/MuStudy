package com.example.ymu.dao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.ymu.dao.base.BaseRepository;
import com.example.ymu.domain.PepoleBasic;

public interface PepoleBasicRepository
		extends BaseRepository<PepoleBasic, Long>, JpaSpecificationExecutor<PepoleBasic> {

}
