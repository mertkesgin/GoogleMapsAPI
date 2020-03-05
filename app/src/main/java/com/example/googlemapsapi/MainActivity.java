package com.example.googlemapsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final int ERROR_REQUEST = 9001;

    private Button btnGoToMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkGoogleServices()){
            btnGoToMaps = findViewById(R.id.btnGoToMaps);
            btnGoToMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    public boolean checkGoogleServices(){

        int availableServices = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (availableServices == ConnectionResult.SUCCESS){
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(availableServices)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,availableServices,ERROR_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(this, "Bir hata oluştu", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
