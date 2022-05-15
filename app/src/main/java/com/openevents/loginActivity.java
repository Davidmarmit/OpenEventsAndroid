package com.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.Api_Class;
import com.openevents.API.Api_Interface;
import com.openevents.API.User;
import com.openevents.API.UserAux;
import com.openevents.API.loginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class loginActivity extends AppCompatActivity {
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
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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
                    api.login(u).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<loginResponse> response) {
                            if (response.isSuccessful()){
                                 String token = response.body().getToken();
                                System.out.println(token);
                                Log.d("token: ", token);
                            }
                        }

                        @Override
                        public void onFailure(Call<loginResponse> call, Throwable t) {
                            Toast.makeText(loginActivity.this, R.string.API_Failure, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                //Intent intent2 = new Intent(loginActivity.this, homeActivity.class);
                //                    // TODO intent.putExtra("user", "user");
                //                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //                    startActivity(intent2);
                //                    finish();
            }
        });
    }
}
