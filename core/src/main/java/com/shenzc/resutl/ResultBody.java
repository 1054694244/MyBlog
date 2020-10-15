package com.shenzc.resutl;

import lombok.Data;

/**
 * ResultBody
 *
 * @author Shenzc
 * 2020/9/4
 */
@Data
public class ResultBody {
    private int code;
    private String msg;
    private Object data;

    public ResultBody(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultBody success(Object data) {
        return new ResultBody(200, "success", data);
    }

    public static ResultBody fail(int code1, String msg) {
        return new ResultBody(code1, msg, null);
    }

    public static ResultBody fail500(String msg) {
        return fail(500, msg);
    }

}
