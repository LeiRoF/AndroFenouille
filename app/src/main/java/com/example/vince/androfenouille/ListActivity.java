package com.example.vince.androfenouille;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<String> myArray;
    private ListView myList;
    private ArrayAdapter<String> myAdapter;
    Download fille = new Download();
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myList = (ListView) findViewById(R.id.listUserView);
        myArray = new ArrayList<String>();
    }

    public void rechercheList(View v) {
        new Thread(new Runnable() {

            public void run() {
               json = fille.DownloadJson("http://infort.gautero.fr/listEtu.php");
            }
        }).start();
        if ( json != null) {
            User.addUser(json);
            myArray.add("first test");
            for (int cpt = 0; cpt < User.userList.size(); cpt++) {
                myArray.add(User.userList.get(cpt).toString());
            }
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray);
            myList.setAdapter(myAdapter);
        }
    }

    public void ajoutUser(View v){
        Intent intent = new Intent(this, ajoutActivity.class);
        startActivity(intent);
    }

}
