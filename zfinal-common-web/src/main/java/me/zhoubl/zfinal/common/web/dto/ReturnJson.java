package me.zhoubl.zfinal.common.web.dto;

import java.io.Serializable;

/**
 * Created by zhoubl on 2017/4/21.
 */
public class ReturnJson implements Serializable{
    private boolean isSuccess;	//是否成功
    private String message;	//消息
    private Object data;	//数据

    public ReturnJson(boolean isSuccess, String message, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
