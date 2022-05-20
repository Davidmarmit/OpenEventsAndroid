package com.openevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.UserAux;
import com.openevents.API.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        Retrofit retrofit = Api_Class.getInstance();
        Api_Interface api = retrofit.create(Api_Interface.class);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()){
                    email.setError("No puede estar vacio.");
                }
                if (password.getText().toString().isEmpty()){
                    password.setError("No puede estar vacio.");
                }
                if (!password.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                    Log.d("Login", "onClick: " + email.getText().toString() + " " + password.getText().toString());
                    UserAux u = new UserAux(email.getText().toString(), password.getText().toString());
                    api.login(u).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()){
                                String token = response.body().getAccessToken();
                                Log.d("token: ", token);
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("token", Context.MODE_PRIVATE);
                                pref.edit().putString("token", token).apply();
                                pref.edit().putString("email", email.getText().toString()).apply();
                                Intent intent2 = new Intent(LoginActivity.this, HomeActivity.class);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent2);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.d("Error", "Error = " + t.getMessage());
                            Toast.makeText(LoginActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
