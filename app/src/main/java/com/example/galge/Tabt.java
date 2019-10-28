package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tabt extends AppCompatActivity {

    Button again;
    TextView ord;

    Galgelogik logik = new Galgelogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabt);


        again = findViewById(R.id.again);
        ord = findViewById(R.id.ord);


        Intent intent = getIntent();
        Bundle whatever = intent.getExtras();


        ord.setText((whatever.getString("ord")));

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Spil.class);
                startActivity(intent);
            }
        });
    }
}