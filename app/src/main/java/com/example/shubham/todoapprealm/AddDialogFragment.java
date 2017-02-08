package com.example.shubham.todoapprealm;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.shubham.todoapprealm.models.todoItem;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by shubham on 7/2/17.
 */

public class AddDialogFragment extends DialogFragment{
    Realm realm;
    Date date;
    String time;
    TextInputEditText newTodo;

    int calendarHour,calendarMinute,year,month,day;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Enter new todo");
        final View v= getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
        builder.setView(v);
        newTodo=(TextInputEditText) v.findViewById(R.id.writeTodo);

        realm=Realm.getDefaultInstance();

        Button b=(Button)v.findViewById(R.id.submit);

        final AlertDialog dialog=builder.create();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String todo=newTodo.getText().toString();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

//                        TextInputEditText newTodo=(TextInputEditText)v.findViewById(R.id.writeTodo);

//                        if(todo!=null)
//                        item.setTodoText("new task");

                        todoItem item=realm.createObject(todoItem.class);
                        item.setTodoText(todo);
                        item.setDate(date);
                        item.setTime(time);


                    }
                });
                scheduleReminder(todo);
                dialog.dismiss();
            }
        });

        Button setDate=(Button)v.findViewById(R.id.setDate);
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        final int hour=c.get(Calendar.HOUR_OF_DAY);
        final int minute=c.get(Calendar.MINUTE);
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        date=c.getTime();
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),android.R.style.Theme_Material_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Calendar cal=Calendar.getInstance();
                        cal.set(Calendar.YEAR,i);
                        cal.set(Calendar.MONTH,i1);
                        cal.set(Calendar.DAY_OF_MONTH,i2);
                        year=i;
                        month=i1;
                        day=i2;
                        date =cal.getTime();

                    }
                },mYear,mMonth,mDay);


                datePickerDialog.show();
            }
        });
//
        time=hour+":"+minute;
        calendarMinute=minute;
        calendarHour=hour;
        Button setTime=(Button)v.findViewById(R.id.setTime);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time=i+":"+i1;
                        calendarHour=i;
                        calendarMinute=i1;
                    }
                }, hour, minute,true);
                timePickerDialog.show();
            }

        });




        return dialog;
    }

    private void scheduleReminder(String todo) {
        Intent i=new Intent(getActivity(),AlarmReceiver.class);
        i.putExtra("Notif Message",todo);
//        Log.i("edittext",newTodo.getText().toString());
        AlarmManager am=(AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi= PendingIntent.getBroadcast(getActivity(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.YEAR,date.getYear());
//        calendar.set(Calendar.MONTH,date.getMonth());
//        calendar.set(Calendar.DAY_OF_MONTH,date.getDay());
        calendar.set(Calendar.HOUR_OF_DAY,calendarHour);
        calendar.set(Calendar.MINUTE,calendarMinute);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        Log.i("date",month +""+year+""+day);
        am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pi);
    }
}
