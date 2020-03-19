package com.example.mevnovosti.service;

import com.example.mevnovosti.model.MevModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;


public interface MevInterface {

    @GET("PhpProject/mev.php")
    Call<ArrayList<MevModel>> getNovosi();


    //@GET("netbeans_php/newPHP.php")
    //Observable<ArrayList<MevModel>> getNovosiRx();


}
