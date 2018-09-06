package com.context;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.model.GenertorModel;
import com.model.TableModel;
import com.util.DateUtils;
import com.util.JdbcUtils;
import com.util.ModelValidUtils;

/**
 * 生成工具类
 * 
 * @author chenghui
 *
 */
public class Genertor {

	
	
	public  void  configurationTblesList(TableModel model){
		try {
			System.out.println(DateUtils.getCurrDate()+":Genertor逆向生成文件开始");
			ModelValidUtils.validModel(model);
			System.out.println(DateUtils.getCurrDate()+":Genertor校验table类通过");
			List<String[]> tableList = model.getTableList();
			for (String[] arrs : tableList) {
				try {
					String tableName=arrs[0];
					if(!JdbcUtils.isTableExits(tableName)){
						System.out.println(DateUtils.getCurrDate()+":Genertor校验["+tableName+"]表不存在");
						continue;
					}
					System.out.println(DateUtils.getCurrDate()+":Genertor校验生成["+tableName+"]表开始");
					configuration(null,tableName,arrs[1]);
					System.out.println(DateUtils.getCurrDate()+":Genertor校验生成["+tableName+"]表结束");
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
 		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 官方的核心代码
	 * chenghui
	 */
	public void configuration(GenertorModel model,String tableName,String dominName) {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator;
			try {
				Configuration  configuration=GenertorFactory.createConfiguration(model,tableName,dominName);
				myBatisGenerator = new MyBatisGenerator(configuration,callback, warnings);
				myBatisGenerator.generate(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
