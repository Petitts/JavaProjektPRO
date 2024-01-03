package org.example.javaprojektpro;

import java.util.List;

public class QuizQestion {
    public String questionText;
    public List<Integer> correctAnswer;
    public List<String> options;
    public QuizQestion(){
        this.questionText="";
    }
    public QuizQestion(String questionText, List<String> options,List<Integer> correctAnswer){
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
    public String getQuestionText(){
        return questionText;
    }
    public List<String> getOptions(){
        return options;
    }
    public List<Integer> getCorrectAnswer(){
        return correctAnswer;
    }
}
