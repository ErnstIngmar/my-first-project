package com.liner.service;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Liner on 18/6/18.
 */
public class ResourcePath {
    private static String resourceFile = "logback.xml";
    private static String propertyFile = "mytest.properties";

    public static void main(String[] args) {
//        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(resourceFile);
//        System.out.println(inputStream.toString());

        ResourcePath resourcePath = new ResourcePath();

        String mail = "whfHh21443@ds.com";
        resourcePath.testCompare(mail);

        String url = "https://www.baidu.com";
        resourcePath.isMatchUrl(url);

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
//        String[] str = resourcePath.listToArray(list);
        System.out.println(list);
        System.out.println(resourcePath.listToArray(list).toString());

        Set<String> sets = resourcePath.listToSet(list);

        Set<Object> set = new HashSet<Object>();
        set.add("00");
        set.add("000");
        Object[] objects = resourcePath.setToArray(set);
        System.out.println(set);
        System.out.println(objects);
    }

    /**
     * 如果不标记resources root,就找不到资源文件,经常发生的问题
     */
    @Test
    public void test() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceFile);
        System.out.println(inputStream.toString());
    }

    private boolean isMatchUrl(String param) {
//        String url = "https:\\/\\/www\\.[a-zA-z0-9]\\.com";
        String url = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";
        Pattern pattern = Pattern.compile(url);
        Matcher matcher = pattern.matcher(param);
        System.out.println(param + " is url is " + matcher.find());
        return matcher.find();
//        boolean b = pattern.matcherhes(param);
//        return b;
    }

    private void testCompare(String param) {
        String email = "^[a-zA-Z0-9]{1,40}@[a-zA-Z0-9]{1,40}\\.[a-zA-Z0-9]{1,40}";
        boolean b = param.matches(email);
        System.out.println(param + " is email is " + b);
    }

    @Test
    public void testReadValue() throws IOException {
        Logger logger = Logger.getLogger("log");
        logger.setLevel(Level.INFO);

        InputStream inputStream;
        Properties properties = new Properties();
        inputStream = ResourcePath.class.getClassLoader().getResourceAsStream(propertyFile);
        properties.load(inputStream);

//        logger.info(properties.getProperty("dbport"));
        String key = properties.getProperty("key");
        key = new String(key.getBytes("ISO-8859-1"), "UTF-8"); //  转码
        logger.info(key);
    }

    @Test
    public void arrayToList() {
        // Arrays.asList() 返回一个受指定数组决定的固定大小的列表。所以不能做 add 、 remove 等操作，否则会报错。
        String[] strings = new String[]{"张三", "李四", "王五"};
//        List stringsToList = Arrays.asList(strings);

        // 需要增删操作时 将数组中的元素一个一个添加到列表，这样列表的长度就不固定了，可以进行增删操作
        List strList = new ArrayList<String>();
        for (String str : strings) {
            strList.add(str);
        }
        strList.add("赵六");
        strList.remove(1);
        System.out.println(strList);

    }

    @Test
    public void converts() {

        String[] strings = new String[]{"张三", "李四", "王五"};

        // 数组转Set
        Set<String> strSet = new HashSet<String>(Arrays.asList(strings));
        strSet.add("孙七");
        strSet.remove(0);
        System.out.println("strSet: " + strSet);

        List listArray = Arrays.asList(strings);
//        listArray.add("0");
        // List转数组
        Object[] objs = listArray.toArray();
        System.out.println("listArray: " + listArray);
        System.out.println("objs: " + objs.toString());

        // List转Set
//        listArray.remove("0");
//        listArray.add("1");
        Set sets = new HashSet(listArray);
        System.out.println("listArray: " + listArray);
        System.out.println("sets: " + sets.toString());

        Set<String> set = new HashSet<String>(Arrays.asList(strings));
        // Set转数组
        Object[] obj = set.toArray();
        // Set转list
        List<String> list = new ArrayList<String>(set);
        System.out.println("obj: " + obj.toString());
        System.out.println("list: " + list.toString());
    }

//    @Test
//    public

    private String[] listToArray(List<String> list) {
        // list转数组
        String[] array = list.toArray(new String[]{}); //需要在用到的时候另外写方法，不支持泛型的Array.
        return array;
    }


    private <T extends Object> Set<T> listToSet(List<T> list) {
        Set<T> set = new HashSet<T>(list); // 具体实现看需求转换成不同的Set的子类。
        return set;
    }

    private Object[] setToArray(Set<Object> set) {
        Object[] array = set.toArray(new Object[]{}); // 需要在用到的时候另外写方法，不支持泛型的Array.
        return array;
    }


}
