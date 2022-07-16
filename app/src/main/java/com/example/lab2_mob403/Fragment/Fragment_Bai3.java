package com.example.lab2_mob403.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_mob403.Adapter.AdapterRecyclerview;
import com.example.lab2_mob403.ApiClient;
import com.example.lab2_mob403.Interface.RequestInterface;
import com.example.lab2_mob403.Model.Movies;
import com.example.lab2_mob403.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Bai3 extends Fragment {
    RecyclerView recyclerView;
    private List<Movies> moviesList;
    private AdapterRecyclerview adapterRecyclerview;
    private static final String TAG = Fragment_Bai3.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bai3, container, false);
        initViewById(view);


        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJson();
    }

    private void loadJson() {
        RequestInterface requestInterface = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Movies>> call = requestInterface.getJSONResponse();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
               moviesList=new ArrayList<>(response.body());
               Log.e(TAG,String.valueOf(moviesList.size()));
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    private void initViewById(View view) {
        recyclerView = view.findViewById(R.id.rv_bai3);
    }
}