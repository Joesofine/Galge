package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Niveau extends AppCompatActivity {

    Button dr, ark, et, to, tre;
    TextView info;
    Galgelogik logik = new Galgelogik();
    ListView list;
    String sværhedgrad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveau);

        info = findViewById(R.id.info);
        dr = findViewById(R.id.dr);
        ark = findViewById(R.id.et);
        list = findViewById(R.id.list);
        et = findViewById(R.id.et);
        to = findViewById(R.id.to);
        tre = findViewById(R.id.tre);

        list.setSelector(R.color.colorMoonstoneLight);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listeelement,
                R.id.listeelem_overskrift, logik.getMuligeOrd());
        list.setAdapter(adapter);


        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra regneark...");
                new AsyncTask(){
                    protected Object doInBackground(Object... arg0) {
                        try {
                            logik.hentOrdFraRegneark("1");
                            adapter.notifyDataSetChanged();
                            return "Ordene blev korrekt hentet fra regneark";

                        } catch (Exception e) {
                            e.printStackTrace();
                            return"Ordene blev ikke hentet korrekt: ";
                        }
                    }
                    @Override
                    protected void onPostExecute(Object resultat) {
                        info.setText("resultat:");
                    }
                }.execute();            }
        });


        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra regneark...");
                new AsyncTask(){
                    protected Object doInBackground(Object... arg0) {
                        try {
                            logik.hentOrdFraRegneark("2");
                            adapter.notifyDataSetChanged();
                            return "Ordene blev korrekt hentet fra regneark";

                        } catch (Exception e) {
                            e.printStackTrace();
                            return"Ordene blev ikke hentet korrekt: ";
                        }
                    }
                    @Override
                    protected void onPostExecute(Object resultat) {
                        info.setText("resultat:");
                    }
                }.execute();
            }
        });

        tre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra regneark...");
                new AsyncTask(){
                    protected Object doInBackground(Object... arg0) {
                        try {
                            logik.hentOrdFraRegneark("3");
                            adapter.notifyDataSetChanged();
                            return "Ordene blev korrekt hentet fra regneark";

                        } catch (Exception e) {
                            e.printStackTrace();
                            return"Ordene blev ikke hentet korrekt: ";
                        }
                    }
                    @Override
                    protected void onPostExecute(Object resultat) {
                        info.setText("resultat:");
                    }
                }.execute();
            }
        });

        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra DRs server....");
                new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object... arg0) {
                        try {
                            logik.hentOrdFraDr();
                            adapter.notifyDataSetChanged();
                            return "Ordene blev korrekt hentet fra DR's server";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return "Ordene blev ikke hentet korrekt: "+e;
                        }
                    }

                    @Override
                    protected void onPostExecute(Object resultat) {
                        info.setText("resultat:");
                    }
                }.execute();
            }
        });
    }
}
