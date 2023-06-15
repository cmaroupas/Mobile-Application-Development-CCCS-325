//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.navigation.NavigationView;


import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RestaurantAdapter.OnItemClickListener {
    private FavoritesDatabaseHelper db; // Database helper instance
    private List<Restaurant> favoriteRestaurants; // List of favorite restaurants
    private RecyclerView favoritesRecyclerView; // RecyclerView to display the favorite restaurants
    private RestaurantAdapter adapter; // Adapter for the RecyclerView

    private DrawerLayout drawer; // DrawerLayout for the navigation drawer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new FavoritesDatabaseHelper(this); // Create an instance of the database helper
        favoriteRestaurants = db.getAllFavorites(); // Initialize the list with data from the database

        favoritesRecyclerView = findViewById(R.id.favorites_recycler_view);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(this, favoriteRestaurants); // Create the adapter with the list of favorite restaurants
        favoritesRecyclerView.setAdapter(adapter); // Set the adapter for the RecyclerView

        drawer = findViewById(R.id.favorites_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.favorites_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayFavoriteRestaurants(); // Display the favorite restaurants in the RecyclerView
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_search) {
            Intent searchIntent = new Intent(this, MainActivity.class);
            startActivity(searchIntent);
            finish();
        } else if (itemId == R.id.nav_favorites) {
            // Stay in this activity and show favorite restaurants
            displayFavoriteRestaurants();
            Toast.makeText(this, "Showing Favorites", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Method to display the favorite restaurants in the RecyclerView
    private void displayFavoriteRestaurants() {
        // Retrieve the favorite restaurants from the database
        favoriteRestaurants = db.getAllFavorites();

        // Set the favorite restaurants in the adapter
        adapter.setRestaurantList(favoriteRestaurants);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Restaurant restaurant) {
        if (restaurant.getRating() == null) {
            Log.e("FavoritesActivity", "Cannot add restaurant with null rating to favorites.");
            Toast.makeText(this, "Cannot add restaurant with null rating to favorites", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = db.addFavorite(restaurant); // Add the selected restaurant to favorites in the database
        if (result == -1) {
            Log.e("FavoritesActivity", "Failed to add restaurant to favorites.");
        } else {
            Log.i("FavoritesActivity", "Restaurant added to favorites with id: " + result);
        }
        Toast.makeText(this, "Restaurant added to favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayFavoriteRestaurants(); // Refresh the list of favorite restaurants when the activity resumes
    }
}
