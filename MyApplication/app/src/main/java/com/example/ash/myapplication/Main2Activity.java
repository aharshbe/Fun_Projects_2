package com.example.ash.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter, arrayAdapter_sharedPreferences;
    ArrayList<String> answers_received, answers_received_shared_prefs;
    SharedPreferences pref_answers;
    SharedPreferences.Editor editor_answers;
    Set<String> set_for_answers, new_set_shared_prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.list_of_favorites);
        set_for_answers = new HashSet<>();
        pref_answers = getApplicationContext().getSharedPreferences("MyPref_answers", MODE_PRIVATE);
        editor_answers = pref_answers.edit();


        Intent i = getIntent();
        answers_received = i.getStringArrayListExtra("sending");

        arrayAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, answers_received);

        listView.setAdapter(arrayAdapter);


        set_for_answers.addAll(answers_received);
        editor_answers.putStringSet("list_of_answesr", set_for_answers);
        editor_answers.apply();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("MyPref", true)) {

            new_set_shared_prefs = pref_answers.getStringSet("list_of_answesr", null);
            answers_received.addAll(new_set_shared_prefs);

            arrayAdapter_sharedPreferences = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, answers_received);

            listView.setAdapter(arrayAdapter_sharedPreferences);

        }










    }

    public void ClickingGoingBackToQuestions(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
