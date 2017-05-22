package com.example.rodolfo.materialdesign.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rodolfo.materialdesign.R;
import com.example.rodolfo.materialdesign.models.User;

import java.util.List;

/**
 * Created by rodolfomg on 13/05/17.
 * User Adapter
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> usersList;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, email, phone;

        ViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            email = (TextView) view.findViewById(R.id.email);
            phone = (TextView) view.findViewById(R.id.phone);
        }
    }


    public UserAdapter(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        Log.w("Adapter", user.getUsername());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

}
