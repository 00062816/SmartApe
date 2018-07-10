package com.example.alexbig.smartape.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable{

    private String premise;
    private int type;
    private List<Answer> answers = new ArrayList<>();

    public Question(){

    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
