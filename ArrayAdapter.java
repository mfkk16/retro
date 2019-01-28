package com.example.calsys.retroapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.RecyclerViewHolder> {
    private List<Inventory> RVsList;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public RecyclerViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
        }
    }

    public ArrayAdapter(List<Inventory> RVsList) {
        this.RVsList = RVsList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Inventory e = RVsList.get(position);
        holder.name.setText(e.first_name());
    }

    @Override
    public int getItemCount() {
        return RVsList.size();
    }
}
