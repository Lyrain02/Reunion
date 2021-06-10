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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Decoder.BASE64Decoder;

public class Util {
    public static Bitmap load(String tag) throws IOException {
        final Bitmap[] bitmap = new Bitmap[1];
        new Thread(new Runnable() {
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
                while (true) {
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
                bitmap[0] = BitmapFactory.decodeByteArray(data, 0, data.length);
            }
        }).start();
        return bitmap[0];
    }

    public static String store(String path) {
        final String[] objectKey = new String[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(Environment.getExternalStorageDirectory().getPath() + path);
                String ACCESS_KEY_ID = "67a3432ef0cf4df6ab78bf82ad3fde88";             // 用户的Access Key ID
                String SECRET_ACCESS_KEY = "8acdbb27f09449c5ae79148cba4a779a";         // 用户的Secret Access Key
                String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档
                // 初始化一个BosClient
                BosClientConfiguration config = new BosClientConfiguration();
                config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
                config.setEndpoint(ENDPOINT);
                BosClient client = new BosClient(config);
                // 获取指定文件
                objectKey[0] = new Random().nextInt() + file.getName();
                // 以文件形式上传Object
                PutObjectResponse putObjectFromFileResponse =
                        client.putObject("file-bed", objectKey[0], file);
                // 打印ETag
                URL url = client.generatePresignedUrl("file-bed", objectKey[0], -1);
            }
        }).start();
        return objectKey[0];
    }

    public static int sign_up(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return send(map2json(map), "/login/sign-up").second;
    }

    public static int sign_in(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return send(map2json(map), "/login/sign-in").second;
    }

    public static int sign_out(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return send(map2json(map), "/login/sign-out").second;
    }

    public static int change_img(String id, String path) {
        Map<String, String> map = new HashMap<>();
        map.put("_id", id);
        map.put("url", store(path));
        return send(map2json(map), "/user/image").second;
    }

    public static int change_name(String id,String name){
        Map<String, String> map = new HashMap<>();
        map.put("_id", id);
        map.put("name", name);
        return send(map2json(map), "/user/image").second;
    }

    public static Map<String, String> get_user(String id){
        Map<String, String> map = new HashMap<>();
        map.put("_id", id);
        return json2map(send(map2json(map), "/user/image").first);
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

    public static Pair<String, Integer> send(String str, String api) {
        final int[] code = {0};
        final String[] mesg = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://1.116.1.85:5000" + api;
                String body = str;
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
                    mesg[0] = postMethod.getResponseBodyAsString();
                    code[0] = postMethod.getStatusCode();
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
            }
        });
        return new Pair<String, Integer>(mesg[0], code[0]);
    }
}

