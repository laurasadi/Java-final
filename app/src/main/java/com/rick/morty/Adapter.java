package com.rick.morty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Rick> data;

    public static final String ENTRY="com.rick.morty.ENTRY";

    // constructor to initialize context and data sent from SearchActivity
    public Adapter(Context context, List<Rick> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_rick, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        Rick current = data.get(position);
        myHolder.textimage.setText(current.getimage());
        myHolder.textgender.setText("Last update: " + current.getgender());
        myHolder.textid.setText("id: " + current.getid());
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textimage;
        TextView textgender;
        TextView textid;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textimage = (TextView) itemView.findViewById(R.id.textimage);
            textgender = (TextView) itemView.findViewById(R.id.textgender);
            textid= (TextView) itemView.findViewById(R.id.textid);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {
            Intent goToNewEntryActivity = new Intent(context, NewEntryActivity.class);
            int itemPosition = getAdapterPosition();
            Rick rick = data.get(itemPosition);

            goToNewEntryActivity.putExtra(ENTRY, rick);
            context.startActivity(goToNewEntryActivity);
        }
    }
}
