package com.context;

import java.security.GeneralSecurityException;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

import com.model.JDBCdriver;
import com.util.GenConstants;
import com.util.ModelValidUtils;

/**
 * 初始化数据库驱动
 * @author chenghui
 *
 */
public class DrivarConfig {
	
	
	
	public static  void initJDBCdrivars(Context context,JDBCdriver diver) throws GeneralSecurityException{
		if(diver != null){
			ModelValidUtils.validModel(diver);
			initJDBCdrivar(context,diver);
		}else{
			initDefaultJDBCdrivar(context);
		}
	}
	
	
	private  static void initDefaultJDBCdrivar(Context context){
		JDBCConnectionConfiguration  jdbcConnnection=new JDBCConnectionConfiguration();
		jdbcConnnection.setConnectionURL(GenConstants.db_URL);
		jdbcConnnection.setDriverClass(GenConstants.db_driver);
		jdbcConnnection.setPassword(GenConstants.db_password);
		jdbcConnnection.setUserId(GenConstants.db_name);
		context.setJdbcConnectionConfiguration(jdbcConnnection);
	}
	
	private static void initJDBCdrivar(Context context,JDBCdriver diver){
		JDBCConnectionConfiguration  jdbcConnnection=new JDBCConnectionConfiguration();
		jdbcConnnection.setConnectionURL(diver.getUrl());
		jdbcConnnection.setDriverClass(GenConstants.db_driver);
		jdbcConnnection.setPassword(diver.getPassword());
		jdbcConnnection.setUserId(diver.getDataName());
		context.setJdbcConnectionConfiguration(jdbcConnnection);
	}
}
