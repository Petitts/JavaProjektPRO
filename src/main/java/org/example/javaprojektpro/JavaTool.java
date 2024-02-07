package org.example.javaprojektpro;

public class JavaTool {
    public String id;
    public String type;
    public String title;
    public String url;
    public String a;
    public JavaTool(){}

    public JavaTool(String type, String title, String url, String a) {
        this.type = type;
        this.title = title;
        this.url = url;
        this.url = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
