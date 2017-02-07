package com.example.shubham.todoapprealm.Adapters;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubham.todoapprealm.MainActivity;
import com.example.shubham.todoapprealm.R;
import com.example.shubham.todoapprealm.models.todoItem;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by shubham on 7/2/17.
 */

public class TodoAdapter extends RealmRecyclerViewAdapter<todoItem,TodoAdapter.TodoHolder> {
    Context context;
    Listener listener;



    public interface Listener{
        void LongClicked(todoItem item);
    }


    public void setListener(Listener listener){
        this.listener=listener;
    }

    public TodoAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<todoItem> data, boolean autoUpdate) {
        super(context,data, true);
        this.context=context;
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodoHolder(LayoutInflater.from(context).inflate(R.layout.todo_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
            todoItem item=  getData().get(position);
        holder.todo.setText(item.getTodoText());
        if(item.getDate()!=null){
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            String date = format.format(item.getDate());
        holder.date.setText(date);}
        else
            holder.date.setText("");

        if(item.getTime()!=null){
            holder.time.setText(item.getTime());
        }
    }

    public class TodoHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.todo) TextView todo;
        @BindView(R.id.date)TextView date;
        @BindView(R.id.time)TextView time;
        public TodoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    todoItem item=getData().get(getAdapterPosition());
                    listener.LongClicked(item);
                    return true;
                }
            });

        }
    }


}
