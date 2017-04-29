package com.example.nedusoftware.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

public class Loction extends AppCompatActivity {

    private LocationManager locationmanager;
    private BaiduMap baidumap;
    private MapView mapView;
    private String provider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_loction);
        initView();
        Muyi();

    }

    public void Location() {
        baidumap = mapView.getMap();
        baidumap.setMyLocationEnabled(true);
        locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationmanager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(Loction.this, "要想定位自己的位置需要在手机高级设置里打开定位服务哦", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationmanager.getLastKnownLocation(provider);
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        MapStatusUpdate update1 = MapStatusUpdateFactory.zoomTo(17);
        baidumap.animateMapStatus(update1);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        baidumap.animateMapStatus(update);
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baidumap.setMyLocationData(locationData);
        locationmanager.requestLocationUpdates(provider,1,1,locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {

        }
        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        baidumap.setMyLocationEnabled(false);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    public void initView(){

        mapView = (MapView) findViewById(R.id.flmap);

    }
    public void Muyi(){
        BaiduMap baidumap = mapView.getMap();
        LatLng ll = new LatLng(43.8295646903,126.5108316580);
        MapStatusUpdate update1 = MapStatusUpdateFactory.zoomTo(17);
        baidumap.animateMapStatus(update1);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        baidumap.animateMapStatus(update);
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(43.8295646903);
        locationBuilder.longitude(126.5108316580);
        MyLocationData locationData = locationBuilder.build();
        baidumap.setMyLocationData(locationData);
    }
}


