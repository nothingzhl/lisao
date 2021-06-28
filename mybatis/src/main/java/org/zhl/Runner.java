package org.zhl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zhl.dao.TblDepartmentDao;
import org.zhl.dao.UserDao;
import org.zhl.entity.TblDepartmentEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        initData(sqlSessionFactory);
        final TblDepartmentDao mapper = sqlSessionFactory.openSession().getMapper(TblDepartmentDao.class);
        final List<TblDepartmentEntity> tblDepartmentEntities = mapper.selectAll();
        System.out.println(tblDepartmentEntities);
        System.out.println(mapper.selectAllDepartmentUser());
        final UserDao userDao = sqlSessionFactory.openSession().getMapper(UserDao.class);
        System.out.println(userDao.selectAll());
        System.out.println(userDao.selectAllLazy());
    }

    private static void initData(SqlSessionFactory sqlSessionFactory) throws IOException {
        final ScriptRunner scriptRunner = new ScriptRunner(sqlSessionFactory.openSession().getConnection());
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(Resources.getResourceAsReader("sql/init.sql"));
    }

}
