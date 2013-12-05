package com.headhunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelloScreen extends Activity implements View.OnClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_screen);
		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btCandidate:
			Intent intentA = new Intent(HelloScreen.this,CandidateMainActivity.class);
			startActivity(intentA);
			break;
		case R.id.btEmployer:
			Intent intentB = new Intent(HelloScreen.this,EmployerMainActivity.class);
			startActivity(intentB);
			break;
		default:
			// бреак и всё тут
			break;
		}
		
	}

}
