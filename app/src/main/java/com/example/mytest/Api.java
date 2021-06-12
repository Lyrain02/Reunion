package com.example.mytest;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

import static com.example.mytest.Util.*;

public class Api {

    public static Map<String, String> get_user_id(String username){
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        Pair<String, Integer> send = send(map2json(map), "/user/id");
        if(send.second==404){
            return null;
        }
        return json2map(send.first);
    }

    public static int sign_up(String username,String password){
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return send(map2json(map),"/login/sign-up").second;
    }

    public static int sign_in(String username,String password){
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return send(map2json(map),"/login/sign-in").second;
    }


    public static int sign_out(String username,String password){
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return send(map2json(map),"/login/sign-out").second;
    }
}
