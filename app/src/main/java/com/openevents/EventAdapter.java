package com.openevents;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openevents.API.Event;

import java.io.Serializable;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private ArrayList<Event> events;

    public EventAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event evento = events.get(position);
        holder.title.setText(evento.getName());
        if(holder.title.getText().equals("No has creado ningun evento")){
            ImageView img = holder.imageView.findViewById(R.id.image_event);
            img.setVisibility(View.INVISIBLE);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Event event_selected = events.get(holder.getAdapterPosition());
                Intent intent = new Intent(view.getContext(), ActivityEventViewer.class);
                intent.putExtra("event",event_selected);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.event_list_item);
            imageView = itemView.findViewById(R.id.image_event);
            title = itemView.findViewById(R.id.title_event);
        }
    }
}
