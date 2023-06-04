//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.assign_4;

public class LaneClosureOption {
    private String name;
    private int iconResId;

    // Constructor to initialize a LaneClosureOption with name and icon resource ID
    public LaneClosureOption(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }

    // Getter method for retrieving the name of the LaneClosureOption
    public String getName() {
        return name;
    }

    // Getter method for retrieving the icon resource ID of the LaneClosureOption
    public int getIconResId() {
        return iconResId;
    }
}