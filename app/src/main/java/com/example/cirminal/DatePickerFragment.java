package com.example.cirminal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    DatePicker mDatePicker;
    private static final String ARG_DATE = "zzzzzzzzzzzzzzzzzzzzzzdate";
    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.time";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Date date=(Date)getArguments().getSerializable(ARG_DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity()) .inflate(R.layout.dialog_date, null);
        mDatePicker=v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);
        //Toast.makeText(getActivity(),"zzzz",Toast.LENGTH_SHORT);

        return new AlertDialog.Builder(getActivity())
                .setTitle(" onCreateDialog ")
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year=mDatePicker.getYear();
                        int month=mDatePicker.getMonth();
                        int day=mDatePicker.getDayOfMonth();
                        Toast.makeText(getActivity(),day+"  "+month,Toast.LENGTH_SHORT).show();
                        Date date=new GregorianCalendar(year,month,day).getTime();
                        sendResult(Activity.RESULT_OK,date);
                        Log.d("ddddddd,","zzzz");

                    }
                })
                .create();
    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(ARG_DATE,date);
        DatePickerFragment datePickerFragment=new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;

    }


    private void sendResult(int resultOk, Date date) {
        if(getTargetFragment()==null)
            return;
        Intent intent=new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultOk,intent);
    }
}
