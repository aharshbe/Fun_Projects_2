package com.example.ash.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.R.attr.data;
import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    TextView questionGen, previousAnswer;
    EditText editText_answer;
    ArrayList<String> questions, answers;
    int increment_question, increment_answer;
    SharedPreferences prefs_answers;
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

//        prefs_answers = getApplicationContext().getSharedPreferences("MyPref_answers", MODE_PRIVATE);
//        editor_answers = prefs_answers.edit();


        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Would you like to play the Questions Game?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            questionGen.setText(questions.get(increment_question));
                            increment_question++;
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Uri uri = Uri.parse("https://media.giphy.com/media/GyMQXGx3WMm6Q/giphy.gif");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            finish();
                            System.exit(0);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();
        }


        questions = new ArrayList<>();
        questions.add("What's your favorite color?");
        questions.add("What's your favorite book?");
        questions.add("What's your favorite computer game?");
        questions.add("What's your favorite book series?");
        questions.add("What's your favorite time of day?");

        questionGen.setText(questions.get(increment_question));

        answers = new ArrayList<>();


    }


    public void ClickingSubmitAnswer(View view) {

        if (editText_answer.getText().toString().equals("")) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("You must enter an answer, sorry bout it. ;)");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

        } else {


            answers.add(editText_answer.getText().toString());
            previousAnswer.setText(answers.get(increment_answer).toString());
            editText_answer.setText("");
            questionGen.setText(questions.get(increment_question));
            increment_question++;
            increment_answer++;


            if (answers.contains("harry potter")) {

                answers.remove("harry potter");
                answers.add("You're a wizard harry!");

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("You're a wizard harry");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Cool",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        }


    }

    public void seeingFavorites(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putStringArrayListExtra("sending", answers);
        set_for_answers.addAll(answers);
//        editor_answers.putStringSet("list_of_answesr", set_for_answers);
//        editor_answers.apply();
        startActivity(intent);

    }


}
