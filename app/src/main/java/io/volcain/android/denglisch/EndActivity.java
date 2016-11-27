package io.volcain.android.denglisch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.volcain.android.denglisch.quiz.QuizManager;

/**
 * Our program ending point with the option to restart the app.
 *
 * Created by volcain on 11/26/16.
 */
public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        displayResult();
    }

    private void displayResult() {
        TextView textView = (TextView) findViewById(R.id.result_text_view);
        int totalSolved = QuizManager.getInstance().getTotalSolved();
        int totalSize = QuizManager.getInstance().getTotalSize();
        textView.setText(getString(R.string.result, totalSolved, totalSize));
    }
}
