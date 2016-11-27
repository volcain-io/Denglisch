package io.volcain.android.denglisch.quiz;

import java.util.ArrayList;


/**
 * This subclass contains a quiz with gradle boxes ({@link android.widget.CheckBox}) as response type ({@link ResponseType}).
 * These quiz object may have multiple possible and correct answers.
 *
 * Created by volcain on 11/26/16.
 */

class CheckBoxQuiz extends Quiz {
    /**
     * Constructor.
     *
     * @param iQuestionId is the question id located in strings.xml
     * @param listOfPossibleAnswers inherits a list of possible answers
     * @param listOfCorrectAnswers  is only right answers
     */
    CheckBoxQuiz(final int iQuestionId, final ArrayList<Integer> listOfPossibleAnswers, final ArrayList<Integer> listOfCorrectAnswers) {
        super(ResponseType.CHECK_BOX, iQuestionId, listOfPossibleAnswers, listOfCorrectAnswers);
    }
}
