package com.woos.test_ad;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

public class MainActivity extends AppCompatActivity
    implements  OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if(mapFragment == null){
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
    }

    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setMapType(NaverMap.MapType.Hybrid);//맵타입 변경
        LatLng location = new LatLng(35.945508,126.682185);//좌표설정
        CameraPosition cameraPosition = new CameraPosition(location, 17);//카메라중앙설정 및 줌값 설정
        naverMap.setCameraPosition(cameraPosition);//네이버 맵에 카메라설정값 적용

        Marker marker = new Marker();//마커생성
        marker.setPosition(location);//마커위치 등록
        marker.setMap(naverMap);//네이버 맵에 마커 표시

    }
}
