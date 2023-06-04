// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.assign_4;

public class LaneClosure {
    private String location;
    private String type;
    private String status;
    private String reportedTime;
    private boolean isSelected;

    // Constructor to initialize a LaneClosure object with location, type, and status
    public LaneClosure(String location, String type, String status) {
        this.location = location;
        this.type = type;
        this.status = status;
        this.reportedTime = "";
        this.isSelected = false;
    }

    // Getter method for retrieving the location of the LaneClosure
    public String getLocation() {
        return location;
    }

    // Getter method for retrieving the type of the LaneClosure
    public String getType() {
        return type;
    }

    // Getter method for retrieving the status of the LaneClosure
    public String getStatus() {
        return status;
    }

    // Getter method for retrieving the reported time of the LaneClosure
    public String getReportedTime() {
        return reportedTime;
    }

    // Getter method for retrieving the selection status of the LaneClosure
    public boolean isSelected() {
        return isSelected;
    }

    // Setter method for setting the selection status of the LaneClosure
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
