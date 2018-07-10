package com.example.alexbig.smartape.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable{

    private String id;
    private String category;
    private String title;
    private String creator;
    private int status;
    private String description;
    private String timeLimit;
    private int numQuestions;
    private String created_date;
    private long Resueltos;
    private long Aprobados;
    private long Reprobados;
    private long Vistos;
    private long Guardados;
    private long Favoritos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    private boolean favorite;
    private boolean saved;
    private List<String> questionsIds = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();

    public Quiz(){

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public long getResueltos() {
        return Resueltos;
    }

    public void setResueltos(long resueltos) {
        Resueltos = resueltos;
    }

    public long getAprobados() {
        return Aprobados;
    }

    public void setAprobados(long aprobados) {
        Aprobados = aprobados;
    }

    public long getReprobados() {
        return Reprobados;
    }

    public void setReprobados(long reprobados) {
        Reprobados = reprobados;
    }

    public long getVistos() {
        return Vistos;
    }

    public void setVistos(long vistos) {
        Vistos = vistos;
    }

    public long getGuardados() {
        return Guardados;
    }

    public void setGuardados(long guardados) {
        Guardados = guardados;
    }

    public long getFavoritos() {
        return Favoritos;
    }

    public void setFavoritos(long favoritos) {
        Favoritos = favoritos;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public List<String> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<String> questionsIds) {
        this.questionsIds = questionsIds;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }
}
