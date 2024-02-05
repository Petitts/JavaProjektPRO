package org.example.javaprojektpro;

public class Image {
    public String id;
    public String subject;
    public String lecture;
    public String number;
    public String url;
    public Image(){this.subject="";}
    public Image(String id, String subject, String lecture, String number, String url) {
        this.id = id;
        this.subject = subject;
        this.lecture = lecture;
        this.number = number;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
