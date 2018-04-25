package com.giulio.matrixcalculatorperfetto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    AdView adView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MobileAds.initialize(this, "ca-app-pub-7224624747847283/6227792156");
        adView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
    }

    public void pressButton2x2(View bottone) {
        Intent intent = new Intent(this, Activity2x2.class);
        startActivity(intent);

    }

    public void pressButton3x3(View bottone) {
        Intent intent = new Intent(this, Activity3x3.class);
        startActivity(intent);
    }

    public void pressButton4x4(View bottone) {

        Intent intent = new Intent(this, Activity4x4.class);
        startActivity(intent);

    }

}