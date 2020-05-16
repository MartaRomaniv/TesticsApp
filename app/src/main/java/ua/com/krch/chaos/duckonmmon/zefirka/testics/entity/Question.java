package ua.com.krch.chaos.duckonmmon.zefirka.testics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
    That class begin to exist only after test is selected
 */
public class Question implements Serializable {
    /**
     * ResourceId for image,
     * if no image for this question then set null
     */
    private Integer image = null;
    /**
     * Question String
     */
    private String question = "";
    /**
     * Correct Answer
     */
    private String correctAnswer = "";
    /**
     *  Answers
     */
    private List<String> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String question,String correctAnswer, List<String> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public Question(Integer image, String question,String correctAnswer, List<String> answers) {
        this.image = image;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
