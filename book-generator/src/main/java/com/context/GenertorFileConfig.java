package com.context;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;

import com.model.ConfigModel;

public class GenertorFileConfig {
	
	public static void  initGenetotFile(Context context,ConfigModel model,String path){
		/**
		 * 创建mapper.xml
		 */
		SqlMapGeneratorConfiguration  sqlmap=new SqlMapGeneratorConfiguration();
		sqlmap.setTargetPackage(model.getXml());
		sqlmap.setTargetProject(path);
		sqlmap.addProperty("enableSubPakages", "true");
		context.setSqlMapGeneratorConfiguration(sqlmap);
		/**
		 * 创建 dao
		 */
		JavaClientGeneratorConfiguration  javaClientGenerator=new JavaClientGeneratorConfiguration();
		javaClientGenerator.addProperty("enableSubPackages", "true");
		javaClientGenerator.setConfigurationType("XMLMAPPER");
		javaClientGenerator.setTargetPackage(model.getDao());
		javaClientGenerator.setTargetProject(path);
		context.setJavaClientGeneratorConfiguration(javaClientGenerator);
		/**
		 *  创建entity
		 */
		JavaModelGeneratorConfiguration  javaModelGeneratorConfiguration =new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage(model.getEntity());
		javaModelGeneratorConfiguration.setTargetProject(path);
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
		javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
	}
}
