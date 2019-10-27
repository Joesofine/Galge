package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hjalp extends AppCompatActivity {

    Button done;
    TextView help, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hjalp);


        done = findViewById(R.id.done);
        help = findViewById(R.id.help);
        text = findViewById(R.id.text);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Beklager! Prøve igen en anden gang! Update følger.");
                //Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                //startActivity(intent);
            }
        });
    }
}