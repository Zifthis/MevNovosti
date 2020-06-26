package com.example.mevnovosti.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Novosti {

    @SerializedName("id_novosti")
    @Expose
    private String idNovosti;
    @SerializedName("naslov")
    @Expose
    private String naslov;
    @SerializedName("pod_naslov")
    @Expose
    private String podNaslov;
    @SerializedName("tekst")
    @Expose
    private String tekst;
    @SerializedName("datum_novosti")
    @Expose
    private String datumNovosti;
    @SerializedName("datum_objave")
    @Expose
    private String datumObjave;
    @SerializedName("vidljivost")
    @Expose
    private String vidljivost;
    @SerializedName("slika")
    @Expose
    private String slika;
    @SerializedName("tip_novosti")
    @Expose
    private String tipNovosti;

    public String getIdNovosti() {
        return idNovosti;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getPodNaslov() {
        return podNaslov;
    }

    public String getTekst() {
        return tekst;
    }

    public String getDatumNovosti() {
        return datumNovosti;
    }

    public String getDatumObjave() {
        return datumObjave;
    }

    public String getVidljivost() {
        return vidljivost;
    }

    public String getSlika() {
        return slika;
    }

    public String getTipNovosti() {
        return tipNovosti;
    }


}
