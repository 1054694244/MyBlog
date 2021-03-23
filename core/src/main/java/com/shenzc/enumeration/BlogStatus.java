package com.shenzc.enumeration;



import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BlogStatus {

    ALL(0,"所有"),
    SAVE(1,"保存"),
    SUBMIT(2,"提交"),
    PASS(3,"审核通过"),
    NOPASS(4,"审核失败");

    private Integer code;

    private String name;

    BlogStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public static List<Map<String, Object>> toList(){
        BlogStatus[] enumList = BlogStatus.values();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (BlogStatus e : enumList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", e.code);
            map.put("name", e.name);
            list.add(map);
        }
        return list;
    }

    public static List<Integer> getCodeList(){
        BlogStatus[] enumList = BlogStatus.values();
        List<Integer> list = new ArrayList<>();
        for (BlogStatus e : enumList) {
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

        BlogStatus[] enumList = BlogStatus.values();
        for (BlogStatus e : enumList) {
            if (StringUtils.pathEquals(e.code + "", code)) {
                return e.name;
            }
        }
        return "";
    }

}
