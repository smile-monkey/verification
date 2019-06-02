package com.technology.verify;

import com.technology.entity.BizOrder;
import com.technology.mapper.BizOrderMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SimpleTest {
    public static void main(String[] args) throws IOException {
        // 带xml配置获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryFromXml();
        // 不带xml配置获取sqlSessionFactory
//        SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryFromCode();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BizOrder bizOrder = session.selectOne("com.technology.mapper.BizOrderMapper.selectOneById", new HashMap<String,Integer>() {{
                put("ida", 1);
            }});
//            要求：mapper接口名称和xml文件名称一致
//            BizOrderMapper mapper = session.getMapper(BizOrderMapper.class);
//            BizOrder bizOrder = mapper.selectOneById(1);
            System.out.println(bizOrder);
        } finally {
            session.close();
        }
    }

    private static SqlSessionFactory getSqlSessionFactoryFromCode() {
        DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/verification", "root", "2318720071");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BizOrderMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    private static SqlSessionFactory getSqlSessionFactoryFromXml() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
