package com.example.springjpa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springjpa.entity.User;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Describe 类描述，功能介绍
 * @Author chenlu
 * @Version V1.0
 * @Date 2019/1/14.
 */
public class Test {
    public static void main(String[] args)throws Exception {
//        int a = 7;
//        System.out.println(a/3);
//        System.out.println(a%3);
//
//        List<Object> dataTem =new ArrayList<>();
//        dataTem.add(new User(1,"s1"));
//        dataTem.add(new User(2,"s2"));
//        dataTem.add(new User(3,"s3"));
//        dataTem.add(new User(4,"s4"));
//        dataTem.add(new User(5,"s5"));
//        dataTem.add(new User(6,"s6"));
//        List<Object> list =dataTem.subList(0,5);
//
//        JSONArray jsonArray = new JSONArray(list);
//        System.out.println(jsonArray);
//        for (int i=0;i<jsonArray.size();i++){
//            JSONObject index = jsonArray.getJSONObject(i);
//            System.out.println(i+"======="+index);
//            if(index.getInteger("id")==2||index.getInteger("id")==4){
//                jsonArray.remove(i);
//                jsonArray.fluentRemove(i);
//                i--;
//            }
//        }
//        List<Object> list2 = jsonArray.subList(4,7);
//        System.out.println(jsonArray);

//
//        String param = encode("353532152121");
//        String param = decode("%255B%257B'name'%253A'FORM_ID'%252C'value'%253A'1057450689507328'%257D%255D");
//        test("23523523");
//        String param = getParam(new String[]{"111","222","333"});
//        System.out.println(param);


        System.out.println(UserController.class.getSimpleName());
        System.out.println(UserController.class.getName());
        System.out.println(UserController.class.getClasses());
        System.out.println(UserController.class.getClass());
        System.out.println(UserController.class.getClass().getName());
    }


    public static String encode(String fsdid) {
        String param = "[{'name':'FORM_ID','value':'" + fsdid + "'}]";
        try {
            System.out.println(param);
            param= URLEncoder.encode(param,"UTF-8");
            System.out.println(param);
            param=URLEncoder.encode(param,"UTF-8");
            System.out.println(param);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
        return param;
    }
    public static String decode(String param) {

        try {
            System.out.println(param);
            param= URLDecoder.decode(param,"UTF-8");
            System.out.println(param);
            param=URLDecoder.decode(param,"UTF-8");
            System.out.println(param);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
        return param;
    }
    public static void test(String fsdid){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("name","FORM_ID");
        jsonObject.put("value",fsdid);
        jsonArray.add(jsonObject);
        String param = jsonArray.toJSONString();
        System.out.println(param);
    }
    public static String getParam(String... fsdid){
        StringBuffer sbf=new StringBuffer("[");
        for(int i=0;i<fsdid.length;i++){
            sbf.append("{'name':'FORM_ID','value':'").append(fsdid[i]).append("'},");
        }
        sbf.delete(sbf.lastIndexOf(","),sbf.length());
        sbf.deleteCharAt(sbf.lastIndexOf(","));
        sbf.append("]");
        return sbf.toString();
    }
}
