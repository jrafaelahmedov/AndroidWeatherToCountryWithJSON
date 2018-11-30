package com.example.rmaahmadov.weathertocountrywithjson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {
    public static String message="";
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String readLine = "";
            while ((readLine = rd.readLine()) != null) {
                result += readLine;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            String weatherInfo = jsonObject.getString("weather");
            JSONArray arr = new JSONArray(weatherInfo);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonPart = arr.getJSONObject(i);
                String main = "";
                String description = "";
                main = jsonPart.getString("main");
                description = jsonPart.getString("description");

                if (main != "" && description != "") {
                    if(message!=null){
                        message="";
                        message+=main+": "+description+"\r\n";
                    }
                    
                }
            }
            
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
