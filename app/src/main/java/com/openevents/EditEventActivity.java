package com.openevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.openevents.API.sendclass.event.*;

import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditEventActivity extends AppCompatActivity {
    private SendEventImage sei;
    private SendEventCategory sec;
    private SendEventDescription sed;
    private SendEventLocation sel;
    private SendEventName sen;
    private SendEventStartDate sesd;
    private SendEventEndDate seed;
    private SendMaxParticipants semp;
    private Integer f_attemps = 0;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);
        SharedPreferences spref = getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = spref.getString("token", "");
        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("event");
        Integer id = event.getId();

        EditText eventName = findViewById(R.id.EventEdit);
        EditText eventDescription = findViewById(R.id.DescriptionEdit);
        EditText eventLocation = findViewById(R.id.LocationEdit);
        EditText eventStart = findViewById(R.id.StartDateEdit);
        EditText eventEnd = findViewById(R.id.EndDateEdit);
        EditText eventImage = findViewById(R.id.ImageEdit);
        EditText eventnparticipants = findViewById(R.id.nparticipatorsEdit);
        Spinner eventCategory = findViewById(R.id.CategoryEdit);
        Button eventSave = findViewById(R.id.SaveButton);

        eventName.setHint(event.getName());
        eventDescription.setHint(event.getDescription());
        eventLocation.setHint(event.getLocation());
        eventStart.setHint(event.getEventStart_date());
        eventEnd.setHint(event.getEventEnd_date());
        eventImage.setHint(event.getImage());
        eventnparticipants.setHint(String.valueOf(event.getN_participators()));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.EventCategories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventCategory.setAdapter(adapter);

        eventSave.setOnClickListener(v -> {
            if(!eventName.getText().toString().isEmpty()){
                sen = new SendEventName(eventName.getText().toString());
                api.updateEventName("Bearer" + token, id, sen).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()) {
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando el nombre del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventDescription.getText().toString().isEmpty()){
                sed = new SendEventDescription(eventDescription.getText().toString());
                api.updateEventDescription("Bearer" + token, id, sed).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando la descripción del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventLocation.getText().toString().isEmpty()){
                sel = new SendEventLocation(eventLocation.getText().toString());
                api.updateEventLocation("Bearer" + token, id, sel).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando la ubicación del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventStart.getText().toString().isEmpty()){
                sesd = new SendEventStartDate(eventStart.getText().toString());
                api.updateEventStartDate("Bearer" + token, id, sesd).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando la fecha de inicio del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventEnd.getText().toString().isEmpty()){
                seed = new SendEventEndDate(eventEnd.getText().toString());
                api.updateEventEndDate("Bearer" + token, id, seed).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando la fecha de fin del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventImage.getText().toString().isEmpty()){
                sei = new SendEventImage(eventImage.getText().toString());
                api.updateEventImage("Bearer" + token, id, sei).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando la imagen del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventnparticipants.getText().toString().isEmpty()){
                semp = new SendMaxParticipants(Integer.parseInt(eventnparticipants.getText().toString()));
                api.updateEventParticipants("Bearer" + token, id, semp).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Toast.makeText(EditEventActivity.this, "Error actualizando el número máximo de participantes del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(!eventCategory.getSelectedItem().toString().isEmpty()){
                sec = new SendEventCategory(eventCategory.getSelectedItem().toString());
                api.updateEventCategory("Bearer" + token, id, sec).enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()){
                            f_attemps++;
                            Log.d("Edit event", response.message());
                            Toast.makeText(EditEventActivity.this, "Error actualizando la categoría del evento.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        f_attemps++;
                        Toast.makeText(EditEventActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
