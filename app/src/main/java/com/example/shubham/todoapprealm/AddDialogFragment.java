package com.example.shubham.todoapprealm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Enter new todo");
        final View v= getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
        builder.setView(v);


        realm=Realm.getDefaultInstance();

        Button b=(Button)v.findViewById(R.id.submit);

        final AlertDialog dialog=builder.create();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        TextInputEditText newTodo=(TextInputEditText)v.findViewById(R.id.writeTodo);
                        String todo=newTodo.getText().toString();
//                        if(todo!=null)
//                        item.setTodoText("new task");

                        todoItem item=realm.createObject(todoItem.class);
                        item.setTodoText(todo);
                        item.setDate(date);
                        dialog.dismiss();

                    }
                });
            }
        });

        Button setDate=(Button)v.findViewById(R.id.setDate);
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
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
                        date =cal.getTime();

                    }
                },mYear,mMonth,mDay);


                datePickerDialog.show();
            }
        });
//        setDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        return dialog;
    }
}
