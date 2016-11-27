package io.volcain.android.denglisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.volcain.android.denglisch.quiz.Quiz;
import io.volcain.android.denglisch.quiz.QuizManager;
import io.volcain.android.denglisch.quiz.ResponseType;

/**
 * This class display all Quizzes with {@link RadioButton} response types.
 *
 * Created by volcain on 11/26/16.
 */

public class RadioButtonActivity extends AppCompatActivity {
    private ArrayList<Quiz> mListOfRadioButtonQuizzes;
    private ArrayList<Integer> mListOfPossibleAnswers;
    private ArrayList<Integer> mListOfCorrectAnswers;
    // to determine the quiz position
    private int mQuizPosition;
    // maximum number of quizzes to solve
    private int mMaxNumberQuizzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        // get list of quizzes according to response type
        mListOfRadioButtonQuizzes = QuizManager.getQuizListByResponseType(ResponseType.RADIO_BUTTON);
        // start with first quiz
        mQuizPosition = 0;
        // maximum number of quizzes to solve
        mMaxNumberQuizzes = mListOfRadioButtonQuizzes.size();
        // create our first quiz
        createQuiz();
    }

    /**
     * Fill the layout with the values of our quiz.
     *
     * @param quiz is the actual quiz which the user must solve.
     */
    private void fillLayout(final Quiz quiz) {
        // get quiz infos
        int iQuestionId = quiz.getQuestion();

        // set the quiz question
        TextView questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setText(getString(iQuestionId));
        // set the first possible answer
        RadioButton firstPossibleAnswerRadioButton = (RadioButton) findViewById(R.id.first_possible_answer_radio_button);
        int iAnswerId = mListOfPossibleAnswers.get(0);
        CharSequence csAnswer = getText(iAnswerId);
        firstPossibleAnswerRadioButton.setText(csAnswer);
        // set the second possible answer
        RadioButton secondPossibleAnswerRadioButton = (RadioButton) findViewById(R.id.second_possible_answer_radio_button);
        iAnswerId = mListOfPossibleAnswers.get(1);
        csAnswer = getText(iAnswerId);
        secondPossibleAnswerRadioButton.setText(csAnswer);
        // set the third possible answer
        RadioButton thirdPossibleAnswerRadioButton = (RadioButton) findViewById(R.id.third_possible_answer_radio_button);
        iAnswerId = mListOfPossibleAnswers.get(2);
        csAnswer = getText(iAnswerId);
        thirdPossibleAnswerRadioButton.setText(csAnswer);
        // set the fourth possible answer
        RadioButton fourthPossibleAnswerRadioButton = (RadioButton) findViewById(R.id.fourth_possible_answer_radio_button);
        iAnswerId = mListOfPossibleAnswers.get(3);
        csAnswer = getText(iAnswerId);
        fourthPossibleAnswerRadioButton.setText(csAnswer);
    }

    /**
     * Check, if the user entered the correct answer and display a toast message with result. If
     * not, stay on the screen until the user enters the correct answer.
     *
     * @param view is the actual view
     */
    public void gradle(final View view) {
        // get value from checked radio button with the help of the radio group
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.possible_answers_radio_group);
        // do work only if a radio button is checked
        if (radioGroup.getCheckedRadioButtonId() > -1) {
            RadioButton checkedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
            // get correct answer from our quiz object. also trim the value and set all characters to lowercase
            String correctAnswer = getString(mListOfCorrectAnswers.get(0)).trim().toLowerCase();
            // get selected answer from the view. also trim the value and set all characters to lowercase
            String selectedAnswer = checkedRadioButton.getText().toString().trim().toLowerCase();
            // gradle if the selected answer is correct
            if (correctAnswer.equals(selectedAnswer)) {
                mListOfRadioButtonQuizzes.get(mQuizPosition).setQuizSolved(true);
                // display the success msg
                Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
            } else {
                mListOfRadioButtonQuizzes.get(mQuizPosition).setQuizSolved(false);
                // display the error msg
                Toast.makeText(this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
            }
            // clear radio button selection for another try
            radioGroup.clearCheck();
            // and move on with the next quiz
            mQuizPosition = mQuizPosition + 1;
            // go create one
            createQuiz();
        } else {
            mListOfRadioButtonQuizzes.get(mQuizPosition).setQuizSolved(false);
            // display the hint
            Toast.makeText(this, getString(R.string.quiz_hint), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Display the current score
     *
     * @param view is the actual view
     */
    public void displayScore(final View view) {
        int totalSolved = QuizManager.getInstance().getTotalSolved();
        int totalSize = QuizManager.getInstance().getTotalSize();
        Toast.makeText(this, getString(R.string.result, totalSolved, totalSize), Toast.LENGTH_SHORT).show();
    }

    /**
     * Go on to the next quiz. If all quizzes are done move on to the next activity ({@link
     * EditTextActivity}).
     */
    private void createQuiz() {
        if (mQuizPosition < mMaxNumberQuizzes) {
            // get next quiz
            Quiz quiz = mListOfRadioButtonQuizzes.get(mQuizPosition);
            // get list of possible answers of the quiz
            mListOfPossibleAnswers = quiz.getListOfPossibleAnswers();
            // get list of correct answers of the quiz
            mListOfCorrectAnswers = quiz.getListOfCorrectAnswers();
            // fill our layout
            fillLayout(quiz);
        } else {
            Intent intent = new Intent(this, EditTextActivity.class);
            startActivity(intent);
        }
    }
}
