package demo.com.locationhelper.location;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class AZSplashActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private boolean isNetworkLocation, isGPSLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager mListener = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(mListener != null){
            isGPSLocation = mListener.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkLocation = mListener.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.e("gps, network", String.valueOf(isGPSLocation + "," + isNetworkLocation));
        }
        handler.postDelayed(() -> {
            if(isGPSLocation){
                Intent intent = new Intent(AZSplashActivity.this, MainActivity.class);
                intent.putExtra("provider", LocationManager.GPS_PROVIDER);
                startActivity(intent);
                finish();
            }else if(isNetworkLocation){
                Intent intent = new Intent(AZSplashActivity.this, MainActivity.class);
                intent.putExtra("provider", LocationManager.NETWORK_PROVIDER);
                startActivity(intent);
                finish();
            }else{
                PermissionSettingUtils.LocationSettingDialog.newInstance().show(getSupportFragmentManager(), "Setting");
            }
        }, 1500);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, AZSplashActivity.class));
        finish();
    }
}
