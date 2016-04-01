package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown";
	private static final String KET_ISANSWERSHOWN = "isAnswerShown";

	private boolean mAnswerIsTrue;
	private boolean mIsAnswerShown;
	private TextView mAnswerTextView;
	private TextView mShowSdkVersion;
	
	private Button mShowAnswer;
	
	private void setAnswerShowResult(boolean isAnswerShown) {
		mIsAnswerShown = isAnswerShown;
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, mIsAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	private void showAnswer() {
		if (mAnswerIsTrue) {
			mAnswerTextView.setText(R.string.true_button);
		} else {
			mAnswerTextView.setText(R.string.false_button);
		}
		setAnswerShowResult(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		
		setAnswerShowResult(false);

		if (savedInstanceState != null) {
			mIsAnswerShown = savedInstanceState.getBoolean(KET_ISANSWERSHOWN, false);
			if (mIsAnswerShown) {
				showAnswer();
			} 
		} 

		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showAnswer();
			}
		});
		
		mShowSdkVersion = (TextView) findViewById(R.id.showSdkVersion);
		mShowSdkVersion.setText("API level " + Build.VERSION.SDK_INT);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean(KET_ISANSWERSHOWN, mIsAnswerShown);
	}

}
