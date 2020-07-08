package com.example.galge;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Highscore extends AppCompatActivity implements AdapterView.OnItemClickListener{

    FloatingActionButton done;
    TextView rankings;
    ListView list;
    static user_obj user;
    static ArrayList<userObj> highArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        list = findViewById(R.id.list);
        rankings = findViewById(R.id.rankings);
        done = findViewById(R.id.done);
        user = user_obj.getInstance();
        highArr = user.getHighArr(getApplicationContext());
        list.setOnItemClickListener(this);
        list.setSelector(R.color.colorMoonstoneLight);
        sortHighScore();

        //Printer un arrayet hvis det indeholder noget
        if(highArr.size() != 0) {
            adapter_user adaptor = new adapter_user(getApplicationContext(), highArr);
            list.setAdapter(adaptor);
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hovedemenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Sorterer highscore listen fra højest til lavest
    private ArrayList<userObj> sortHighScore(){
        Collections.sort(highArr, new Comparator< userObj >() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override public int compare(userObj p1, userObj p2) {
                return Integer.compare(p1.score,p2.score);
            }
        });
        Collections.reverse(highArr);
        return highArr;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onBackPressed(){
        Spil.logik.nulstil();
        finish();
    }
}
