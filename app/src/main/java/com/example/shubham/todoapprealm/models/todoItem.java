package com.example.shubham.todoapprealm.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by shubham on 7/2/17.
 */

public class todoItem extends RealmObject {
    String todoText;
    Date date;

    public todoItem(String todoText, Date date) {
        this.todoText = todoText;
        this.date = date;
    }

    public todoItem() {
        //empty
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
