/*
 * Copyright (c) Fei Yang, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package com.example.fei5_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


/**
 * The type Show history and count.
 */
public class ShowHistoryAndCount extends AppCompatActivity {

    private ListView historyList;
    private TextView lovecount;
    private TextView joycount;
    private TextView fearcount;
    private TextView angercount;
    private TextView sadnesscount;
    private TextView surprisecount;

    private static final String FILENAME = "file.sav";

    private ArrayAdapter<Emotion> emoadapter;
    private ArrayList<Emotion> emos = new ArrayList<Emotion>();

    /**
     * The constant editEmotion.
     */
    public static final String editEmotion = "\"com.example.fei5_feelsbook.EDITEMO\"";
    /**
     * The constant editComment.
     */
    public static final String editComment = "\"com.example.fei5_feelsbook.EDITCOM\"";
    /**
     * The constant editDate.
     */
    public static final String editDate = "\"com.example.fei5_feelsbook.EDITDATE\"";

    private int position;

    /**
     * The Num l.
     */
    int numL, /**
     * The Num s.
     */
    numS, /**
     * The Num sa.
     */
    numSa, /**
     * The Num j.
     */
    numJ, /**
     * The Num a.
     */
    numA, /**
     * The Num f.
     */
    numF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history_and_count);

        historyList = (ListView)findViewById(R.id.history_list);
        lovecount = (TextView)findViewById(R.id.love_num);
        joycount = (TextView)findViewById(R.id.joy_num);
        surprisecount = (TextView)findViewById(R.id.surprise_num);
        sadnesscount = (TextView)findViewById(R.id.sadness_num);
        fearcount = (TextView)findViewById(R.id.fear_num);
        angercount = (TextView)findViewById(R.id.anger_num);

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                Toast.makeText(ShowHistoryAndCount.this,"You choose No."+ (i+1),Toast.LENGTH_SHORT).show();

                //change page and send data to EditPage
                //Reference: https://developer.android.com/training/basics/intents/result
                Intent intent = new Intent(ShowHistoryAndCount.this,EditHistory.class);
                String editemo = emos.get(i).getFeeling();
                String editcom = emos.get(i).getComment();
                Date editdate = emos.get(i).getDate();
                intent.putExtra(editEmotion,editemo);
                intent.putExtra(editComment,editcom);
                intent.putExtra(editDate,editdate.getTime());

                startActivityForResult(intent, 1);

            }
        });

    }//end of onCreate

    //Get feedback from edit_history_page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == 1) {
                //when delete button was clicked...
                String Emotion = data.getStringExtra(EditHistory.editEmotion);
                String Comment = data.getStringExtra(EditHistory.editComment);
                String day = data.getStringExtra(EditHistory.editDate);
                ImportantEmotion newemotion = new ImportantEmotion();
                newemotion.setFeeling(Emotion);
                newemotion.setComment(Comment);

                //TRANSFER STRING TO DATE Reference:https://developer.android.com/reference/java/text/SimpleDateFormat
                SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    Date date = fmt.parse(day);
                    newemotion.setDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                emos.set(position,newemotion);
                saveInFile();
                emoadapter.notifyDataSetChanged();
            }
            else if(resultCode == 2){
                //when delete button was clicked...
                emos.remove(position);
                saveInFile();
                emoadapter.notifyDataSetChanged();
            }
        }
    }


    //Load data from saved file
    protected void onStart() {
        super.onStart();
        loadFromFile();
        emoadapter = new ArrayAdapter<Emotion>(this,
                R.layout.list_item, emos);
        historyList.setAdapter(emoadapter);
        countEmotion();//Get emotion count
        sortEmotionList();//sort the whole list
    }

    private void countEmotion(){
        //count emotion
        int length = emos.size();
        numL = 0;
        numJ = 0;
        numS = 0;
        numSa = 0;
        numF = 0;
        numA = 0;
        for(int i = 0;i<length;i++){
            String feeling = emos.get(i).getFeeling();
            if (feeling.compareTo("Love") == 0){
                numL += 1;
            }
            if (feeling.compareTo("Joy") == 0){
                numJ += 1;
            }
            if (feeling.compareTo("Surprise") == 0){
                numS += 1;
            }
            if (feeling.compareTo("Sadness") == 0){
                numSa += 1;
            }
            if (feeling.compareTo("Fear") == 0){
                numF += 1;
            }
            if (feeling.compareTo("Anger") == 0){
                numA += 1;
            }
        }
        lovecount.setText(String.valueOf(numL));
        joycount.setText(String.valueOf(numJ));
        fearcount.setText(String.valueOf(numF));
        angercount.setText(String.valueOf(numA));
        sadnesscount.setText(String.valueOf(numSa));
        surprisecount.setText(String.valueOf(numS));
    }

    private void sortEmotionList(){
        Comparator<Emotion> comparator = new Comparator<Emotion>() {
            @Override
            public int compare(Emotion e1, Emotion e2) {
                if(e1.getDate().before(e2.getDate())){
                    return -1;
                }
                else if(e1.getDate().after(e2.getDate())){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            };
        Collections.sort(emos, comparator);
        }

    private void  loadFromFile() {
        try {

            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            Type listEmotionType=new TypeToken<ArrayList<ImportantEmotion>>(){}.getType();
            emos=gson.fromJson(reader,listEmotionType);

        } catch (FileNotFoundException e) {
            Toast.makeText(ShowHistoryAndCount.this,"No Emotion History right now!!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
