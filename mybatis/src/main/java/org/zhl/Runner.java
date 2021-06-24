package org.zhl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;
import org.zhl.dao.TblDepartmentDao;

import java.io.InputStream;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-06-23 17:07
 **/
public class Runner {

    public static void main(String[] args) throws Exception {
        final InputStream mybatisConfig = Resources.getResourceAsStream("mybatis-config.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfig);
        RunScript
            .execute(sqlSessionFactory.openSession().getConnection(), Resources.getResourceAsReader("sql/init.sql"));

        final TblDepartmentDao mapper = sqlSessionFactory.openSession().getMapper(TblDepartmentDao.class);
        System.out.println(mapper.count());
    }

}
