package org.zhl.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-06-25 16:21
 **/
@Data
public class UserEntity {
    private String id;

    private String name;

    private Integer age;

    private Date birthday;

    private TblDepartmentEntity department;
}
