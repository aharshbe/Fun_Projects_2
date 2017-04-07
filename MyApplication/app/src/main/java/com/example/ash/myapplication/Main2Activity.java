package com.example.ash.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> answers_received ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        answers_received = getIntent().getStringArrayListExtra("SendingArrayOfFavorites");


        listView = (ListView) findViewById(R.id.list_of_favorites);


        arrayAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, answers_received);

        listView.setAdapter(arrayAdapter);
    }

    public void ClickingGoingBackToQuestions(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
