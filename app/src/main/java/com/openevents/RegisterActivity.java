package com.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.receiveclass.RegisterResponse;
import com.openevents.API.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText name = findViewById(R.id.name);
        EditText lastName = findViewById(R.id.last_name);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || name.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Todos los campos deben estar llenos.", Toast.LENGTH_SHORT).show();
                }else{
                    User user = new User(name.getText().toString(), lastName.getText().toString(),email.getText().toString(), password.getText().toString() , "Coolgers");
                    Log.d("User", user.getName() + " / " + user.getLastname() + " / " + user.getEmail() + " / " + user.getImage());
                    Retrofit retrofit = Api_Class.getInstance();
                    Api_Interface api = retrofit.create(Api_Interface.class);
                    api.register(user).enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registro completado, puedes logearte.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }else{
                                String error = response.message();
                                Log.d("Error", error);
                                Toast.makeText(RegisterActivity.this, "Error al registrarse, verifica los campos.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Error al conectarse a la API.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
