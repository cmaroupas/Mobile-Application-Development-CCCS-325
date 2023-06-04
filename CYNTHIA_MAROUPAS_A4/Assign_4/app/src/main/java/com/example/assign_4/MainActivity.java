//Student Name: Cynthia Maroupas Student ID: 261119382


package com.example.assign_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LaneClosurePagerAdapter pagerAdapter;
    private ArrayList<LaneClosure> laneClosures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewPager and PagerAdapter
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new LaneClosurePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Setup TabLayout with ViewPager
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Method to add a LaneClosure to the list and update the adapter
    public void addLaneClosure(LaneClosure laneClosure) {
        laneClosures.add(laneClosure);
        pagerAdapter.updateLaneClosures(laneClosures);
    }

    // Method to remove a LaneClosure from the list and update the adapter
    public void removeLaneClosure(int position) {
        laneClosures.remove(position);
        pagerAdapter.updateLaneClosures(laneClosures);
    }

    // Method to switch to the LaneClosureListFragment
    public void showLaneClosureListFragment() {
        viewPager.setCurrentItem(0);
    }

    // Method to set the visibility of the add lane ImageView
    public void setAddLaneVisibility(int visibility) {
        ImageView addLaneImageView = findViewById(R.id.ic_add_lane);
        addLaneImageView.setVisibility(visibility);
    }

    // Custom PagerAdapter for handling Fragments and titles
    private class LaneClosurePagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public LaneClosurePagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

            // Add fragments and titles to the lists
            fragments.add(new LaneClosureListFragment());
            fragments.add(new SelectLaneClosureFragment());
            fragmentTitles.add("Lane Closures");
            fragmentTitles.add("Select Lane Closure");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }

        // Method to update the LaneClosures in the LaneClosureListFragment
        public void updateLaneClosures(List<LaneClosure> updatedLaneClosures) {
            LaneClosureListFragment fragment = (LaneClosureListFragment) fragments.get(0);
            fragment.updateLaneClosures(updatedLaneClosures);
        }
    }
}
