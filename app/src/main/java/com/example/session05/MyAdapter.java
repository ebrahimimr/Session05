package com.example.session05;

import static androidx.core.content.ContextCompat.startForegroundService;

import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> mData;
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter() {
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent, false);
        MyViewHolder holder = new MyViewHolder(v);

        holder.container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });

        holder.btnTitle.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(holder.container.getContext(), TestForegroundService.class);
            @Override
            public void onClick(View v) {
                //do button click work here with
                // mData.get( viewHolder.getAdapterPosition() );
                //Log.d("position", "onItemClick: "+holder.getAdapterPosition());

                Integer pos =holder.getAdapterPosition();
                if (pos == 0) {
                    startForegroundService(holder.container.getContext(), intent);
                }
                if (pos == 1) {
                    holder.container.getContext().stopService(intent);
                }
                if (pos == 2) {
                    Toast.makeText(holder.container.getContext(), "BroadcastReceiver{ACTION_AIRPLANE_MODE_CHANGED}Registerd!", Toast.LENGTH_SHORT).show();
                    TestBroadcastReceiver recevier =new TestBroadcastReceiver();
                    IntentFilter intentFilter =new IntentFilter();
                    intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                    holder.container.getContext().registerReceiver(recevier,intentFilter);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        switch (position) {
            case 0 :holder.btnTitle.setText("Foreground Service Strat");
                break;
            case 1 :holder.btnTitle.setText("Foreground Service Stop");
                break;
            case 2 :holder.btnTitle.setText("Broadcast");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public Button btnTitle;
        public View container;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView;
            btnTitle = itemView.findViewById(R.id.btnTitle);
        }
    }
}
