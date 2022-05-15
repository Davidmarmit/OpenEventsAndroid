package com.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.User;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText Name = findViewById(R.id.name);
        EditText LastName = findViewById(R.id.last_name);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || Name.getText().toString().isEmpty() || LastName.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Todos los campos deben estar llenos.", Toast.LENGTH_SHORT).show();
                }else{
                    User user = new User(email.getText().toString(), password.getText().toString(), Name.getText().toString(), LastName.getText().toString(),"Coolgers");

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

}
