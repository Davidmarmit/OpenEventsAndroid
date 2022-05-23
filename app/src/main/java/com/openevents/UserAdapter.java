package com.openevents;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openevents.API.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<User> users;

    public UserAdapter (ArrayList<User> users) {
        this.users = users;
    }

    public void setFilter(ArrayList<User> newUsers) {
        users = new ArrayList<>();
        users.addAll(newUsers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_viewer_list, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        String fullname = user.getName() + " " + user.getLastname();
        holder.username.setText(fullname);
        holder.email.setText(user.getEmail());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = users.get(holder.getAdapterPosition());
                Intent intent = new Intent(view.getContext(), UserViewerActivity.class);
                intent.putExtra("user", user);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView username;
        public TextView email;
        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            EditText editText = itemView.findViewById(R.id.user_search_edit_text);
            linearLayout = itemView.findViewById(R.id.user_list_item);
            imageView = itemView.findViewById(R.id.image_user);
            username = itemView.findViewById(R.id.user_fullname);
            email = itemView.findViewById(R.id.user_email);
        }
    }
}
