package com.example.rodolfo.materialdesign;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rodolfo.materialdesign.api.ApiClient;
import com.example.rodolfo.materialdesign.api.ApiInterface;
import com.example.rodolfo.materialdesign.views.ListUsersActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View view){
        TextInputEditText iUser = (TextInputEditText) findViewById(R.id.user);
        TextInputEditText iPass = (TextInputEditText) findViewById(R.id.pass);

        String username = iUser.getText().toString();
        String password = iPass.getText().toString();

        this.loginProcess(username, password);

        Intent intent = new Intent(this, ListUsersActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "hello");
        startActivity(intent);
    }

    private boolean loginProcess(String username, String password){
        Retrofit client = ApiClient.getClient();
        ApiInterface service = client.create(ApiInterface.class);                               // Creamos la interface para poder realizar las conexiones

        Log.w("Retrofit","Connection Start");
        Call<String> call = service.loginValidation(username, password);                        // Creamos una llamada al servidor, a la ruta loginValidation definida en ApiInterface
        call.enqueue(new Callback<String>() {                                                   // Colocamos nuestra llamada en una cola para que se realice de manera asincrona
            @Override
            public void onResponse(Call<String> call, Response<String> response){               // Se ejecuta cuando obtenemos respuesta del servidor, (200, 303, 404, 500)
                String loginResponse = response.body();                                         // Response contiene la respuesta del servidor, .body() nos da el cuerpo de una respuesta exitosa
                Toast.makeText(LoginActivity.this, loginResponse, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t){                              // Se ejecuta cuando ocurre un error al conectarse al servidor (Error de red)
                call.cancel();
                Log.w("Retrofit","Connection Lost");
                Log.w("Retrofit",t.getMessage());
            }
        });

        return false;
    }
}
