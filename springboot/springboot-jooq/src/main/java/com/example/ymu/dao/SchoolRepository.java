package com.example.ymu.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.ymu.dao.base.BaseRepository;
import com.example.ymu.domain.School;

public interface SchoolRepository extends BaseRepository<School, Long>,JpaSpecificationExecutor<School> {

}
