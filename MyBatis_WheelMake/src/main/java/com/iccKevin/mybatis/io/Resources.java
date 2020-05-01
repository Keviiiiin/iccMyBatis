package com.iccKevin.mybatis.io;

import java.io.InputStream;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * 使用类加载器读取配置文件的类
 */
public class Resources {

    /**
     * 根据传入的参数，获取一个字节输入流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath){
        // 类加载器的getResource和getResourceAsStream方法是从编译后字节码文件的存放目录为根目录查找
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
