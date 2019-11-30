package com.iteso.altomobileproject;

import android.app.DownloadManager;
import android.app.ListActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends ListActivity {

    CallbackManager callbackManager = CallbackManager.Factory.create();

    public  ArrayList<Animal> Animals = new ArrayList<Animal>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.header,getListView(),false);
        getListView().addHeaderView(header);
        TextView log_out = (TextView) findViewById(R.id.log_out);


        try {
            if(Animals.size()==0){
                getHttpResponse();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


    }

    public void getHttpResponse() throws IOException {

        String url = this.getString(R.string.data);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String mMessage = response.body().string();

                if(response.isSuccessful()) {
                    try {

                        JSONArray jason = new JSONArray(mMessage);
                        for (int i = 0; i < jason.length(); i++) {
                            JSONObject obj = jason.getJSONObject(i);
                            Animal animal = new Animal(
                                    obj.getString("name"),
                                    Integer.parseInt(obj.getString("life")),
                                    Integer.parseInt(obj.getString("id")),
                                    obj.getString("pictureURL")
                            );
                            Animals.add(animal);

                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable(){
                            @Override
                            public void run() {

                                Adapter adapter= new Adapter(MainActivity.this, Animals);
                                setListAdapter(adapter);

                            }
                        });

                    } catch (JSONException e) {
                        Log.e("JsonError", "unexpected JSON exception", e);
                    }
                }

            }
        });
    }


}
