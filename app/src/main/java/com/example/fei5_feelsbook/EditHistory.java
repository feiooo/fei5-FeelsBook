/*
 * Copyright (c) Fei Yang, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package com.example.fei5_feelsbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Edit history.
 */
public class EditHistory extends AppCompatActivity {

    private TextView TheEmotion;
    private EditText EditDate;
    private EditText EditTime;
    private EditText EditComment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_history);

        TheEmotion = (TextView) findViewById(R.id.TheEmotion);
        EditDate = (EditText) findViewById(R.id.EditDate);
        EditDate.setInputType(InputType.TYPE_NULL);
        EditComment = (EditText) findViewById(R.id.EditComment);
        EditTime = (EditText) findViewById(R.id.EditTime);
        EditTime.setInputType(InputType.TYPE_NULL);

        Button save = (Button) findViewById(R.id.Save);
        Button delete = (Button) findViewById(R.id.Delete);

        Intent intent = getIntent();
        String Emotion = intent.getStringExtra(ShowHistoryAndCount.editEmotion);
        String Comment = intent.getStringExtra(ShowHistoryAndCount.editComment);
        Date date = new Date();
        date.setTime(intent.getLongExtra(ShowHistoryAndCount.editDate, -1));



        //date
        String year = String.valueOf(date.getYear()+1900);
        String month = String.valueOf(date.getMonth()+1);
        String day = String.valueOf(date.getDay());
        //time
        String hour = String.valueOf(date.getHours());
        String minute = String.valueOf(date.getMinutes());
        String second = String.valueOf(date.getSeconds());


        //SetText for TextView and EditText
        TheEmotion.setText(Emotion);
        EditComment.setText(Comment);
        EditDate.setText(year + "-" + month + "-" + day);
        EditTime.setText(hour + ":" + minute + ":" + second);

        //set DatePicker
        EditDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    DatePicker();
                }
            }
        });

        EditDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePicker();
            }
        });


        //set TimePicker
        EditTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    TimePicker();
                }
            }
        });

        EditTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TimePicker();
            }
        });


        //Set SaveButton

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = EditComment.getText().toString();
                String feeling = TheEmotion.getText().toString();
                String day = EditDate.getText().toString();
                String time = EditTime.getText().toString();
                String date = day + " " + time;
                Intent intent = new Intent(EditHistory.this,ShowHistoryAndCount.class);
                intent.putExtra(editEmotion,feeling);
                intent.putExtra(editComment,text);
                intent.putExtra(editDate,date);
                setResult(1, intent);
                finish();


            }

        });

        //Set DeleteButton
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditHistory.this,ShowHistoryAndCount.class);
                setResult(2, intent);
                finish();

            }

        });



    }
    //DatePicker Reference: https://blog.csdn.net/xx326664162/article/details/53436123
    private void DatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(EditHistory.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                EditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    //TimePicker Reference: http://www.zoftino.com/android-timepicker-example
    private void TimePicker() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                EditTime.setText(hourOfDay + ":" + minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show();


    }




}



