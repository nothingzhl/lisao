package org.zhl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhanghanlin
 */
@Mapper
public interface TblDepartmentDao {

    @Select("select count(1) from tbl_department")
    Integer count();
}
