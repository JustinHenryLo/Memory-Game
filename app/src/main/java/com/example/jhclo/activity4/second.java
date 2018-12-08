package com.example.jhclo.activity4;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Debug;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class second extends AppCompatActivity {
    TextView correct;
    TextView wrong;
    TextView timer;
    HashMap<Integer,String> button_list = new HashMap<Integer,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        correct = findViewById(R.id.correct_tb);
        wrong = findViewById(R.id.wrong_tb);
        timer = findViewById(R.id.timer);

        ArrayList<String> keys = new ArrayList<String>();
        keys.add("A");keys.add("B");keys.add("C");keys.add("D");
        keys.add("E");keys.add("F");keys.add("G");keys.add("H");

        ArrayList<Integer> ids =  new ArrayList<Integer>();
        ids.add(R.id.buttonA);ids.add(R.id.buttonB);ids.add(R.id.buttonC);ids.add(R.id.buttonD);
        ids.add(R.id.buttonE);ids.add(R.id.buttonF);ids.add(R.id.buttonG);ids.add(R.id.buttonH);
        ids.add(R.id.buttonI);ids.add(R.id.buttonJ);ids.add(R.id.buttonK);ids.add(R.id.buttonL);
        ids.add(R.id.buttonM);ids.add(R.id.buttonN);ids.add(R.id.buttonO);ids.add(R.id.buttonP);

        Random rand = new Random();
        for (String k:keys) {
            int indexA = rand.nextInt(ids.size());
            button_list.put(ids.get(indexA),k);
            ((Button)findViewById(ids.get(indexA))).setText(k);
            ids.remove(indexA);
            int indexB = rand.nextInt(ids.size());
            button_list.put(ids.get(indexB),k);
            ((Button)findViewById(ids.get(indexB))).setText(k);
            ids.remove(indexB);
        }
    }

    @Override
    protected void onResume() {
        disable_all();
        final CountDownTimer clock = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished){
                timer.setText((millisUntilFinished / 1000)+"");
            }

            public void onFinish() {
                timer.setText("Start");
                hide_all();
                enable_all();
            }
        }.start();
        super.onResume();
    }

    public void hide_all(){
        for(int x : button_list.keySet()){
            ((Button)findViewById(x)).setText("???");
        }
    }

    public void disable_all(){
        for(int x : button_list.keySet()){
            ((Button)findViewById(x)).setEnabled(false);
        }
    }

    public void enable_all(){
        for(int x : button_list.keySet()){
            ((Button)findViewById(x)).setEnabled(true);
        }
    }

    public CountDownTimer hide_moves;
    int prev = -1;
    int buttonID1 = -1;
    int buttonID2 = -1;
    boolean run_once = true;
    boolean wrong_timer = false;

    public void clicked(View v) {
        final Button clicked = (Button) v;
        clicked.setText(button_list.get(clicked.getId()));
        if (wrong_timer == true) { //is hide_moves timer ticking, if yes hide the buttons that are wrong on new button press
            Log.d("tags","prev "+button_list.get(prev) +" current" +button_list.get(clicked.getId()));
            hide_moves.cancel();
            ((Button) findViewById(buttonID1)).setText("???");
            ((Button) findViewById(buttonID2)).setText("???");
            ((Button) findViewById(buttonID1)).setBackground(getDrawable(R.drawable.buttons));
            ((Button) findViewById(buttonID2)).setBackground(getDrawable(R.drawable.buttons));
            ((Button) findViewById(buttonID1)).setEnabled(true);
            ((Button) findViewById(buttonID2)).setEnabled(true);
            clicked.setBackground(getDrawable(R.drawable.button_blue));
            prev = clicked.getId();
            buttonID1 = -1;
            buttonID2 = -1;
            wrong_timer = false;
        } else {
            if (prev == -1) {
                prev = clicked.getId();
                clicked.setBackground(getDrawable(R.drawable.button_blue));
            } else if (prev != clicked.getId()) {//not same button clicked
                final Button prev_button = findViewById(prev);
                clicked.setBackground(getDrawable(R.drawable.button_blue));
                if (button_list.get(clicked.getId()) == button_list.get(prev)) {
                    correct.setText(((Integer.parseInt(correct.getText().toString())) + 1) + "");
                    clicked.setBackground(getDrawable(R.drawable.buttons));
                    prev_button.setBackground(getDrawable(R.drawable.buttons));
                    clicked.setEnabled(false);
                    prev_button.setEnabled(false);
                    prev = -1;
                } else {
                        wrong.setText(((Integer.parseInt(wrong.getText().toString())) + 1) + "");
                        buttonID1 = prev;
                        buttonID2 = clicked.getId();
                        final Button btn1 = findViewById(buttonID1);
                        final Button btn2 = findViewById(buttonID2);
                        btn1.setBackground(getDrawable(R.drawable.button_red));
                        btn2.setBackground(getDrawable(R.drawable.button_red));
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        wrong_timer = true;
                        hide_moves = new CountDownTimer(2000, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                btn1.setText("???");
                                btn2.setText("???");
                                btn1.setBackground(getDrawable(R.drawable.buttons));
                                btn2.setBackground(getDrawable(R.drawable.buttons));
                                btn1.setEnabled(true);
                                btn2.setEnabled(true);
                                prev = -1;
                                wrong_timer = false;
                            }
                        }.start();
                }
            }
            //prev = clicked.getId();
            //Toast.makeText(getApplicationContext(), "prev "+button_list.get(prev) +" current" +button_list.get(clicked.getId()), Toast.LENGTH_SHORT).show();
        }
    }



    public void checkFinished(){

    }
}


