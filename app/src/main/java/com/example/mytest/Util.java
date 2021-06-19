package com.example.mytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ImageReader;
import android.util.Pair;

import android.os.Bundle;
import android.os.Environment;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.PutObjectResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.example.mytest.user.Data;
import com.example.mytest.user.Person;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import Decoder.BASE64Decoder;

public class Util {
    public static Bitmap load(String tag) throws IOException {
        final Bitmap[] res = {null};
        Thread t =new Thread(new Runnable() {
            @Override
            public void run() {
                String ACCESS_KEY_ID = "67a3432ef0cf4df6ab78bf82ad3fde88";             // 用户的Access Key ID
                String SECRET_ACCESS_KEY = "8acdbb27f09449c5ae79148cba4a779a";         // 用户的Secret Access Key
                String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档
                // 初始化一个BosClient
                BosClientConfiguration config = new BosClientConfiguration();
                config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
                config.setEndpoint(ENDPOINT);
                BosClient client = new BosClient(config);
                BosObject object = client.getObject("file-bed", tag);
                InputStream objectContent = object.getObjectContent();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(true){
                    try {
                        if ((len = objectContent.read(buffer)) == -1) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    outStream.write(buffer, 0, len);
                }
                try {
                    outStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] data = outStream.toByteArray();
                res[0] = BitmapFactory.decodeByteArray(data, 0, data.length);
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res[0];
    }

    public static String store(String path) {
        File file = new File(path);
        String ACCESS_KEY_ID = "67a3432ef0cf4df6ab78bf82ad3fde88";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "8acdbb27f09449c5ae79148cba4a779a";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档
        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);
        // 获取指定文件
        String objectKey = file.getName();
        // 以文件形式上传Object
        PutObjectResponse putObjectFromFileResponse =
                client.putObject("file-bed", objectKey, file);
        // 打印ETag
        URL url = client.generatePresignedUrl("file-bed", objectKey, -1);
        return objectKey;
    }

    public static Map<String, String> json2map(String str) {
        return (Map<String, String>) JSON.parse(str);
    }

    public static List<Map<String, String>> json2list(String str) {
        return (List<Map<String, String>>) JSONArray.parse(str);
    }

    public static String map2json(Map<String, String> map) {
        return JSON.toJSONString(map);
    }

    public static String list2json(List<Map<String, String>> list) {
        return JSON.toJSONString(list);
    }

    public static Pair<String,Integer> send(String str, String api) {
        String url = "http://1.116.1.85:5000" + api;
        String body = str;
        int code = 0;
        String mesg = null;
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        Map<String, Object> map = JSONObject.parseObject(body, Map.class);
        Set<String> set = map.keySet();
        for (String s : set) {
            postMethod.addParameter(s, map.get(s).toString());
        }
        try {
            httpClient.executeMethod(postMethod);
            mesg = postMethod.getResponseBodyAsString();
            code = postMethod.getStatusCode();
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0);
        }
        return new Pair<String,Integer>(mesg,code);
    }

    public static Person map2person(Map<String, String> map) {
        Person person = new Person();
        if(map.containsKey("name")&&map.get("name")!=null&&map.get("name")!="")
            person.setName(map.get("name"));
        if(map.containsKey("location")&&map.get("location")!=null&&map.get("location")!="")
            person.setLocation(map.get("location"));
        if(map.containsKey("date")&&map.get("date")!=null&&map.get("date")!="")
            person.setDate(map.get("date"));
        if(map.containsKey("age")&&map.get("age")!=null&&map.get("age")!="")
            person.setAge(map.get("age"));
        if(map.containsKey("birth")&&map.get("birth")!=null&&map.get("birth")!="")
            person.setBirth(map.get("birth"));
        if(map.containsKey("sex")&&map.get("sex")!=null&&map.get("sex")!="")
            person.setSex(map.get("sex"));
        if(map.containsKey("blood")&&map.get("blood")!=null&&map.get("blood")!="")
            person.setBlood(map.get("blood"));
        if(map.containsKey("height")&&map.get("height")!=null&&map.get("height")!="")
            person.setHeight(map.get("height"));
        if(map.containsKey("weight")&&map.get("weight")!=null&&map.get("weight")!="")
            person.setWeight(map.get("weight"));
        if(map.containsKey("appearance")&&map.get("appearance")!=null&&map.get("appearance")!="")
            person.setAppearance(map.get("appearance"));
        if(map.containsKey("other")&&map.get("other")!=null&&map.get("other")!="")
            person.setOther(map.get("other"));
        if(map.containsKey("state")&&map.get("state")!=null&&map.get("state")!="")
            person.setStatus(Integer.parseInt(map.get("state")));
        if(map.containsKey("time")&&map.get("time")!=null&&map.get("time")!="")
            person.setPdate(map.get("time"));
        if(map.containsKey("s_name")&&map.get("s_name")!=null&&map.get("s_name")!="")
            person.setS_name(map.get("s_name"));
        if(map.containsKey("s_phone")&&map.get("s_phone")!=null&&map.get("s_phone")!="")
            person.setS_phone(map.get("s_phone"));
        if(map.containsKey("s_address")&&map.get("s_address")!=null&&map.get("s_address")!="")
            person.setS_address(map.get("s_address"));
        if(map.containsKey("s_postcode")&&map.get("s_postcode")!=null&&map.get("s_postcode")!="")
            person.setS_postcode(map.get("s_postcode"));
        if(map.containsKey("s_relative")&&map.get("s_relative")!=null&&map.get("s_relative")!="")
            person.setS_relative(map.get("s_relative"));
        if(map.containsKey("id")&&map.get("id")!=null&&map.get("id")!="")
            person.setPid(Integer.parseInt(map.get("id")));
        if(map.containsKey("image")&&map.get("image")!=null&&map.get("image")!=""){
            int image = Integer.parseInt(map.get("image"));
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(image);
            person.setImage(integers);
        }else {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(R.drawable.eg_girl);
            person.setImage(integers);
        }
        System.out.println(person.getImage().get(0));
        return person;
    }

    public static ArrayList<Person> map2people(List<Map<String, String>> list){
        ArrayList<Person> people = new ArrayList<>();
        for (Map<String, String> map:list){
            people.add(map2person(map));
        }
        return people;
    }

    public static Map<String, String> person2map(Person person){
        Map<String, String> map = new HashMap<>();
        map.put("image",Integer.toString(person.getImage().get(1)));
        map.put("name",person.getName());
        map.put("date",person.getDate());
        map.put("age",person.getAge());
        map.put("birth",person.getBirth());
        map.put("sex",person.getSex());
        map.put("blood",person.getBlood());
        map.put("height",person.getHeight());
        map.put("weight",person.getWeight());
        map.put("appearance",person.getAppearance());
        map.put("other",person.getOther());
        map.put("state",Integer.toString(person.getStatus()));
        map.put("s_name",person.getS_name());
        map.put("s_phone",person.getS_phone());
        map.put("s_address",person.getS_address());
        map.put("s_postcode",person.getS_postcode());
        map.put("s_relative",person.getS_relative());
        return map;
    }

}

