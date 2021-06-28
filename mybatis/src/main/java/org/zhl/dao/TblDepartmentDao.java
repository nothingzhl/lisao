package org.zhl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhl.entity.TblDepartmentEntity;

import java.util.List;

/**
 * @author zhanghanlin
 */
@Mapper
public interface TblDepartmentDao {

    @Select("select count(1) from tbl_department")
    Integer count();

    List<TblDepartmentEntity> selectAll();

    @Select("select * from tbl_department where id = #{id}")
    TblDepartmentEntity selectById(@Param("id") Integer id);

    List<TblDepartmentEntity> selectAllDepartmentUser();
}
