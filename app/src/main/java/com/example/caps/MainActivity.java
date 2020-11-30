package com.example.caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.TextKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.roumani.i2c.MPro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.question)).setText(qa[0]);
    }
    private Game g = new Game();
    private String qa[] = g.qa().split("\\n");
    private String log = "";
    int qCount = 1;
    int score = 0;

    public void buttonClicked(View v) {

        try
        {
            String question = ((TextView) findViewById(R.id.question)).getText().toString();
            String response = ((EditText) findViewById(R.id.answer)).getText().toString();
            String currentQ = ((TextView) findViewById(R.id.qNum)).getText().toString();
            log = currentQ + ": " + question + "\nYour answer: " + response + "\nCorrect answer: " + qa[1] + "\n\n" + log;

            String answer = qa[1].toUpperCase();
            response = response.toUpperCase();


            if (answer.contentEquals(response)) {
                score += 1;
            }
            qCount += 1;

            ((TextView) findViewById(R.id.score)).setText("Score = "+ score);
            ((TextView) findViewById(R.id.log)).setText(log);

            g = new Game();

            qa = g.qa().split("\\n");

            if (qCount >= 10) {
                ((TextView) findViewById(R.id.qNum)).setText("Game Over!");
                ((Button) findViewById(R.id.done)).setEnabled(false);
            }
            else {
                ((TextView) findViewById(R.id.qNum)).setText("Q# "+ qCount);
                ((TextView) findViewById(R.id.question)).setText(qa[0]);
            }

        }

        catch (Exception e)
        {
            String msg = e.getMessage();
            Toast label = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            label.show();
        }

    }
}