package com.test.test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.test.test.R;
import com.test.test.api.API;
import com.test.test.api.RetrofitClient;
import com.test.test.fragment.PhotoFragment;
import com.test.test.fragment.TextFragment;
import com.test.test.fragment.WebFragment;
import com.test.test.model.MenuResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    private List<com.test.test.model.Menu> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getMenu();

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getMenu() {
        API service = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<MenuResponse> call = service.getMenu();
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<MenuResponse> call, @NonNull Response<MenuResponse> response) {
                if (!response.isSuccessful()) return;
                menuItems = response.body().getMenu();
                setMenu();
            }

            @Override
            public void onFailure(@NonNull Call<MenuResponse> call, @NonNull Throwable t) {
                Snackbar.make(drawer, "Fail", Snackbar.LENGTH_SHORT);
            }
        });
    }

    private void setMenu() {
        Menu menu = navigationView.getMenu();
        for (com.test.test.model.Menu item : menuItems) menu.add(item.getName());
        menu.setGroupCheckable(0, true, true);

        menu.getItem(0).setChecked(true);
        onNavigationItemSelected(menu.getItem(0));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        com.test.test.model.Menu selectedItem = getMenuItem(item.getTitle().toString());
        if (selectedItem == null) return false;

        if (selectedItem.getFunction().equalsIgnoreCase("image")) {
            switchFragment(PhotoFragment.newInstance(selectedItem.getParam()));
        }
        if (selectedItem.getFunction().equalsIgnoreCase("text")) {
            switchFragment(TextFragment.newInstance(selectedItem.getParam()));
        }
        if (selectedItem.getFunction().equalsIgnoreCase("url")) {
            switchFragment(WebFragment.newInstance(selectedItem.getParam()));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private com.test.test.model.Menu getMenuItem(String title) {
        for (com.test.test.model.Menu item : menuItems) {
            if (item.getName().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }


    public void switchFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }


}
