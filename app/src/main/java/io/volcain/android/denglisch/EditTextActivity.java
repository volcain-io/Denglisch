package io.volcain.android.denglisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.volcain.android.denglisch.quiz.Quiz;
import io.volcain.android.denglisch.quiz.QuizManager;
import io.volcain.android.denglisch.quiz.ResponseType;

/**
 * This class display all Quizzes with {@link EditText} response types.
 *
 * Created by volcain on 11/26/16.
 */
public class EditTextActivity extends AppCompatActivity {
    private ArrayList<Quiz> mListOfEditTextQuizzes;
    private ArrayList<Integer> mListOfCorrectAnswers;
    // to determine the quiz position
    private int mQuizPosition;
    // maximum number of quizzes to solve
    private int mMaxNumberOfQuizzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        // get list of quizzes according to response type
        mListOfEditTextQuizzes = QuizManager.getQuizListByResponseType(ResponseType.EDIT_TEXT);
        // start with first quiz
        mQuizPosition = 0;
        // maximum number of quizzes to solve
        mMaxNumberOfQuizzes = mListOfEditTextQuizzes.size();
        // create our first quiz
        createQuiz();
    }

    /**
     * Fill the layout with the values of our quiz.
     *
     * @param quiz is the actual quiz which the user must solve.
     */
    private void fillLayout(final Quiz quiz) {
        // get quiz informations
        int iQuestionId = quiz.getQuestion();

        // set the quiz question
        TextView questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setText(getText(quiz.getQuestion()));
    }

    /**
     * Check, if the user entered the correct answer and display a toast message with result. If
     * not, stay on the screen until the user enters the correct answer.
     *
     * @param view is the actual view
     */
    public void gradle(final View view) {
        // get the user input
        EditText answerEditText = (EditText) findViewById(R.id.answer_edit_text);

        // do work only if edit text is not empty
        if (!answerEditText.getText().toString().isEmpty()) {
            // get correct answer from our quiz object. also trim the value and set all characters to lowercase
            String correctAnswer = getString(mListOfCorrectAnswers.get(0)).trim().toLowerCase();
            // get selected answer from the view. also trim the value and set all characters to lowercase
            String selectedAnswer = answerEditText.getText().toString().trim().toLowerCase();
            // gradle if the selected answer is correct
            if (correctAnswer.equals(selectedAnswer)) {
                mListOfEditTextQuizzes.get(mQuizPosition).setQuizSolved(true);
                // display the success msg
                Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
            } else {
                mListOfEditTextQuizzes.get(mQuizPosition).setQuizSolved(false);
                // display the error msg
                Toast.makeText(this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
            }
            // clear edit text for another try
            answerEditText.setText("");
            // and move on with the next quiz
            mQuizPosition = mQuizPosition + 1;
            // go create one
            createQuiz();
        } else {
            mListOfEditTextQuizzes.get(mQuizPosition).setQuizSolved(false);
            // display the error msg
            Toast.makeText(this, getString(R.string.quiz_hint_2), Toast.LENGTH_SHORT).show();
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
     * CheckBoxActivity}).
     */
    private void createQuiz() {
        if (mQuizPosition < mMaxNumberOfQuizzes) {
            // get next quiz
            Quiz quiz = mListOfEditTextQuizzes.get(mQuizPosition);
            // get list of correct answers of the quiz
            mListOfCorrectAnswers = quiz.getListOfCorrectAnswers();
            // fill our layout
            fillLayout(quiz);
        } else {
            Intent intent = new Intent(this, CheckBoxActivity.class);
            startActivity(intent);
        }
    }
}
