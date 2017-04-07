package com.example.ash.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView questionGen, previousAnswer;
    EditText editText_answer;
    ArrayList<String> questions, answers ;
    int increment_question, increment_answer;
    SharedPreferences pref_answers;
    SharedPreferences.Editor editor_answers;
    Set<String> set_for_answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionGen = (TextView) findViewById(R.id.textView);
        previousAnswer = (TextView) findViewById(R.id.your_previous_answer);
        editText_answer = (EditText) findViewById(R.id.editText);
        set_for_answers = new HashSet<String>();

        pref_answers = getApplicationContext().getSharedPreferences("MyPref_answers", MODE_PRIVATE);
        editor_answers = pref_answers.edit();


        questionGen.setText("Would you like to play the Questions Game?");


        questions = new ArrayList<>();
        questions.add("What's your favorite color?");
        questions.add("What's your favorite book?");
        questions.add("What's your favorite computer game?");
        questions.add("What's your favorite book series?");
        questions.add("What's your favorite time of day?");

        answers = new ArrayList<>();






    }



    public void ClickingSubmitAnswer(View view) {
        answers.add(editText_answer.getText().toString());
        previousAnswer.setText(answers.get(increment_answer).toString());
        editText_answer.setText("");
        questionGen.setText(questions.get(increment_question));
        increment_question ++;
        increment_answer ++;


    }



    public void seeingFavorites(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putStringArrayListExtra("sending", answers);
        set_for_answers.addAll(answers);
        editor_answers.putStringSet("list_of_answesr", set_for_answers);
        editor_answers.apply();
        startActivity(intent);

    }
}
