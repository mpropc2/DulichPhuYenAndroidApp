package layout;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hongocman.doan2_android.Common.GPSTracker;
import com.example.hongocman.doan2_android.Common.GetRouteTask;
import com.example.hongocman.doan2_android.R;
import com.example.hongocman.doan2_android.models.BanDoModel;
import com.example.hongocman.doan2_android.models.BanDoTask;
import com.example.hongocman.doan2_android.models.DanhMucApiModel;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.WeakHashMap;

import static android.content.Context.LOCATION_SERVICE;

public class Fragment_bando extends Fragment implements OnMapReadyCallback{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  int MIN_TIME_BW_UPDATES = 60000;
    private  int MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    public DanhMucApiModel danhMucApiModel;
    public GoogleMap mMap;

    public WeakHashMap<MarkerOptions, BanDoModel> haspMap = new WeakHashMap<MarkerOptions, BanDoModel>();


    private Button btn_map_mode;
    private Button btn_satellite_mode;
    private Button btn_show_direction;
    private LatLng mylocation;
    private Location location;
    private LocationManager mLocationManager;

    public Fragment_bando() {
        // Required empty public constructor
    }

    public static Fragment_bando newInstance(DanhMucApiModel param1, String param2) {
        Fragment_bando fragment = new Fragment_bando();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            danhMucApiModel = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_bando, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFrament = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrament.getMapAsync(this);

        btn_map_mode = (Button) view.findViewById(R.id.btn_map_mode);
        btn_satellite_mode = (Button) view.findViewById(R.id.btn_satellite_mode);
        btn_show_direction = (Button)view.findViewById(R.id.btn_show_direction);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (!mMap.isMyLocationEnabled())
            mMap.setMyLocationEnabled(true);

        btn_map_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        btn_satellite_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        btn_show_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create class object
                GPSTracker gps = new GPSTracker(getContext());
                // check if GPS enabled
                if(gps.canGetLocation() && gps.isGPSEnabled){
                    if(true) {
                        mylocation = new LatLng(gps.getLatitude(), gps.getLongitude());

                        if(danhMucApiModel.getLstBanDo() != null && danhMucApiModel.getLstBanDo().size() != 0) {
                            LatLng endLocation = new LatLng(danhMucApiModel.getLstBanDo().get(0).getLatitude(), danhMucApiModel.getLstBanDo().get(0).getLongitude());
                            new GetRouteTask(getActivity(), mylocation, endLocation, "You'are here!", mMap).execute();
                        }
                    }
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        new BanDoTask(this).execute(danhMucApiModel.getMaDanhMuc());
    }


}
