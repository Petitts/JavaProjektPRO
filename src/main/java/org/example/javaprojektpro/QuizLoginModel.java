package org.example.javaprojektpro;

public class QuizLoginModel {
    private String correctPassword;
    public QuizLoginModel(String correctPassword){
        this.correctPassword = correctPassword;
    }
    public boolean isPasswordCorrect(String enteredPassword){
        return correctPassword.equals(enteredPassword);
    }
}
