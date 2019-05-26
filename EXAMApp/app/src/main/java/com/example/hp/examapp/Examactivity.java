package com.example.hp.examapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Examactivity extends AppCompatActivity {
    private TextView textViewquest,textViewscore,textViewquestioncount,textViewctimer;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4,rb5;
    private Button confirm;

    private ColorStateList textColorDefaultrb;
    private ColorStateList textColorDfaultcd;
    private CountDownTimer countDownTimer;
    private  long timeleftinmillis;
    private List<Questions> questionsList;
    private  int Questioncounter;
    private  int questioncounttotal;
    private  Questions currentquestion;
    private static final long COUNTDOWN_MILLIS=30000;

    private int score;
    private boolean answeredquestion;
    private  long backpressedtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examactivity);
        textViewquest=(TextView)findViewById(R.id.textviewques);
        textViewscore=(TextView)findViewById(R.id.textscore);
        textViewquestioncount=(TextView)findViewById(R.id.textviewquestion);
        textViewctimer=(TextView)findViewById(R.id.timer);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        rb1=(RadioButton)findViewById(R.id.radiobutton1);
        rb2=(RadioButton)findViewById(R.id.radiobutton2);
        rb3=(RadioButton)findViewById(R.id.radiobutton3);
        rb4=(RadioButton)findViewById(R.id.radiobutton4);
        rb5=(RadioButton)findViewById(R.id.radiobutton5);
        confirm=(Button)findViewById(R.id.confirm);

        textColorDefaultrb=rb1.getTextColors();
        textColorDfaultcd=textViewctimer.getTextColors();
        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        questionsList=databaseHelper.getAllquestion();//database created here

        questioncounttotal=questionsList.size();
        Collections.shuffle(questionsList);//for getting questions in random order
        
        shownextquestion();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answeredquestion) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked() || rb5.isChecked())
                        checkanswer();

                    else {
                        Toast.makeText(Examactivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    shownextquestion();
                }
            }
        });

    }

    private void shownextquestion() {

        rb1.setTextColor(textColorDefaultrb);
        rb2.setTextColor(textColorDefaultrb);
        rb3.setTextColor(textColorDefaultrb);
        rb4.setTextColor(textColorDefaultrb);
        rb5.setTextColor(textColorDefaultrb);
        radioGroup.clearCheck();

        if(Questioncounter< questioncounttotal)
        {
            currentquestion=questionsList.get(Questioncounter);
            textViewquest.setText(currentquestion.getQuestion());
            rb1.setText(currentquestion.getChoice1());
            rb2.setText(currentquestion.getChoice2());
            rb3.setText(currentquestion.getChoice3());
            rb4.setText(currentquestion.getChoice4());
            rb5.setText(currentquestion.getChoice5());

            Questioncounter++;
            textViewquestioncount.setText("Question " + Questioncounter + "/" + questioncounttotal);
            answeredquestion=false;
            confirm.setText("confirmed");
            timeleftinmillis=COUNTDOWN_MILLIS;
            startcountdowntimer();
        }
        else
        {
            finishexam();
        }

    }
    private void startcountdowntimer()
    {
        countDownTimer=new CountDownTimer(timeleftinmillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeleftinmillis=millisUntilFinished;
                updateCountdowntext();
            }

            @Override
            public void onFinish() {
                timeleftinmillis=0;
                updateCountdowntext();
                shownextquestion();
                //checkanswer();

            }
        }.start();
    }

    private void updateCountdowntext()
    {
        int minutes= (int) ((timeleftinmillis /1000)/60);
        int seconds=(int) (timeleftinmillis/1000)%60;
        String timeformat=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        textViewctimer.setText(timeformat);

        if(timeleftinmillis<10000)
        {
            textViewctimer.setTextColor(Color.RED);
        }
        else
        {
            textViewctimer.setTextColor(textColorDfaultcd);
        }
    }
    private void checkanswer()
    {
        answeredquestion=true;
        countDownTimer.cancel();
        RadioButton rbselected=findViewById(radioGroup.getCheckedRadioButtonId());//whichever radio butoon is selected its id is stored in rbselected
        int answernr=radioGroup.indexOfChild(rbselected) + 1;
        if(answernr==currentquestion.getAnswernr())
        {
            score++;
            textViewscore.setText("Score: " + score);
        }
        showanswer();
    }
    private  void showanswer()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        rb5.setTextColor(Color.RED);

        switch (currentquestion.getAnswernr())
        {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewquest.setText("Answer 1 is the correct answer");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewquest.setText("Answer 2 is the correct answer");
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewquest.setText("Answer 3 is the correct answer");
                   break;

            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewquest.setText("Answer 4 is the correct answer");
                 break;

            case 5:
                rb5.setTextColor(Color.GREEN);
                textViewquest.setText("Answer 5 is the correct answer");
                break;
        }
        if(Questioncounter< questioncounttotal)
        {
            confirm.setText("Next");
        }
        else
        {
            confirm.setText("Finish");
        }
    }
    private void finishexam() {
        Intent resultintent=new Intent();
        resultintent.putExtra("EXTRA SCORE",score);
        setResult(RESULT_OK,resultintent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if(backpressedtime +2000 >System.currentTimeMillis())
        {
            finishexam();
        }
        else
        {
            Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backpressedtime=System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }
    }
}
