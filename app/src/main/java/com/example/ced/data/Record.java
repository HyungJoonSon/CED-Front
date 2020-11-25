package com.example.ced.data;

public class Record {
    private String title;
    private String date;
    private String content;

    public Record(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public void setContent(String content) { this.content = content; }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() { return content; }
}

