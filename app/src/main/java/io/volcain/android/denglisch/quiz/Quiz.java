package io.volcain.android.denglisch.quiz;

import java.util.ArrayList;

/**
 * This class represents a single quiz which will be displayed on the screen. A quiz includes a
 * response type ({@link ResponseType}), the question itself, possible answers and the possible
 * correct answers.
 *
 * Created by volcain on 11/26/16.
 */
public class Quiz {
    // what type of quiz (needed to select the appropriate layout)
    private ResponseType responseType;
    // the question of the quiz
    private int question;
    // list of possible answers
    private ArrayList<Integer> listOfPossibleAnswers = null;
    // list of correct answers
    private ArrayList<Integer> listOfCorrectAnswers = null;
    // state if quiz was solved by the user
    private boolean quizSolved;

    /**
     * Constructor.
     *
     * @param responseType          type of quiz
     * @param question              is the question to be solved
     * @param listOfPossibleAnswers inherits a list of possible answers
     * @param listOfCorrectAnswers  is only right answers
     */
    Quiz(final ResponseType responseType, final int question, final ArrayList<Integer> listOfPossibleAnswers, final ArrayList<Integer> listOfCorrectAnswers) {
        this.responseType = responseType;
        this.question = question;
        this.listOfPossibleAnswers = listOfPossibleAnswers;
        this.listOfCorrectAnswers = listOfCorrectAnswers;
        this.quizSolved = false;
    }

    /**
     * Constructor.
     *
     * @param listOfPossibleAnswers inherits a list of possible answers
     * @param listOfCorrectAnswers  is only right answer
     */
    public Quiz(final ArrayList<Integer> listOfPossibleAnswers, final ArrayList<Integer> listOfCorrectAnswers) {
        this.listOfPossibleAnswers = listOfPossibleAnswers;
        this.listOfCorrectAnswers = listOfCorrectAnswers;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(final ResponseType responseType) {
        this.responseType = responseType;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(final int question) {
        this.question = question;
    }

    public ArrayList<Integer> getListOfPossibleAnswers() {
        return listOfPossibleAnswers;
    }

    public void setListOfPossibleAnswers(final ArrayList<Integer> listOfPossibleAnswers) {
        this.listOfPossibleAnswers = listOfPossibleAnswers;
    }

    public ArrayList<Integer> getListOfCorrectAnswers() {
        return listOfCorrectAnswers;
    }

    public void setListOfCorrectAnswers(final ArrayList<Integer> listOfCorrectAnswers) {
        this.listOfCorrectAnswers = listOfCorrectAnswers;
    }

    boolean getQuizSolved() {
        return quizSolved;
    }

    public void setQuizSolved(final boolean quizSolved) {
        this.quizSolved = quizSolved;
    }
}
