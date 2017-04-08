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

//        pref_answers = getApplicationContext().getSharedPreferences("MyPref_answers", MODE_PRIVATE);
//        editor_answers = pref_answers.edit();


        Intent i = getIntent();
        answers_received = i.getStringArrayListExtra("sending");

        arrayAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, answers_received);

        listView.setAdapter(arrayAdapter);


        set_for_answers.addAll(answers_received);


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("My_Prefs_ListView",
                MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
// This will remove all entries of the specific SharedPreferences
        editor.clear();
        for (int ii = 0; ii < arrayAdapter.getCount(); ++ii){
            // This assumes you only have the list items in the SharedPreferences.
            editor.putString(String.valueOf(i), arrayAdapter.getItem(ii).toString());
        }
        editor.commit();
// ...
// And the reverse process:
        SharedPreferences prefs2 = getApplicationContext().getSharedPreferences("My_Prefs_ListView_2",
                MODE_PRIVATE);
        for (int ii = 0;; ++ii){
            final String str = prefs2.getString(Integer.valueOf(ii).toString(), "");
            if (!str.equals("")){
                arrayAdapter.add(str);
            } else {
                break; // Empty String means the default value was returned.
            }
        }

//        editor_answers.putStringSet("list_of_answesr", set_for_answers);
//        editor_answers.commit();
//
//        SharedPreferences pref_answers = PreferenceManager.getDefaultSharedPreferences(this);
//        if (pref_answers.getBoolean("MyPref_answers", true)) {
//
//            new_set_shared_prefs = pref_answers.getStringSet("list_of_answesr", null);
//
//            arrayAdapter_sharedPreferences = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, answers_received);
//
//            listView.setAdapter(arrayAdapter_sharedPreferences);
//
//        }










    }

    public void ClickingGoingBackToQuestions(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
