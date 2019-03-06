package com.example.hack4impactminiproject1;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.MediaPlayer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button firstChoice;
    Button secondChoice;
    Button thirdChoice;
    Button fourthChoice;
    Button playAgain;
    int locationOfCorrectAnswer;
    int locationOfTextColor;
    int score;
    String[] answers = new String[4];
    TextView colorTextView;
    TextView timerTextView;
    TextView scoreTextView;
    TextView gameEndTextView;
    RelativeLayout gameLayout;
    RelativeLayout gameEnd;
    HashMap<String,String> stringColor = new HashMap<String,String>();
    CountDownTimer countDownTimer;
    MediaPlayer correct;
    MediaPlayer lose;
    MediaPlayer win;
    public void start(View view)
    {
        score = 0;
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        gameEnd.setVisibility(View.INVISIBLE);
        timerTextView.setText("4s");
        scoreTextView.setText(Integer.toString(score));
        countDownTimer = new CountDownTimer(4100,1000)
        {
            @Override
            public void onTick(long millisUntilFinished){
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish(){
                lose.start();
                gameLayout.setVisibility(View.INVISIBLE);
                gameEnd.setVisibility(View.VISIBLE);
                gameEndTextView.setText("You lose :(!");
                countDownTimer.cancel();
            }
        };
        generateQuestion();
    }

    public void chooseAnswer(View view)
    {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            correct.start();
            score++;
            scoreTextView.setText(Integer.toString(score));
            if (score == 8)
            {
                win.start();
                gameLayout.setVisibility(View.INVISIBLE);
                gameEnd.setVisibility(View.VISIBLE);
                gameEndTextView.setText("You win! :)");
                countDownTimer.cancel();
            }
            else
            {
                generateQuestion();
            }
        }
        else
        {
            lose.start();
            gameLayout.setVisibility(View.INVISIBLE);
            gameEnd.setVisibility(View.VISIBLE);
            gameEndTextView.setText("You lose :(!");
            countDownTimer.cancel();
        }
    }

    public void generateQuestion()
    {
        Arrays.fill(answers, null );
        String[] keys = stringColor.keySet().toArray(new String[stringColor.size()]);
        String correctKey = keys[new Random().nextInt(keys.length)];
        String incorrectKey = keys[new Random().nextInt(keys.length)];
        while (incorrectKey.equals(correctKey))
        {
            incorrectKey = keys[new Random().nextInt(keys.length)];
        }
        String copyOfIncorrectKey = incorrectKey;

        colorTextView.setText(correctKey);
        colorTextView.setTextColor(Color.parseColor(stringColor.get(incorrectKey)));
        locationOfCorrectAnswer = new Random().nextInt(4);
        locationOfTextColor = new Random().nextInt(4);
        while (locationOfTextColor == locationOfCorrectAnswer)
        {
            locationOfTextColor = new Random().nextInt(4);
        }
        answers[locationOfCorrectAnswer] = correctKey;
        answers[locationOfTextColor] = copyOfIncorrectKey;

        for(int i = 0; i<4; i++)
        {
            if (answers[i] == null)
            {
                incorrectKey = keys[new Random().nextInt(keys.length)];
                while (incorrectKey.equals(correctKey) || Arrays.asList(answers).contains(incorrectKey))
                {
                    incorrectKey = keys[new Random().nextInt(keys.length)];
                }
                answers[i] = incorrectKey;
            }
        }


        firstChoice.setBackgroundColor(Color.parseColor(stringColor.get(answers[0])));
        secondChoice.setBackgroundColor(Color.parseColor(stringColor.get(answers[1])));
        thirdChoice.setBackgroundColor(Color.parseColor(stringColor.get(answers[2])));
        fourthChoice.setBackgroundColor(Color.parseColor(stringColor.get(answers[3])));

        countDownTimer.cancel();
        countDownTimer.start();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        firstChoice = findViewById(R.id.firstChoice);
        secondChoice = findViewById(R.id.secondChoice);
        thirdChoice = findViewById(R.id.thirdChoice);
        fourthChoice = findViewById(R.id.fourthChoice);
        playAgain = findViewById(R.id.playAgain);
        gameLayout = findViewById(R.id.gameLayout);
        gameEnd = findViewById(R.id.gameEnd);
        scoreTextView = findViewById(R.id.score);
        colorTextView = findViewById(R.id.colorText);
        timerTextView = findViewById(R.id.timer);
        gameEndTextView = findViewById(R.id.gameEndTextView);



        stringColor.put("Red", "#FF0000");
        stringColor.put("Green", "#00FF00");
        stringColor.put("Blue", "#0000FF");
        stringColor.put("Yellow", "#FFFF00");
        stringColor.put("Orange", "#FFA500");
        stringColor.put("Black", "#000000");
        stringColor.put("Cyan", "#00FFFF");
        stringColor.put("Gray", "#808080");
        stringColor.put("Magenta", "#FF00FF");

        correct = MediaPlayer.create(this, R.raw.correct);
        win = MediaPlayer.create(this, R.raw.win);
        lose = MediaPlayer.create(this, R.raw.lose);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        gameEnd.setVisibility(View.INVISIBLE);









    }
}
