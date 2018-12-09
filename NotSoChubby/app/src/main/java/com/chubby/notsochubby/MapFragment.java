package com.chubby.notsochubby;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chubby.notsochubby.models.entities.SpotAndCategory;
import com.chubby.notsochubby.models.utils.MetricsUtils;
import com.chubby.notsochubby.viewmodels.MapViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    public MapFragment() {}


    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.gmap, mapFragment).commit();
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapViewModel mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        mapViewModel.getSpots().observe(this, new Observer<List<SpotAndCategory>>() {
            @Override
            public void onChanged(List<SpotAndCategory> spots) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for(SpotAndCategory spot : spots){
                    LatLng latLng = new LatLng(spot.spot.getGeolat(), spot.spot.getGeolon());
                    builder.include(latLng);
                    googleMap.addMarker(new MarkerOptions().position(latLng)).setTag(spot);
                }
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, (int) MetricsUtils.pxFromDp(requireContext(), 30));
                googleMap.moveCamera(cu);
            }
        });
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                final int dX = getResources().getDimensionPixelSize(R.dimen.map_dx);
                final int dY = getResources().getDimensionPixelSize(R.dimen.map_dy);
                final Projection projection = googleMap.getProjection();
                final Point markerPoint = projection.toScreenLocation(
                        marker.getPosition()
                );
                markerPoint.offset(dX, dY);
                final LatLng newLatLng = projection.fromScreenLocation(markerPoint);
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(newLatLng));
                marker.showInfoWindow();
                return true;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                SpotAndCategory spotAndCategory = (SpotAndCategory) marker.getTag();
                if(spotAndCategory != null){
                    String geo = spotAndCategory.spot.getGeolat() + "," + spotAndCategory.spot.getGeolon() ;
                    Uri gmmIntentUri = Uri.parse("geo:"+geo +"?q="+geo+"("+spotAndCategory.spot.getName()+")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                        startActivity(mapIntent);
                    } else {
                        Toast.makeText(requireContext(), R.string.no_navigation_app, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                SpotAndCategory spotAndCategory = (SpotAndCategory) marker.getTag();
                if(spotAndCategory == null) return null;
                final LinearLayout info = new LinearLayout(requireContext());
                info.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                DisplayMetrics metrics = new DisplayMetrics();
                requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, metrics);
                LLParams.width = metrics.widthPixels - (int) px;
                info.setLayoutParams(LLParams);

                int padding = (int) MetricsUtils.pxFromDp(requireContext(), 5);

                info.setPadding(padding, padding, padding, padding);

                TextView spotTitle = new TextView(requireContext());
                spotTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorSecondary));
                spotTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                spotTitle.setTypeface(spotTitle.getTypeface(), Typeface.BOLD);
                spotTitle.setText(spotAndCategory.spot.getName());
                spotTitle.setIncludeFontPadding(false);

                TextView category = new TextView(requireContext());
                category.setPadding(0, padding, 0, 0);
                category.setTypeface(spotTitle.getTypeface(), Typeface.BOLD);
                category.setTextColor(ContextCompat.getColor(requireContext(), R.color.greyText));
                category.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                category.setText(spotAndCategory.category.getName());

                TextView spotLocation = new TextView(requireContext());
                spotLocation.setPadding(0, padding, 0, 0);
                spotLocation.setTextColor(ContextCompat.getColor(requireContext(), R.color.greyText));
                spotLocation.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                spotLocation.setText(spotAndCategory.spot.getFullAddress());

                TextView spotGPS = new TextView(requireContext());
                spotGPS.setPadding(0, padding, 0, 0);
                spotGPS.setTextColor(ContextCompat.getColor(requireContext(), R.color.greyText));
                spotGPS.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                String gps = "GPS: " + spotAndCategory.spot.getGeolat() + "," + spotAndCategory.spot.getGeolon();
                spotGPS.setText(gps);

                TextView directions = new TextView(requireContext());
                directions.setPadding(0, padding, 0, padding);
                directions.setTextColor(ContextCompat.getColor(requireContext(), R.color.greyText));
                directions.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                directions.setTypeface(spotTitle.getTypeface(), Typeface.BOLD);
                directions.setText(getString(R.string.get_directions));
                directions.setGravity(Gravity.CENTER_HORIZONTAL);

                info.addView(spotTitle);
                info.addView(category);
                info.addView(spotLocation);
                info.addView(spotGPS);
                info.addView(directions);


                return info;
            }
        });
    }

}
