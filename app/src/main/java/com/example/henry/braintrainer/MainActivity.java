package com.example.henry.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button playAgainButton;
    TextView sumTextView;
    TextView scoreTextView;
    TextView resultTextView;
    TextView timerTextView;
    int locationOfCorrectAnswer;
    int score = 0;
    int numOfQuestions = 0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    CountDownTimer countDownTimer;

    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));


    }

    public void playAgain(View view) {
        playAgainButton.setVisibility(View.INVISIBLE);
        score = 0;
        numOfQuestions = 0;
        resultTextView.setVisibility(View.INVISIBLE);
        timerTextView.setText("30s");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                int sec = (int)l/1000;
                timerTextView.setText(Integer.toString(sec) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Times Up!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        };

        countDownTimer.start();

        newQuestion();

    }
    public void chooseAnswer(View view) {

        Log.i("Tag",view.getTag().toString());
        resultTextView.setVisibility(View.VISIBLE);
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                Log.i("RESULT","Correct!");
                resultTextView.setText("Correct!");
                resultTextView.setVisibility(View.VISIBLE);
                score++;
        } else {
            resultTextView.setText("WRONG!");
            Log.i("RESULT","Wrong :(");
        }

        numOfQuestions++;

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));

        newQuestion();

    }
    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);       // 0 to 20 range

        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();
        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while(wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);

        resultTextView.setVisibility(View.INVISIBLE);

        startButton.setVisibility(View.VISIBLE);

        gameLayout = findViewById(R.id.gameLayout);
        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
