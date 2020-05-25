package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int correctAnswers = 0;
    private static final int MAX_CORRECT_ANSWERS = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastMesg(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    //Add one point to the correctAnswers when all three checkboxes are clicked
    public void clickBoxes(){
         CheckBox wonderWoman = (CheckBox) findViewById(R.id.wonder_women);
         CheckBox superman = (CheckBox) findViewById(R.id.superman);

         boolean check1  = wonderWoman.isChecked();
         boolean check2  = superman.isChecked();
         if (check1 || check2){
             return;
        }else{
             correctAnswers++;
         }
    }

    /**
     * compare the user answer and the correct answer
     * @param answer
     * @param userAnswer
     */
    public void answerChecker(String answer, String userAnswer){
        if(answer.equals(userAnswer)) correctAnswers += 1;
    }

    /**
     * Gets the data from the user's radio button selection
     * @param answer
     * @param id
     */
    public void questionToAnswer(String answer, View id){
        RadioGroup group = (RadioGroup) id;
        int radiobuttonId = group.getCheckedRadioButtonId();
        if (radiobuttonId < 0) {
           toastMesg("Please Note you miss a question");

        }else{
            RadioButton btn = (RadioButton) findViewById(radiobuttonId);
            String radioBtnText = btn.getText().toString();
            answerChecker(answer, radioBtnText);
        }


    }

    /**
     * reset the correct answer counter
     */
    public void reset(){
        correctAnswers = 0;
    }


    public void editTextAnswer(String answer, View id){
        EditText text = (EditText) id;
        String userAnswer = text.getText().toString().toLowerCase();
        answerChecker(answer, userAnswer);
    }


    /**
     * question func: finds the questions and sends the answer to questionToAnswer()
     */
    public void questions () {
        View questionOne = findViewById(R.id.question_1);
        questionToAnswer("Loki", questionOne);
        View questionTwo = findViewById(R.id.question_2);
        questionToAnswer("Captain America", questionTwo);
        View questionThree = findViewById(R.id.question_3);
        questionToAnswer("Thor", questionThree);
        View questionFour = findViewById(R.id.question_4);
        questionToAnswer("Black Widow", questionFour);
        View questionFive = findViewById(R.id.question_5);
        questionToAnswer("Avengers Infinity War", questionFive);
        View questionSeven = findViewById(R.id.user_edit_text_answer);
        editTextAnswer("vision", questionSeven);
    }



    public String user_name(){
        EditText name = (EditText) findViewById(R.id.user_name);
        return String.valueOf(name.getText());
    }


    /**
     * the user submit their answer
     * a toast message will appear with result.
     * @param v
     */
    public void submitUserAnswer (View v) {
        questions();
        clickBoxes();
        String name = user_name();
        String text = "";
        if(correctAnswers == MAX_CORRECT_ANSWERS){
            text = "Great Job "+name+" YOU WON!! " + correctAnswers+ " out of "+ MAX_CORRECT_ANSWERS;

        }else{
            text = "Good work "+name +" Your result: "+correctAnswers+" out of " + MAX_CORRECT_ANSWERS;
        }
        toastMesg(text);
        reset();


    }
}
