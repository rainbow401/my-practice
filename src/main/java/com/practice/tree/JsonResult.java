package com.practice.tree;

import com.fasterxml.jackson.annotation.JsonView;
import com.practice.tree.annotation.TreeNodeView;
import lombok.Data;

import java.io.Serializable;

/**
 * @author onedayday
 */
@Data
@JsonView(Object.class)
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private boolean success = true;
    private static String code = "200";
    private static String msg = "SUCCESS";

    private T data;

    private JsonResult() {

    }

    private JsonResult(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult success() {
        JsonResult result = new JsonResult(true, code, "", (Object) null);
        return result;
    }

    public static <T> JsonResult success(String msg) {
        JsonResult result = new JsonResult(true, code, msg, (Object) null);
        return result;
    }

    public static <T> JsonResult success(String msg, T data) {
        JsonResult result = new JsonResult(true, code, msg, data);
        return result;
    }

    public static <T> JsonResult success(String code, String msg) {
        JsonResult result = new JsonResult(true, code, msg, (Object) null);
        return result;
    }

    public static <T> JsonResult data(T data) {
        JsonResult result = new JsonResult(true, code, "", data);
        return result;
    }

    public static <T> JsonResult success(String code, String msg, T data) {
        JsonResult result = new JsonResult(true, code, msg, data);
        return result;
    }

    public static JsonResult fail(String code, String errorMsg) {
        JsonResult result = new JsonResult(false, code, errorMsg, null);
        return result;
    }

    public static JsonResult fail(String errorMsg) {
        JsonResult result = new JsonResult(false, "-1", errorMsg, null);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}