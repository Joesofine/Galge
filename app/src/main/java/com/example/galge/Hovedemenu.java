package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hovedemenu extends AppCompatActivity {

   Button ins, niveau,valg;
   TextView velkommen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hovedemenu);

        ins = findViewById(R.id.ins);
        velkommen = findViewById(R.id.textView);
        niveau =findViewById(R.id.niveau);
        valg = findViewById(R.id.valg);


        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Meme.class);
                startActivity(intent);
            }
        });


        niveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Niveau.class);
                startActivity(intent);
            }
        });
        valg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NiveauValg.class);
                startActivity(intent);
            }
        });
    }
}
