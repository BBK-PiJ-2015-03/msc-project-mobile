package com.j2fx.auglogindriver;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class UserJobOffer extends AppCompatActivity {
JobUpdater jobUpdater = new JobUpdater();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_job_offer);

        final TextView etUsername = (TextView) findViewById(R.id.etUsername);
        final TextView etClient = (TextView) findViewById(R.id.etClient);
        final TextView etPickup = (TextView) findViewById(R.id.etPickup);
        final TextView etDrop = (TextView) findViewById(R.id.etDrop);
        final TextView etDue = (TextView) findViewById(R.id.etDue);
        final TextView etNotes = (TextView) findViewById(R.id.etNotes);
        final TextView etPrice = (TextView) findViewById(R.id.etPrice);

        final Button bAccept = (Button) findViewById(R.id.bAccept);
        final Button bDecline = (Button) findViewById(R.id.bDecline); // will implement later

        Booking b = Booking.getInstance();
        etClient.setText("Client:  "+b.getClient());
        etPickup.setText("Pickup:  "+ b.getPickup());
        etDrop.setText("Drop:  "+b.getDrop());
        etDue.setText("Due:  "+b.getDue());
        etNotes.setText("Notes:  "+b.getNotes());
        etPrice.setText(b.getPrice());

        final MediaPlayer mMediaPlayer = MediaPlayer.create(UserJobOffer.this, R.raw.mtheme);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();



//        UpdateDriver.statusUpdate(.getText().toString()); - Maybe add another state, waiting for driver to accept/decline job
        etUsername.setText(UserActive.d.getName()); // Display name of user

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                acceptJob();
            }
        });
        bDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.stop();
                declineJob();
            }
        });

    }

    private void acceptJob(){
        jobUpdater.jobAccepted(Booking.getInstance().getBookingNumber());
        UpdateDriver.statusUpdate("Busy");
        this.finish();
        Intent onJob = new Intent(UserJobOffer.this,UserOnJob.class);
        onJob.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(onJob);
    }

    private void declineJob(){
        jobUpdater.jobDeclined(Booking.getInstance().getBookingNumber());
        this.finish();
        UpdateDriver.statusUpdate("Awaiting Job");
    }


}
