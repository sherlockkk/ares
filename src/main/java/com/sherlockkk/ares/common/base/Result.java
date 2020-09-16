package com.sherlockkk.ares.common.base;

import com.sherlockkk.ares.common.constants.CommonConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author SongJian
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回数据对象 data
     */
    private Object result;

    public static Result ok() {
        Result r = new Result();
        r.setCode(CommonConstant.RESULT_OK_200);
        r.setMessage("操作成功");
        return r;
    }

    public static Result ok(Object data) {
        Result r = new Result();
        r.setCode(CommonConstant.RESULT_OK_200);
        r.setMessage("操作成功");
        r.setResult(data);
        return r;
    }

    public static Result error(String msg) {
        return error(CommonConstant.RESULT_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result errorParam(String msg) {
        return error(CommonConstant.RESULT_PARAMETER_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static Result noAuth() {
        return error(CommonConstant.RESULT_NO_AUTH, "操作权限不足，请联系管理员授权");
    }
}
