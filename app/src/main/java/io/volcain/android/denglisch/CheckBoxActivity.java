package io.volcain.android.denglisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.volcain.android.denglisch.quiz.Quiz;
import io.volcain.android.denglisch.quiz.QuizManager;
import io.volcain.android.denglisch.quiz.ResponseType;

/**
 * This class display all Quizzes with {@link CheckBox} response types.
 *
 * Created by volcain on 11/26/16.
 */
public class CheckBoxActivity extends AppCompatActivity {
    private ArrayList<Quiz> mListOfCheckBoxQuizzes;
    private ArrayList<Integer> mListOfPossibleAnswers;
    private ArrayList<Integer> mListOfCorrectAnswers;
    // to determine the quiz position
    private int mQuizPosition;
    // maximum number of quizzes to solve
    private int mMaxNumberOfQuizzes;
    private ArrayList<String> checkedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        // get list of quizzes according to response type
        mListOfCheckBoxQuizzes = QuizManager.getQuizListByResponseType(ResponseType.CHECK_BOX);
        // start with first quiz
        mQuizPosition = 0;
        // maximum number of quizzes to solve
        mMaxNumberOfQuizzes = mListOfCheckBoxQuizzes.size();
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
        mListOfPossibleAnswers = quiz.getListOfPossibleAnswers();
        mListOfCorrectAnswers = quiz.getListOfCorrectAnswers();
        checkedAnswers = new ArrayList<>(mListOfCorrectAnswers.size());

        // set the quiz question
        TextView questionTextView = (TextView) findViewById(R.id.question_text_view);
        questionTextView.setText(getString(iQuestionId));
        // set the first possible answers
        final CheckBox firstPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.first_possible_answer_check_box);
        firstPossibleAnswerCheckBox.setOnCheckedChangeListener(new CheckedChangeListener(firstPossibleAnswerCheckBox));
        int iAnswerId = mListOfPossibleAnswers.get(0);
        CharSequence csAnswer = getText(iAnswerId);
        firstPossibleAnswerCheckBox.setText(csAnswer);
        // set the second possible answers
        CheckBox secondPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.second_possible_answer_check_box);
        secondPossibleAnswerCheckBox.setOnCheckedChangeListener(new CheckedChangeListener(secondPossibleAnswerCheckBox));
        iAnswerId = mListOfPossibleAnswers.get(1);
        csAnswer = getText(iAnswerId);
        secondPossibleAnswerCheckBox.setText(csAnswer);
        // set the third possible answers
        CheckBox thirdPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.third_possible_answer_check_box);
        thirdPossibleAnswerCheckBox.setOnCheckedChangeListener(new CheckedChangeListener(thirdPossibleAnswerCheckBox));
        iAnswerId = mListOfPossibleAnswers.get(2);
        csAnswer = getText(iAnswerId);
        thirdPossibleAnswerCheckBox.setText(csAnswer);
        // set the fourth possible answers
        CheckBox fourthPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.fourth_possible_answer_check_box);
        fourthPossibleAnswerCheckBox.setOnCheckedChangeListener(new CheckedChangeListener(fourthPossibleAnswerCheckBox));
        iAnswerId = mListOfPossibleAnswers.get(3);
        csAnswer = getText(iAnswerId);
        fourthPossibleAnswerCheckBox.setText(csAnswer);
    }

    /**
     * Check, if the user entered the correct answer and display a toast message with result. If
     * not, stay on the screen until the user enters the correct answer.
     *
     * @param view is the actual view
     */
    public void gradle(final View view) {
        // get check boxes to reset them
        final CheckBox firstPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.first_possible_answer_check_box);
        final CheckBox secondPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.second_possible_answer_check_box);
        final CheckBox thirdPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.third_possible_answer_check_box);
        final CheckBox fourthPossibleAnswerCheckBox = (CheckBox) findViewById(R.id.fourth_possible_answer_check_box);

        // do work only if at least one check box is checked
        if (firstPossibleAnswerCheckBox.isChecked() || secondPossibleAnswerCheckBox.isChecked() || thirdPossibleAnswerCheckBox.isChecked() || fourthPossibleAnswerCheckBox.isChecked()) {
            // gradle if the selected answer is correct
            if (doCheck()) {
                mListOfCheckBoxQuizzes.get(mQuizPosition).setQuizSolved(true);
                // display the success msg
                Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
            } else {
                mListOfCheckBoxQuizzes.get(mQuizPosition).setQuizSolved(false);
                // display the error msg
                Toast.makeText(this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
            }
            // clear gradle boxes after each gradle for another try
            firstPossibleAnswerCheckBox.setChecked(false);
            secondPossibleAnswerCheckBox.setChecked(false);
            thirdPossibleAnswerCheckBox.setChecked(false);
            fourthPossibleAnswerCheckBox.setChecked(false);
            // and move on with the next quiz
            mQuizPosition = mQuizPosition + 1;
            // go create one
            createQuiz();
        } else {
            mListOfCheckBoxQuizzes.get(mQuizPosition).setQuizSolved(false);
            // display the error msg
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
     * This method checks whether the checked box is correct or not.
     */
    private boolean doCheck() {
        boolean isCorrectAnswer = false;

        if (checkedAnswers.size() == mListOfCorrectAnswers.size()) {
            int count = 0;
            for (Integer iCorrectAnswerId : mListOfCorrectAnswers) {
                String correctAnswer = getString(iCorrectAnswerId);
                for (String checkedAnswer : checkedAnswers) {
                    if (correctAnswer.equals(checkedAnswer)) {
                        count++;
                        break;
                    }
                }
            }
            if (count == checkedAnswers.size())
                isCorrectAnswer = true;
        }

        return isCorrectAnswer;
    }

    /**
     * Go on to the next quiz. If all quizzes are done move on to the next activity ({@link
     * EndActivity}).
     */
    private void createQuiz() {
        if (mQuizPosition < mMaxNumberOfQuizzes) {
            Quiz quiz = mListOfCheckBoxQuizzes.get(mQuizPosition);
            fillLayout(quiz);
        } else {
            Intent intent = new Intent(this, EndActivity.class);
            startActivity(intent);
        }
    }

    /**
     * puts each right answer into a list
     */
    private class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private CheckBox checkBox;

        private CheckedChangeListener(final CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                checkedAnswers.add(checkBox.getText().toString());
            } else {
                checkedAnswers.remove(checkBox.getText().toString());
            }
        }
    }
}
