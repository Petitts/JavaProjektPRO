package org.example.javaprojektpro;

import java.util.List;

public class QuizQestion {
    public String id;
    public String questionText;
    public List<Integer> correctAnswers;
    public List<String> options;
    public String imageURL;
    public QuizQestion(){
        this.questionText="";
    }
    public QuizQestion(String id, String questionText, List<String> options,List<Integer> correctAnswer, String imageURL){
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswers = correctAnswer;
        this.imageURL = imageURL;
    }
    public String getQuestionText(){
        return questionText;
    }
    public List<String> getOptions(){
        return options;
    }
    public List<Integer> getCorrectAnswer(){
        return correctAnswers;
    }
    public  String getImageURL(){return imageURL;}
}
