package com.openevents;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        EditText name = findViewById(R.id.EventEdit);
        EditText description = findViewById(R.id.DescriptionEdit);
        Spinner category = findViewById(R.id.CategoryEdit);
        EditText start = findViewById(R.id.StartDateEdit);
        EditText end = findViewById(R.id.EndDateEdit);

        Button submit = findViewById(R.id.SaveButton);

    }
}
