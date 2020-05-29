package com.example.wesmart.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.wesmart.R;

public class LiveFragment extends Fragment {

    private NotificationManagerCompat notificationManager;

    ImageView onIndicatorImg, offIndicatorImg, alertDialogImg;
    TextView onIndicatorText, offIndicatorText, alertText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);

        notificationManager = NotificationManagerCompat.from(getActivity());

        onIndicatorText = view.findViewById(R.id.on);
        offIndicatorText = view.findViewById(R.id.off);
        alertText = view.findViewById(R.id.textView18);

        onIndicatorImg = view.findViewById(R.id.indicator3);
        offIndicatorImg = view.findViewById(R.id.indicator);
        alertDialogImg = view.findViewById(R.id.hzline);

        offIndicatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnAlertOn();
            }
        });

        onIndicatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnAlertOff();
            }
        });

        alertDialogImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialog();


            }
        });

        return view;
    }

    public void turnAlertOn() {
        onIndicatorText.setTextColor(Color.parseColor("#000000"));
        offIndicatorText.setTextColor(Color.parseColor("#495458"));

        onIndicatorImg.setVisibility(View.VISIBLE);
        offIndicatorImg.setVisibility(View.INVISIBLE);
    }

    public void turnAlertOff() {
        onIndicatorText.setTextColor(Color.parseColor("#495458"));
        offIndicatorText.setTextColor(Color.parseColor("#000000"));

        onIndicatorImg.setVisibility(View.INVISIBLE);
        offIndicatorImg.setVisibility(View.VISIBLE);

    }

}
