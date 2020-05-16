package ua.com.krch.chaos.duckonmmon.zefirka.testics.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ua.com.krch.chaos.duckonmmon.zefirka.testics.application.App;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.ConstantQuestion;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Question;

public final class QuestionProviderService {

    private QuestionProviderService(){

    }

    private static App app = App.getINSTANCE();

    public static Question getQuestion(ConstantQuestion constantQuestion,List<Integer> randomAnswer,Integer randomA){
        Question question = new Question();
        question.setImage(constantQuestion.getImage());
        question.setQuestion(app.getString(constantQuestion.getQuestion()));
        question.setCorrectAnswer(app.getString(constantQuestion.getCorrectAnswer()));
        if (randomAnswer == null && randomA == null){
            question.setAnswers(Arrays.asList(app.getResources().getStringArray(constantQuestion.getAnswers())));
        } else if (randomA == null) {
            Collections.shuffle(randomAnswer);
            ArrayList<String> answers = new ArrayList<>();
            int count = 3;
            for (int i = 0; i < count; i++){
                if (!randomAnswer.get(i).equals(constantQuestion.getCorrectAnswer())){
                    answers.add(app.getString(randomAnswer.get(i)));
                } else {
                    count+=1;
                }
            }
            answers.add(app.getString(constantQuestion.getCorrectAnswer()));
            Collections.shuffle(answers);
            question.setAnswers(answers);
        } else {
            List<String> answ = Arrays.asList(app.getResources().getStringArray(randomA));
            String correctAnswer = app.getString(constantQuestion.getCorrectAnswer());
            Collections.shuffle(answ);
            ArrayList<String> answers = new ArrayList<>();
            int count = 3;
            for (int i = 0; i < count; i++){
                if (!answ.get(i).equals(correctAnswer)){
                    answers.add(answ.get(i));
                } else {
                    count+=1;
                }
            }
            answers.add(correctAnswer);
            Collections.shuffle(answers);
            question.setAnswers(answers);
        }
        return question;
    }
}
