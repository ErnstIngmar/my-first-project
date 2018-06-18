package com.liner.service;

import org.junit.Test;
import sun.applet.Main;

import java.io.InputStream;

/**
 * Created by Liner on 18/6/18.
 */
public class ResourcePath {
    private static String resourceFile = "abc.xml";

    public static void main(String[] args) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(resourceFile);
        System.out.println(inputStream.toString());
    }

    /**
     * 如果不标记resources root,就找不到资源文件,经常发生的问题
     */
    @Test
    public void test(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceFile);
        System.out.println(inputStream.toString());
    }
}
