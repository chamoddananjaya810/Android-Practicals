package com.example.uicomponentpactical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uicomponentpactical.R;
import com.example.uicomponentpactical.activity.ProfileActivity;
import com.example.uicomponentpactical.entity.User;

import java.security.PublicKey;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> userList;
    Context context;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        User user = userList.get(position);
        holder.userTextView.setText(user.getName());
        holder.cityTextView.setText(user.getName());

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
                //    intent.putExtra();

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userTextView;
        TextView cityTextView;

        Button viewBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userTextView = itemView.findViewById(R.id.userTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            viewBtn = itemView.findViewById(R.id.viewBtn);


        }
    }
}
