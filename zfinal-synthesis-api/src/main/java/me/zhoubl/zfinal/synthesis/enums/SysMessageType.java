package me.zhoubl.zfinal.synthesis.enums;

/**
 * Created by zhoubl on 2017/6/18.
 */
public enum SysMessageType {

    DEFAULT("默认", 0), LOGOUT("踢出登录", 1);

    private String text;
    private Integer val;

    SysMessageType(String text, Integer val) {
        this.text = text;
        this.val = val;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
