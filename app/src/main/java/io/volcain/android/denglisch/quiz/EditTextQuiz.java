package io.volcain.android.denglisch.quiz;

import java.util.ArrayList;

/**
 * This subclass contains a quiz with edit text ({@link android.widget.EditText}) as response type ({@link ResponseType}).
 * These quiz object has zero possible and only one correct answer.
 *
 * Created by volcain on 11/26/16.
 */

class EditTextQuiz extends Quiz {
    /**
     * Constructor.
     *
     * @param iQuestionId is the question id located in strings.xml
     * @param listOfCorrectAnswers  is only right answers
     */
    EditTextQuiz(final int iQuestionId, final ArrayList<Integer> listOfCorrectAnswers) {
        super(ResponseType.EDIT_TEXT, iQuestionId, null, listOfCorrectAnswers);
    }
}
