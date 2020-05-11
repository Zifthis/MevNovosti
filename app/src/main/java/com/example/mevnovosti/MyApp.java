package com.example.mevnovosti;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.example.mevnovosti.adapter.MevAdapter;
import com.example.mevnovosti.model.MevModel;
import com.example.mevnovosti.model.Novosti;

import java.util.ArrayList;


public class MyApp extends Application {

    public static MyApp instance = null;
    private Fragment fragment;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }

    public ArrayList<Novosti> mevModels = new ArrayList<>();
    public ArrayList<Novosti> mevOpca = new ArrayList<>();
    public ArrayList<Novosti> mevRac = new ArrayList<>();
    public ArrayList<Novosti> mevOr = new ArrayList<>();
    public ArrayList<Novosti> mevMts = new ArrayList<>();

    //home adapter for search
    public MevAdapter homeMevAdapter;
    public MevAdapter raMevAdapter;
    public MevAdapter osMevAdapter;
    public MevAdapter mtsMevAdapter;

    public MevAdapter getRaMevAdapter() {
        return raMevAdapter;
    }

    public MevAdapter getOsMevAdapter() {
        return osMevAdapter;
    }

    public MevAdapter getMtsMevAdapter() {
        return mtsMevAdapter;
    }

    public void setRaMevAdapter(MevAdapter raMevAdapter) {
        this.raMevAdapter = raMevAdapter;
    }

    public void setOsMevAdapter(MevAdapter osMevAdapter) {
        this.osMevAdapter = osMevAdapter;
    }

    public void setMtsMevAdapter(MevAdapter mtsMevAdapter) {
        this.mtsMevAdapter = mtsMevAdapter;
    }

    public MevAdapter getHomeMevAdapter() {
        return homeMevAdapter;
    }

    public void setHomeMevAdapter(MevAdapter homeMevAdapter) {
        this.homeMevAdapter = homeMevAdapter;
    }

    public ArrayList<Novosti> getMevModels() {
        return mevModels;
    }

    public void setMevModels(ArrayList<Novosti> mevModels) {
        this.mevModels = mevModels;
    }

    public ArrayList<Novosti> getMevOpca() {
        return mevOpca;
    }

    public void setMevOpca(ArrayList<Novosti> mevOpca) {
        this.mevOpca = mevOpca;
    }

    public ArrayList<Novosti> getMevRac() {
        return mevRac;
    }

    public void setMevRac(ArrayList<Novosti> mevRac) {
        this.mevRac = mevRac;
    }

    public ArrayList<Novosti> getMevOr() {
        return mevOr;
    }

    public void setMevOr(ArrayList<Novosti> mevOr) {
        this.mevOr = mevOr;
    }

    public ArrayList<Novosti> getMevMts() {
        return mevMts;
    }

    public void setMevMts(ArrayList<Novosti> mevMts) {
        this.mevMts = mevMts;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public void setInstance(MyApp instance) {
        this.instance = instance;
    }

    //set current fragment on view
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
