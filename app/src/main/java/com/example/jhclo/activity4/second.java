package com.example.jhclo.activity4;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class second extends AppCompatActivity {
    TextView correct;
    TextView wrong;
    TextView timer;
    HashMap<Integer,String> button_list = new HashMap<Integer,String>();
    int prev = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        correct = findViewById(R.id.correct_tb);
        wrong = findViewById(R.id.wrong_tb);
        timer = findViewById(R.id.timer);
        button_list.put(R.id.buttonA,"A");
        button_list.put(R.id.buttonB,"A");
        button_list.put(R.id.buttonC,"B");
        button_list.put(R.id.buttonD,"B");
        button_list.put(R.id.buttonE,"C");
        button_list.put(R.id.buttonF,"C");
        button_list.put(R.id.buttonG,"D");
        button_list.put(R.id.buttonH,"D");
        button_list.put(R.id.buttonI,"E");
        button_list.put(R.id.buttonJ,"E");
        button_list.put(R.id.buttonK,"F");
        button_list.put(R.id.buttonL,"F");
        button_list.put(R.id.buttonM,"G");
        button_list.put(R.id.buttonN,"G");
        button_list.put(R.id.buttonO,"H");
        button_list.put(R.id.buttonP,"H");
    }

    @Override
    protected void onResume() {
        final CountDownTimer clock = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished){
                timer.setText((millisUntilFinished / 1000)+"");
            }

            public void onFinish() {
                timer.setText("Start");
                hide_all();
            }
        }.start();
        super.onResume();
    }

    public void hide_all(){
        for(int x : button_list.keySet()){
            ((Button)findViewById(x)).setText("???");
        }
    }

    public void clicked(View v) {
        Button clicked = (Button) v;
        if(prev == -1) {
            prev = clicked.getId();
        }
        else if (prev != clicked.getId()) {
            Button prev_button = ((Button)findViewById(prev));
            if (prev_button.getText().toString() == clicked.getText().toString()){
                correct.setText(((Integer.parseInt(correct.getText().toString())) + 1) + "");
                clicked.setEnabled(false);
                prev_button.setEnabled(false);
            } else {
                wrong.setText(((Integer.parseInt(wrong.getText().toString())) + 1) + "");
                //add timer
            }
            prev = -1;
        }
    }



    public void checkFinished(){

    }
}


