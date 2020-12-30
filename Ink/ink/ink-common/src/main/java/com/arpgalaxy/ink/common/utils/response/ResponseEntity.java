package com.arpgalaxy.ink.common.utils.response;

import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Map;

/**
 * @author arpgalaxy
 * @date 2020/9/17
 * @email 13605560342@163.com
 * @description
 */
public class ResponseEntity<T> {
    private int code;
    private String message;
    private T data;

    public ResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseEntity(int code, T data) {
        this.code = code;
        this.message = null;
        this.data = data;
    }
    public ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
