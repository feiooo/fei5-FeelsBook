/*
 * Copyright (c) Fei Yang, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package com.example.fei5_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private EditText comment;
    private static final String FILENAME = "file.sav";
    private ArrayList<Emotion> emos = new ArrayList<Emotion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //editText
        comment = (EditText) findViewById(R.id.comment);


        //emotionButton
        Button love = (Button) findViewById(R.id.love);
        Button joy = (Button) findViewById(R.id.joy);
        Button sadness = (Button) findViewById(R.id.sadness);
        Button surprise = (Button) findViewById(R.id.surprise);
        Button anger = (Button) findViewById(R.id.anger);
        Button fear = (Button) findViewById(R.id.fear);

        //otherButton
        Button history = (Button) findViewById(R.id.history);


        //when click emotionButton
        //love
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Today you feel love!",Toast.LENGTH_SHORT).show();
                String text = comment.getText().toString();
                String feeling = "Love";
                ImportantEmotion newEmotion = new ImportantEmotion();
                newEmotion.setFeeling(feeling);
                newEmotion.setComment(text);
                newEmotion.setDate(new Date());
                emos.add(newEmotion);
                saveInFile();
            }

        });

        //joy
        joy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Today you feel joy!",Toast.LENGTH_SHORT).show();
                String text = comment.getText().toString();
                String feeling = "Joy";
                ImportantEmotion newEmotion = new ImportantEmotion();
                newEmotion.setFeeling(feeling);
                newEmotion.setComment(text);
                newEmotion.setDate(new Date());
                emos.add(newEmotion);
                saveInFile();
            }

        });

        //fear
        fear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Today you feel fear!",Toast.LENGTH_SHORT).show();
                String text = comment.getText().toString();
                String feeling = "Fear";
                ImportantEmotion newEmotion = new ImportantEmotion();
                newEmotion.setFeeling(feeling);
                newEmotion.setComment(text);
                newEmotion.setDate(new Date());
                emos.add(newEmotion);
                saveInFile();
            }

        });

        //surprise
        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Today you feel surprise!",Toast.LENGTH_SHORT).show();
                String text = comment.getText().toString();
                String feeling = "Surprise";
                ImportantEmotion newEmotion = new ImportantEmotion();
                newEmotion.setFeeling(feeling);
                newEmotion.setComment(text);
                newEmotion.setDate(new Date());
                emos.add(newEmotion);
                saveInFile();
            }

        });

        //sadness
        sadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Today you feel sadness!",Toast.LENGTH_SHORT).show();
                String text = comment.getText().toString();
                String feeling = "Sadness";
                ImportantEmotion newEmotion = new ImportantEmotion();
                newEmotion.setFeeling(feeling);
                newEmotion.setComment(text);
                newEmotion.setDate(new Date());
                emos.add(newEmotion);
                saveInFile();
            }

        });

        //anger
        anger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Today you feel anger!",Toast.LENGTH_SHORT).show();
                String text = comment.getText().toString();
                String feeling = "Anger";
                ImportantEmotion newEmotion = new ImportantEmotion();
                newEmotion.setFeeling(feeling);
                newEmotion.setComment(text);
                newEmotion.setDate(new Date());
                emos.add(newEmotion);
                saveInFile();
            }

        });


    }

    /**
     * Show history.
     *
     * @param view the view
     */
    public void showHistory(View view){
        Intent intent = new Intent(this, ShowHistoryAndCount.class);
        startActivity(intent);
    }


    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emos,writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
