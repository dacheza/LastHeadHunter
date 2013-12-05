package com.headhunter;

import com.headhunter.R;

import com.headhunter.EmployerMainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class EmployerMainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
	public static final String RESPONSE_TEXT_KEY = "answer"; 
	
	private static final String FULL_NAME_KEY = "fullName";
	private static final String BIRTH_DATE_KEY = "birthDate";
	private static final String GENDER_KEY = "gender";
	private static final String POSITION_NAME_KEY = "positionName";
	private static final String SALARY_KEY = "salary";
	private static final String PHONE_KEY = "phone";
	private static final String EMAIL_KEY = "email";

	TextView tvFIOValueActivity2;
    TextView tvBirthdayValueActivity2;
    TextView tvSexValueActivity2;
    TextView tvPositionValueActivity2;
    TextView tvPayValueActivity2;
    TextView tvPhoneNumberValueActivity2;
    TextView tvEmailValueActivity2;

    Button bSendAnswerActivity2;
    private EditText edAnswer;
    private Bundle resume;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        tvFIOValueActivity2 = (TextView)findViewById(R.id.tvFIOValueActivity2);
        tvBirthdayValueActivity2 = (TextView)findViewById(R.id.tvBirthdayValueActivity2);
        tvSexValueActivity2 = (TextView)findViewById(R.id.tvSexValueActivity2);
        tvPositionValueActivity2 = (TextView)findViewById(R.id.tvPositionValueActivity2);
        tvPayValueActivity2 = (TextView)findViewById(R.id.tvPayValueActivity2);
        tvPhoneNumberValueActivity2 = (TextView)findViewById(R.id.tvPhoneNumberValueActivity2);
        tvEmailValueActivity2= (TextView)findViewById(R.id.tvEmailValueActivity2);
        
        resume = getIntent().getExtras();
        tvFIOValueActivity2.setText(resume.getString(FULL_NAME_KEY));
        tvBirthdayValueActivity2.setText(resume.getString(BIRTH_DATE_KEY));
        tvSexValueActivity2.setText(resume.getString(GENDER_KEY));
        tvPositionValueActivity2.setText(resume.getString(POSITION_NAME_KEY));
        tvPayValueActivity2.setText(resume.getString(SALARY_KEY));
        tvPhoneNumberValueActivity2.setText(resume.getString(PHONE_KEY));
        tvEmailValueActivity2.setText(resume.getString(EMAIL_KEY));
        
        edAnswer = (EditText)findViewById(R.id.edAnswerActivity2);
        bSendAnswerActivity2 = (Button)findViewById(R.id.bSendAnswerActivity2);
        // смена шрифта
        TextView tvFIOActivity2 = (TextView) findViewById(R.id.tvFIOActivity2);
        Typeface digitalFont = Typeface.createFromAsset(this.getAssets(), "fonts/arial.ttf");
        tvFIOActivity2.setTypeface(digitalFont);

    }

    public void onClickbSendAnswer(View v) {
        Toast.makeText(this, "Сообщение отправлено", Toast.LENGTH_LONG).show();
        Intent dataIntent = new Intent();
        dataIntent.putExtra(RESPONSE_TEXT_KEY, edAnswer.getText().toString());
        setResult(RESULT_OK, dataIntent);
        finish();
      /*  Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);*/
    }

    public void onClickPhone(View v) {
        String strPhoneNum = tvPhoneNumberValueActivity2.getText().toString();
        Toast.makeText(this, strPhoneNum, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strPhoneNum));
        startActivity(intent);
    }

    public void onClickEmail(View v) {
        /*TODO СНАЧАЛА ПРОВЕРЕЯМ ЗАПУСТИТЬСЯ ЛИ ЕMAIL , ЕСЛИ НЕТ -ВЫВОДИМ СООБЩЕНИЕ НА ВАШЕМ ТЕЛЕФОНЕ НЕ НАСТРОЕНО ПОДК
        * TODO ЛЮЧЕНИЕ К EMAIL  ПЕРЕЙТИ К НАСТРОЙКЕ? И ЕСЛИ ПОЛЬЗОВАТЕЛЬ НАЖИМАЕТ ДА - ТО ЗАПУСКАЕМ ПРИЛОЖЕНИЕ*/

       /* Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.email");
        startActivity(intent);*/

      /*  String strEmai = tvEmailValueActivity2.getText().toString();
        Toast.makeText(this, strEmai, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_SENDTO);    //emailIntent

        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { strEmai });   // Кому
        intent.putExtra(Intent.EXTRA_SUBJECT, "ТЕМА ПИСЬМА");
        intent.putExtra(Intent.EXTRA_TEXT, "ТЕКСТ ПИСЬМА");

        EmployerMainActivity.this.startActivity(Intent.createChooser(intent, "Отправка письма..."));*/
        /*startActivity(intent);*/



         //ТОЖЕ С ФОРУМА СПОСОБ КОТОРЫЙ ПРОСТО ДОЛЖЕН КЛИЕНТ ЗАПУСКАТЬ
     /*   Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.setClassName("com.android.email", "com.android.email.activity.Welcome");
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(emailIntent);*/



        String strEmai = tvEmailValueActivity2.getText().toString();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{strEmai});
       /* i.putExtra(Intent.EXTRA_SUBJECT, "Тема письма");
        i.putExtra(Intent.EXTRA_TEXT   , "Тело письма");*/
        try {
            startActivity(Intent.createChooser(i, "Послать e-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmployerMainActivity.this, "В вашей системе не найдено почтовых клиентов", Toast.LENGTH_SHORT).show();
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.email");
            startActivity(intent);
        }


       /* // С чем
        emailIntent.putExtra(
                android.content.Intent.EXTRA_STREAM,
                Uri.parse("file://"
                        + Environment.getExternalStorageDirectory()
                        + "/Клипы/SOTY_ATHD.mp4"));

        emailIntent.setType("text/video");  */
        // Поехали!
        /*SimpleEMail.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка письма...")); */
    }

    public void onClickImage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        /*ImageView ivFace = (ImageView)findViewById(R.id.ivLittleFace);*/
        /*ImageView ivFace = new ImageView(this);
        ivFace.setImageResource(R.drawable.face_activity2);*/
        /*ivFace.setMinimumHeight(LayoutParams.MATCH_PARENT);
        ivFace.setMinimumWidth(LayoutParams.MATCH_PARENT);*/

        builder.setView(getLayoutInflater().inflate(R.layout.activity2_big_face, null));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}