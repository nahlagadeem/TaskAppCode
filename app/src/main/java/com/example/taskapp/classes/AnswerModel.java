package com.example.taskapp.classes;

public class AnswerModel {
    int id=0;
    String answer="";

    public AnswerModel() {
    }

    public AnswerModel(String answer) {
        this.answer = answer;
    }

    public AnswerModel(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
