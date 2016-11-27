package io.volcain.android.denglisch.quiz;

import java.util.ArrayList;

/**
 * This subclass contains a quiz with radio buttons ({@link android.widget.RadioButton}) as response type ({@link ResponseType}).
 * These quiz object may have multiple possible answers and only one correct answer.
 *
 * Created by volcain on 11/26/16.
 */

class RadioButtonQuiz extends Quiz {

    /**
     * Constructor.
     *
     * @param iQuestionId is the question id located in strings.xml
     * @param listOfPossibleAnswers inherits a list of possible answers
     * @param listOfCorrectAnswers  is only right answers
     */
    RadioButtonQuiz(final int iQuestionId, final ArrayList<Integer> listOfPossibleAnswers, final ArrayList<Integer> listOfCorrectAnswers) {
        super(ResponseType.RADIO_BUTTON, iQuestionId, listOfPossibleAnswers, listOfCorrectAnswers);
    }

}
