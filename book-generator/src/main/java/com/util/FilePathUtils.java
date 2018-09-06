package com.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.exception.GenertorException;

/**
 * 通过路径文件来确实当前的项目的绝对路径
 * @author chenghui
 * mybatis反向生成时,需要加载mysql的驱动包此时的路径应该为绝对路径，根绝源码来看,不支持相对路径
 * 会抛出 RunTimeException
 * 找不到路径 Cannot resolve classpath entry
 * ! 统一管理驱动包，防止生成工具出问题
 * 1.在项目下新建lib的包,放入jar
 * 2.通过确实路径文件，来确实项目在机器上的绝对路径
 * 3.加载包
 * !!!
 * 	待优化点:通过maven 的项目来解决
 */
public class FilePathUtils {
	/**
	 * 项目的路径是一个txt 形式
	 * 不允许修改path.txt文件名
	 * 
	 */
	
	
	/**
	 * 获取路径
	 * @return
	 * @throws GenertorException 
	 */
	public static String getPath() throws GenertorException{
//		URL fileUrl=FilePathUtils.class.getResource(GenConstants.PATH_FILE_NAME);
		File directory = new File("");//参数为空
		String courseFile = null;
		try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		HashMap<String,Object> hashMap=new HashMap<>();
//		hashMap.put("url", courseFile);
// 		validFelieExits(hashMap);
// 		replacePath(hashMap);
//		return (String) hashMap.get("_path");
		courseFile=courseFile.replace("\\","/");
		return courseFile+"/";
	}
	
	
	/**
	 * 判断文件是否存在
	 * @return
	 * @throws GenertorException 
	 */
	private static  void  validFelieExits(HashMap<String, Object>  hashMap) throws GenertorException{
		GenertorException genertorException=GenertorException.getInstance("获取不到路径文件");
		URL url= (URL) hashMap.get("url");
		if(url == null){
			throw genertorException;
		}
		String path=url.getPath();
		if(path == null || path.equals("")){
			throw genertorException;
		}
		File   file=new File(path);
		if(!file.exists()){
			file=null;
			throw genertorException;
		}
		file=null;
		hashMap.clear();
		hashMap.put("path", path);
	}	
	
	private static void   replacePath(HashMap<String, Object>  hashMap) throws GenertorException{
		List<String>  keys=GenConstants.keyList();
		String path=(String) hashMap.get("path");
		GenertorException genertorException=GenertorException.getInstance("处理文件异常");
		String  _key=null;
		for (String key : keys) {
			if(path.contains(key)){
				_key=key;
				break;
			}
		}
		String[]  arrs=path.split(_key);
		hashMap.clear();
		String  _path=arrs[0];
		if(_path == null || _path.equals("")){
			throw genertorException;
		}
		_path=_path.substring(1, _path.length());
		hashMap.put("_path", _path);
	}


	public static void main(String[] args) {
		File directory = new File("");//参数为空
		String courseFile = null;
		try {
			courseFile = directory.getCanonicalPath();
			System.out.println("args = [" + courseFile + "]");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
