package org.zhl.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author zhanghanlin
 */
@Data
public class TblDepartmentEntity {

  private String id;
  private String name;
  private String tel;

  private Set<UserEntity> users;
}
