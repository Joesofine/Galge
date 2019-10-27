package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hovedemenu extends AppCompatActivity {

   Button ins, start, hjalp;
   TextView velkommen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hovedemenu);

        ins = findViewById(R.id.ins);
        start = findViewById(R.id.start);
        hjalp = findViewById(R.id.hjalp);
        velkommen = findViewById(R.id.textView);


        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Meme.class);
                startActivity(intent);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Spil.class);
                startActivity(intent);
            }
        });

        hjalp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hjalp.class);
                startActivity(intent);
            }
        });


    }
}
