package com.example.alexbig.smartape.models;

public class Question {

    private String premise;
    private int type;

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
}
