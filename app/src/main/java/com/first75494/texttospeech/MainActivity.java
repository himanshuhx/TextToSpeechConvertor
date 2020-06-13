package com.first75494.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView editText;
    private Button button;
    private TextToSpeech textToSpeech;
    private SeekBar seekbarpitch;
    private SeekBar seekbarspeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        seekbarpitch = (SeekBar) findViewById(R.id.seekBar1);
        seekbarspeed = (SeekBar) findViewById(R.id.seekBar2);

        textToSpeech =new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
              if(status != TextToSpeech.ERROR){
                  textToSpeech.setLanguage(Locale.ENGLISH);
              }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get text from edittext
                String text = editText.getText().toString();

                float pitch = (float) seekbarpitch.getProgress() / 50;
                if(pitch < 0.1) pitch = 0.1f;

                float speed = (float) seekbarspeed.getProgress() / 50;
                if(speed < 0.1) speed = 0.1f;

                textToSpeech.setPitch(pitch);
                textToSpeech.setSpeechRate(speed);

                //converting text to speech
                textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    @Override
    protected void onPause() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}
