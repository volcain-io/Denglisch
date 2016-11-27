package io.volcain.android.denglisch.quiz;

import java.util.ArrayList;
import java.util.Iterator;

import io.volcain.android.denglisch.R;

/**
 * Creates our list of quizzes, which include 3 quizzes each response type ({@link ResponseType}).
 * In total there are\n 3 quizzes with radio buttons 3 quizzes with gradle boxes 3 quizzes with edit
 * texts.
 *
 * Created by volcain on 11/26/16.
 */

public class QuizManager {
    private static ArrayList<Quiz> listOfQuizzes = null;

    /**
     * private constructor (singleton pattern)
     */
    private QuizManager() {
    }

    /**
     * singleton pattern
     *
     * @return an instance of {@link QuizManager}
     */
    public static QuizManager getInstance() {
        createQuizList();
        return QuizManagerHelper.INSTANCE;
    }

    /**
     * creates the whole quiz list with 9 questions including their response type ({@link
     * ResponseType}) and their possible and correct answers
     */
    private static void createQuizList() {
        if (null == listOfQuizzes) {
            listOfQuizzes = new ArrayList<>(9);

            // first quiz
            ArrayList<Integer> possibleAnswers = new ArrayList<>(4);
            possibleAnswers.add(R.string.possible_answer_woman);
            possibleAnswers.add(R.string.possible_answer_man);
            possibleAnswers.add(R.string.possible_answer_boy);
            possibleAnswers.add(R.string.possible_answer_bread);
            // correct answer: "man"
            ArrayList<Integer> listOfCorrectAnswers = new ArrayList<>(1);
            listOfCorrectAnswers.add(R.string.correct_answer_man);
            RadioButtonQuiz radioButtonQuiz = new RadioButtonQuiz(R.string.question_which_is_man, possibleAnswers, listOfCorrectAnswers);
            listOfQuizzes.add(radioButtonQuiz);

            // second quiz
            possibleAnswers = new ArrayList<>(4);
            possibleAnswers.add(R.string.possible_answer_woman);
            possibleAnswers.add(R.string.possible_answer_water);
            possibleAnswers.add(R.string.possible_answer_boy);
            possibleAnswers.add(R.string.possible_answer_man);
            // correct answer: "boy"
            listOfCorrectAnswers = new ArrayList<>(1);
            listOfCorrectAnswers.add(R.string.correct_answer_boy);
            radioButtonQuiz = new RadioButtonQuiz(R.string.question_which_is_boy, possibleAnswers, listOfCorrectAnswers);
            listOfQuizzes.add(radioButtonQuiz);

            // third quiz
            possibleAnswers = new ArrayList<>(4);
            possibleAnswers.add(R.string.possible_answer_woman);
            possibleAnswers.add(R.string.possible_answer_water);
            possibleAnswers.add(R.string.possible_answer_boy);
            possibleAnswers.add(R.string.possible_answer_man);
            // correct answer: "woman"
            listOfCorrectAnswers = new ArrayList<>(1);
            listOfCorrectAnswers.add(R.string.correct_answer_woman);
            radioButtonQuiz = new RadioButtonQuiz(R.string.question_which_is_woman, possibleAnswers, listOfCorrectAnswers);
            listOfQuizzes.add(radioButtonQuiz);

            // fourth quiz
            // correct answer: "a man"
            listOfCorrectAnswers = new ArrayList<>(1);
            listOfCorrectAnswers.add(R.string.correct_answer_a_man);
            EditTextQuiz editTextQuiz = new EditTextQuiz(R.string.question_a_man, listOfCorrectAnswers);
            listOfQuizzes.add(editTextQuiz);

            // fifth quiz
            // correct answer: "a woman"
            listOfCorrectAnswers = new ArrayList<>(1);
            listOfCorrectAnswers.add(R.string.correct_answer_a_woman);
            editTextQuiz = new EditTextQuiz(R.string.question_a_woman, listOfCorrectAnswers);
            listOfQuizzes.add(editTextQuiz);

            // sixth quiz
            // correct answer: "I am a man"
            listOfCorrectAnswers = new ArrayList<>(1);
            listOfCorrectAnswers.add(R.string.correct_answer_i_am_a_man);
            editTextQuiz = new EditTextQuiz(R.string.question_i_am_a_man, listOfCorrectAnswers);
            listOfQuizzes.add(editTextQuiz);

            // seventh quiz
            possibleAnswers = new ArrayList<>(1);
            possibleAnswers.add(R.string.possible_answer_a);
            possibleAnswers.add(R.string.possible_answer_it);
            possibleAnswers.add(R.string.possible_answer_he);
            possibleAnswers.add(R.string.possible_answer_she);
            // correct answers: "ein", "der"
            listOfCorrectAnswers = new ArrayList<>(2);
            listOfCorrectAnswers.add(R.string.correct_answer_a);
            listOfCorrectAnswers.add(R.string.correct_answer_he);
            CheckBoxQuiz checkBoxQuiz = new CheckBoxQuiz(R.string.question_i_am_placeholder_boy, possibleAnswers, listOfCorrectAnswers);
            listOfQuizzes.add(checkBoxQuiz);

            // eigth quiz
            possibleAnswers = new ArrayList<>(4);
            possibleAnswers.add(R.string.possible_answer_a);
            possibleAnswers.add(R.string.possible_answer_she);
            possibleAnswers.add(R.string.possible_answer_he);
            possibleAnswers.add(R.string.possible_answer_it);
            // correct answers: "ein", "der"
            listOfCorrectAnswers = new ArrayList<>(2);
            listOfCorrectAnswers.add(R.string.correct_answer_a);
            listOfCorrectAnswers.add(R.string.correct_answer_he);
            checkBoxQuiz = new CheckBoxQuiz(R.string.question_i_am_placeholder_man, possibleAnswers, listOfCorrectAnswers);
            listOfQuizzes.add(checkBoxQuiz);

            // ninth quiz
            possibleAnswers = new ArrayList<>(4);
            possibleAnswers.add(R.string.possible_answer_it);
            possibleAnswers.add(R.string.possible_answer_she);
            possibleAnswers.add(R.string.possible_answer_feminin_a);
            possibleAnswers.add(R.string.possible_answer_he);
            // correct answers: "eine", "die"
            listOfCorrectAnswers = new ArrayList<>(2);
            listOfCorrectAnswers.add(R.string.correct_answer_feminin_a);
            listOfCorrectAnswers.add(R.string.correct_answer_she);
            checkBoxQuiz = new CheckBoxQuiz(R.string.question_i_am_placeholder_woman, possibleAnswers, listOfCorrectAnswers);
            listOfQuizzes.add(checkBoxQuiz);
        }
    }

