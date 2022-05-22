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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.Event;
import com.openevents.API.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<Event> ownedEvents = new ArrayList<Event>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment events.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_myevents, container, false);

        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);
        SharedPreferences spref = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = spref.getString("token", "");
        String email_value = spref.getString("email", null);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);

        api.getEvents("Bearer" + token).enqueue(new Callback<ArrayList<Event>>() {

            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.isSuccessful()) {
                    events.clear();
                    events.addAll(response.body());

                    api.searchUser("Bearer " + token, email_value).enqueue(new retrofit2.Callback<ArrayList<User>>() {
                        @Override
                        public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                            if(response.isSuccessful()){
                                Log.d("response", call.request().url().toString());
                                for (int i = 0; i < events.size(); i++) {
                                    if(events.get(i).getOwner_id() == response.body().get(0).getId()){
                                        ownedEvents.add(events.get(i));
                                    }
                                }
                                if(ownedEvents.size() == 0){
                                    ownedEvents.add(0,new Event("No has creado ningun evento",null,null,null,null,null,null,null));
                                }
                                recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_myevents);
                                layoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(layoutManager);
                                adapter = new EventAdapter(ownedEvents);
                                recyclerView.setAdapter(adapter);
                            } else {
                                showToast(getActivity(), "No se ha podido obtener la información del usuario");
                            }
                        }
                        @Override
                        public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                            Log.d("Query","Query = " + call.request().url());
                            Log.d("sr",t.getMessage());
                            showToast(getActivity(), "Error de connexion con la API");
                        }
                    });


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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityCreateEvent.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}