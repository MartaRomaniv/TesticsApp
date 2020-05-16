package ua.com.krch.chaos.duckonmmon.zefirka.testics.entity;

import java.io.Serializable;

/**
 * As we don't know what language will be selected we need this class,
 * what have pointers to our Questions
 */
public class ConstantQuestion implements Serializable {
    /**
     * ResourceId for image,
     * if no image for this question then set null
     */
    private Integer image = null;
    /**
     * Pointer to question in resources
     */
    private Integer question;
    /**
     * Pointer to correct answer
     */
    private Integer correctAnswer;
    /**
     *
     *  Pointer to answers in resources,
     *  if answers is selected randomly leave as null
     */
    private Integer answers = null;

    public ConstantQuestion() {
    }

    public ConstantQuestion(Integer image, Integer question, Integer correctAnswer, Integer answers) {
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

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getAnswers() {
        return answers;
    }

    public void setAnswers(Integer answers) {
        this.answers = answers;
    }
}
