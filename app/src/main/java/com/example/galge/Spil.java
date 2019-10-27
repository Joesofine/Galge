package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Spil extends AppCompatActivity {

    Galgelogik logik = new Galgelogik();
    Button test;
    TextView regler, ord;
    EditText in;
    int i = 0;
    ImageView galge;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spil);

        test = findViewById(R.id.test);
        regler = findViewById(R.id.regler);
        ord = findViewById(R.id.ord);
        galge = findViewById(R.id.galge);
        in = findViewById(R.id.in);

        ord.setText(logik.getSynligtOrd());

        logik.logStatus(); // Så vi kan se det rigtige ord i loggen

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bogstav = in.getText().toString();
                logik.gætBogstav(bogstav);
                in.setText("");
                in.setError(null);
                ord.setText(logik.getSynligtOrd());

                if (!logik.erSidsteBogstavKorrekt()) {
                    i = i + 1;

                    if (i==1)
                        galge.setImageResource(R.drawable.forkert1);
                    else if (i==2)
                        galge.setImageResource(R.drawable.forkert2);
                    else if (i==3)
                        galge.setImageResource(R.drawable.forkert3);
                    else if (i==4)
                        galge.setImageResource(R.drawable.forkert4);
                    else if (i==5)
                        galge.setImageResource(R.drawable.forkert5);
                    else if (i==6)
                        galge.setImageResource(R.drawable.forkert6);
                }

                opdaterSkærm();
            }
        });

    }

    private void opdaterSkærm() {
        regler.setText("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if (logik.erSpilletVundet()) {
            regler.append("\nDu har vundet");
        }
        if (logik.erSpilletTabt()) {
            regler.setText("Du har tabt, ordet var : " + logik.getOrdet());
        }

    }
}

