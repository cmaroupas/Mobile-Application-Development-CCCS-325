// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.assign_4;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LaneClosureListAdapter extends RecyclerView.Adapter<LaneClosureListAdapter.ViewHolder> {

    public interface OnTrashIconClickListener {
        void onTrashIconClick(int position);
    }

    private List<LaneClosure> laneClosures;
    private OnTrashIconClickListener trashIconClickListener;
    private int selectedPos = RecyclerView.NO_POSITION;

    public LaneClosureListAdapter(List<LaneClosure> laneClosures, OnTrashIconClickListener trashIconClickListener) {
        this.laneClosures = laneClosures;
        this.trashIconClickListener = trashIconClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lane_closure_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LaneClosure laneClosure = laneClosures.get(position);
        String type = laneClosure.getType();
        String status = laneClosure.getStatus();

        // Set the name TextView with the type and status of the lane closure
        holder.nameTextView.setText(type + ", " + status);

        // Set the icon based on the lane closure type
        Drawable typeIcon;
        switch (type) {
            case "Shoulder":
                typeIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_shoulder);
                break;
            case "HOV":
                typeIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_hov);
                break;
            case "Median":
                typeIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_median);
                break;
            case "Ramp":
                typeIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_ramp);
                break;
            case "Gore":
                typeIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_gore);
                break;
            default:
                typeIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_open);
                break;
        }

        // Set the icon based on the lane closure status
        Drawable statusIcon;
        switch (status) {
            case "Closed":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_closed);
                break;
            case "Unknown":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_unknown);
                break;
            case "Rolling":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_rolling);
                break;
            case "Blocked":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_blocked);
                break;
            case "Alternating":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_alternating);
                break;
            case "Intermittent":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_intermittent);
                break;
            case "Lanes Affected":
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_lanesaffected);
                break;
            default:
                statusIcon = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.ic_open);
                break;
        }

        // Create the layered drawable with three icons: background, type, and status
        Drawable[] layers = new Drawable[3];
        layers[0] = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.lane_closure_shoulder);
        layers[1] = typeIcon;
        layers[2] = statusIcon;
        LayerDrawable layerDrawable = new LayerDrawable(layers);

        // Set the layered drawable as the image for the icon ImageView
        holder.iconImageView.setImageDrawable(layerDrawable);

        // Set the selected state for the item
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return laneClosures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ImageView iconImageView;

        public ViewHolder(View view) {
            super(view);

            // Initialize views for each item in the RecyclerView
            nameTextView = view.findViewById(R.id.nameTextView);
            iconImageView = view.findViewById(R.id.iconImageView);

            // Add trash icon click listener
            view.findViewById(R.id.ic_trash).setOnClickListener(v -> {
                if (trashIconClickListener != null) {
                    trashIconClickListener.onTrashIconClick(getAdapterPosition());
                }
            });

            // Set click listener for the item view
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update the selected position and notify the adapter of the change
                    notifyItemChanged(selectedPos);
                    selectedPos = getLayoutPosition();
                    notifyItemChanged(selectedPos);
                }
            });
        }
    }
}
