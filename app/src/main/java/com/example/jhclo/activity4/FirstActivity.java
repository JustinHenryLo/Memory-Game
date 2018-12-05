package com.example.jhclo.activity4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button Start;
    Button Controls;
    Button HighScore;
    Button Quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Start = findViewById(R.id.start);
        Controls = findViewById(R.id.controls);
        HighScore =  findViewById(R.id.highscore);
        Quit =  findViewById(R.id.quit);

    }
    public void gameStart(View v){
        Intent intent = new Intent(this,second.class);
        startActivity(intent);
        //finish();
    }

    public void quit(View v){
        this.finish();
    }
}
