package com.example.weatherui;

import android.widget.RelativeLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    //Setting values for temperature, precipitation and humidity
    private int temperature = 29;
    private int precipitationPercentage = 90;
    private int humidity = 57;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HalfCircularProgressBar progressBar = findViewById(R.id.halfCircularProgressBar);

        setDynamicBackground(progressBar);

        progressBar.setMax(100);
        progressBar.setProgress(temperature);
        progressBar.setTemperature(temperature);



        TextView precipitationTextView = findViewById(R.id.actualRainTextView);
        TextView humidityTextView = findViewById(R.id.actualhumidity);


        precipitationTextView.setText(precipitationPercentage + "%");
        humidityTextView.setText( + humidity + "%");


        TextView profileTextView = findViewById(R.id.profileTextView);



        profileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        CardView firstCardView = findViewById(R.id.firstCardView);


        firstCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });
    }

    private void setDynamicBackground(HalfCircularProgressBar progressBar) {
        RelativeLayout mainLayout = findViewById(R.id.RelativeLayoutId1);

        if (temperature < 5) {
            mainLayout.setBackgroundResource(R.drawable.bluesnow);
        } else if (precipitationPercentage > 80) {
            mainLayout.setBackgroundResource(R.drawable.rainyfinal);
        } else {
            mainLayout.setBackgroundResource(R.drawable.sunnyfinal);
        }
    }
}
