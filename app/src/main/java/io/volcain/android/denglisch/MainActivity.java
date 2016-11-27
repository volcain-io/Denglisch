package io.volcain.android.denglisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Our starting point.
 *
 * Created by volcain on 11/26/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Go on to the next quiz. If all quizzes are done move on the next view (edit text).
     *
     * @param view is the actual view
     */
    public void startQuiz(final View view) {
        Intent intent = new Intent(this, RadioButtonActivity.class);
        startActivity(intent);
    }
}
