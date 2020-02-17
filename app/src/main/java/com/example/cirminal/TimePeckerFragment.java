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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.example.cirminal.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class TimePeckerFragment extends DialogFragment {
    public static final String EXTRA_TIME = "com.bignerdranch.android.criminalintent.time";
    TimePicker timePicker;
    private static final String ARG_TIME = "zzzzzzzzzzzzzzzzzzzzzztime";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
final Time time=(Time)getArguments().getSerializable(ARG_TIME);

        View view= LayoutInflater.from(getActivity()).inflate(R.layout.time_dailog,null);
        timePicker=view.findViewById(R.id.time_view);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(time);
        int h = calendar.get(Calendar.HOUR);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);

        return new AlertDialog.Builder(getActivity()).setView(view).setTitle(" Time Dialog ")
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int h = timePicker.getHour();
                        int m = timePicker.getMinute();
                        Time t=new Time(h,m,0);
                        sendResult(Activity.RESULT_OK,t);
                    }
                }).create();
    }
    public static TimePeckerFragment newInstance(Time time){
        Bundle args=new Bundle();
        args.putSerializable(ARG_TIME,time);
        TimePeckerFragment timePeckerFragment=new TimePeckerFragment();
        timePeckerFragment.setArguments(args);
        return timePeckerFragment;

    }
    private void sendResult(int resultCode, Time time) {
        if (getTargetFragment() == null) { return; }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
