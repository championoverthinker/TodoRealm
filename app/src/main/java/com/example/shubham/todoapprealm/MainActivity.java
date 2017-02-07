package com.example.shubham.todoapprealm;

import android.app.DatePickerDialog;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.shubham.todoapprealm.Adapters.TodoAdapter;
import com.example.shubham.todoapprealm.models.todoItem;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements TodoAdapter.Listener, View.OnClickListener {
    @BindView(R.id.todoList) RecyclerView recyclerView;
    TodoAdapter adapter;
    private Realm realm;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm=Realm.getDefaultInstance();
        ButterKnife.bind(this);
        setUpRecyclerView();
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.addTodo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClicked();
            }
        });



    }

//    @OnClick(R.id.addTodo)
    void fabClicked(){
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setMessage("Enter new todo");
//        final View v= getLayoutInflater().inflate(R.layout.dialog,null);
//        builder.setView(v);
//
//
//        Button b=(Button)v.findViewById(R.id.submit);
//
//        final AlertDialog dialog=builder.create();
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                realm.executeTransactionAsync(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//
//                        TextInputEditText newTodo=(TextInputEditText)v.findViewById(R.id.writeTodo);
//                        String todo=newTodo.getText().toString();
////                        if(todo!=null)
////                        item.setTodoText("new task");
//
//                        todoItem item=realm.createObject(todoItem.class);
//                        item.setTodoText(todo);
//                        item.setDate(date);
//                        dialog.dismiss();
//
//                    }
//                });
//            }
//        });
//
//        Button setDate=(Button)v.findViewById(R.id.setDate);
//        final Calendar c = Calendar.getInstance();
//        int mYear = c.get(Calendar.YEAR);
//        int mMonth = c.get(Calendar.MONTH);
//        int mDay = c.get(Calendar.DAY_OF_MONTH);
//        setDate.setOnClickListener(MainActivity.this);
////        setDate.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////            }
////        });
//
//
//        dialog.show();

        DialogFragment addDialog=new AddDialogFragment();
        addDialog.show(getSupportFragmentManager(),"dialog");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void setUpRecyclerView() {
        adapter=new TodoAdapter(this,realm.where(todoItem.class).findAllAsync(),true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setListener(this);

    }

    @Override
    public void LongClicked(final todoItem item) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<todoItem> realmResults=realm.where(todoItem.class).findAll();
                todoItem item1=realmResults.get(realmResults.indexOf(item));
                item1.deleteFromRealm();
            }
        });
    }

    @Override
    public void onClick(View view) {
//        DatePickerDialog datePickerDialog=new DatePickerDialog(this);
//        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                Calendar cal=Calendar.getInstance();
//                cal.set(Calendar.YEAR,i);
//                cal.set(Calendar.MONTH,i1);
//                cal.set(Calendar.DAY_OF_MONTH,i2);
//                date =cal.getTime();
//
//            }
//        });
//
//        datePickerDialog.show();

//        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//
//
//
//                    }
//                }, 1997,28,8);
//        datePickerDialog1.show();
    }
}
