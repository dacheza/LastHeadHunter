package com.headhunter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Camera extends Activity {
	//CandidateMainActivity candidate = new CandidateMainActivity();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
		
	}

}
