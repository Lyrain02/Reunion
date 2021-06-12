package com.example.mytest;

import java.util.HashMap;
import java.util.Map;

import static com.example.mytest.Util.*;

public class Api {

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
