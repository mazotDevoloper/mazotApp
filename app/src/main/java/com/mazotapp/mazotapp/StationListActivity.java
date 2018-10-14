package com.mazotapp.mazotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StationListActivity extends AppCompatActivity {



    private ListView lvStations;
    Boolean boolGasoline,boolDiesel,boolLPG,boolLowPrice,boolDistance,boolToilet,boolSocialFacility,boolStationBrands,boolStarbucks;
    private List<StationModel> addStation = new ArrayList<StationModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);



        Bundle userChoice = getIntent().getExtras();

        //boolean ları bundle la diğer tarafından çekilen yer

        boolDiesel = userChoice.getBoolean("boolDiesel");
        boolDistance = userChoice.getBoolean("boolDistance");
        boolGasoline = userChoice.getBoolean("boolGasoline");
        boolLowPrice = userChoice.getBoolean("boolLowPrice");
        boolLPG = userChoice.getBoolean("boolLPG");
        boolSocialFacility = userChoice.getBoolean("boolSocialFacility");
        boolStarbucks = userChoice.getBoolean("boolStarbucks");
        boolStationBrands = userChoice.getBoolean("boolStationBrands");
        boolToilet = userChoice.getBoolean("boolToilet");


        lvStations = findViewById(R.id.lvStations);

        //istasyon ekleme

        addStation.add(new StationModel(R.drawable.opet_logo,R.drawable.opet_gurcan_photo,"OPET  GÜRCAN Petrol","Benzin fiyatı: 6.83tl","OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.",41.048434, 28.984981));
        addStation.add(new StationModel(R.drawable.bp_logo,R.drawable.bp_turer_photo,"BP TURER Petrol","Benzin fiyatı: 6.83tl","BP Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır. Kapsamlı bir market ürün içeriği sunan c-store da bulunmaktadır.",41.056920, 28.996398));
        addStation.add(new StationModel(R.drawable.shell_logo,R.drawable.shell_shell_photo,"Shell SHELL Petrol","Benzin fiyatı: 6,83tl","Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, para çekebileceğiniz bir ATM ve tuvalet bulunmaktadır.",41.069317, 29.004395));
        addStation.add(new StationModel(R.drawable.energy_logo,R.drawable.energy_kesan_photo,"ENERGY  merkez KEŞAN Petrol","Benzin fiyatı: 6,08tl","ENERGY Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet ve yağ değişimi bulunmaktadır.",41.055653, 28.809547));
        addStation.add(new StationModel(R.drawable.total_logo,R.drawable.total_total_photo,"TOTAL   TOTAL Petrol ","Benzin fiyatı: 6,83tl","TOTAL Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda hava ve su makinesi, tuvalet bulunmaktadır.",41.044693, 28.978260));
        addStation.add(new StationModel(R.drawable.opet_logo,R.drawable.opet_mehmetcik_photo,"OPET   MEHMETÇİK Petrol ","Benzin fiyatı: 6.83tl","OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.",41.107400, 29.015091));
        addStation.add(new StationModel(R.drawable.opet_logo,R.drawable.opet_kampet_photo,"OPET  KAMPET GÜRCAN Petrol ","Benzin fiyatı: 6.83tl","OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, restoran, fast food restoranı, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.",41.065662, 28.987355));
        addStation.add(new StationModel(R.drawable.aytemiz_logo,R.drawable.aytemiz_sisli_photo,"AYTEMİZ   ŞİŞLİ Petrol ","Benzin fiyatı: 6.83tl","AYTEMİZ Şişli akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır.",41.058522, 28.965656));
        addStation.add(new StationModel(R.drawable.shell_logo,R.drawable.shell_as_photo,"Shell AS Petrol ","Benzin fiyatı: 6.92tl","Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, tuvalet bulunmaktadır.",41.065717, 29.002209));
        addStation.add(new StationModel(R.drawable.petrol_ofisi_logo,R.drawable.petrolofisi_orsay_photo,"Petrol Ofisi İstanbul ORSAY Petrol ","Benzin fiyatı: 6.89tl","Petrol Ofisi Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda yıkama makinesi, hava ve su makinesi, tuvalet bulunmaktadır.",41.047798, 28.968614));

        //istasyon listesi oluşturuyorum parametrelere göre
        PrivateAdapter station = new PrivateAdapter(StationListActivity.this,addStation);

        //istasyon listesini adaptöre yolluyoruz
        lvStations.setAdapter(station);

        lvStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //istasyon bilgilerini Bundle la diğer sayfaya aktarıyorum

                String infoStation = addStation.get(position).getStationInfo();
                String infoStName = addStation.get(position).getStationName();
                String infoStGasoline = addStation.get(position).getStationPrGasoline();

                int infoStPhoto = addStation.get(position).getStPhoto();

                double infoStPositionX = addStation.get(position).getStPositionX();
                double infoStPositionY = addStation.get(position).getStPositionY();


                Bundle stationInformations = new Bundle();
                stationInformations.putDouble("infoStPositionX",infoStPositionX);
                stationInformations.putDouble("infoStPositionY",infoStPositionY);
                stationInformations.putInt("infoStPhoto",infoStPhoto);
                stationInformations.putString("infoStName",infoStName);
                stationInformations.putString("infoStGasoline",infoStGasoline);
                stationInformations.putString("infoStation",infoStation);

                Intent stationInformation = new Intent(StationListActivity.this,InformationStationActivity.class);
                stationInformation.putExtras(stationInformations);
                startActivity(stationInformation);
            }
        });



    }
}




