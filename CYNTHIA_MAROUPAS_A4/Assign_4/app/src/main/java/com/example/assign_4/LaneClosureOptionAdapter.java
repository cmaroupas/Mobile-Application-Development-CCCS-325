// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.assign_4;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LaneClosureOptionAdapter extends RecyclerView.Adapter<LaneClosureOptionAdapter.ViewHolder> {

    private LaneClosureOption selectedOption;
    private List<LaneClosureOption> options;
    private LaneClosureOptionClickListener clickListener;
    private OnTrashIconClickListener trashIconClickListener;

    public interface LaneClosureOptionClickListener {
        void onLaneClosureOptionClick(int position);
    }

    public interface OnTrashIconClickListener {
        void onTrashIconClick(int position);
    }

    public LaneClosureOptionAdapter(List<LaneClosureOption> options, LaneClosureOptionClickListener clickListener, OnTrashIconClickListener trashIconClickListener) {
        this.options = options;
        this.clickListener = clickListener;
        this.trashIconClickListener = trashIconClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lane_closure_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LaneClosureOption option = options.get(position);

        // Set the name and icon of the option
        holder.nameTextView.setText(option.getName());
        holder.iconImageView.setImageResource(option.getIconResId());

        // Set the background color based on the selected state
        if (option.equals(selectedOption)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#800080")); // Color code for purple
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        // Handle click events on the item
        holder.itemView.setOnClickListener(v -> {
            if (option.equals(selectedOption)) {
                selectedOption = null;
            } else {
                selectedOption = option;
            }
            notifyDataSetChanged();
            if (clickListener != null) {
                clickListener.onLaneClosureOptionClick(position);
            }
        });

        // Handle click events on the trash icon
        holder.trashImageView.setOnClickListener(v -> {
            if (trashIconClickListener != null) {
                trashIconClickListener.onTrashIconClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public LaneClosureOption getItem(int position) {
        return options.get(position);
    }

    public void setSelectedOption(LaneClosureOption option) {
        selectedOption = option;
    }

    public LaneClosureOption getSelectedOption() {
        return selectedOption;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView iconImageView;
        ImageView trashImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views for each item in the RecyclerView
            nameTextView = itemView.findViewById(R.id.nameTextView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            trashImageView = itemView.findViewById(R.id.ic_trash); // Initialized the trash icon
        }
    }
}
