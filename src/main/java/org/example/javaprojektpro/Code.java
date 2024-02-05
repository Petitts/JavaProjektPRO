package org.example.javaprojektpro;

public class Code {
    public String id;
    public String language;
    public String laboratory;
    public String exercise;
    public String title;
    public String content;

    public Code(){this.content="";}

    public Code(String language, String laboratory, String exercise, String title, String content) {
        this.language = language;
        this.laboratory = laboratory;
        this.exercise = exercise;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
