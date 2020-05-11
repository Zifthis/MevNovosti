package com.example.mevnovosti.service;

import com.example.mevnovosti.model.MevModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MevInterface {

    @GET("sql.php")
    Call<MevModel> getNovosi();

}
