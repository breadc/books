package com.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by chenghui on 2018/9/6.
 */
public class PathUtils {

    public  void getpath1(){
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
    }

    public  void getpath2() throws IOException {
        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath() ;
        System.out.println(courseFile);
    }
    public  void getpath3(){
        URL xmlpath = this.getClass().getClassLoader().getResource("selected.txt");
        System.out.println(xmlpath);
    }
    public  void getpath4(){
        System.out.println(System.getProperty("user.dir"));
    }
    public  void getpath5(){
        System.out.println( System.getProperty("java.class.path"));
    }
}
