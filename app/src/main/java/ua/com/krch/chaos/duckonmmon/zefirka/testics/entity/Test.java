package ua.com.krch.chaos.duckonmmon.zefirka.testics.entity;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {
    private Integer name;
    private Integer image;
    private Integer maxMark;
    private String key;
    private List<ConstantQuestion> questions;
    /**
     * Set to null, if answers are selected from resources
     */
    private List<Integer> randomAnswers;

    private Integer randomAnswersArray = null;

    public Test(Integer name, Integer image, Integer maxMark, String key,List<ConstantQuestion> questions,List<Integer> randomAnswers) {
        this.name = name;
        this.image = image;
        this.maxMark = maxMark;
        this.key = key;
        this.questions = questions;
        this.randomAnswers = randomAnswers;
    }

    public Test(Integer name, Integer image, Integer maxMark, String key,List<ConstantQuestion> questions,int randomAnswersArray) {
        this.name = name;
        this.image = image;
        this.maxMark = maxMark;
        this.key = key;
        this.questions = questions;
        this.randomAnswersArray = randomAnswersArray;
    }

    public Test() {
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(Integer maxMark) {
        this.maxMark = maxMark;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<ConstantQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ConstantQuestion> questions) {
        this.questions = questions;
    }

    public List<Integer> getRandomAnswers() {
        return randomAnswers;
    }

    public void setRandomAnswers(List<Integer> randomAnswers) {
        this.randomAnswers = randomAnswers;
    }

    public Integer getRandomAnswersArray() {
        return randomAnswersArray;
    }

    public void setRandomAnswersArray(Integer randomAnswersArray) {
        this.randomAnswersArray = randomAnswersArray;
    }
}
