package com.example.rodolfo.materialdesign.api;

/**
 * Created by rodolfo on 11/03/17.
 */

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://148.234.36.60:8888/api/";      // Nuestro server con nuestra API
    private static Retrofit retrofit = null;                                    // Objeto retrofit para las conexiones

    public static Retrofit getClient() {
        if (retrofit == null){                                                  // Nos aseguramos que no se haya creado un objeto retrofit
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()                                   // Inicializamos el objeto Retrofit
                        .baseUrl(BASE_URL)                                      // Indicamos a cual sitio se estará conectando
                        .addConverterFactory(GsonConverterFactory.create(gson)) // Indicamos cual será nuestro serializador y deserializador de objetos (JSON)
                        .build();                                               // Construimos el objeto
            Log.w("Retrofit","Retrofit Start");
        }
        return retrofit;
    }

}
