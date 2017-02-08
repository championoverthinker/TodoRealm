package com.example.shubham.todoapprealm;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.shubham.todoapprealm.Adapters.TodoAdapter;

/**
 * Created by shubham on 8/2/17.
 */

public class ItemTouchHelperClass extends ItemTouchHelper.Callback {

    TouchHelperListener listener;
    public interface TouchHelperListener{
        void onSwiped(int pos);
    }

    public ItemTouchHelperClass(TouchHelperListener listener) {

        this.listener = listener;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int upFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(upFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder.getAdapterPosition());
    }
}
