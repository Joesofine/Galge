package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class Vundet extends AppCompatActivity {

    TextView forkert, score;
    EditText name;
    Button tilbage, high;
    MediaPlayer lyd;
    String ordet, forkerteBogstaver;
    ArrayList<userObj> highArr = new ArrayList<>();
    user_obj user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vundet);

        forkert = findViewById(R.id.forkert);
        score = findViewById(R.id.score);
        name = findViewById(R.id.name);
        tilbage = findViewById(R.id.tilbage);
        high = findViewById(R.id.high);
        lyd = MediaPlayer.create(this, R.raw.cher);
        lyd.setVolume(1,1);
        user = user_obj.getInstance();

        highArr = user.getHighArr(this);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int fuldStyrke = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int aktuelStyrke = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (aktuelStyrke < fuldStyrke / 5) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, fuldStyrke / 5, AudioManager.FLAG_SHOW_UI);
        }

        Intent intent = getIntent();
        Bundle fejl = intent.getExtras();
        Bundle whatever = intent.getExtras();

        ordet = whatever.getString("ord");
        forkerteBogstaver = fejl.getString("forkert");

        Spil.logik.calculateScore();
        forkert.setText(forkerteBogstaver);
        score.setText(Spil.logik.getScoreString());


        lyd.start();


        tilbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hovedemenu.class);
                startActivity(intent);
                lyd.stop();
                Spil.logik.nulstil();
                finish();
            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(name.getText()).equals("")){
                    name.setError("Please write your name to save highscore");

                } else {
                    highArr.add(new userObj(String.valueOf(name.getText()), Spil.logik.getScoreInt()));
                    user.setHighArr(highArr,getApplicationContext());
                    Intent intent = new Intent(getApplicationContext(), Highscore.class);
                    startActivity(intent);
                    lyd.stop();
                    Spil.logik.nulstil();
                    finish();
                }
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
