package com.openevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditProfileActivity extends android.app.Activity {
    User user;
    private Retrofit retrofit = Api_Class.getInstance();
    private Api_Interface api = retrofit.create(Api_Interface.class);

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        SharedPreferences spref = this.getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = spref.getString("token", null);
        String email_value = spref.getString("email", null);


        EditText name = (EditText) findViewById(R.id.NameEdit);
        EditText lastname = (EditText) findViewById(R.id.LastNameEdit);
        EditText email = (EditText) findViewById(R.id.EmailEdit);
        EditText password = (EditText) findViewById(R.id.PasswordEdit);
        EditText image = (EditText) findViewById(R.id.ImageEdit);
        Button save = (Button) findViewById(R.id.SaveButton);

        api.searchUser("Bearer " + token, email_value).enqueue(new retrofit2.Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()){
                    Log.d("response", call.request().url().toString());
                    name.setText(response.body().get(0).getName());
                    lastname.setText(response.body().get(0).getLastname());
                    email.setText(response.body().get(0).getEmail());;
                    image.setText(response.body().get(0).getImage());
                } else {
                    showToast(getApplicationContext(), "No se ha podido obtener la información del usuario");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.d("Query","Query = " + call.request().url());
                Log.d("sr",t.getMessage());
                showToast(getApplicationContext(), "Error de connexion con la API");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_value = name.getText().toString();
                String lastname_value = lastname.getText().toString();
                String email_value = email.getText().toString();
                String password_value = password.getText().toString();
                String image_value = image.getText().toString();
                if (password_value.isEmpty() || name_value.isEmpty() ||lastname_value.isEmpty() || email_value.isEmpty() || image_value.isEmpty()) {
                    showToast(getApplicationContext(), "Ningun campo puede estar vacio");
                }else{
                    user = new User(name_value, lastname_value, email_value, password_value, image_value);

                    api.updateUser("Bearer " + token, user).enqueue(new retrofit2.Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(response.isSuccessful()){
                                Log.d("response", call.request().url().toString());
                                showToast(getApplicationContext(), "Usuario editado correctamente");
                            } else {
                                Log.d("message",response.message());
                                showToast(getApplicationContext(), "No se ha podido editar el usuario");
                            }
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d("Query","Query = " + call.request().url());
                            Log.d("sr",t.getMessage());
                            showToast(getApplicationContext(), "Error de connexion con la API");
                        }
                    });
                }

            }
        });
    }
}
