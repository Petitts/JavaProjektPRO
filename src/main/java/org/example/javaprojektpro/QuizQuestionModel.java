package org.example.javaprojektpro;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class QuizQuestionModel {
    private final List<QuizQestion> questions;
    public static int score = 0;
    public QuizQuestionModel(){
        ObjectMapper objectMapper = new ObjectMapper();
        questions = new ArrayList<>();
        try {
            URL qURL = getClass().getResource("questions.json");
            File jsonFile = new File(qURL.toString());
            QuizQestion[] questionsArray = objectMapper.readValue(new File("src/main/resources/org/example/javaprojektpro/questions.json"), QuizQestion[].class);
            for (QuizQestion question : questionsArray){
                questions.add(question);
            }
            /*for (int i = 1; i <= 20; i++) {
                List<String> options = new ArrayList<>();
                options.add("Option 1");
                options.add("Option 2");
                options.add("Option 3");
                options.add("Option 4");
                List<Integer> correctAnswers = new ArrayList<>();
                correctAnswers.add(0);
                correctAnswers.add(2);
                questions.add(new QuizQestion("Question " + i, options, correctAnswers));
            }*/
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<QuizQestion> getQuestions(){
        return questions;
    }
}
