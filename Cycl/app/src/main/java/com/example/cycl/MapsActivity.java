package com.example.cycl;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cycl.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import java.util.List;
import java.util.Locale;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    FloatingActionButton fabQr;
    LatLng mylocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Intent intent = getIntent();
        fabQr = findViewById(R.id.fabQr);
        fabQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE,
                                Barcode.FORMAT_AZTEC)
                        .build();
                GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(MapsActivity.this,options);
                scanner
                        .startScan()
                        .addOnSuccessListener(
                                barcode -> {
                                    String rawValue = barcode.getRawValue();
                                    if (rawValue.equals("Scan me")){
                                        if(intent.getStringExtra("key").equals("0")){
                                            Toast.makeText(MapsActivity.this, "Bike Hired", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(MapsActivity.this,Ride.class).putExtra("key","0"));
                                            finish();
                                        }

                                        else if (intent.getStringExtra("key").equals("1")){
                                            Toast.makeText(MapsActivity.this, "Scooter Hired", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(MapsActivity.this,Ride.class).putExtra("key","1"));
                                            finish();
                                        }
                                    }
                                    else{
                                        Toast.makeText(MapsActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addOnCanceledListener(
                                () -> {
                                })
                        .addOnFailureListener(
                                e -> {

                                });
            }
        });
        LatLng Montazah = new LatLng(31.281994, 30.011912);
        LatLng M3moora = new LatLng(31.284152, 30.027191);
        LatLng AAST = new LatLng(31.308051, 30.062179);
        LatLng Mandara = new LatLng(31.271953, 30.003290);
        LatLng Miami = new LatLng(31.265352, 29.997844);
        mMap = googleMap;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            mylocation= new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 13));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                    .zoom(17)
                    .bearing(90)
                    .tilt(40)
                    .build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions()
                    .position(Montazah)
                    .title("Montazah")
                    .icon(BitmapFromVector(getApplicationContext(),R.drawable.marker)));
            mMap.addMarker(new MarkerOptions()
                    .position(M3moora)
                    .title("M3moora")
                    .icon(BitmapFromVector(getApplicationContext(),R.drawable.marker)));
            mMap.addMarker(new MarkerOptions()
                    .position(AAST)
                    .title("Arab Academy for Science, Technology & Maritime Transport")
                    .icon(BitmapFromVector(getApplicationContext(),R.drawable.marker)));
            mMap.addMarker(new MarkerOptions()
                    .position(Mandara)
                    .title("Mandara")
                    .icon(BitmapFromVector(getApplicationContext(),R.drawable.marker)));
            mMap.addMarker(new MarkerOptions()
                    .position(Miami)
                    .title("Miami")
                    .icon(BitmapFromVector(getApplicationContext(),R.drawable.marker)));

        }

    }
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}