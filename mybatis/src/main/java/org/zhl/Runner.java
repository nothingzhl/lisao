package org.zhl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

        final Connection connection = sqlSessionFactory.openSession().getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("select * from tbl_department");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(0));
        }

        System.out.println(resultSet);
    }

}
