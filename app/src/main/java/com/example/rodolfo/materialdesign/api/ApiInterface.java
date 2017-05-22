package com.example.rodolfo.materialdesign.api;

/*
  Created by rodolfo on 11/03/17.
  EndPoints
 */

import com.example.rodolfo.materialdesign.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{username}/{password}")                                       // Indicamos el tipo de conexion y la ruta a la que nos conectaremos
    Call<String> loginValidation(@Path("username") String username,     // Sustituye la variable username en el Path del GET
                                 @Path("password") String password);    // Sustituye la variable password en el Path del GET

    @GET("{username}")
    Call<User> getUserData(@Path("username") String username);

    @GET("users")
    Call<List<User>> getAllUsers();

    @FormUrlEncoded
    @POST("users/create")
    Call<User> createUser(@Field("username") String user,
                          @Field("password") String pass,
                          @Field("email") String email);

}
