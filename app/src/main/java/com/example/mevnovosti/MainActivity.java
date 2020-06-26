package com.example.mevnovosti;


import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mevnovosti.adapter.MevAdapter;
import com.example.mevnovosti.model.MevModel;
import com.example.mevnovosti.model.Novosti;
import com.example.mevnovosti.service.MevInterface;
import com.example.mevnovosti.service.RetrofitInstance;
import com.example.mevnovosti.ui.home.HomeFragment;
import com.example.mevnovosti.ui.menadzment.MenadzmentFragment;
import com.example.mevnovosti.ui.odrzivi_razvoj.OdrziviRazvojFragment;
import com.example.mevnovosti.ui.racunarstvo.RacunarstvoFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ArrayList<Novosti> opcaLista = new ArrayList<>();
    private ArrayList<Novosti> racLista = new ArrayList<>();
    private ArrayList<Novosti> mtsLista = new ArrayList<>();
    private ArrayList<Novosti> orLista = new ArrayList<>();
    private ArrayList<Novosti> listMev;
    private Fragment fragment = null;
    private FragmentTransaction ft = null;
    private FragmentManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration
                .Builder(
                R.id.nav_home,
                R.id.nav_menadzment,
                R.id.nav_odrzivi_razvoj,
                R.id.nav_racunarstvo)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //HomeFragment load on app start
        fragment = new HomeFragment();
        manager = getSupportFragmentManager();
        ft = manager.beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment).commit();
        manager.findFragmentById(R.id.nav_host_fragment);



        getNovosi();


    }



    //search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (MyApp.getInstance().getHomeMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getHomeMevAdapter()).getFilter().filter(query);

                }
                if (MyApp.getInstance().getMtsMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getMtsMevAdapter()).getFilter().filter(query);

                }
                if (MyApp.getInstance().getOsMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getOsMevAdapter()).getFilter().filter(query);

                }
                if (MyApp.getInstance().getRaMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getRaMevAdapter()).getFilter().filter(query);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //called when you type each letter in search
                if (MyApp.getInstance().getHomeMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getHomeMevAdapter()).getFilter().filter(newText);

                }
                if (MyApp.getInstance().getMtsMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getMtsMevAdapter()).getFilter().filter(newText);

                }
                if (MyApp.getInstance().getOsMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getOsMevAdapter()).getFilter().filter(newText);

                }
                if (MyApp.getInstance().getRaMevAdapter() != null) {
                    ((MevAdapter) MyApp.getInstance().getRaMevAdapter()).getFilter().filter(newText);
                }
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void getNovosi() {

        MevInterface mevInterface1 = RetrofitInstance.getModel().create(MevInterface.class);
        mevInterface1.getNovosi().enqueue(new Callback<MevModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<MevModel> call, Response<MevModel> response) {
                if (response.isSuccessful()) {
                    //adding items on listaMev list with retrofit response received from server
                    MevModel mevModel = response.body();
                    listMev = mevModel.getNovosti();

                    //Sort retrofit data list by date.
                    listMev.sort(Comparator.comparing(Novosti::getDatumObjave).reversed());

                    opcaLista = new ArrayList<>();
                    racLista = new ArrayList<>();
                    mtsLista = new ArrayList<>();
                    orLista = new ArrayList<>();
                    for (int i = 0; i < listMev.size(); i++) {
                        String tipNovosti = listMev.get(i).getTipNovosti();
                        if (tipNovosti.equals("1")) {
                            opcaLista.add(listMev.get(i));
                        } else if (tipNovosti.equals("2")) {
                            racLista.add(listMev.get(i));
                        } else if (tipNovosti.equals("3")) {
                            mtsLista.add(listMev.get(i));
                        } else if (tipNovosti.equals("4")) {
                            orLista.add(listMev.get(i));
                        }
                    }

                    MyApp.getInstance().setMevModels(listMev);
                    MyApp.getInstance().setMevOpca(opcaLista);
                    MyApp.getInstance().setMevRac(racLista);
                    MyApp.getInstance().setMevMts(mtsLista);
                    MyApp.getInstance().setMevOr(orLista);


                    //setup current fragment on app view after refreshing fragment
                    fragment = MyApp.getInstance().getFragment();
                    if (fragment instanceof HomeFragment) {
                        ((HomeFragment) fragment).updateHomeRecyclerView();
                    } else if (fragment instanceof RacunarstvoFragment) {
                        ((RacunarstvoFragment) fragment).updateRacRecyclerView();
                    } else if (fragment instanceof MenadzmentFragment) {
                        ((MenadzmentFragment) fragment).updateMtsRecyclerView();
                    } else if (fragment instanceof OdrziviRazvojFragment) {
                        ((OdrziviRazvojFragment) fragment).updateOrRecyclerView();
                    }

                }
            }

            @Override
            public void onFailure(Call<MevModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
