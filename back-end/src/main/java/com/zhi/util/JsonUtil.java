package com.zhi.util;

import com.alibaba.fastjson.JSONObject;
import com.zhi.pojo.Product;
import com.zhi.pojo.Purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static String objectToJson(List<Object> list){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error_code",0);
        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }

    public static String objectToJson(Object o){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error_code",0);
        jsonObject.put("data",o);
        return jsonObject.toJSONString();
    }

    public static String objectToJsonWithCount(List<Product> list,int count){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error_code",0);
        jsonObject.put("count",count);
        jsonObject.put("data",list);
        return jsonObject.toJSONString();
    }

    public static String objectToJsonWithSex(List<Purchase> list){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error_code",0);
        List<Purchase> male = new ArrayList<Purchase>();
        List<Purchase> female = new ArrayList<Purchase>();
        for(Purchase o : list){
            if(o.getC_sex()==0){
                male.add(o);
            }
            else female.add(o);
        }

        Map<String,List<Purchase>> male_map = new HashMap<String, List<Purchase>>();
        Map<String,List<Purchase>> female_map = new HashMap<String, List<Purchase>>();
        male_map.put("male",male);
        female_map.put("female",female);
        List<Map<String,List<Purchase>>> l = new ArrayList<Map<String,List<Purchase>>>();
        l.add(male_map);
        l.add(female_map);
        jsonObject.put("data",l);
        return jsonObject.toJSONString();
    }

}
