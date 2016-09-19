package com.j2fx.auglogindriver;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * Login Activity
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.login);

        //Below to prevent network blocking error [credit to stackoverflow]
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        bLogin.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          System.out.println("Clicked Login " + etUsername.getText().toString());
                                          UserActive ua = new UserActive();
                                          ua.authenticateUser(etUsername.getText().toString(), etPassword.getText().toString());
                                          if (ua.getAuthenticated()) {
                                              System.out.println("USER AUTHENTICATED!");
                                              Intent loginIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                              startActivity(loginIntent);
                                              UpdateDriver.active(UserActive.d.getId());
                                          } else {
                                              System.out.println("INVALID USER!");
                                          }

                                          //Below clears username/password fields, have set a delay and run on seperate thread.
                                          Runnable mMyRunnable = new Runnable() {
                                              @Override
                                              public void run() {
                                                  etPassword.setText("");
                                                  etUsername.setText("");
                                                  etPassword.clearFocus();
                                              }
                                          };
                                          Handler myHandler = new Handler();
                                          myHandler.postDelayed(mMyRunnable, 1000);
                                      }



                                  }
        );

    }

}
