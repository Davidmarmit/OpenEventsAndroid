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
import com.openevents.API.sendclass.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditProfileActivity extends android.app.Activity {
    private SendName sn;
    private SendLastname sln;
    private SendEmail se;
    private SendPassword sp;
    private SendImage si;
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
        ;


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
                    name.setHint(response.body().get(0).getName());
                    lastname.setHint(response.body().get(0).getLastname());
                    email.setHint(response.body().get(0).getEmail());;
                    image.setHint(response.body().get(0).getImage());
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
                if(name.getText().toString().equals("") && lastname.getText().toString().equals("") && email.getText().toString().equals("") && password.getText().toString().equals("") && image.getText().toString().equals("")){
                    showToast(getApplicationContext(), "No has introducido ningun dato");
                }else{
                    if(!name.getText().toString().equals("")){
                        sn = new SendName(name.getText().toString());
                        api.updateUserName("Bearer " + token,sn).enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    showToast(getApplicationContext(), "Nombre actualizado");
                                }else{
                                    Log.d("response", response.message());
                                    showToast(getApplicationContext(), "Error al actualizar el nombre");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                showToast(getApplicationContext(), "Error de connexion con la API");
                            }
                        });
                    }
                    if(!lastname.getText().toString().equals("")){
                        sln = new SendLastname(lastname.getText().toString());
                        api.updateUserLastname("Bearer " + token,sln).enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    showToast(getApplicationContext(), "Apellido actualizado");
                                }else{
                                    Log.d("response", response.message());
                                    showToast(getApplicationContext(), "Error al actualizar el Apellido");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                showToast(getApplicationContext(), "Error de connexion con la API");
                            }
                        });
                    }
                    if(!email.getText().toString().equals("")){
                        se = new SendEmail(email.getText().toString());
                        api.updateUserEmail("Bearer " + token,se).enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    showToast(getApplicationContext(), "Email actualizado");
                                }else{
                                    Log.d("response", response.message());
                                    showToast(getApplicationContext(), "Error al actualizar el Email");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                showToast(getApplicationContext(), "Error de connexion con la API");
                            }
                        });
                    }
                    if(!password.getText().toString().equals("")){
                        sp = new SendPassword(password.getText().toString());
                        api.updateUserPassword("Bearer " + token,sp).enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    showToast(getApplicationContext(), "Password actualizado");
                                }else{
                                    Log.d("response", response.message());
                                    showToast(getApplicationContext(), "Error al actualizar el Password");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                showToast(getApplicationContext(), "Error de connexion con la API");
                            }
                        });
                    }
                    if (!image.getText().toString().equals("")){
                        si = new SendImage(image.getText().toString());
                        api.updateUserImage("Bearer " + token,si).enqueue(new retrofit2.Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    showToast(getApplicationContext(), "Image actualizado");
                                }else{
                                    Log.d("response", response.message());
                                    showToast(getApplicationContext(), "Error al actualizar el Image");
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                showToast(getApplicationContext(), "Error de connexion con la API");
                            }
                        });
                    }
                }
            }
        });
    }
}
