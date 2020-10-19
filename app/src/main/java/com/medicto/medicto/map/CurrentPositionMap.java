package com.medicto.medicto.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.medicto.medicto.Fragment_Tab.GpsTracker;
import com.medicto.medicto.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentPositionMap extends AppCompatActivity implements MapView.CurrentLocationEventListener
        , MapReverseGeoCoder.ReverseGeoCodingResultListener {

    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};



    MapPoint DEFAULT_MARKER_POINT = null;

    private MapPOIItem mDefaultMaker;
    private MapPOIItem phamMarker;
    Intent dataIntent;

    ImageButton btn , btn2 , btn3;

    double latitude;
    double longitude;
    MapView mapView;

    String pharName = null;
    double pharLatitude = 0 , pharLongtitude = 0;

    boolean itemClickCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_position_map);



            dataIntent = getIntent();
         pharName = dataIntent.getExtras().getString("pharName");
            pharLatitude = Double.parseDouble(dataIntent.getExtras().getString("pharLatitude"));
         pharLongtitude =  Double.parseDouble(dataIntent.getExtras().getString("pharLongitude"));
         itemClickCheck = dataIntent.getExtras().getBoolean("pharCheck");

        btn = (ImageButton)findViewById(R.id.marker_make_button);
        btn2 = (ImageButton)findViewById(R.id.move_current_my_position_button);
        btn3 = (ImageButton)findViewById(R.id.activity_current_position_map_pharLocation);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMoveMyCurrentPosition();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createDefaultMarker(mapView);
                createFranchiseMarker(mapView);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.setMapCenterPoint( MapPoint.mapPointWithGeoCoord((double)pharLatitude,(double)pharLongtitude),true);
            }
        });



        if (!checkLocationServicesStatus()){
            showDialogForLocationServiceSetting();
        }else{
            checkRunTimePermission();
        }


        mapView = (MapView)findViewById(R.id.kakao_maps_view2);
        mapView.setZoomLevel(2, true);
        mapView.setCurrentLocationEventListener(this);
      // mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
        mapView.setShowCurrentLocationMarker(true);
        mapView.setMapType(MapView.MapType.Standard);
        gpsTracker = new GpsTracker(CurrentPositionMap.this);

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        String address = getCurrentAddress(latitude,longitude);

        Toast.makeText(CurrentPositionMap.this,"현재위치 \n위도 " + latitude + "\n 경도 "+longitude,Toast.LENGTH_LONG).show();


//        ViewGroup mapViewContainer = (ViewGroup)findViewById(R.id.kakao_maps_view);

        DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude,longitude);


        System.out.println("맵뷰의 센터 포인트 는 "+mapView.getMapCenterPoint().toString());

        MapPoint mp = mapView.getMapCenterPoint();
        MapPoint.GeoCoordinate gc = mp.getMapPointGeoCoord();

        createDefaultMarker(mapView);

        System.out.println("ItemClickCheck" + itemClickCheck);
        System.out.println("위도와 경도"+pharLatitude + " /////" + pharLongtitude);
        if (itemClickCheck == true){
            createPickMarker(mapView);
        }else {
            createFranchiseMarker(mapView);
        }
    }


    private void setMoveMyCurrentPosition(){

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude,longitude),true);
    }

    private void createDefaultMarker(MapView mapView){
        mDefaultMaker = new MapPOIItem();
        String name = "내 현재 위치";
        mDefaultMaker.setItemName(name);
        mDefaultMaker.setTag(0);
        mDefaultMaker.setMapPoint(DEFAULT_MARKER_POINT);
        mDefaultMaker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
       // mDefaultMaker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mDefaultMaker.setCustomImageResourceId(R.drawable.location);

        mapView.addPOIItem(mDefaultMaker);
        mapView.selectPOIItem(mDefaultMaker, true);

        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT,false);
    }

    private void createPickMarker(MapView mapView){
        phamMarker = new MapPOIItem();

        phamMarker.setItemName(pharName);
        phamMarker.setTag(1);
        phamMarker.setMapPoint(MapPoint.mapPointWithGeoCoord((double)pharLatitude,(double)pharLongtitude));
        phamMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        phamMarker.setCustomImageResourceId(R.drawable.map_asdasd);
        //phamMarker.setCustomImageAutoscale(true);
        phamMarker.setCustomImageAnchor(0.5f,1.0f);


        mapView.addPOIItem(phamMarker);
        mapView.selectPOIItem(phamMarker, true);
        mapView.setMapCenterPoint( MapPoint.mapPointWithGeoCoord((double)pharLatitude,(double)pharLongtitude),false);
        }

    private void createFranchiseMarker(MapView mapView){

        phamMarker = new MapPOIItem();

        phamMarker.setItemName("반석약국");
        phamMarker.setTag(1);
        phamMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(36.3917888,127.3123291));
        phamMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);


        mapView.addPOIItem(phamMarker);
        mapView.selectPOIItem(phamMarker, true);


        MapPOIItem pharm2 = new MapPOIItem();
        MapPOIItem pharm3 = new MapPOIItem();
        MapPOIItem pharm4 = new MapPOIItem();
        MapPOIItem pharm5 = new MapPOIItem();


        pharm2.setItemName("우리들의약국");
        pharm3.setItemName("명근당약국");
        pharm4.setItemName("갤러리비티약국");
        pharm5.setItemName("가톨릭약국");

        pharm2.setMapPoint(MapPoint.mapPointWithGeoCoord(36.3628951,127.3491626));
        pharm3.setMapPoint(MapPoint.mapPointWithGeoCoord(36.3633003,127.3567316));
        pharm4.setMapPoint(MapPoint.mapPointWithGeoCoord(36.3512445,127.3779281));
        pharm5.setMapPoint(MapPoint.mapPointWithGeoCoord(36.3294978,127.4288251));

        pharm2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        pharm3.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        pharm4.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        pharm5.setMarkerType(MapPOIItem.MarkerType.CustomImage);

       // pharm2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
     //   pharm3.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
      //  pharm4.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
      //  pharm5.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        pharm2.setCustomImageResourceId(R.drawable.map_asdasd);
        pharm3.setCustomImageResourceId(R.drawable.map_asdasd);
        pharm4.setCustomImageResourceId(R.drawable.map_asdasd);
        pharm5.setCustomImageResourceId(R.drawable.map_asdasd);

        pharm2.setCustomImageAnchor(0.5f,1.0f);
        pharm3.setCustomImageAnchor(0.5f,1.0f);
        pharm4.setCustomImageAnchor(0.5f,1.0f);
        pharm5.setCustomImageAnchor(0.5f,1.0f);

        mapView.addPOIItem(pharm2);
        mapView.selectPOIItem(pharm2, true);
        mapView.addPOIItem(pharm3);
        mapView.selectPOIItem(pharm3, true);
        mapView.addPOIItem(pharm4);
        mapView.selectPOIItem(pharm4, true);
        mapView.addPOIItem(pharm5);
        mapView.selectPOIItem(pharm5, true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setShowCurrentLocationMarker(false);
    }



    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(CurrentPositionMap.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(CurrentPositionMap.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(CurrentPositionMap.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(CurrentPositionMap.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(CurrentPositionMap.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(CurrentPositionMap.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(CurrentPositionMap.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(CurrentPositionMap.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentPositionMap.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        onFinishReversGeoCoding(s);
    }

    private void onFinishReversGeoCoding(String s) {
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReversGeoCoding("Fail");
    }
}