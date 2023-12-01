package com.dicogram.database;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SqlSessionManager {
	
	
	static SqlSessionFactory sqlSessionFactory;
	
	//sqlSessionManager 객체가  호출 되면 무조건 실행 할 수 있도록 static사용
	static {
		try {
			String resource = "com/dicogram/database/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlSession() {
		
		return sqlSessionFactory;
	}
}
