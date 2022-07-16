package com.example.lab2_mob403;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lab2_mob403.Fragment.Fragment_Bai1;
import com.example.lab2_mob403.Fragment.Fragment_Bai2;
import com.example.lab2_mob403.Fragment.Fragment_Bai3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViewByID();

        this.initBottomNav();
    }

    private void initBottomNav() {
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        positionNav(R.id.menu_bai1);
        loadFragment(new Fragment_Bai1());
    }

    private void initViewByID() {
        this.bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_bai1:
                loadFragment(new Fragment_Bai1());
                positionNav(R.id.menu_bai1);
                break;
            case R.id.menu_bai2:
                loadFragment(new Fragment_Bai2());
                positionNav(R.id.menu_bai2);
                break;
            case R.id.menu_bai3:
                loadFragment(new Fragment_Bai3());
                positionNav(R.id.menu_bai3);
                break;
        }

        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void positionNav(int Item) {
        this.bottomNavigationView.getMenu().findItem(Item).setChecked(true);
    }
}