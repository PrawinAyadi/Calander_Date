package com.example.calander_date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView tvDOB;
    private TextView tvTime;
    private ProgressBar progressBar;
    private int progressStatus =0;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDOB = findViewById(R.id.tvDOB);
        tvTime = findViewById(R.id.tvTime);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

        tvDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDatePicker();
            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTime();
            }
        });
    }

    private void loadDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog
                (this, this, year, month, day);
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
        String date = "Month Date Year:" + month + "/" + dayofMonth + "/" + year;
        tvDOB.setText(date);

    }

    private void loadTime() {
        final Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        final int second = cal.get(Calendar.SECOND);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofday, int minute) {
                tvTime.setText("Time is" + hourofday + ":" + minute);

            }
        }, hour, minute, false);
        timePickerDialog.show();


     new Thread(new Runnable() {
        @Override
        public void run() {
            while ( progressStatus<100){
                progressStatus += 1;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(progressStatus);
                        textView.setText(progressStatus + "/" + progressBar.getMax());
                    }
                };
                try{
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }).start();

}
}



