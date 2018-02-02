package me.zhoubl.zfinal.common.ex;

/**
 * Created by zhoubl on 2017/2/9.
 */
public class BizEx extends RuntimeException {

    /**
     * 参数异常
     */
    public static final int PARAM_ERROR_CODE = 10000001;

    /**
     * 业务异常
     */
    public static final int BIZ_ERROR_CODE = 10000002;

    private int errorCode;
    private String msg;

    public BizEx(String msg){
        super(msg);
    }

    public BizEx(int errorCode, String msg) {
        super(msg + "(" + errorCode + ")");
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
