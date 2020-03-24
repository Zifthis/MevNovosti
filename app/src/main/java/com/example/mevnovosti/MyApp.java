package com.example.mevnovosti;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.example.mevnovosti.adapter.MevAdapter;
import com.example.mevnovosti.model.MevModel;

import java.util.ArrayList;


public class MyApp extends Application {

    public static MyApp instance = null;
    private Fragment fragment;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }

    public ArrayList<MevModel> mevModels = new ArrayList<>();
    public ArrayList<MevModel> mevOpca = new ArrayList<>();
    public ArrayList<MevModel> mevRac = new ArrayList<>();
    public ArrayList<MevModel> mevOr = new ArrayList<>();
    public ArrayList<MevModel> mevMts = new ArrayList<>();

    //home adapter for search
    public MevAdapter homeMevAdapter;

    //getter homeAdapter
    public MevAdapter getHomeMevAdapter() {
        return homeMevAdapter;
    }

    //setter homeAdapter
    public void setHomeMevAdapter(MevAdapter homeMevAdapter) {
        this.homeMevAdapter = homeMevAdapter;
    }

    public ArrayList<MevModel> getMevOpca() {
        return mevOpca;
    }

    public void setMevOpca(ArrayList<MevModel> mevOpca) {
        this.mevOpca = mevOpca;
    }

    public ArrayList<MevModel> getMevRac() {
        return mevRac;
    }

    public void setMevRac(ArrayList<MevModel> mevRac) {
        this.mevRac = mevRac;
    }

    public ArrayList<MevModel> getMevOr() {
        return mevOr;
    }

    public void setMevOr(ArrayList<MevModel> mevOr) {
        this.mevOr = mevOr;
    }

    public ArrayList<MevModel> getMevMts() {
        return mevMts;
    }

    public void setMevMts(ArrayList<MevModel> mevMts) {
        this.mevMts = mevMts;
    }

    public ArrayList<MevModel> getMevModels() {
        return mevModels;
    }

    public void setMevModels(ArrayList<MevModel> mevModels) {
        this.mevModels = mevModels;
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
