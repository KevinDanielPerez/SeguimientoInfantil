package com.example.seguimientoinfantilapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChildActivityAdapter extends RecyclerView.Adapter<ChildActivityAdapter.ActivityViewHolder> {

    private Context context;
    private ArrayList<ChildActivity> activityList;

    public ChildActivityAdapter(Context context, ArrayList<ChildActivity> activityList) {
        this.context = context;
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        ChildActivity activity = activityList.get(position);
        Log.d("ChildActivityAdapter", "Actividades: " + activity.getDescripcion() + " | Fecha: " + activity.getFecha());
        holder.descriptionTextView.setText(activity.getDescripcion());
        holder.dateTextView.setText(activity.getFecha());
    }


    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView, dateTextView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.activity_description);
            dateTextView = itemView.findViewById(R.id.activity_date);
        }
    }
}
