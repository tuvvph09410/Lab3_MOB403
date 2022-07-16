package com.example.lab2_mob403.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.lab2_mob403.AsyncTank.AsyncTankBai1;
import com.example.lab2_mob403.R;


public class Fragment_Bai1 extends Fragment {
    ListView lv_Bai1;
    AsyncTankBai1 asyncTankBai1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bai1, container, false);

        initViewBy(view);

        initListView();
        return view;
    }

    private void initListView() {
        asyncTankBai1 = new AsyncTankBai1(lv_Bai1, getContext());
        asyncTankBai1.execute();
    }


    private void initViewBy(View view) {
        lv_Bai1 = view.findViewById(R.id.lv_bai1);

    }
}