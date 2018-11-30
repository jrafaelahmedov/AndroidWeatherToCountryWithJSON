package com.example.rmaahmadov.weathertocountrywithjson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText enterCityName;
    private Button btnGetCityWeather;
    private TextView resultTextView;

    public void findWeather(View view) {
        String cityName = enterCityName.getText().toString();
        Log.i("mywather", cityName);
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://samples.openweathermap.org/data/2.5/weather?q=?" + cityName + "&appid=" + "14fd29fcb21e4fe2a87a066225d77591");
        if (DownloadTask.message != "") {
            resultTextView.setText(DownloadTask.message);
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(enterCityName.getWindowToken(), 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterCityName = findViewById(R.id.enterCityName);
        btnGetCityWeather = findViewById(R.id.btnGetCityWeather);
        resultTextView = findViewById(R.id.resultTextView);
    }

}
