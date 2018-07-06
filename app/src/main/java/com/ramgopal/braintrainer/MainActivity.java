package com.ramgopal.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView totalView,timerView,resultView,questionView;
    Button button,button2,button3,button4,button6,button5;
    RelativeLayout questionLayout;
    int score = 0,question = 0;int random;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    public void questionGenerator()
    {
        Random ran = new Random();
        int a = ran.nextInt(21);
        int b = ran.nextInt(21);
        questionView.setText(Integer.toString(a)+ " + " +Integer.toString(b));

        int total = a+b;
        arrayList.clear();
        random = ran.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i == random)
                arrayList.add(total);
            else
                arrayList.add(ran.nextInt(43));
        }
        button2.setText(Integer.toString(arrayList.get(0)));
        button3.setText(Integer.toString(arrayList.get(1)));
        button4.setText(Integer.toString(arrayList.get(2)));
        button5.setText(Integer.toString(arrayList.get(3)));



    }


    public void playAgain(View view)
    {
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
        button5.setClickable(true);
        score = 0;
        resultView.setText("");
        totalView.setText("0/0");
        question = 0;
        button6.setVisibility(View.INVISIBLE);
        questionGenerator();

        new CountDownTimer(30100,1000){

            public void onTick(long milliSecondsUntilLeft)
            {
                if(milliSecondsUntilLeft<10000)
                    timerView.setText("0"+Long.toString(milliSecondsUntilLeft/1000));
                else
                    timerView.setText(Long.toString(milliSecondsUntilLeft/1000));

            }
            public void onFinish()
            {
              timerView.setText("00");
              resultView.setText("Your Score:"+Integer.toString(score));
              button6.setVisibility(View.VISIBLE);
              button2.setClickable(false);
                button3.setClickable(false);
                button4.setClickable(false);
                button5.setClickable(false);
                Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();

            }
        }.start();

    }


    public void clickGo(View view)
    {
        button.setVisibility(View.INVISIBLE);
        questionLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button6));
    }

    public void clickAnswer(View view) {

    int tagValue = Integer.parseInt(view.getTag().toString());
    if(tagValue == random)
    {
        score++;
        resultView.setText("Correct!");
    }
    else
        resultView.setText("Wrong");
    question++;
    totalView.setText(Integer.toString(score)+ "/" + Integer.toString(question));
    questionGenerator();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = (TextView) findViewById(R.id.questionView);
        totalView = (TextView) findViewById(R.id.totalView);
        resultView = (TextView) findViewById(R.id.resultView);
        timerView = (TextView) findViewById(R.id.timerView);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button6 = (Button) findViewById(R.id.button6);
        button5 = (Button) findViewById(R.id.button5);
        questionLayout = (RelativeLayout) findViewById(R.id.questionLayout);


    }
}
