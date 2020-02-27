package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<json> call = apiInterface.gets();
        view =findViewById(R.id.view);

        call.enqueue(new Callback<json>() {
            @Override
            public void onResponse(Call<json> call, Response<json> response) {
                Toast.makeText(MainActivity.this, response.message()+" "+response.code(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()) {
                    view.setText(response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<json> call, Throwable t) {

                view.setText(t.getMessage());

            }
        });
    }


}
