package com.example.adamsrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    TextView textViewHours;
    TextView textViewLocation;
    Button pickupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // initialise your views
        textViewHours = (TextView) view.findViewById(R.id.tv_home_hours);
        textViewLocation = (TextView) view.findViewById(R.id.tv_home_location);

        SpannableString contentHours = new SpannableString(textViewHours.getText().toString());
        contentHours.setSpan(new UnderlineSpan(), 0, contentHours.length(), 0);
        textViewHours.setText(contentHours);

        SpannableString contentLocation = new SpannableString(textViewLocation.getText().toString());
        contentLocation.setSpan(new UnderlineSpan(), 0, contentLocation.length(), 0);
        textViewLocation.setText(contentLocation);

        pickupButton = (Button) view.findViewById(R.id.btn_circle_pickUp);

        pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getContext(), PickUp_menu.class));
            }
        });

    }

}
