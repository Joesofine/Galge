package com.example.galge;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.util.Base64;
import com.example.galge.userObj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class user_obj {
    private static final user_obj ourInstance = new user_obj();
    private ArrayList<userObj> highArr = new ArrayList<>();
    public static user_obj getInstance() {
        return ourInstance;
    }

    private user_obj() {
    }

    public void setHighArr(ArrayList<userObj> arr, Context context) {
        saveUser(context, arr);
    }


    public ArrayList<userObj> getHighArr(Context context) {
        highArr.clear();
        loadUser(context);
        return highArr;
    }

    private void loadUser(Context context) {
        SharedPreferences sharedPreferences =  context.getSharedPreferences("Users", Context.MODE_PRIVATE);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("HighArr", null);
        Type type = new TypeToken<ArrayList<userObj>>() {
        }.getType();
        highArr = gson.fromJson(json, type);
        if (highArr == null){
            highArr = new ArrayList<>();
        }
    }




    private void saveUser(Context context, ArrayList<userObj> arr){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        editor.putString("HighArr",json);
        editor.apply();
    }

}
