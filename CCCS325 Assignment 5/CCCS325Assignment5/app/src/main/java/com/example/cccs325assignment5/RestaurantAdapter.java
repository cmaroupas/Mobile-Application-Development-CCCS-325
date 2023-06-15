// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private List<Restaurant> restaurantList;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList != null ? restaurantList : new ArrayList<>();
        sortRestaurantsByPrice();
    }

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the restaurant item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        if (restaurantList == null || restaurantList.size() == 0) {
            // Handle empty or null restaurant list
            return;
        }

        Restaurant restaurant = restaurantList.get(position);

        // Set click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    // Notify the listener about the click event
                    onItemClickListener.onItemClick(restaurantList.get(adapterPosition));
                }
                setSelectedPosition(adapterPosition);
            }
        });

        // Set long click listener for the item view
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete restaurant");
                    builder.setMessage("Are you sure you want to delete " + restaurantList.get(adapterPosition).getName() + "?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Remove the restaurant from the list
                            restaurantList.remove(adapterPosition);
                            // Notify the adapter about the removal
                            notifyItemRemoved(adapterPosition);
                            // Notify the adapter about the range change to update the remaining items
                            notifyItemRangeChanged(adapterPosition, restaurantList.size());
                        }
                    });
                    builder.setNegativeButton("No", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                return true;
            }
        });

        // Bind the restaurant data to the ViewHolder
        holder.nameTextView.setText(restaurant.getName());
        setRatingStars(holder.ratingLinearLayout, restaurant.getRating());

        if (restaurant.getCategories() != null && !restaurant.getCategories().isEmpty()) {
            holder.categoryTextView.setText(restaurant.getCategories().get(0).getTitle());
        } else {
            holder.categoryTextView.setText("");
        }

        holder.phoneTextView.setText(restaurant.getPhone());

        if (restaurant.getLocation() != null) {
            holder.addressTextView.setText(restaurant.getLocation().getAddress1());
        } else {
            holder.addressTextView.setText("");
        }

        holder.priceTextView.setText(restaurant.getPrice());

        // Set up Glide to load and display the restaurant image
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.error_image); // Error image if loading fails

        Glide.with(holder.itemView.getContext())
                .load(restaurant.getImageUrl())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@NonNull GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // Handle image loading failure
                        Log.e("GLIDE_ERROR", "Image loading failed", e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // No additional actions needed when the image is successfully loaded
                        return false;
                    }
                })
                .into(holder.restaurantImageView);
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList != null ? restaurantList : new ArrayList<>();
        sortRestaurantsByPrice();
        notifyDataSetChanged();
    }

    public void sortRestaurantsByPrice() {
        Log.d("SORTING", "Before sorting:");
        for (Restaurant r : restaurantList) {
            Log.d("SORTING", r.getName() + ": " + r.getPrice());
        }

        // Sort the restaurant list by price using a custom Comparator
        Collections.sort(restaurantList, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                String price1 = r1.getPrice();
                String price2 = r2.getPrice();

                // Handle cases where the price might be null
                if (price1 == null && price2 == null) {
                    return 0;
                } else if (price1 == null) {
                    return 1;
                } else if (price2 == null) {
                    return -1;
                }

                // Compare based on the length of the price string
                return Integer.compare(price1.length(), price2.length());
            }
        });

        Log.d("SORTING", "After sorting:");
        for (Restaurant r : restaurantList) {
            Log.d("SORTING", r.getName() + ": " + r.getPrice());
        }

        notifyDataSetChanged();
    }

    private void setRatingStars(LinearLayout linearLayout, Double rating) {
        linearLayout.removeAllViews(); // Clear existing star icons

        // Null check for rating
        if (rating == null) {
            Log.e("RATING_ERROR", "Rating was null for restaurant.");
            return;
        }

        int roundedRating = (int) Math.round(rating);

        // Add star icons to the LinearLayout based on the rounded rating value
        for (int i = 0; i < roundedRating; i++) {
            ImageView starIcon = (ImageView) LayoutInflater.from(linearLayout.getContext())
                    .inflate(R.layout.star_icon, linearLayout, false);
            linearLayout.addView(starIcon); // Add the star icon to the LinearLayout
        }
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private LinearLayout ratingLinearLayout;
        private TextView categoryTextView;
        private TextView phoneTextView;
        private TextView addressTextView;
        private TextView priceTextView;
        private ImageView restaurantImageView;

        RestaurantViewHolder(View itemView) {
            super(itemView);
            // Initialize views in the restaurant item layout
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ratingLinearLayout = itemView.findViewById(R.id.ratingLinearLayout);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            restaurantImageView = itemView.findViewById(R.id.restaurantImageView);
        }
    }

    public Restaurant getSelectedRestaurant() {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            return restaurantList.get(selectedPosition);
        }
        return null;
    }

    public void setSelectedPosition(int position) {
        int previousSelectedPosition = selectedPosition;
        selectedPosition = position;

        // Notify the adapter about the item changes to update the UI
        if (previousSelectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(previousSelectedPosition);
        }
        if (selectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(selectedPosition);
        }
    }
}
