package com.example.hp.examapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

//import com.example.vidhit.evoletandroid.R;

public class Login extends AppCompatActivity {

    private EditText myemailEDT, myPasswordEDT;
    private Button mySigninBT, mySignUpBt, myforgotp;
    private TextView mforgot;
    LoginButton login_button;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            System.out.println("exception ");
        }
        intialization();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent start=new Intent(Login.this,MainActivity.class);
                startActivity(start);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void intialization() {

        myemailEDT = (EditText) findViewById(R.id.activity_main_email_edt);
        myPasswordEDT = (EditText) findViewById(R.id.activity_main_pwd_edt);
        mySigninBT = (Button) findViewById(R.id.actvity_main_Signin_btn);
        mforgot = (TextView) findViewById(R.id.fp);
        login_button=(LoginButton)findViewById(R.id.login_button);
        callbackManager=callbackManager = CallbackManager.Factory.create();

        //mySignUpBt=(Button)findViewById(R.id.button2);
        //myforgotp=(Button)findViewById(R.id.button);
       /* FloatingActionButton fab = findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aIntents = new Intent(Login.this, Sign_Up.class);
                startActivity(aIntents);

            }
        });*/

        clickListener();


    }

    private void clickListener() {

/*

                 Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

               String aPwdSTR = myPasswordEDT.getText().toString();

                if (!aPwdSTR.equals(""))

                {

                    Toast.makeText(getApplicationContext(), aPwdSTR, Toast.LENGTH_SHORT).show();

                }


                Intent aIntent = new Intent(getApplicationContext(), SecondActivity.class);
                Bundle aBundle = new Bundle();

                aBundle.putString("KEY", "VALUE");

                aIntent.putExtra("KEY", "Email");

                aIntent.putExtra("KEY1", "PWD");


                startActivity(aIntent);

*/
      /*  mforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aIntent1 = new Intent(Login.this, forgotpass.class);
                startActivity(aIntent1);

            }

        });*/

        mySigninBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* String user = myemailEDT.getText().toString();
                String pwd = myPasswordEDT.getText().toString();
                if (user.equals("")) {
                    //System.out.println("Please enter username");
                    Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_SHORT).show();

                }
                else if (pwd.equals("")) {

                    //System.out.println("Please enter password");
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }*/

               /* Intent aIntent = new Intent(Login.this, QUESTI.class);
                //aIntent.putExtra("KEY1", user);
                //aIntent.putExtra("KEY2", pwd);
                startActivity(aIntent);

            }
        });*/
      /*  mySignUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = myemailEDT.getText().toString();
                String pwd = myPasswordEDT.getText().toString();
                if (user.equals("")) {
                    //System.out.println("Please enter username");
                    Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_SHORT).show();

                }
                else if (pwd.equals("")) {

                    //System.out.println("Please enter password");
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent aIntents = new Intent(Login.this, signupapge.class);
                    startActivity(aIntents);
                }

            }
        });


        myforgotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = myemailEDT.getText().toString();
                String pwd = myPasswordEDT.getText().toString();

                if (user.equals("")) {
                    //System.out.println("Please enter username");
                    Toast.makeText(Login.this, "Please enter username", Toast.LENGTH_SHORT).show();

                }
                else if (pwd.equals("")) {

                    //System.out.println("Please enter password");
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent aIntents = new Intent(Login.this, forgotpass.class);
                    startActivity(aIntents);
                }

            }
        });*/


            }

            boolean doubleBackToExitPressedOnce = false;

   /* @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Are you sure you want to exit (Yes/No)?", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }*/

         /*   @Override
            public void onBackPressed() {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Closing Activity")
                        .setMessage("Are you sure you want to exit(Yes/No)?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }*/

        });

    }
}



