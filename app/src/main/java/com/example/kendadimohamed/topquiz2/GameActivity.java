package com.example.kendadimohamed.topquiz2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kendadimohamed.topquiz2.model.Question;
import com.example.kendadimohamed.topquiz2.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private QuestionBank mQuestionBank;
    private String mquestion;
    private Question question;
    private int mNumberOfQuestions;
    private int mScore;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mNumberOfQuestions=6;
        mScore=0;

        text = (TextView) findViewById(R.id.activity_game_question_text);
        btn1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        btn2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        btn3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        btn4 = (Button) findViewById(R.id.activity_game_answer4_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn1.setTag(0);
        btn2.setTag(1);
        btn3.setTag(2);
        btn4.setTag(3);
        this.gen();


    }
    private void gen(){
        if (mNumberOfQuestions==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // End the activity
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        }
        else{
            mQuestionBank = this.generateQuestions();
            question=mQuestionBank.getQuestion();
            this.displayQuestion(question);}

    }
    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }
    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        mquestion=question.getmQuestion();
        text.setText(mquestion);
        btn1.setText(question.getmChoiceList().get(0));
        btn2.setText(question.getmChoiceList().get(1));
        btn3.setText(question.getmChoiceList().get(2));
        btn4.setText(question.getmChoiceList().get(3));




    }


    @Override
    public void onClick(View v) {
        mNumberOfQuestions--;
        int responseIndex = (int) v.getTag();
        if(question.getmAnswerIndex()==responseIndex){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else{
            Toast.makeText(this, "wrong answer !", Toast.LENGTH_SHORT).show();
        }
        this.gen();
    }
}

