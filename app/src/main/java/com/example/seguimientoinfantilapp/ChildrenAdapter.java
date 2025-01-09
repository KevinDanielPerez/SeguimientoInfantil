package com.example.seguimientoinfantilapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.ChildViewHolder> {

    private Context context;
    private ArrayList<Child> childrenList;

    public ChildrenAdapter(Context context, ArrayList<Child> childrenList) {
        this.context = context;
        this.childrenList = childrenList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        Child child = childrenList.get(position);
        holder.nameTextView.setText(child.getNombre());
        holder.ageTextView.setText(child.getFechaNacimiento());  // Muestra la fecha de nacimiento, no la edad
    }


    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, ageTextView;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.child_name);
            ageTextView = itemView.findViewById(R.id.child_age);
        }
    }
}
