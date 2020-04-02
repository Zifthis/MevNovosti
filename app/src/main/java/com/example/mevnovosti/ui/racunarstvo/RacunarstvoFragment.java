package com.example.mevnovosti.ui.racunarstvo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mevnovosti.MainActivity;
import com.example.mevnovosti.MyApp;
import com.example.mevnovosti.R;
import com.example.mevnovosti.adapter.MevAdapter;


public class RacunarstvoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private MevAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_racunarstvo, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container_rac);
        swipeRefreshLayout.setOnRefreshListener(this);


        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevRac());
        recyclerView = root.findViewById(R.id.text_racunarstvo);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        root.setBackgroundColor(Color.parseColor("#ADCE82"));

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        MyApp.getInstance().setFragment(RacunarstvoFragment.this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshArrayList();
            }
        },1500);
    }


    public void refreshArrayList() {
        ((MainActivity)root.getContext()).getNovosi();
    }


    public void updateRacRecyclerView() {

        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(),"Ažurirano", Toast.LENGTH_SHORT).show();
        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevRac());
        recyclerView = root.findViewById(R.id.text_racunarstvo);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
