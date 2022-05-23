package com.openevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.Event;
import com.openevents.API.User;
import com.openevents.API.receiveclass.PostResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventViewerActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_viewer);

        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("event");
        setTitle("Event " + event.getName() + ".");
        Log.d("Event", "Event id is " + event.getId());

        TextView eventName = findViewById(R.id.EventName);
        TextView eventDescription = findViewById(R.id.EventDescription);
        TextView eventStartDate = findViewById(R.id.EventStartDate);
        TextView eventLocation = findViewById(R.id.EventLocation);
        TextView eventCategory = findViewById(R.id.EventCategory);
        TextView eventEndDate = findViewById(R.id.EventEndDate);
        Button eventJoin = findViewById(R.id.JoinButton);
        Button editEvent = findViewById(R.id.EditEventButton);
        Button deleteEvent = findViewById(R.id.DeleteEventButton);

        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);
        SharedPreferences spref = getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = spref.getString("token", "");
        api.getUsersAssistingEvent("bearer " + token, "events/" +event.getId() + "/assistances").enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()){
                    Log.d("Assisting:", response.body().size() + " users.");
                    for(User u : response.body()){
                        Log.d("Assisting:", u.getName() + " / " + u.getId());
                        if (u.getId() == spref.getInt("id", 0)){
                            Log.d("Assisting:", "User " + spref.getInt("id", 0) + " is assisting.");
                            eventJoin.setText("Joined");
                            eventJoin.setBackgroundColor(getResources().getColor(R.color.red));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
        eventName.setText(event.getName());
        eventDescription.setText(event.getDescription());
        eventStartDate.setText(event.getEventStart_date());
        eventLocation.setText(event.getLocation());
        eventCategory.setText(event.getType());
        eventEndDate.setText(event.getEventEnd_date());
        eventJoin.setText("Join");

        if(event.getOwner_id() == spref.getInt("id", 0)){
            editEvent.setVisibility(View.VISIBLE);
            editEvent.setClickable(true);
            deleteEvent.setVisibility(View.VISIBLE);
            deleteEvent.setClickable(true);
        }
        editEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventViewerActivity.this, EditEventActivity.class);
                intent.putExtra("event", event);
                startActivity(intent);
            }
        });

        deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api.deleteEvent("bearer " + token, "events/" + event.getId()).enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(EventViewerActivity.this, "Evento borrado", Toast.LENGTH_SHORT).show();
                            Log.d("Delete Event:", "Event deleted.");
                            finish();
                        }else{
                            Toast.makeText(EventViewerActivity.this, "Evento no borrado por un error.", Toast.LENGTH_SHORT).show();
                            Log.d("Delete Event:", "Event not deleted.");
                        }
                    }
                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Log.d("Delete Event:", "Event not deleted.");
                        Toast.makeText(EventViewerActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        eventJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eventJoin.getText().equals("Join")) {
                    api.addUserAssistingEvent("bearer " + token,"events/" +event.getId() + "/assistances").enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("Assisting:", "User " + spref.getInt("id", 0) + " is now assisting.");
                                eventJoin.setText("Joined");
                                eventJoin.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                Toast.makeText(EventViewerActivity.this, "Error joining event. Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<PostResponse> call, Throwable t) {
                            Toast.makeText(EventViewerActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    api.deleteUserAssistingEvent("bearer " + token,"events/" +event.getId() + "/assistances").enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("Assisting:", "User " + spref.getInt("id", 0) + " is no longer assisting.");
                                eventJoin.setText("Join");
                                eventJoin.setBackgroundColor(getResources().getColor(R.color.purple_200));
                            } else {
                                Toast.makeText(EventViewerActivity.this, "Error leaving event. Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostResponse> call, Throwable t) {
                            Toast.makeText(EventViewerActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
