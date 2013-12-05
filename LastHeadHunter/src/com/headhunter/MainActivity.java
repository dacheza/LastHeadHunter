package com.headhunter;

import java.text.Format;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String FULL_NAME_KEY = "fullName";
	private static final String BIRTH_DATE_KEY = "birthDate";
	private static final String GENDER_KEY = "gender";
	private static final String POSITION_NAME_KEY = "positionName";
	private static final String SALARY_KEY = "salary";
	private static final String PHONE_KEY = "phone";
	private static final String EMAIL_KEY = "email";

	private static final int REQUESTCODE_RESPONSE = 100;
	private Bundle responseIntent;

	int DIALOG_DATE = 1;
	int startYear = 1995;
	int startMonth = 1;
	int startDay = 1;
	EditText edName;
	EditText edSurname;
	EditText edPatronymic;
	TextView edDate;
	EditText edPosition;
	EditText edSalary;
	Button btnClearFields;
	
	private Spinner spSexValue;
	private EditText edPhone;
	private EditText edEmail;
    
	private ImageView btnPlusEmail;
	private ImageView btnPlusPhone;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edName=(EditText)findViewById(R.id.name);
        edSurname=(EditText)findViewById(R.id.surname);
        edPatronymic=(EditText)findViewById(R.id.patronymic);
        edDate=(TextView)findViewById(R.id.birth);
        edPosition=(EditText)findViewById(R.id.position);
        edSalary=(EditText)findViewById(R.id.salary);
        edPhone=(EditText)findViewById(R.id.phone);
        edEmail=(EditText)findViewById(R.id.email);
        spSexValue=(Spinner)findViewById(R.id.spSexValue);
        btnClearFields=(Button)findViewById(R.id.bClearFields);
        
        // скроем кнопки, покуда не реализовали их обработку
        btnPlusEmail=(ImageView)findViewById(R.id.addEmailButton);
        btnPlusPhone=(ImageView)findViewById(R.id.addPhoneButton);
        btnPlusEmail.setVisibility(ImageView.GONE);
        btnPlusPhone.setVisibility(ImageView.GONE);
        
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
        tabHost.setCurrentTabByTag("formTag");
        
        // 1.1.2. вторая вкладка
        tabSpec = tabHost.newTabSpec("resumeTag");
        tabSpec.setIndicator("Резюме");
        tabSpec.setContent(R.id.answer);
        tabHost.addTab(tabSpec);
        
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
    		 edDate.setText(""+ (String.valueOf(startDay).length() < 2 ? "0"+startDay : startDay)+ "." + ((startMonth+1) < 10 ? "0"+(startMonth+1) : (startMonth+1)) + "." + startYear);
    	 }
      };
     // Конец пикера
      
      
    public void onClick(View v) {
    	    switch (v.getId()) {
    	    case R.id.bClearFields:
    	    	edName.getEditableText().clear();
    	    	edSurname.getEditableText().clear();
    	    	edPatronymic.getEditableText().clear();
    	    	edDate.getEditableText().clear();
    	    	edPosition.getEditableText().clear();
    	    	edSalary.getEditableText().clear();
    	    	edPhone.getEditableText().clear();
    	    	edEmail.getEditableText().clear();
    	    	// нужно ещё скинуть спиннер
    	      break;
    	    case R.id.bSendAnswerActivity2:
    	    	Intent intent = new Intent(MainActivity.this,Activity2.class);

    			intent.putExtra(FULL_NAME_KEY, edSurname.getText().toString()+" "+edName.getText().toString()+" "+edPatronymic.getText().toString());
    			intent.putExtra(BIRTH_DATE_KEY, edDate.getText().toString());
    			intent.putExtra(GENDER_KEY, spSexValue.getSelectedItem().toString());
    			intent.putExtra(POSITION_NAME_KEY, edPosition.getText().toString());
    			intent.putExtra(SALARY_KEY, edSalary.getText().toString());
    			intent.putExtra(PHONE_KEY, edPhone.getText().toString());
    			intent.putExtra(EMAIL_KEY, edEmail.getText().toString());

    			startActivityForResult(intent, REQUESTCODE_RESPONSE);   
    			break;
    	    }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, dataIntent);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUESTCODE_RESPONSE:
				responseIntent = dataIntent.getExtras();
					if (responseIntent.get(Activity2.RESPONSE_TEXT_KEY) != null) {
						AlertDialog.Builder adb = new AlertDialog.Builder(this)
						.setTitle(R.string.answerText).setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						})
						.setMessage(responseIntent.get(Activity2.RESPONSE_TEXT_KEY).toString()).setIcon(R.drawable.ic_launcher);
						AlertDialog di = adb.create();
						di.show();
					}
			
				break;

			default:
				break;
			}
		}

    }
}
