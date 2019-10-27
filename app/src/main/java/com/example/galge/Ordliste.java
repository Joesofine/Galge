package com.example.galge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Ordliste extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordliste);

        String[] lande = { "Danmark", "Norge", "Sverige"};
        listView = findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  android.R.id.text1, lande ));

    }


}
