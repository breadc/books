package com.context;

import java.util.List;
import java.util.Map;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PluginConfiguration;

/**
 * 新增插件
 * @author chenghui
 *
 */
public class PluginConfig {
	
	public static void  createPlugins(List<Map<String,String>> configList){
//		for (Map<String, String> map : configList) {
//			PluginConfiguration  pluginConfiguration=new PluginConfiguration();
////			pluginConfiguration.setConfigurationType(configuartiontype);
//		}
	
	}
	
	/**
	 * 新增插件
	 * @param context
	 * @param pluginConfiguration
	 */
	private void  addPlugins(Context  context,PluginConfiguration pluginConfiguration){
		context.addPluginConfiguration(pluginConfiguration);
	}
	
	/**
	 * 默认插件
	 * @param context
	 */
	private void  addPlugins(Context  context){
		PluginConfiguration pluginConfiguration=new PluginConfiguration();
		pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.PaginationPlugin");
		PluginConfiguration pluginConfiguration2=new PluginConfiguration();
		pluginConfiguration2.setConfigurationType("org.mybatis.generator.plugins.CustomOrCriteriaPlugin");
		PluginConfiguration pluginConfiguration3=new PluginConfiguration();
		pluginConfiguration3.setConfigurationType("org.mybatis.generator.plugins.RenameExampleClassPlugin");
		pluginConfiguration3.addProperty("searchString", "Example$");
		pluginConfiguration3.addProperty("replaceString", "Criteria");
		context.addPluginConfiguration(pluginConfiguration);
		context.addPluginConfiguration(pluginConfiguration2);
		context.addPluginConfiguration(pluginConfiguration3);
	}
}	