    /**
     * this method returns a list of quizzes based on response type.
     *
     * @param responseType possible values are RADIO_BUTTON, EDIT_TEXT, CHECK_BOX
     * @return ArrayList<Quiz> a list of quizzes or an empty list, if there is no such response type
     */
    public static ArrayList<Quiz> getQuizListByResponseType(final ResponseType responseType) {
        ArrayList<Quiz> quizList = new ArrayList<>();
        if (null != listOfQuizzes) {
            Iterator<Quiz> quizIterator = listOfQuizzes.iterator();
            switch (responseType) {
                case RADIO_BUTTON:
                    while (quizIterator.hasNext()) {
                        Quiz quiz = quizIterator.next();
                        if (quiz instanceof RadioButtonQuiz) {
                            RadioButtonQuiz radioButtonQuiz = (RadioButtonQuiz) quiz;
                            quizList.add(radioButtonQuiz);
                        }
                    }
                    break;
                case EDIT_TEXT:
                    while (quizIterator.hasNext()) {
                        Quiz quiz = quizIterator.next();
                        if (quiz instanceof EditTextQuiz) {
                            EditTextQuiz editTextQuiz = (EditTextQuiz) quiz;
                            quizList.add(editTextQuiz);
                        }
                    }
                    break;
                case CHECK_BOX:
                    while (quizIterator.hasNext()) {
                        Quiz quiz = quizIterator.next();
                        if (quiz instanceof CheckBoxQuiz) {
                            CheckBoxQuiz checkBoxQuiz = (CheckBoxQuiz) quiz;
                            quizList.add(checkBoxQuiz);
                        }
                    }
                    break;
            }
        }
        return quizList;
    }

    /**
     * Return a quiz object or null.
     *
     * @param index is the position of item in the list
     * @return Quiz or null if there doesn't exists any list.
     */
    public Quiz getQuiz(final int index) {
        Quiz quiz = null;
        if (null != listOfQuizzes) {
            quiz = listOfQuizzes.get(index);
        }
        return quiz;
    }

    /**
     * Total of solved quizzes by the user
     *
     * @return number of total solved quizzes
     */
    public int getTotalSolved() {
        int totalSolved = 0;
        if (null != listOfQuizzes) {
            for (Quiz quiz: listOfQuizzes ) {
                if (quiz.getQuizSolved())
                    totalSolved++;
            }
        }
        return totalSolved;
    }

    /**
     * Total number of quizzes we provide
     *
     * @return 0 if no quizzes exists else total number of quizzes
     */
    public int getTotalSize() {
        int totalSize = 0;
        if (null != listOfQuizzes)
            totalSize = listOfQuizzes.size();
        return totalSize;
    }

    /**
     * singleton pattern
     */
    private static class QuizManagerHelper {
        private static final QuizManager INSTANCE = new QuizManager();
    }

}
