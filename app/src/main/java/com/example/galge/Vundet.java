package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.media.MediaPlayer.*;

public class Vundet extends AppCompatActivity {

    TextView forkert;
    Button tilbage;
    MediaPlayer lyd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vundet);

        forkert = findViewById(R.id.forkert);
        tilbage = findViewById(R.id.tilbage);
        lyd = MediaPlayer.create(this, R.raw.cher);
        lyd.setVolume(1,1);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int fuldStyrke = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int aktuelStyrke = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (aktuelStyrke < fuldStyrke / 5) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, fuldStyrke / 5, AudioManager.FLAG_SHOW_UI);
        }

        Intent intent = getIntent();
        Bundle fejl = intent.getExtras();

        forkert.setText(fejl.getString("forkert")+"!");

        lyd.start();

        tilbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hovedemenu.class);
                startActivity(intent);
                lyd.stop();
            }
        });

    }
}
