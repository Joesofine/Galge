package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Spil extends AppCompatActivity {

    static Galgelogik logik = new Galgelogik();
    Button test;
    TextView regler, ord;
    EditText in;
    ImageView galge;
    int i = 0;
    String ordet, forkerte, valgtord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spil);

        test = findViewById(R.id.test);
        regler = findViewById(R.id.regler);
        ord = findViewById(R.id.ord);
        galge = findViewById(R.id.galge);
        in = findViewById(R.id.in);

        Intent intent = getIntent();
        final Bundle ordvalg = intent.getExtras();

        ordet = ordvalg.getString("Valgtord");
        logik.setOrdet(ordet);
        logik.setSynligtOrd(ordet);
        ord.setText(logik.getSynligtOrd());

        logik.logStatus(); // Så vi kan se det rigtige ord i loggen


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bogstav = in.getText().toString();
                logik.gætBogstav(bogstav, ordet);
                in.setText("");
                in.setError(null);
                if (!logik.erSidsteBogstavKorrekt()) {
                    i = i + 1;

                    if (i == 1)
                        galge.setImageResource(R.drawable.forkert1);
                    else if (i == 2)
                        galge.setImageResource(R.drawable.forkert2);
                    else if (i == 3)
                        galge.setImageResource(R.drawable.forkert3);
                    else if (i == 4)
                        galge.setImageResource(R.drawable.forkert4);
                    else if (i == 5)
                        galge.setImageResource(R.drawable.forkert5);
                    else if (i == 6)
                        galge.setImageResource(R.drawable.forkert6);
                }
                logik.setSynligtOrd(ordet);
                ord.setText(logik.getSynligtOrd());
                opdaterSkærm();
            }
        });

    }

    private void opdaterSkærm() {
        regler.setText("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if (logik.erSpilletVundet()) {
            forkerte = String.valueOf(logik.getAntalForkerteBogstaver());

            Bundle fejl = new Bundle();
            fejl.putString("forkert", forkerte);
            Intent intent = new Intent(this, Vundet.class);
            intent.putExtras(fejl);
            startActivity(intent);
            finish();

        }
        if (logik.erSpilletTabt()) {
            Bundle whatever = new Bundle();
            whatever.putString("ord", ordet);
            Intent intent = new Intent(this, Tabt.class);
            intent.putExtras(whatever);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onBackPressed(){
        Spil.logik.nulstil();
        finish();
    }
}


