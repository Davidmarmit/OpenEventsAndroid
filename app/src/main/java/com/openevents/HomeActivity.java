package com.openevents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.Event;
import com.openevents.API.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Cerrar OpenEvents")
                .setMessage("¿Seguro que quieres cerrar la aplicación?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(HomeActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Mantener sesión")
                                .setMessage("¿Quieres mantener la sesión abierta?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.clear();
                                        editor.apply();
                                        finish();
                                    }
                                })
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                })
                                .show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = pref.getString("token", null);
        String email = pref.getString("email", null);
        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);

        api.searchUser("Bearer " + token, email).enqueue(new retrofit2.Callback<ArrayList<User>>() {

            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    Log.d("User info", response.body().get(0).getId() + " " + response.body().get(0).getName() + " " + response.body().get(0).getLastname() + " " + response.body().get(0).getEmail());
                    pref.edit().putInt("id", response.body().get(0).getId()).apply();
                    pref.edit().putString("name", response.body().get(0).getName()).apply();
                    pref.edit().putString("lastname", response.body().get(0).getLastname()).apply();

                } else {
                    Toast.makeText(HomeActivity.this, "Error al obtener la informacion del usuario logeado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

}
