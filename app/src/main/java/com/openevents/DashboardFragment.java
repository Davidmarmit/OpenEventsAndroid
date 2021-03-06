package com.openevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.Event;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<Event> filteredEvents = new ArrayList<Event>();
    private ArrayList<Event> bestEventsArray = new ArrayList<Event>();
    private ArrayList<Event> categoryEvents = new ArrayList<Event>();


    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button sports = rootView.findViewById(R.id.sportsbutton);
        Button music = rootView.findViewById(R.id.musicbutton);
        Button food = rootView.findViewById(R.id.foodbutton);
        Button art = rootView.findViewById(R.id.artsbutton);
        Button others = rootView.findViewById(R.id.otherbutton);
        Button politics = rootView.findViewById(R.id.politicsbutton);
        Button film = rootView.findViewById(R.id.filmbutton);
        Button travel = rootView.findViewById(R.id.travelbutton);
        Button business = rootView.findViewById(R.id.businessbutton);
        Button bestEvents = rootView.findViewById(R.id.bestEvents);
        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);
        SharedPreferences spref = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = spref.getString("token", "");
        FloatingActionButton fab = rootView.findViewById(R.id.fab_home);

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sports.getText().equals("Sports")){
                    sports.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Sports")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    sports.setText("Sports");
                }
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(music.getText().equals("Music")){
                    music.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Music")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    music.setText("Music");
                }
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(food.getText().equals("Food")){
                    food.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Food")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    food.setText("Food");
                }
            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(art.getText().equals("Arts")){
                    art.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Arts")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    art.setText("Arts");
                }
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(others.getText().equals("Other")){
                    others.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Other")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    others.setText("Other");
                }
            }
        });

        politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(politics.getText().equals("Politics")){
                    politics.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Politics")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    politics.setText("Politics");
                }
            }
        });

        film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(film.getText().equals("Film")){
                    film.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Film")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    film.setText("Film");
                }
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(travel.getText().equals("Travel")){
                    travel.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Travel")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    travel.setText("Travel");
                }
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(business.getText().equals("Business")){
                    business.setText("All Events");
                    categoryEvents.clear();
                    if(!events.isEmpty()) {
                        for (Event e : events) {
                            if (e.getType().equals("Business")) {
                                categoryEvents.add(e);
                            }
                        }
                        if(categoryEvents.isEmpty()){
                            Toast.makeText(getContext(), "No se han encontrado eventos.", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setFilter(categoryEvents);
                    }
                }else{
                    adapter.setFilter(events);
                    business.setText("Arts");
                }
            }
        });

        bestEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bestEvents.getText().toString().equals("Best Events")){
                    bestEvents.setText("All Events");
                    api.getBestEvents("Bearer " + token).enqueue(new Callback<ArrayList<Event>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                            if (response.isSuccessful()) {
                                bestEventsArray = response.body();
                                adapter.setFilter(bestEventsArray);
                            }else{
                                Toast.makeText(getActivity(), "Error obteniendo los mejores eventos.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                            Toast.makeText(getActivity(), R.string.API_Failure, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    bestEvents.setText("Best Events");
                    adapter.setFilter(events);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivitySearchEvent.class);
                intent.putExtra("events", events);
                startActivity(intent);
            }
        });

        api.getEvents("Bearer" + token).enqueue(new Callback<ArrayList<Event>>() {

            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.isSuccessful()) {
                    events.clear();
                    events.addAll(response.body());

                    recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new EventAdapter(events);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "No hay actividades", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Log.d("Query","Query = " + call.request().url());
                Log.d("Error", "Error = " + t.getMessage());
                Toast.makeText(getActivity(), R.string.API_Failure, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}