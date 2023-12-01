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
    private int temperature = 104;
    private int precipitationPercentage = 30;
    private int humidity = 58;
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


        CardView firstCardView = findViewById(R.id.idfirstCardView);

        firstCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the Calendar activity
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });


        CardView secondCardView = findViewById(R.id.idsecondCardView);


        secondCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the Calendar activity
                Intent intent = new Intent(MainActivity.this, Exploreaqi.class);
                startActivity(intent);
            }
        });



    }

    private void setDynamicBackground(HalfCircularProgressBar progressBar) {
        RelativeLayout mainLayout = findViewById(R.id.RelativeLayoutId1);

        if (precipitationPercentage > 80) {
            mainLayout.setBackgroundResource(R.drawable.rainyfinal);}
        else if (temperature < 5) {
                mainLayout.setBackgroundResource(R.drawable.bluesnow);}
         else {
            mainLayout.setBackgroundResource(R.drawable.sunnyfinal);
        }
    }
}
