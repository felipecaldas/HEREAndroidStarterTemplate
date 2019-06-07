package com.here.generator.hereandroidstartertemplate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.LoadSceneCallback;
import com.here.sdk.mapview.LoadSceneError;
import com.here.sdk.mapview.MapStyle;
import com.here.sdk.mapview.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a MapView instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

    }

    private void loadMapScene() {
        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, new LoadSceneCallback() {
            @Override
            public void onLoadScene(@NonNull LoadSceneError loadSceneError) {
                if (loadSceneError == LoadSceneError.UNKNOWN) {
                    mapView.getCamera().setTarget(new GeoCoordinates(52.530932, 13.384915));
                    mapView.getCamera().setZoomLevel(14);
                } else {
                    Log.d(TAG, "onLoadScene failed: " + loadSceneError.toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
