package com.example.rodolfo.materialdesign.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rodolfo.materialdesign.R;
import com.example.rodolfo.materialdesign.adapters.UserAdapter;
import com.example.rodolfo.materialdesign.api.ApiClient;
import com.example.rodolfo.materialdesign.api.ApiInterface;
import com.example.rodolfo.materialdesign.models.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Path;

public class ListUsersActivity extends AppCompatActivity {
    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new UserAdapter(userList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareUsersData();
    }

    private void prepareUsersData() {
        Retrofit client = ApiClient.getClient();
        final ApiInterface service = client.create(ApiInterface.class);

        Call<List<User>> call = service.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList.addAll(response.body());
                for (User user : userList) {
                    Log.w("Retrofit", user.getUsername());
                }

                adapter.notifyDataSetChanged();
                
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });



    }

}
