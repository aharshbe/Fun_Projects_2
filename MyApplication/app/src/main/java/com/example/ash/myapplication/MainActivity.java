package com.example.ash.myapplication;

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

public class MainActivity extends AppCompatActivity {

    TextView questionGen, previousAnswer;
    EditText editText_answer;
    ArrayList<String> questions, answers ;
    int increment_question, increment_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionGen = (TextView) findViewById(R.id.textView);
        previousAnswer = (TextView) findViewById(R.id.your_previous_answer);
        editText_answer = (EditText) findViewById(R.id.editText);


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
        increment_answer ++;


    }

    public void ClickingNextQuestion(View view) {
        questionGen.setText(questions.get(increment_question));
        increment_question ++;

    }
}
