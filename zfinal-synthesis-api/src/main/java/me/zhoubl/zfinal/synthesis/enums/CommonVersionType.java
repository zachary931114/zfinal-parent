package me.zhoubl.zfinal.synthesis.enums;

/**
 * Created by zhoubl on 2017/2/14.
 */
public enum CommonVersionType {

    DEFAULT("默认", 0);

    private String text;
    private Integer val;

    CommonVersionType(String text, Integer val) {
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
