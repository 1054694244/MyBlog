package com.shenzc.enumeration;



import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BlogSelected {

    ALL(0,"全部"),
    PUBLIC(1,"公开"),
    PRIVATE(2,"私密"),
    AUDIT(3,"审核"),
    SAVE(4,"草稿箱"),
    DELETE(5,"回收站");

    private Integer code;

    private String name;

    BlogSelected(Integer code,String name){
        this.code = code;
        this.name = name;
    }

    public static List<Map<String, Object>> toList(){
        BlogSelected[] enumList = BlogSelected.values();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (BlogSelected e : enumList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", e.code);
            map.put("name", e.name);
            list.add(map);
        }
        return list;
    }

    public static List<Integer> getCodeList(){
        BlogSelected[] enumList = BlogSelected.values();
        List<Integer> list = new ArrayList<>();
        for (BlogSelected e : enumList) {
            list.add(e.code);
        }
        return list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(String code) {
        if (StringUtils.isEmpty(code)) {
            return "";
        }

        BlogSelected[] enumList = BlogSelected.values();
        for (BlogSelected e : enumList) {
            if (StringUtils.pathEquals(e.code + "", code)) {
                return e.name;
            }
        }
        return "";
    }

}
