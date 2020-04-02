package com.example.mevnovosti.ui.menadzment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mevnovosti.MainActivity;
import com.example.mevnovosti.MyApp;
import com.example.mevnovosti.R;
import com.example.mevnovosti.adapter.MevAdapter;

import java.util.Map;
import java.util.Set;

public class MenadzmentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private MevAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View root;
    private FragmentManager fragmentManager = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_menadzment, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container_mts);
        swipeRefreshLayout.setOnRefreshListener(this);


        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevMts());
        recyclerView = root.findViewById(R.id.text_menadzment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        root.setBackgroundColor(Color.parseColor("#3E95C4"));

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        MyApp.getInstance().setFragment(MenadzmentFragment.this);
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

    public void updateMtsRecyclerView() {

        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(),"Ažurirano", Toast.LENGTH_SHORT).show();
        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevMts());
        recyclerView = root.findViewById(R.id.text_menadzment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

}
