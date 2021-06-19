package com.example.mytest;

import android.util.Pair;

import com.example.mytest.user.Person;
import com.example.mytest.user.PersonClue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mytest.Util.*;
import static com.example.mytest.Util.send;

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

    public static Map<String, String> get_user(int id){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(id));
        Pair<String, Integer> send = send(map2json(map), "/user/detail");
        if(send.second==404){
            return null;
        }
        return json2map(send.first);
    }

    public static int change_name(int id,String name){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(id));
        map.put("name",name);
        return send(map2json(map),"/user/name").second;
    }

    public static ArrayList<Person> pool_a(){
        Map<String, String> map = new HashMap<>();
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/post/pool/A").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static ArrayList<Person> pool_b(){
        Map<String, String> map = new HashMap<>();
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/post/pool/B").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static Person get_detail_a(int pid){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(pid));
//        System.out.println(pid);
        String first = send(map2json(map), "/post/detail/A").first;
//        System.out.println(first);
        Person person = map2person(json2map(first));
//        System.out.println("person========================"+person);
        return person;
    }

    public static Person get_detail_b(int pid){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(pid));
//        System.out.println(pid);
        String first = send(map2json(map), "/post/detail/B").first;
//        System.out.println(first);
        Person person = map2person(json2map(first));
//        System.out.println("person========================"+person);
        return person;
    }

    public static ArrayList<PersonClue> find_clue_a(int pid){
        Map<String, String> map = new HashMap<>();
//        System.out.println("pid=============="+pid);
        map.put("post_id",Integer.toString(pid));
        String first = send(map2json(map), "/clue/get/A").first;
//        System.out.println("json==============="+first);
        List<Map<String, String>> maps = json2list(first);
        ArrayList<PersonClue> personClues = new ArrayList<>();
        for (Map<String, String> map1:maps){
            PersonClue personClue = new PersonClue(Integer.parseInt(map1.get("post_id")),Integer.parseInt(map1.get("user_id")),map1.get("time"),map1.get("msg"));
            personClues.add(personClue);
        }
        return personClues;
    }

    public static ArrayList<PersonClue> find_clue_b(int pid){
        Map<String, String> map = new HashMap<>();
        map.put("post_id",Integer.toString(pid));
        String first = send(map2json(map), "/clue/get/B").first;
        List<Map<String, String>> maps = json2list(first);
        ArrayList<PersonClue> personClues = new ArrayList<>();
        for (Map<String, String> map1:maps){
            PersonClue personClue = new PersonClue(Integer.parseInt(map1.get("post_id")),Integer.parseInt(map1.get("user_id")),map1.get("time"),map1.get("msg"));
            personClues.add(personClue);
        }
        return personClues;
    }

    public static int post_post_a(Person person){
        Map<String, String> map = person2map(person);
//        String str = map2json(map);
//        System.out.println(str);
        map.put("poster_id",json2map(send(map2json(new HashMap<String, String>()),"/user/cur").first).get("id"));
        return send(map2json(map),"/post/A").second;
    }

    public static int post_post_b(Person person){
        Map<String, String> map = person2map(person);
        map.put("poster_id",json2map(send(map2json(new HashMap<String, String>()),"/user/cur").first).get("id"));
        return send(map2json(map),"/post/B").second;
    }

    public static ArrayList<Person> user_find_my_post_a(int uid){
        Map<String, String> map = new HashMap<>();
        map.put("user_id",Integer.toString(uid));
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/user/my-post/A").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static ArrayList<Person> user_find_my_post_b(int uid){
        Map<String, String> map = new HashMap<>();
        map.put("user_id",Integer.toString(uid));
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/user/my-post/B").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static int update_state_post_a(int pid,String state){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(pid));
        map.put("new_state",state);
        return send(map2json(map),"/post/detail/state/A").second;
    }

    public static int update_state_post_b(int pid,String state){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(pid));
        map.put("new_state",state);
        return send(map2json(map),"/post/detail/state/B").second;
    }

    public static int clue_a(PersonClue personClue){
        Map<String, String> map = new HashMap<>();
        map.put("post_id",Integer.toString(personClue.getPid()));
        map.put("user_id",Integer.toString(personClue.getUid()));
        map.put("msg", personClue.getMsg());
        return send(map2json(map),"/clue/A").second;
    }

    public static int clue_b(PersonClue personClue){
        Map<String, String> map = new HashMap<>();
        map.put("post_id",Integer.toString(personClue.getPid()));
        map.put("user_id",Integer.toString(personClue.getUid()));
        map.put("msg", personClue.getMsg());
        return send(map2json(map),"/clue/B").second;
    }

    public static ArrayList<Person> user_clue_a(int uid){
        Map<String, String> map = new HashMap<>();
        map.put("user_id",Integer.toString(uid));
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/user/clue/A").first));
        System.out.println("people========================"+people);
        return people;
    }

    public static ArrayList<Person> user_clue_b(int uid){
        Map<String, String> map = new HashMap<>();
        map.put("user_id",Integer.toString(uid));
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/user/clue/B").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static ArrayList<Person> find_similar_post_a(){
        Map<String, String> map = new HashMap<>();
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/post/similar/A").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static ArrayList<Person> find_similar_post_b(){
        Map<String, String> map = new HashMap<>();
        ArrayList<Person> people = map2people(json2list(send(map2json(map), "/post/similar/B").first));
//        System.out.println("people========================"+people);
        return people;
    }

    public static int change_identify(int uid){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(uid));
        return send(map2json(map),"/user/identify").second;
    }

    public static int change_image(int uid,int image){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(uid));
        map.put("url",Integer.toString(image));
        return send(map2json(map),"/user/image").second;
    }

    public static int set_image(String objKey){
        Map<String, String> map = new HashMap<>();
        map.put("src",objKey);
        return Integer.parseInt(json2map(send(map2json(map),"/image/set").first).get("id"));
    }

    public static String get_image(int id){
        Map<String, String> map = new HashMap<>();
        map.put("id",Integer.toString(id));
        return json2map(send(map2json(map),"/image/get").first).get("src");
    }
}
