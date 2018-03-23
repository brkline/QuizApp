package com.example.android.quizapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int correctAnswers = 0;
    int wrongAnswers = 0;
    String messageText = "";

    EditText question1EditText;
    RadioGroup question2RadioGroup;
    RadioGroup question4RadioGroup;
    RadioGroup question5RadioGroup;
    RadioButton question2RadioButton1;
    RadioButton question2RadioButton2;
    RadioButton question2RadioButton3;
    RadioButton question4RadioButton1;
    RadioButton question4RadioButton2;
    RadioButton question4RadioButton3;
    RadioButton question5RadioButton1;
    RadioButton question5RadioButton2;
    RadioButton question5RadioButton3;
    CheckBox question3CheckBox1;
    CheckBox question3CheckBox2;
    CheckBox question3CheckBox3;
    CheckBox question3CheckBox4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question1EditText = findViewById(R.id.question1_editText);
        question2RadioGroup = findViewById(R.id.question2_radioGroup);
        question4RadioGroup = findViewById(R.id.question4_radioGroup);
        question5RadioGroup = findViewById(R.id.question5_radioGroup);
        question2RadioButton1 = findViewById(R.id.question2_radioButton1);
        question2RadioButton2 = findViewById(R.id.question2_radioButton2);
        question2RadioButton3 = findViewById(R.id.question2_radioButton3);
        question4RadioButton1 = findViewById(R.id.question4_radioButton1);
        question4RadioButton2 = findViewById(R.id.question4_radioButton2);
        question4RadioButton3 = findViewById(R.id.question4_radioButton3);
        question5RadioButton1 = findViewById(R.id.question5_radioButton1);
        question5RadioButton2 = findViewById(R.id.question5_radioButton2);
        question5RadioButton3 = findViewById(R.id.question5_radioButton3);
        question3CheckBox1 = findViewById(R.id.question3_checkBox1);
        question3CheckBox2 = findViewById(R.id.question3_checkBox2);
        question3CheckBox3 = findViewById(R.id.question3_checkBox3);
        question3CheckBox4 = findViewById(R.id.question3_checkBox4);
        Resources res = getResources();
        messageText = res.getString(R.string.question_source_message);
        Toast.makeText(this, messageText, Toast.LENGTH_LONG).show();
    }

    /*
    * Checks the answers and displays the appropriate Toast message
    */
    public void checkAnswers(View v) {

        /*
        * Question and answer source:  http://www.starwars.com/news/star-wars-trivia-tuesday-answers
        */

        int answer1;
        int answer2;
        int answer4;
        int answer5;
        Resources res = getResources();

        // We're going to try to cast the EditText to an int.  If it throws an exception, give them opportunity to reenter
        try {
            answer1 = Integer.parseInt(question1EditText.getText().toString());
        } catch (NumberFormatException e) {
            messageText = res.getString(R.string.question1_message);
            Toast.makeText(this, messageText, Toast.LENGTH_SHORT).show();
            return;
        }

        // Determine which Radio Button is checked for each of the groups
        answer2 = question2RadioGroup.getCheckedRadioButtonId();
        answer4 = question4RadioGroup.getCheckedRadioButtonId();
        answer5 = question5RadioGroup.getCheckedRadioButtonId();

        // Check the answers and accumulate the correct and wrong answers
        if (answer1 == 3) {
            correctAnswers += 1;
        } else {
            wrongAnswers += 1;
        }

        if (question2RadioGroup.getCheckedRadioButtonId() != -1) {
            if (answer2 == question2RadioButton1.getId()) {
                correctAnswers += 1;
            } else {
                wrongAnswers += 1;
            }
        }

        if (question3CheckBox2.isChecked() && question3CheckBox4.isChecked() && !(question3CheckBox1.isChecked()) && !(question3CheckBox3.isChecked())) {
            correctAnswers += 1;
        } else {
            wrongAnswers += 1;
        }
        if (question4RadioGroup.getCheckedRadioButtonId() != -1) {
            if (answer4 == question4RadioButton2.getId()) {
                correctAnswers += 1;
            } else {
                wrongAnswers += 1;
            }
        }

        if (question5RadioGroup.getCheckedRadioButtonId() != -1) {
            if (answer5 == question5RadioButton3.getId()) {
                correctAnswers += 1;
            } else {
                wrongAnswers += 1;
            }
        }

        // Add the correct and wrong answers together to make sure we get 5
        if (correctAnswers + wrongAnswers != 5) {
            messageText = res.getString(R.string.not_all_answered_message);
            Toast.makeText(this, messageText, Toast.LENGTH_SHORT).show();
            return;
        }

        messageText = res.getString(R.string.correct_answer_message, correctAnswers);
        // Display the number they got correct
        Toast.makeText(this, messageText, Toast.LENGTH_SHORT).show();
    }

    /*
    * Resets the questions
    */
    public void resetAll(View v) {
        question1EditText.setText("");
        question2RadioGroup.clearCheck();
        question3CheckBox1.setChecked(false);
        question3CheckBox2.setChecked(false);
        question3CheckBox3.setChecked(false);
        question3CheckBox4.setChecked(false);
        question4RadioGroup.clearCheck();
        question5RadioGroup.clearCheck();
    }
}
