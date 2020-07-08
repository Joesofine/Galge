package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Niveau extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Galgelogik logik = new Galgelogik();
    Button dr, ark, et, to, tre, array;
    TextView info;
    ListView list;
    String ValgtOrd, one = "one ", two = "two" , three = "three", web = "web";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveau);

        info = findViewById(R.id.title);
        dr = findViewById(R.id.dr);
        list = findViewById(R.id.list);
        et = findViewById(R.id.et);
        to = findViewById(R.id.to);
        tre = findViewById(R.id.tre);
        array = findViewById(R.id.array);

        ValgtOrd = "";

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listeelement,
                R.id.listeelem_overskrift, logik.getMuligeOrd());

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        list.setSelector(R.color.colorMoonstoneLight);



        array.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra array...");
                logik.hentOrdFraArray();
                adapter.notifyDataSetChanged();
                info.setText("Færdig: Succes!");
            }
        });

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra regneark...");
                hentOrd(one, adapter);
            }
        });


        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Henter ord fra regneark...");
                hentOrd(two,adapter);
            }
        });

        tre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra regneark...");
                hentOrd(three,adapter);
            }
        });

        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                info.setText("Henter ord fra DRs server....");
                hentOrd(web,adapter);
            }
        });
    }
    public void onItemClick(AdapterView<?> liste, View v, int position, long id) {

        ValgtOrd = logik.setOrdet(logik.getMuligeOrd().get(position));

        //Printer en toast med ordet der skal gættes - bruges til at teste appen. Har valgt at lade den feature blive.
        Toast.makeText(this, logik.getOrdet(), Toast.LENGTH_SHORT).show();

        Bundle ordvalg = new Bundle();
        ordvalg.putString("Valgtord", ValgtOrd);
        Intent intent = new Intent(getApplicationContext(), Spil.class);
        intent.putExtras(ordvalg);
        startActivity(intent);
    }

    public void hentOrd (final String from, final ArrayAdapter adapter){
        new AsyncTask(){
            protected Object doInBackground(Object... arg0) {
                try {
                    if (from.equals(one)){
                        logik.hentOrdFraRegneark("1");
                    } else if (from.equals(two)){
                        logik.hentOrdFraRegneark("2");
                    } else if (from.equals(three)){
                        logik.hentOrdFraRegneark("3");
                    } else if (from.equals(web)){
                        logik.hentOrdFraDr();
                    }

                    adapter.notifyDataSetChanged();
                    return "Succes!";

                } catch (Exception e) {
                    e.printStackTrace();
                    return"Fejl!";
                }
            }
            @Override
            protected void onPostExecute(Object resultat) {
                info.setText("Færdig!" );
            }
        }.execute();
    }
}
