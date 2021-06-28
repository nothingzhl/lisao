package org.zhl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhl.entity.UserEntity;

import java.util.List;

@Mapper
public interface UserDao {
    List<UserEntity> selectAll();

    List<UserEntity> selectAllLazy();

    @Select("select * from tbl_user where department_id = #{departmentId} ")
    UserEntity findAllByDepartmentId(@Param("departmentId") String departmentId);
}
