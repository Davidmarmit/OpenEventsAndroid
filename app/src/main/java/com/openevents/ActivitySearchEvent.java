package com.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openevents.API.Event;

import java.util.ArrayList;

public class ActivitySearchEvent extends AppCompatActivity {
    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Event> filteredEvents = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);
        setTitle("Search Events");

        CheckBox byLocation = findViewById(R.id.event_search_location_checkbox);
        CheckBox byDate = findViewById(R.id.event_search_date_checkbox);
        EditText search_input = findViewById(R.id.event_search_edit_text);
        search_input.setHint("By default: Name");

        byLocation.setOnClickListener(v -> {
            if (byLocation.isChecked()) {
                byDate.setChecked(false);
            }
        });
        byDate.setOnClickListener(v -> {
            if (byDate.isChecked()) {
                byLocation.setChecked(false);
            }
        });


        search_input.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                if(!events.isEmpty()){
                    if(byLocation.isChecked()){
                        filteredEvents.clear();
                        for(Event e : events){
                            if(e.getLocation().toLowerCase().contains(text.toLowerCase())){
                                filteredEvents.add(e);
                            }
                        }
                    }
                    else if(byDate.isChecked()){
                        filteredEvents.clear();
                        for(Event e : events){
                            if(e.getDate().contains(text)){
                                filteredEvents.add(e);
                            }
                        }
                    }
                    else {
                        filteredEvents.clear();
                        for(Event e : events){
                            if(e.getName().toLowerCase().contains(text.toLowerCase())){
                                filteredEvents.add(e);
                            }
                        }
                    }
                    adapter.setFilter(filteredEvents);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Intent intent = getIntent();
        events = (ArrayList<Event>) intent.getSerializableExtra("events");


        recyclerView = findViewById(R.id.recycler_view_eventsearch);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
    }
}
