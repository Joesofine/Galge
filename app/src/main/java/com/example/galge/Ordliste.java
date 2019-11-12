package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Ordliste extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    Galgelogik logik = new Galgelogik();
    String ValgtOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordliste);

        ValgtOrd = "";

        listView = findViewById(R.id.listView1);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listeelement, R.id.listeelem_overskrift, logik.getMuligeOrd());

        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        listView.setSelector(R.color.colorMoonstoneLight);


    }
    public void onItemClick(AdapterView<?> liste, View v, int position, long id) {

        ValgtOrd = logik.setOrdet(logik.getMuligeOrd().get(position));

        Toast.makeText(this, logik.getOrdet(), Toast.LENGTH_SHORT).show();


        Bundle ordvalg = new Bundle();
        ordvalg.putString("Valgtord", ValgtOrd);
        Intent intent = new Intent(getApplicationContext(), Spil.class);
        intent.putExtras(ordvalg);
        startActivity(intent);
    }
}

