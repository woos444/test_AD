package com.woos.test_ad;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements  OnMapReadyCallback{

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

        //좌표설정

        LatLng kunsanU  = new LatLng(35.945508,126.682185);
        LatLng kunsanS  = new LatLng(35.967672,126.736811);
        LatLng jbnu  = new LatLng(35.846976,127.129410);
        LatLng wku  = new LatLng(35.969457,126.957314);

        LatLngBounds bounds1 =new LatLngBounds(jbnu,kunsanU);
        LatLngBounds bounds2 =new LatLngBounds(wku,kunsanS);
        LatLngBounds bounds = new LatLngBounds(bounds1.getCenter(),bounds2.getCenter());


        CameraPosition cameraPosition = new CameraPosition(bounds.getCenter(),9);//카메라중앙설정 및 줌값 설정
        naverMap.setCameraPosition(cameraPosition);//네이버 맵에 카메라설정값 적용

        InfoWindow kunU = new InfoWindow();
        kunU.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산대";
            }
        });
        InfoWindow kunS = new InfoWindow();
        kunS.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산시청";
            }
        });
        InfoWindow jbU = new InfoWindow();
        jbU.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "전북대";
            }
        });
        InfoWindow wkU = new InfoWindow();
        wkU.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원광대";
            }
        });


        //마커생성
        Marker ksU_M = new Marker();
        Marker ksS_M = new Marker();
        Marker jbnu_M = new Marker();
        Marker wku_M= new Marker();

        //마커위치 등록
        ksU_M.setPosition(kunsanU);
        ksS_M.setPosition(kunsanS);
        jbnu_M.setPosition(jbnu);
        wku_M.setPosition(wku);

        //네이버 맵에 마커 표시
        ksU_M.setMap(naverMap);
        ksS_M.setMap(naverMap);
        jbnu_M.setMap(naverMap);
        wku_M.setMap(naverMap);

        kunU.open(ksU_M);
        kunS.open(ksS_M);
        jbU.open(jbnu_M);
        wkU.open(wku_M);


        PathOverlay path = new PathOverlay();
        path.setCoords(Arrays.asList(kunsanU,kunsanS,wku,jbnu));
        path.setWidth(30);
        path.setMap(naverMap);

    }
}
