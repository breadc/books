package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 判断表是否存在
 * 
 * @author chenghui 
 * jdbc 连接数据库 1.加载驱动(数据连接驱动) 2.创建连接 3.创建一个Statement 对象 4.执行sql
 *         5.处理结果 6.关闭连接和流
 *  
 * jdbc 
 * 注意点 
 * a.数据库连接避免重发创建连接,以及连接消耗情况
 * b.事务处理，默认自动提交事务，可以手动开启，批量执行sql时
 * c.调用存储过程
 * d.二进制流处理，针对于大字段
 */
public class JdbcUtils {

	public static boolean isTableExits(String tableName) {
		long st=System.currentTimeMillis();
		boolean  result=false;
		Connection con=null;
		Statement stmt=null;
		try {
			/** 加载驱动 */
 			Class.forName(GenConstants.db_driver);
			/** 获取连接 */
			con = DriverManager.getConnection(GenConstants.db_URL,
					GenConstants.db_name, GenConstants.db_password);
			 stmt = con.createStatement();
			 /**创建sql语句 **/
			String sql = "SELECT TABLE_SCHEMA,TABLE_NAME FROM information_schema.TABLES WHERE table_name ='"
					+ tableName + "';";
			/**执行sql **/
			ResultSet executeQuery = stmt.executeQuery(sql);
			/**处理结果 **/
			if (executeQuery != null) {
				while (executeQuery.next()) {
					if(executeQuery.getString("TABLE_SCHEMA").equals(GenConstants.db_data)){
						if(executeQuery.getString("TABLE_NAME").equals(tableName)){
							result=true;
							break;
						}
					}
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				/**关流**/
				if(con!=null){
					con.close();
				}
				if(stmt!=null){
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		long end=System.currentTimeMillis();
		System.out.println(end-st);
		return result;
	}
	public static void main(String[] args) {
		isTableExits("tbl_amount_detail");
	}
}
