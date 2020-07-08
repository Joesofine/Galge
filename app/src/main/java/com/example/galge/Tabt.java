package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tabt extends AppCompatActivity {

    Button again;
    TextView ord;
    MediaPlayer lyd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabt);


        again = findViewById(R.id.again);
        ord = findViewById(R.id.ord);
        lyd = MediaPlayer.create(this, R.raw.boo);
        lyd.setVolume(1,1);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int fuldStyrke = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int aktuelStyrke = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (aktuelStyrke < fuldStyrke / 5) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, fuldStyrke / 5, AudioManager.FLAG_SHOW_UI);
        }


        Intent intent = getIntent();
        Bundle whatever = intent.getExtras();


        ord.setText((whatever.getString("ord")));

        lyd.start();


        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hovedemenu.class);
                startActivity(intent);
                lyd.stop();
                Spil.logik.nulstil();
            }
        });
    }
    @Override
    public void onBackPressed(){
        lyd.stop();
        Spil.logik.nulstil();
        finish();
    }
}