package com.example.wesmart.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wesmart.Graphs.DayGraph;
import com.example.wesmart.Graphs.MonthGraph;
import com.example.wesmart.Graphs.WeekGraph;
import com.example.wesmart.Graphs.YearGraph;
import com.example.wesmart.R;

public class DeviceFragment extends Fragment {

    Button dayBtn, weekBtn, monthBtn, yearBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);

        loadFragment(new DayGraph());

        dayBtn = view.findViewById(R.id.btn_day);
        weekBtn = view.findViewById(R.id.btn_week);
        monthBtn = view.findViewById(R.id.btn_month);
        yearBtn = view.findViewById(R.id.btn_year);

        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnColor(1);
                loadFragment(new DayGraph());
            }
        });

        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnColor(2);
                loadFragment(new WeekGraph());
            }
        });

        monthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnColor(3);
                loadFragment(new MonthGraph());
            }
        });

        yearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnColor(4);
                loadFragment(new YearGraph());
            }
        });

        return view;
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container1, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    private void changeBtnColor(int i) {

        if (i==1) {
            dayBtn.setBackgroundColor(Color.parseColor("#69ab1d"));
            dayBtn.setTextColor(Color.parseColor("#ffffff"));
        } else {
            dayBtn.setBackgroundColor(Color.parseColor("#bdbdbd"));
            dayBtn.setTextColor(Color.parseColor("#000000"));
        }

        if (i==2) {
            weekBtn.setBackgroundColor(Color.parseColor("#69ab1d"));
            weekBtn.setTextColor(Color.parseColor("#ffffff"));
        } else {
            weekBtn.setBackgroundColor(Color.parseColor("#bdbdbd"));
            weekBtn.setTextColor(Color.parseColor("#000000"));
        }

        if (i==3) {
            monthBtn.setBackgroundColor(Color.parseColor("#69ab1d"));
            monthBtn.setTextColor(Color.parseColor("#ffffff"));
        } else {
            monthBtn.setBackgroundColor(Color.parseColor("#bdbdbd"));
            monthBtn.setTextColor(Color.parseColor("#000000"));
        }

        if (i==4) {
            yearBtn.setBackgroundColor(Color.parseColor("#69ab1d"));
            yearBtn.setTextColor(Color.parseColor("#ffffff"));
        } else {
            yearBtn.setBackgroundColor(Color.parseColor("#bdbdbd"));
            yearBtn.setTextColor(Color.parseColor("#000000"));
        }
    }
}
