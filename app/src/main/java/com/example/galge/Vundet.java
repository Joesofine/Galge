package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vundet extends AppCompatActivity {

    TextView forkert;
    Button again, tilbage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vundet);

        forkert = findViewById(R.id.forkert);
        again = findViewById(R.id.again);
        tilbage = findViewById(R.id.tilbage);

        Intent intent = getIntent();
        Bundle fejl = intent.getExtras();

        forkert.setText(fejl.getString("forkert")+"!");

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Spil.class);
                startActivity(intent);
            }
        });

        tilbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hovedemenu.class);
                startActivity(intent);
            }
        });

    }
}
