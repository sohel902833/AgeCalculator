package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.content.PeriodicSync;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    Button btBirth,btToday,btCalculate;
    TextView tvResult;
    DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btBirth=findViewById(R.id.bt_birth);
        btToday=findViewById(R.id.bt_today);
        btCalculate=findViewById(R.id.bt_calculate);
        tvResult=findViewById(R.id.tv_result);


        Calendar calendar=Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyy");
        String date=simpleDateFormat.format(Calendar.getInstance().getTime());
        btToday.setText(date);




        btBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            DatePickerDialog datePickerDialog=new DatePickerDialog(
                                    MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                                    ,dateSetListener1,year,month,day
                            );

                            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            datePickerDialog.show();

            }
        });

        dateSetListener1=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month=month+1;
                String date=day+"/"+month+"/"+year;
                btBirth.setText(date);

            }
        };
        btToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            DatePickerDialog datePickerDialog=new DatePickerDialog(
                                    MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                                    ,dateSetListener2 ,year,month,day
                            );

                            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            datePickerDialog.show();

            }
        });

        dateSetListener2=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month=month+1;
                String date=day+"/"+month+"/"+year;
                btToday.setText(date);

            }
        };


        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String sDate =btBirth.getText().toString();
                String eDate=btToday.getText().toString();
                SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd/MM/yyy");

                try {
                    Date date1=simpleDateFormat1.parse(sDate);
                    Date date2=simpleDateFormat1.parse(eDate);


                    long startDate=date1.getTime();
                    long endDate=date2.getTime();
                    if(startDate<=endDate){
                      org.joda.time.Period period = new Period(startDate,endDate, PeriodType.yearMonthDay());
                        int years=period.getYears();
                        int months=period.getMonths();
                        int days=period.getDays();
                        tvResult.setText(years +" Years | "+months+" Months | "+days+"Days");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });









    }
}
