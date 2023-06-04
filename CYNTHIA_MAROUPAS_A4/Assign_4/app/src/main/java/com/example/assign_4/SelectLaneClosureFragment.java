// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.assign_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectLaneClosureFragment extends Fragment {

    private ImageView selectedTypeImageView;
    private ImageView selectedStatusImageView;
    private boolean isTypeSelected = false;
    private boolean isStatusSelected = false;
    private static final int SELECTED_COLOR = R.color.selected_icon_background;
    private static final int UNSELECTED_COLOR = R.color.unselected_icon_background;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_lane_closure, container, false);

        // Initialize ImageView references
        ImageView shoulderImageView = view.findViewById(R.id.ic_shoulder);
        ImageView hovImageView = view.findViewById(R.id.ic_hov);
        ImageView medianImageView = view.findViewById(R.id.ic_median);
        ImageView rampImageView = view.findViewById(R.id.ic_ramp);
        ImageView goreImageView = view.findViewById(R.id.ic_gore);

        ImageView closedImageView = view.findViewById(R.id.ic_closed);
        ImageView unknownImageView = view.findViewById(R.id.ic_unknown);
        ImageView rollingImageView = view.findViewById(R.id.ic_rolling);
        ImageView blockedImageView = view.findViewById(R.id.ic_blocked);
        ImageView alternatingImageView = view.findViewById(R.id.ic_alternating);
        ImageView intermittentImageView = view.findViewById(R.id.ic_intermittent);
        ImageView lanesAffectedImageView = view.findViewById(R.id.ic_lanesaffected);

        // Set click listeners for type and status ImageView
        shoulderImageView.setOnClickListener(v -> onTypeImageClicked(v));
        hovImageView.setOnClickListener(v -> onTypeImageClicked(v));
        medianImageView.setOnClickListener(v -> onTypeImageClicked(v));
        rampImageView.setOnClickListener(v -> onTypeImageClicked(v));
        goreImageView.setOnClickListener(v -> onTypeImageClicked(v));

        closedImageView.setOnClickListener(v -> onStatusImageClicked(v));
        unknownImageView.setOnClickListener(v -> onStatusImageClicked(v));
        rollingImageView.setOnClickListener(v -> onStatusImageClicked(v));
        blockedImageView.setOnClickListener(v -> onStatusImageClicked(v));
        alternatingImageView.setOnClickListener(v -> onStatusImageClicked(v));
        intermittentImageView.setOnClickListener(v -> onStatusImageClicked(v));
        lanesAffectedImageView.setOnClickListener(v -> onStatusImageClicked(v));

        // Set click listeners for cancel and save buttons
        view.findViewById(R.id.btn_cancel).setOnClickListener(v -> onCancelButtonClicked());
        view.findViewById(R.id.btn_save).setOnClickListener(v -> onSaveButtonClicked());

        return view;
    }

    private void onTypeImageClicked(View view) {
        ImageView clickedImageView = (ImageView) view;

        if (selectedTypeImageView == clickedImageView) {
            // Deselect the type
            selectedTypeImageView = null;
            clickedImageView.setBackgroundResource(R.drawable.round_light_grey_bg);
            isTypeSelected = false;
        } else {
            // Deselect the previously selected type
            if (selectedTypeImageView != null) {
                selectedTypeImageView.setBackgroundResource(R.drawable.round_light_grey_bg);
            }

            // Select the new type
            selectedTypeImageView = clickedImageView;
            selectedTypeImageView.setBackgroundResource(SELECTED_COLOR);
            isTypeSelected = true;
        }
    }

    private void onStatusImageClicked(View view) {
        ImageView clickedImageView = (ImageView) view;

        if (selectedStatusImageView == clickedImageView) {
            // Deselect the status
            selectedStatusImageView = null;
            clickedImageView.setBackgroundResource(R.drawable.round_light_grey_bg);
            isStatusSelected = false;
        } else {
            // Deselect the previously selected status
            if (selectedStatusImageView != null) {
                selectedStatusImageView.setBackgroundResource(R.drawable.round_light_grey_bg);
            }

            // Select the new status
            selectedStatusImageView = clickedImageView;
            selectedStatusImageView.setBackgroundResource(SELECTED_COLOR);
            isStatusSelected = true;
        }
    }

    private void onCancelButtonClicked() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.setAddLaneVisibility(View.GONE);
            activity.showLaneClosureListFragment();
        }
    }

    private void onSaveButtonClicked() {
        if (!isTypeSelected || !isStatusSelected) {
            Toast.makeText(getContext(), "Please select both type and status.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the selected type and status
        String selectedType = selectedTypeImageView != null ? getTypeOrStatusFromImageView(selectedTypeImageView) : "";
        String selectedStatus = selectedStatusImageView != null ? getTypeOrStatusFromImageView(selectedStatusImageView) : "";

        String location = "";
        LaneClosure laneClosure = new LaneClosure(location, selectedType, selectedStatus);

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.addLaneClosure(laneClosure);
            activity.setAddLaneVisibility(View.GONE);
            activity.showLaneClosureListFragment();
        }
    }

    private String getTypeOrStatusFromImageView(ImageView imageView) {
        if (imageView == null) {
            return "";
        }

        int id = imageView.getId();
        if (id == R.id.ic_shoulder) {
            return "Shoulder";
        } else if (id == R.id.ic_hov) {
            return "HOV";
        } else if (id == R.id.ic_median) {
            return "Median";
        } else if (id == R.id.ic_ramp) {
            return "Ramp";
        } else if (id == R.id.ic_gore) {
            return "Gore";
        } else if (id == R.id.ic_closed) {
            return "Closed";
        } else if (id == R.id.ic_unknown) {
            return "Unknown";
        } else if (id == R.id.ic_rolling) {
            return "Rolling";
        } else if (id == R.id.ic_blocked) {
            return "Blocked";
        } else if (id == R.id.ic_alternating) {
            return "Alternating";
        } else if (id == R.id.ic_intermittent) {
            return "Intermittent";
        } else if (id == R.id.ic_lanesaffected) {
            return "Lanes Affected";
        } else {
            return "";
        }
    }
}
