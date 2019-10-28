package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Ordliste extends AppCompatActivity {

    ListView listView;
    Galgelogik logik = new Galgelogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordliste);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listeelement, R.id.listeelem_overskrift, logik.getMuligeOrd());

        listView = findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }
    public void onItemClick(AdapterView<?> liste, View v, int position, long id) {
        Toast.makeText(this, "Klik på " + position, Toast.LENGTH_SHORT).show();
    }
}

