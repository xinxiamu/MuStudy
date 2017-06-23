package com.example.ymu.dao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.ymu.dao.base.BaseRepository;
import com.example.ymu.domain.Teacher;

public interface TeacherRepository extends BaseRepository<Teacher, Long>,JpaSpecificationExecutor<Teacher> {

}
