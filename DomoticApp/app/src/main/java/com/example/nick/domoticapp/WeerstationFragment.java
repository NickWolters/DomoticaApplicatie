package com.example.nick.domoticapp;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nick.domoticapp.Model.WeatherAPIContent;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeerstationFragment extends Fragment {
    private View view;


    public WeerstationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_weerstation, container, false);

        RequestQueue queue = Volley.newRequestQueue(getContext());
        final String url = "http://api.openweathermap.org/data/2.5/weather?zip=7471,nl&units=metric&lang=nl&APPID=ab3a9db781e0cf7712d54f0a7fcdf2c3";

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Gson gson = new Gson();
                        WeatherAPIContent object = gson.fromJson(String.valueOf(response), WeatherAPIContent.class);
                        enterOpenWeatherValue(view, object);
                        WeatherAPIContent.setWeatherAPIInstance(object);
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );


        String urlWeerstation ="http://192.168.178.18/weerstation";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlWeerstation,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        insertValuesFromWeerstation(view, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Weerstation Response","That didn't work!");
            }
        });


        queue.add(stringRequest);
        queue.add(getRequest);
        return view;
    }

    public void insertValuesFromWeerstation(View view, String response){
        String[] parts = response.split("/");
        TextView domoTemp = view.findViewById(R.id.domoTempText);
        TextView domoHum = view.findViewById(R.id.domoHumidityText);
        domoTemp.setText(parts[1] + "\t / \t" + parts[2]);
        domoHum.setText(parts[0] + "%");
    }

    public void enterOpenWeatherValue(View view, WeatherAPIContent weatherAPIContent){
        TextView mainText = view.findViewById(R.id.mainText);
        TextView descriptionText = view.findViewById(R.id.descriptionText);
        TextView pressureText = view.findViewById(R.id.pressureText);
        TextView humidityText = view.findViewById(R.id.humidityText);
        TextView windText = view.findViewById(R.id.windText);
        ImageView weatherImage = view.findViewById(R.id.weatherImage);

        mainText.setText(weatherAPIContent.getWeather().get(0).getMain());
        descriptionText.setText(weatherAPIContent.getWeather().get(0).getDescription() + " / " + weatherAPIContent.getMain().getTemp());
        pressureText.setText("Luchtdruk : " + weatherAPIContent.getMain().getPressure());
        humidityText.setText("Luchtvochtigheid : " + weatherAPIContent.getMain().getHumidity());
        windText.setText("Windkracht : " + weatherAPIContent.getWind().getSpeed());

        int weatherID = weatherAPIContent.getWeather().get(0).getId();

        if(weatherID >= 200 && weatherID <= 232){
            weatherImage.setImageResource(R.drawable.storm);
        } else if(weatherID >= 300 && weatherID <= 321){
            weatherImage.setImageResource(R.drawable.drizzle);
        } else if(weatherID >= 500 && weatherID <= 504){
            weatherImage.setImageResource(R.drawable.rainy);
        } else if(weatherID >= 511 && weatherID <= 531){
            weatherImage.setImageResource(R.drawable.rain);
        } else if(weatherID >= 600 && weatherID <= 622){
            weatherImage.setImageResource(R.drawable.snowflake);
        } else if(weatherID >= 701 && weatherID <= 781){
            weatherImage.setImageResource(R.drawable.haze);
        } else if(weatherID == 800){
            weatherImage.setImageResource(R.drawable.sun);
        } else if(weatherID == 801){
            weatherImage.setImageResource(R.drawable.few_clouds);
        } else if(weatherID >= 802 && weatherID <= 804){
            weatherImage.setImageResource(R.drawable.cloud);
        }

    }



}
