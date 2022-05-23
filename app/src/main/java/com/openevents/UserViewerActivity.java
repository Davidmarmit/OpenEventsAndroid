package com.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.openevents.API.User;

public class UserViewerActivity extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView image;
    TextView id;
    TextView lastname;
    User user;
    Button friend;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewer);



        name = findViewById(R.id.NameEdit);
        email = findViewById(R.id.EmailEdit);
        image = findViewById(R.id.ImageEdit);
        id = findViewById(R.id.IdEdit);
        lastname = findViewById(R.id.LastNameEdit);
        friend = findViewById(R.id.FriendButton);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        setTitle("User: " + user.getName() + " " + user.getLastname());
        name.setText(user.getName());
        email.setText(user.getEmail());
        image.setText(user.getImage());
        id.setText(String.valueOf(user.getId()));
        lastname.setText(user.getLastname());


        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(friend.getText().equals("Send Friend Request")){
                    friend.setText("Cancel Friend Request");
                    friend.setBackgroundColor(getResources().getColor(R.color.red));
                }else{
                    friend.setText("Send Friend Request");
                    friend.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
            }
        });
    }
}
