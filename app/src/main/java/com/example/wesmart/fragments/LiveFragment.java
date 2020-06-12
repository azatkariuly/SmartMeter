package com.example.wesmart.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.example.wesmart.stomp.StompManager;
import com.example.wesmart.stomp.Telemetry;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Objects;

public class LiveFragment extends Fragment {
    private static final String LOG_TAG = "StompClient";
    private static final String WS_SERVER = "wss://boiling-coast-60811.herokuapp.com/telemetry-websocket/websocket";
    private static final String WS_TOPIC = "/sensor/1/telemetry";

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    private static final Gson GSON_CONVERTER = new Gson();

    private NotificationManagerCompat notificationManager;

    private ImageView onIndicatorImg, offIndicatorImg, alertDialogImg;
    private TextView onIndicatorText, offIndicatorText, alertText;

    private TextView voltageText, currentText;

    private StompManager stompManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);

        notificationManager = NotificationManagerCompat.from(getActivity());

        onIndicatorText = view.findViewById(R.id.on);
        offIndicatorText = view.findViewById(R.id.off);
        alertText = view.findViewById(R.id.textView18);

        voltageText = view.findViewById(R.id.voltage);
        currentText = view.findViewById(R.id.amper);

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

        stompManager = new StompManager(WS_SERVER);
        stompManager.connect();
        stompManager.subscribeTopic(WS_TOPIC, stompMessage -> {
            Log.d(LOG_TAG, stompMessage.getPayload());
            setTelemetry(stompMessage.getPayload());
        }, throwable -> {
            Log.d(LOG_TAG, Objects.requireNonNull(throwable.getMessage()));
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        stompManager.disconnect();
        stompManager = null;
        super.onDestroyView();
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

    @SuppressLint("SetTextI18n")
    private void setTelemetry(String payload) {
        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            Telemetry telemetry = GSON_CONVERTER.fromJson(payload, Telemetry.class);
            voltageText.setText(DECIMAL_FORMAT.format(telemetry.getVoltage()) + " V");
            currentText.setText(DECIMAL_FORMAT.format(telemetry.getCurrent()) + " A");
        });
    }
}
