package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    
    private City currentCity;
    private static final String KEY = "city";

    interface AddCityDialogListener {
        void addCity(City city);
        void onEditCity(City city);
    }
    private AddCityDialogListener listener;

    public static AddCityFragment newInstance(City city) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
             try {
                 listener = (AddCityDialogListener) context;
             } catch (ClassCastException e) {
                 throw new RuntimeException(context + " must implement AddCityDialogListener with new methods");
             }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        if (getArguments() != null) {
            currentCity = (City) getArguments().getSerializable(KEY);
        }

        String dialogTitle;
        String positiveButtonText;
        
        if (currentCity != null) {
            dialogTitle = "Edit City";
            positiveButtonText = "OK";
            editCityName.setText(currentCity.getName());
            editProvinceName.setText(currentCity.getProvince());
        } else {
            dialogTitle = "Add a city";
            positiveButtonText = "Add";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(dialogTitle)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(positiveButtonText, (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    if (currentCity != null) {
                        currentCity.setName(cityName);
                        currentCity.setProvince(provinceName);
                        listener.onEditCity(currentCity);
                    } else {
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .create();
    }
}