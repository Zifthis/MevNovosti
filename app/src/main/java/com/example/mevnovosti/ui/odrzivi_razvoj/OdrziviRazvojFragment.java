package com.example.mevnovosti.ui.odrzivi_razvoj;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mevnovosti.MainActivity;
import com.example.mevnovosti.MyApp;
import com.example.mevnovosti.R;
import com.example.mevnovosti.adapter.MevAdapter;
import com.example.mevnovosti.model.MevModel;

import java.util.ArrayList;

public class OdrziviRazvojFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private MevAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_odrzivi_razvoj, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container_or);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevOr());
        recyclerView = root.findViewById(R.id.text_odrzivi_razvoj);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);



        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        MyApp.getInstance().setFragment(OdrziviRazvojFragment.this);
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

    public void updateOrRecyclerView() {

        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(),"Ažurirano", Toast.LENGTH_SHORT).show();
        adapter = new MevAdapter(root.getContext(), MyApp.getInstance().getMevOr());
        recyclerView = root.findViewById(R.id.text_odrzivi_razvoj);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }


}
