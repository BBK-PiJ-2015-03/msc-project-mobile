package com.j2fx.auglogindriver;

import android.content.Intent;
import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_user_area);

        final TextView etUsername = (TextView) findViewById(R.id.etUsername);
        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final TextView tvStatus = (TextView) findViewById(R.id.tvStatus);
        final Button bSignout = (Button) findViewById(R.id.bAccept);
        final Button bDistress = (Button) findViewById(R.id.bDecline);
        final Switch activeSwitch = (Switch) findViewById(R.id.activeSwitch);


        UpdateDriver.statusUpdate(tvStatus.getText().toString());
        etUsername.setText(UserActive.d.getName()); // Display name of user
        listenForJobs();
        activeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activeSwitch.setText("Available");
                    UpdateDriver.statusUpdate("Awaiting Job");
                    tvStatus.setText("Awaiting Job");
                } else {
                    activeSwitch.setText("Unavailable");
                    UpdateDriver.statusUpdate("Break");
                    tvStatus.setText("On Break");
                }
            }
        });



        bSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut(){
        UpdateDriver.statusUpdate("offline");
        UpdateDriver.inactive(UserActive.d.getId());
        UserActive.clearDriver();
        this.finish();
    }

    private void listenForJobs(){
        JobListener.start(this);
    }

    private void jobOffer(){
        Intent loginIntent = new Intent(UserAreaActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }


}
