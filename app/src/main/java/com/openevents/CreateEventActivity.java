package com.openevents;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);
        SharedPreferences spref = getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = spref.getString("token", "");

        setTitle("Create Event");
        EditText name = findViewById(R.id.EventEdit);
        EditText description = findViewById(R.id.DescriptionEdit);
        Spinner category = findViewById(R.id.CategoryEdit);
        EditText start = findViewById(R.id.StartDateEdit);
        EditText end = findViewById(R.id.EndDateEdit);
        EditText location = findViewById(R.id.LocationEdit);
        EditText n_participants = findViewById(R.id.nparticipatorsEdit);
        EditText image = findViewById(R.id.ImageEdit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.EventCategories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        Button submit = findViewById(R.id.SaveButton);

        submit.setOnClickListener(v -> {
            String eventName = name.getText().toString();
            String eventDescription = description.getText().toString();
            String eventCategory = category.getSelectedItem().toString();
            String eventStart = start.getText().toString();
            String eventEnd = end.getText().toString();
            String eventLocation = location.getText().toString();
            String eventNParticipants = n_participants.getText().toString();
            String eventImage = image.getText().toString();
            if (eventName.isEmpty() || eventDescription.isEmpty() || eventCategory.isEmpty() || eventStart.isEmpty() || eventEnd.isEmpty() || eventLocation.isEmpty() || eventNParticipants.isEmpty() || eventImage.isEmpty()) {
                Toast.makeText(this, "Porfavor rellena todos los campos.", Toast.LENGTH_SHORT).show();
                if (eventName.isEmpty()) {
                    name.setError("Campo obligatorio");
                }
                if (eventDescription.isEmpty()) {
                    description.setError("Campo obligatorio");
                }
                if (eventStart.isEmpty()) {
                    start.setError("Campo obligatorio");
                }
                if (eventEnd.isEmpty()) {
                    end.setError("Campo obligatorio");
                }
                if (eventLocation.isEmpty()) {
                    location.setError("Campo obligatorio");
                }
                if (eventNParticipants.isEmpty()) {
                    n_participants.setError("Campo obligatorio");
                }
                if (eventImage.isEmpty()) {
                    image.setError("Campo obligatorio");
                }
            }
            Event event = new Event(eventName, eventImage, eventLocation, eventDescription, eventStart, eventEnd, Integer.valueOf(eventNParticipants), eventCategory);
            api.postEvent("Bearer " + token, event).enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    if (response.isSuccessful()) {
                        finish();
                    } else {
                        Toast.makeText(CreateEventActivity.this, "Error creando evento.", Toast.LENGTH_SHORT).show();
                        Log.d("Error", response.message() + " " + response.body());
                    }
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Toast.makeText(CreateEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
