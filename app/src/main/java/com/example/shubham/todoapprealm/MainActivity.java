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
import android.support.v7.widget.helper.ItemTouchHelper;
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

public class MainActivity extends AppCompatActivity  {
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


    void fabClicked(){

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
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        ItemTouchHelper.Callback callback = new ItemTouchHelperClass(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



    }

//    @Override
//    public void LongClicked(final todoItem item) {
//
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                RealmResults<todoItem> realmResults=realm.where(todoItem.class).findAll();
//                todoItem item1=realmResults.get(realmResults.indexOf(item));
//                item1.deleteFromRealm();
//            }
//        });
//    }

//    @Override
//    public void onClick(View view) {
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
//    }
}
