package com.example.mevnovosti.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;

public class MevModel {

    @SerializedName("novosti")
    ArrayList<Novosti> novosti = new ArrayList<>();

    public ArrayList<Novosti> getNovosti() {
        return novosti;
    }

    public void setNovosti(ArrayList<Novosti> novosti) {
        this.novosti = novosti;
    }


}

