package com.example.mevnovosti.ui.home;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private MevAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container_home);
        swipeRefreshLayout.setOnRefreshListener(this);


        recyclerView = root.findViewById(R.id.text_home);
        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevModels());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        MyApp.getInstance().setFragment(HomeFragment.this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshArrayList();
            }
        }, 1500);
    }


    public void refreshArrayList() {
        ((MainActivity) root.getContext()).getNovosi();
    }


    public void updateHomeRecyclerView() {

        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), "Ažurirano", Toast.LENGTH_SHORT).show();
        recyclerView = root.findViewById(R.id.text_home);
        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevModels());
        MyApp.getInstance().setHomeMevAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

}
