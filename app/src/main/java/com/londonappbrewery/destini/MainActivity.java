package com.londonappbrewery.destini;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String STORY_INDEX = "StoryIndex";

    private TextView mStoryTextView;
    private Button mButtonTop, mButtonBottom;
    private int mStoryIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mStoryIndex = savedInstanceState.getInt(STORY_INDEX);
        } else {
            mStoryIndex = 1;
        }

        mStoryTextView = findViewById(R.id.storyTextView);
        mButtonTop = findViewById(R.id.buttonTop);
        mButtonBottom = findViewById(R.id.buttonBottom);

        mButtonBottom.setOnClickListener(mButtonOnClickListener);
        mButtonTop.setOnClickListener(mButtonOnClickListener);

        updateAppView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STORY_INDEX, mStoryIndex);
    }

    private View.OnClickListener mButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button pressedButton = (Button) v;
            checkButtonAndUpdateIndex(pressedButton.getText());
            updateAppView();
        }
    };

    private void checkButtonAndUpdateIndex(CharSequence buttonText) {
        if (buttonText.equals(getResources().getString(R.string.T1_Ans1))) {
            mStoryIndex = 2;
        } else if (buttonText.equals(getResources().getString(R.string.T1_Ans2))) {
            mStoryIndex = 3;
        } else if (buttonText.equals(getResources().getString(R.string.T2_Ans1))) {
            mStoryIndex = 2;
        } else if (buttonText.equals(getResources().getString(R.string.T2_Ans2))) {
            mStoryIndex = 4;
        } else if (buttonText.equals(getResources().getString(R.string.T3_Ans1))) {
            mStoryIndex = 5;
        } else if (buttonText.equals(getResources().getString(R.string.T3_Ans2))) {
            mStoryIndex = 6;
        }
    }

    private void updateViews(int story, int top, int bottom) {
        mStoryTextView.setText(story);
        if (top == 0 || bottom == 0) {
            mButtonTop.setVisibility(View.GONE);
            mButtonBottom.setVisibility(View.GONE);
            new AlertDialog.Builder(this)
                    .setMessage("GAME OVER. See you next time! ;)")
                    .setCancelable(false)
                    .setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        } else {
            mButtonTop.setText(top);
            mButtonBottom.setText(bottom);
        }
    }

    private void updateAppView() {
        if (mStoryIndex == 1) {
            updateViews(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2);
        } else if (mStoryIndex == 2) {
            updateViews(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2);
        } else if (mStoryIndex == 3) {
            updateViews(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2);
        } else if (mStoryIndex == 4) {
            updateViews(R.string.T4_End, 0 ,0);
        } else if (mStoryIndex == 5) {
            updateViews(R.string.T6_End, 0, 0);
        } else if (mStoryIndex == 6) {
            updateViews(R.string.T5_End, 0, 0);
        }
    }
}
