// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String API_KEY = "maB8nLWoNgr3fLJ5Nx3vVvKPUJ_d2GaG0OHnsUGFLD8bYSrTu93Szlh5Tf0epU0Vxb7ySyW1fdaeotk9kH5mg4icqlQJRqZE-B29q-K1zg5sjIOB4qVOdrs0kX-CZHYx";

    private EditText searchEditText;
    private Spinner sortSpinner;
    private RecyclerView restaurantRecyclerView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private YelpRepository yelpRepository;
    private List<Restaurant> restaurantList;
    private RestaurantAdapter restaurantAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        sortSpinner = findViewById(R.id.sortSpinner);
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Resize the close icon and set it to the EditText
        Drawable closeIcon = getResources().getDrawable(R.drawable.ic_close);
        if (closeIcon != null) {
            closeIcon.setBounds(0, 0, 40, 40);
            searchEditText.setCompoundDrawables(null, null, closeIcon, null);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_search) {
                    performSearch();
                } else if (itemId == R.id.nav_favorites) {
                    Intent favoritesIntent = new Intent(MainActivity.this, FavoritesActivity.class);
                    startActivity(favoritesIntent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        yelpRepository = new YelpRepository();

        restaurantList = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(this, restaurantList);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.sort_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                performSearch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        restaurantAdapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant restaurant) {
                showAddToFavoritesDialog(restaurant);
            }
        });

        searchEditText.setOnTouchListener(new View.OnTouchListener() {
            final int DRAWABLE_RIGHT = 2;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getX() >= (searchEditText.getWidth() - searchEditText.getPaddingRight() - searchEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        searchEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });


    }

    private void performSearch() {
        final String searchTerm = searchEditText.getText().toString();
        final String location = "Toronto";
        final String sortCriteria = sortSpinner.getSelectedItemPosition() == 0 ? "rating" : "price";

        Log.d("API_REQUEST", "Search Term: " + searchTerm);
        Log.d("API_REQUEST", "Location: " + location);
        Log.d("API_REQUEST", "Sort Criteria: " + sortCriteria);

        Call<RestaurantResponse> call;

        if ("price".equals(sortCriteria)) {
            call = yelpRepository.getRestaurants(searchTerm, location);
        } else {
            call = yelpRepository.getRestaurants(searchTerm, location, sortCriteria);
        }

        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful()) {
                    RestaurantResponse restaurantResponse = response.body();
                    if (restaurantResponse != null && restaurantResponse.getRestaurants() != null) {
                        Log.d("API_RESPONSE", "Number of restaurants fetched: " + restaurantResponse.getRestaurants().size());
                        List<Restaurant> sortedList = sortRestaurants(restaurantResponse.getRestaurants(), sortCriteria);
                        restaurantList.clear();
                        restaurantList.addAll(sortedList);
                        restaurantAdapter.notifyDataSetChanged();
                    } else {
                        Log.d("API_ERROR", "Null response or empty restaurant list");
                        Toast.makeText(MainActivity.this, "No restaurants found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("API_ERROR", "Response not successful. Code: " + response.code());
                    Toast.makeText(MainActivity.this, "Failed to fetch restaurants", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Log.d("API_ERROR", "API call failed", t);
                Toast.makeText(MainActivity.this, "Failed to connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Restaurant> sortRestaurants(List<Restaurant> restaurants, String sortCriteria) {
        switch (sortCriteria) {
            case "rating":
                Collections.sort(restaurants, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant r1, Restaurant r2) {
                        Double rating1 = r1.getRating();
                        Double rating2 = r2.getRating();
                        if (rating1 != null && rating2 != null) {
                            return Double.compare(rating2, rating1);
                        } else if (rating1 == null && rating2 != null) {
                            return -1;
                        } else if (rating1 != null && rating2 == null) {
                            return 1;
                        }
                        return 0;
                    }
                });
                break;
            case "price":
                Collections.sort(restaurants, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant r1, Restaurant r2) {
                        String price1 = r1.getPrice();
                        String price2 = r2.getPrice();
                        if (price1 != null && price2 != null) {
                            return convertPrice(price1) - convertPrice(price2);
                        } else if (price1 == null && price2 != null) {
                            return -1;
                        } else if (price1 != null && price2 == null) {
                            return 1;
                        }
                        return 0;
                    }
                });
                break;
        }
        return restaurants;
    }

    private int convertPrice(String price) {
        if (price.equals("$")) {
            return 1;
        } else if (price.equals("$$")) {
            return 2;
        } else if (price.equals("$$$")) {
            return 3;
        } else if (price.equals("$$$$")) {
            return 4;
        }
        return 0;
    }

    private void showAddToFavoritesDialog(final Restaurant restaurant) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to Favorites");
        builder.setMessage("Do you want to add this restaurant to favorites?");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addToFavorites(restaurant);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void addToFavorites(Restaurant restaurant) {
        FavoritesDatabaseHelper databaseHelper = new FavoritesDatabaseHelper(this);
        databaseHelper.addFavorite(restaurant);
        Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_search) {
            performSearch();
        } else if (itemId == R.id.nav_favorites) {
            Intent favoritesIntent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(favoritesIntent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
