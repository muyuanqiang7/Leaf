package com.huiztech.leaf.common.result;

import java.io.Serializable;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/8 12:15]
 * @description []
 */
public class RpcResponse implements Serializable {
    private boolean success;
    private Object data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RpcResponse getSuccessResponse(Object data, String message) {
        RpcResponse response = new RpcResponse();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static RpcResponse getSuccessResponse(Object data) {
        RpcResponse response = new RpcResponse();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage("success");
        return response;
    }

    public static RpcResponse getFailureResponse(String message) {
        RpcResponse response = new RpcResponse();
        response.setSuccess(false);
        response.setData(-1L);
        response.setMessage(message);
        return response;
    }


}
