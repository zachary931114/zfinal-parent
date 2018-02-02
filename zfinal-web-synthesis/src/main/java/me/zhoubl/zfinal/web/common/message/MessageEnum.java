package me.zhoubl.zfinal.web.common.message;

/**
 * Created by zhoubl on 2017/6/18.
 */
public enum MessageEnum {

    TEST("test", "test/list");

    private String title;
    private String url;

    MessageEnum(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
