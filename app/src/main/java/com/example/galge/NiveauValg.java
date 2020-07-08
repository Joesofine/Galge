package com.example.galge;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class NiveauValg extends AppCompatActivity {

    Button dr, ark, et, to, tre, array, liste;
    TextView info;
    Galgelogik logik = new Galgelogik();
    String ValgtOrd, RanOrd, one = "one", two = "two", three = "three", web = "web";
    private ArrayList<String> ordliste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveauvalg);

        info = findViewById(R.id.info);
        dr = findViewById(R.id.dr);
        ark = findViewById(R.id.et);
        et = findViewById(R.id.et);
        to = findViewById(R.id.to);
        tre = findViewById(R.id.tre);
        array = findViewById(R.id.array);
        liste = findViewById(R.id.tekst);




        array.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra array...");
                logik.hentOrdFraArray();
                gemOrd();
            }
        });

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra regneark...");
                hentOrd(one);
            }
        });


        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra regneark...");
                hentOrd(two);
            }
        });

        tre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra regneark...");
                hentOrd(three);
            }
        });

        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra DRs server....");
                hentOrd(web);
            }
        });

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Niveau.class);
                startActivity(intent);
            }
        });

    }

    public void gemOrd () {
        ordliste = logik.getMuligeOrd();
        RanOrd = ordliste.get(new Random().nextInt(ordliste.size()));
        ValgtOrd = logik.setOrdet(RanOrd);

        //Printer en toast med ordet der skal gættes - bruges til at teste appen. Har valgt at lade den feature blive.
        Toast.makeText(getApplicationContext(), logik.getOrdet(), Toast.LENGTH_SHORT).show();

        Bundle ordvalg = new Bundle();
        ordvalg.putString("Valgtord", ValgtOrd);
        Intent intent = new Intent(getApplicationContext(), Spil.class);
        intent.putExtras(ordvalg);
        startActivity(intent);
    }

    public void hentOrd (final String from){
        new AsyncTask(){
            protected Object doInBackground(Object... arg0) {
                try {
                    if (from.equals(one)){
                        logik.hentOrdFraRegneark("1");

                    } else if (from.equals(two)){
                        logik.hentOrdFraRegneark("2");

                    }else if (from.equals(three)){
                        logik.hentOrdFraRegneark("3");

                    }else if (from.equals(web)){
                        logik.hentOrdFraDr();
                    }

                    return "Ordene blev hentet";

                } catch (Exception e) {
                    e.printStackTrace();
                    return"Ordene blev ikke hentet";
                }
            }
            @Override
            protected void onPostExecute(Object resultat) {
                gemOrd();
                info.setText("Færdig:\n" + resultat );
            }
        }.execute();
    }
}
