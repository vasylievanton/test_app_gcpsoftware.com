package com.test.test.api;

import com.test.test.model.MenuResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

;

public interface API {

    @GET("/s/fk3d5kg6cptkpr6/menu.json?dl=1")
    Call<MenuResponse> getMenu();
}
