package com.example.calsys.retroapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.calsys.retroapi.config.APIinterface;
import com.example.calsys.retroapi.config.ApiClient;
import com.example.calsys.retroapi.config.response.Tutorsubjectlistresponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Home extends AppCompatActivity {

    RecyclerView recyc;
    ArrayAdapter adapter;
    ArrayList<Inventory> array_name = new ArrayList<>();

    int id = 2, cat = 0;
    APIinterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyc = findViewById(R.id.recy);
        adapter = new ArrayAdapter(array_name);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyc.setLayoutManager(lm);
        recyc.setItemAnimator(new DefaultItemAnimator());
        recyc.setAdapter(adapter);
        Tutor_List(id, cat);
    }

    private void Tutor_List(int id, int cat) {
        apiInterface = ApiClient.getClient().create(APIinterface.class);
        Call<Tutorsubjectlistresponse> callregister = apiInterface.get_tutors(id, cat);
        callregister.enqueue(new Callback<Tutorsubjectlistresponse>() {
            @Override
            public void onResponse(@NonNull Call<Tutorsubjectlistresponse> call, @NonNull retrofit2.Response<Tutorsubjectlistresponse> response) {
                Tutorsubjectlistresponse x = response.body();
                if (x == null) {
                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(Home.this, "body" + error, Toast.LENGTH_LONG).show();
                        Log.e("ERROR", error);

                        Toast.makeText(Home.this, "NULL", Toast.LENGTH_SHORT).show();
                        Log.e("success", error);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Home.this, "IOException", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String status = String.valueOf(x.getStatus());
                    if (status.equals("success")) {
                        for (int i = 0; i < x.getData().size(); i++) {
                            String tutor_name = x.getData().get(i).getFirst_name();
                            Inventory d = new Inventory(tutor_name);
                            array_name.add(d);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(Home.this, status, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Tutorsubjectlistresponse> call, Throwable t) {
                //  progressDialog.cancel();
                Toast.makeText(Home.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
