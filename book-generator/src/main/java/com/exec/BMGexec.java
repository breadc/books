package com.exec;

import com.context.Genertor;
import com.model.TableModel;


/**
 * mybatis Genertor 生成文件的入口
 * 1.解决经常需要找反向生成工具
 * 2.解决每个人反向生成工具不一致造成的文件差异，解决合并的问题
 * 3.生成同一的文件，解决代码冲突
 * 4.解决繁琐的命令行
 * 5.解决配置每次都需要配置的问题
 * 
 * 待优化点
 * 	1.判断库
 *  2.判断表
 *  3.优化jar包
 * @author chenghui
 *
 */
public class BMGexec {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Genertor  genertor=new Genertor();
		TableModel tbles=new TableModel();
		tbles.addTable("tbl_book_info", "BookInfo");
		genertor.configurationTblesList(tbles);
	}
	
}
