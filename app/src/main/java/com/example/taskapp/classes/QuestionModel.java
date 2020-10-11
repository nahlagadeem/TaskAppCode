package com.example.taskapp.classes;

public class QuestionModel {
    String id="0";
    String username="";
    String title="";
    String image="";

    public QuestionModel() {
    }

    public QuestionModel(String id, String username, String title, String image) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
