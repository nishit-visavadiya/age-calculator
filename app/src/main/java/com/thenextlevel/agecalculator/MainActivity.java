package com.thenextlevel.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button findDob_btn, calculate_btn;
    TextView today_txt, dob_txt, age_txt;
    String mbirthday, mtoday;

    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today_txt = findViewById(R.id.dateText);
        dob_txt = findViewById(R.id.dobText);
        age_txt = findViewById(R.id.ageText);
        findDob_btn = findViewById(R.id.dobButton);
        calculate_btn = findViewById(R.id.calButton);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mtoday = simpleDateFormat.format(Calendar.getInstance().getTime());
        today_txt.setText(mtoday);

        findDob_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), dateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                mbirthday = dayOfMonth + "/" + month + "/" + year;
                dob_txt.setText(mbirthday);
            }
        };

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 = simpleDateFormat1.parse(mbirthday);
                    Date date2 = simpleDateFormat1.parse(mtoday);

                    long startDate = date1.getTime();
                    long endDate = date2.getTime();

                    Period period = new Period(startDate, endDate, PeriodType.yearMonthDay());
                    int years = period.getYears();
                    int months = period.getMonths();
                    int days = period.getDays();

                    age_txt.setText(years + "years |" + months + "month |" + days + "days");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
