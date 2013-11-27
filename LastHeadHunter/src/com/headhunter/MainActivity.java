package com.headhunter;

import com.headhunter.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;

public class MainActivity extends Activity {
	 int DIALOG_DATE = 1;
	 int startYear = 2000;
	 int startMonth = 01;
	 int startDay = 01;
	 EditText edName;
	 EditText edSurname;
	 EditText edPatronymic;
	 EditText edDate;
	 EditText edPosition;
	 EditText edSalary;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edName=(EditText)findViewById(R.id.name);
        edSurname=(EditText)findViewById(R.id.surname);
        edPatronymic=(EditText)findViewById(R.id.patronymic);
        edDate=(EditText)findViewById(R.id.birth);
        edPosition=(EditText)findViewById(R.id.position);
        edSalary=(EditText)findViewById(R.id.salary);
        
        
        // 1. Щас будут табы!
        // 1.1. фигачим табы
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        
        // 1.1.1 первая вкладка
        tabSpec = tabHost.newTabSpec("formTag");
        tabSpec.setIndicator("Анкета");
        tabSpec.setContent(R.id.resume);
        tabHost.addTab(tabSpec);
        
        // 1.1.2. вторая вкладка
        tabSpec = tabHost.newTabSpec("resumeTag");
        tabSpec.setIndicator("Резюме");
        tabSpec.setContent(R.id.answer);
        tabHost.addTab(tabSpec);
        
        // 1.2 первая вкладка ставится по умолчанию
        tabHost.setCurrentTabByTag("formTag");
    }  

    
    // Пикер для даты рождения
    public void onClickDialog(View view) {
        showDialog(DIALOG_DATE);
      }
      
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
          DatePickerDialog tpd = new DatePickerDialog(this, callBack, startYear, startMonth, startDay);
          return tpd;
        }
        return super.onCreateDialog(id);
     }
      
     OnDateSetListener callBack = new OnDateSetListener() {
    	 public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
    		 startYear = year;
    		 startMonth = monthOfYear;
    		 startDay = dayOfMonth;
    		 edDate.setText(startDay + "." + (startMonth+1) + "." + startYear);
    	 }
      };
     // Конец пикера
      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickbSendResume(View v) {
		Intent i = new Intent(this, Activity2.class);
		startActivity(i);
	}
    
}
