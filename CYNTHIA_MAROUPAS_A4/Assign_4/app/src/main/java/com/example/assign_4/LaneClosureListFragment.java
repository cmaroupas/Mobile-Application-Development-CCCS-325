//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.assign_4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LaneClosureListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private LaneClosureListAdapter mAdapter;
    private TextView mInstructionText;
    private ImageView mAddLaneImageView;
    private List<LaneClosure> laneClosures;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lane_closure_list, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mInstructionText = view.findViewById(R.id.instruction_text);
        mAddLaneImageView = view.findViewById(R.id.ic_add_lane);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        laneClosures = new ArrayList<>();
        mAdapter = new LaneClosureListAdapter(laneClosures, this::onTrashIconClick);
        mRecyclerView.setAdapter(mAdapter);

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean isFirstLaunch = sharedPreferences.getBoolean("first_launch", true);

        if (isFirstLaunch) {
            laneClosures.add(new LaneClosure("Location1", "Type1", "ReportedTime1"));
            laneClosures.add(new LaneClosure("Location2", "Type2", "ReportedTime2"));
            mAdapter.notifyDataSetChanged();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("first_launch", false);
            editor.apply();
        }

        updateInstructionTextVisibility();

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.setAddLaneVisibility(View.VISIBLE);
        }
    }

    private void updateInstructionTextVisibility() {
        if (laneClosures.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            mInstructionText.setVisibility(View.VISIBLE);
            mAddLaneImageView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mInstructionText.setVisibility(View.GONE);
            mAddLaneImageView.setVisibility(View.GONE);
        }
    }

    public void updateLaneClosures(List<LaneClosure> updatedLaneClosures) {
        if (laneClosures != null) {
            laneClosures.clear();
            laneClosures.addAll(updatedLaneClosures);
            mAdapter.notifyDataSetChanged();
            updateInstructionTextVisibility();
        }
    }

    public void updateSelectedLaneClosure(String selectedType, String selectedStatus) {
        for (LaneClosure laneClosure : laneClosures) {
            laneClosure.setSelected(false);
        }

        for (LaneClosure laneClosure : laneClosures) {
            if (laneClosure.getType().equals(selectedType) && laneClosure.getReportedTime().equals(selectedStatus)) {
                laneClosure.setSelected(true);
                break;
            }
        }

        mAdapter.notifyDataSetChanged();
    }

    private void onTrashIconClick(int position) {
        laneClosures.remove(position);
        mAdapter.notifyItemRemoved(position);
        updateInstructionTextVisibility();
    }
}
