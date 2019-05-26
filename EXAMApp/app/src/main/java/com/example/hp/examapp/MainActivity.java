package com.example.hp.examapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   private  static final int  REQUEST_CODE_EXAM=1;
    Button b,blogin;
    public static final String SHARED_PREFERENCES="shared_preferences";
    public static final String Key_highscore="key_highscore";

    private TextView textViewhighscore;
    private  int Highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewhighscore=(TextView)findViewById(R.id.textview_highscore);
        loadHighscore();
        b=findViewById(R.id.demob);
        blogin=findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Examactivity.class);
                startActivityForResult(intent,REQUEST_CODE_EXAM);
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,Login.class);
                startActivity(in);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_EXAM)
        {
            if(resultCode==RESULT_OK)
            {
                int score=data.getIntExtra("EXTRA SCORE",0);
                if(score>Highscore)
                    updaateHighscore(score);
            }
        }
    }

    private  void loadHighscore()
    {
        SharedPreferences sh=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        Highscore=sh.getInt(Key_highscore,0);
        textViewhighscore.setText("Highscore  :" + Highscore);


    }

    private void updaateHighscore(int highscorenew) {

        Highscore=highscorenew;
        textViewhighscore.setText("Highscore  :" + Highscore);

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(Key_highscore,Highscore);
        editor.apply();


    }
}
