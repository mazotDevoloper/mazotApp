package com.mazotapp.mazotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StationListActivity extends AppCompatActivity {


    ImageView backIcon;
    private ListView lvStations;
    String fuelType;
    Boolean boolGasoline,boolDiesel,boolLPG,boolLowPrice,boolDistance,boolToilet,boolSocialFacility,boolStationBrands,boolStarbucks;
    private List<StationModel> addStation = new ArrayList<StationModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);

        backIcon = findViewById(R.id.imgBack_icon);

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

        fuelType = "";

        if(boolDiesel){
            fuelType += "Dizel";
        }else if(boolGasoline){
            fuelType += "Benzin";
        }else if(boolLPG){
            fuelType += "LPG";
        }

        //istasyon ekleme

        if(boolLowPrice && !boolDistance && !boolToilet) {
            addStation.add(new StationModel(R.drawable.energy_logo, R.drawable.energy_kesan_photo, "ENERGY  merkez KEŞAN Petrol", fuelType + " fiyatı: 6,08tl", "Mesafe:1.8km", "ENERGY Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet ve yağ değişimi bulunmaktadır.", 41.055653, 28.809547));
            addStation.add(new StationModel(R.drawable.aytemiz_logo, R.drawable.aytemiz_sisli_photo, "AYTEMİZ   ŞİŞLİ Petrol ", fuelType + " fiyatı: 6.81tl", "Mesafe:2.9km", "AYTEMİZ Şişli akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır.", 41.058522, 28.965656));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_gurcan_photo, "OPET  GÜRCAN Petrol", fuelType + " fiyatı: 6.83tl", "Mesafe:3.8km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.048434, 28.984981));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_kampet_photo, "OPET  KAMPET GÜRCAN Petrol ", fuelType + " fiyatı: 6.84tl", "Mesafe:1.1km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, restoran, fast food restoranı, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.065662, 28.987355));
            addStation.add(new StationModel(R.drawable.bp_logo, R.drawable.bp_turer_photo, "BP TURER Petrol", fuelType + " fiyatı: 6.85tl", "Mesafe:2.5km", "BP Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır. Kapsamlı bir market ürün içeriği sunan c-store da bulunmaktadır.", 41.056920, 28.996398));
            addStation.add(new StationModel(R.drawable.shell_logo, R.drawable.shell_shell_photo, "Shell Şişli Petrol", fuelType + " fiyatı: 6,86tl", "Mesafe:4.3km", "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, para çekebileceğiniz bir ATM ve tuvalet bulunmaktadır.", 41.069317, 29.004395));
            addStation.add(new StationModel(R.drawable.total_logo, R.drawable.total_total_photo, "TOTAL   TOTAL Petrol ", fuelType + " fiyatı: 6,87tl", "Mesafe:5.7km", "TOTAL Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda hava ve su makinesi, tuvalet bulunmaktadır.", 41.044693, 28.978260));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_mehmetcik_photo, "OPET   MEHMETÇİK Petrol ", fuelType + " fiyatı: 6.88tl", "Mesafe:6.8km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.107400, 29.015091));
            addStation.add(new StationModel(R.drawable.petrol_ofisi_logo, R.drawable.petrolofisi_orsay_photo, "Petrol Ofisi ORSAY", fuelType + " fiyatı: 6.89tl", "Mesafe:2.2km", "Petrol Ofisi Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda yıkama makinesi, hava ve su makinesi, tuvalet bulunmaktadır.", 41.047798, 28.968614));
            addStation.add(new StationModel(R.drawable.shell_logo, R.drawable.shell_as_photo, "Shell AS Petrol ", fuelType + " fiyatı: 6.92tl", "Mesafe:8.8km", "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, tuvalet bulunmaktadır.", 41.065717, 29.002209));
        }
        if(boolDistance && !boolToilet && !boolLowPrice){
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_kampet_photo, "OPET  KAMPET GÜRCAN Petrol ", fuelType + " fiyatı: 6.84tl", "Mesafe:1.1km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, restoran, fast food restoranı, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.065662, 28.987355));
            addStation.add(new StationModel(R.drawable.energy_logo, R.drawable.energy_kesan_photo, "ENERGY  merkez KEŞAN Petrol", fuelType + " fiyatı: 6,08tl", "Mesafe:1.8km", "ENERGY Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet ve yağ değişimi bulunmaktadır.", 41.055653, 28.809547));
            addStation.add(new StationModel(R.drawable.petrol_ofisi_logo, R.drawable.petrolofisi_orsay_photo, "Petrol Ofisi ORSAY", fuelType + " fiyatı: 6.89tl", "Mesafe:2.2km", "Petrol Ofisi Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda yıkama makinesi, hava ve su makinesi, tuvalet bulunmaktadır.", 41.047798, 28.968614));
            addStation.add(new StationModel(R.drawable.bp_logo, R.drawable.bp_turer_photo, "BP TURER Petrol", fuelType + " fiyatı: 6.85tl", "Mesafe:2.5km", "BP Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır. Kapsamlı bir market ürün içeriği sunan c-store da bulunmaktadır.", 41.056920, 28.996398));
            addStation.add(new StationModel(R.drawable.aytemiz_logo, R.drawable.aytemiz_sisli_photo, "AYTEMİZ   ŞİŞLİ Petrol ", fuelType + " fiyatı: 6.81tl", "Mesafe:2.9km", "AYTEMİZ Şişli akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır.", 41.058522, 28.965656));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_gurcan_photo, "OPET  GÜRCAN Petrol", fuelType + " fiyatı: 6.83tl", "Mesafe:3.8km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.048434, 28.984981));
            addStation.add(new StationModel(R.drawable.shell_logo, R.drawable.shell_shell_photo, "Shell Şişli Petrol", fuelType + " fiyatı: 6,86tl", "Mesafe:4.3km", "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, para çekebileceğiniz bir ATM ve tuvalet bulunmaktadır.", 41.069317, 29.004395));
            addStation.add(new StationModel(R.drawable.total_logo, R.drawable.total_total_photo, "TOTAL   TOTAL Petrol ", fuelType + " fiyatı: 6,87tl", "Mesafe:5.7km", "TOTAL Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda hava ve su makinesi, tuvalet bulunmaktadır.", 41.044693, 28.978260));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_mehmetcik_photo, "OPET   MEHMETÇİK Petrol ", fuelType + " fiyatı: 6.88tl", "Mesafe:6.8km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.107400, 29.015091));
            addStation.add(new StationModel(R.drawable.shell_logo, R.drawable.shell_as_photo, "Shell AS Petrol ", fuelType + " fiyatı: 6.92tl", "Mesafe:8.8km", "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, tuvalet bulunmaktadır.", 41.065717, 29.002209));
        }
        if(boolToilet && !boolDistance && !boolLowPrice){
            addStation.add(new StationModel(R.drawable.aytemiz_logo, R.drawable.aytemiz_sisli_photo, "AYTEMİZ   ŞİŞLİ Petrol ", fuelType + " fiyatı: 6.81tl", "Mesafe:2.9km", "AYTEMİZ Şişli akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır.", 41.058522, 28.965656));
            addStation.add(new StationModel(R.drawable.bp_logo, R.drawable.bp_turer_photo, "BP TURER Petrol", fuelType + " fiyatı: 6.85tl", "Mesafe:2.5km", "BP Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır. Kapsamlı bir market ürün içeriği sunan c-store da bulunmaktadır.", 41.056920, 28.996398));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_gurcan_photo, "OPET  GÜRCAN Petrol", fuelType + " fiyatı: 6.83tl", "Mesafe:3.8km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.048434, 28.984981));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_mehmetcik_photo, "OPET   MEHMETÇİK Petrol ", fuelType + " fiyatı: 6.88tl", "Mesafe:6.8km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.107400, 29.015091));
            addStation.add(new StationModel(R.drawable.shell_logo, R.drawable.shell_as_photo, "Shell AS Petrol ", fuelType + " fiyatı: 6.92tl", "Mesafe:8.8km", "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, tuvalet bulunmaktadır.", 41.065717, 29.002209));
            addStation.add(new StationModel(R.drawable.opet_logo, R.drawable.opet_kampet_photo, "OPET  KAMPET GÜRCAN Petrol ", fuelType + " fiyatı: 6.84tl", "Mesafe:1.1km", "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, restoran, fast food restoranı, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.", 41.065662, 28.987355));
            addStation.add(new StationModel(R.drawable.shell_logo, R.drawable.shell_shell_photo, "Shell Şişli Petrol", fuelType + " fiyatı: 6,86tl", "Mesafe:4.3km", "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, para çekebileceğiniz bir ATM ve tuvalet bulunmaktadır.", 41.069317, 29.004395));
            addStation.add(new StationModel(R.drawable.petrol_ofisi_logo, R.drawable.petrolofisi_orsay_photo, "Petrol Ofisi ORSAY", fuelType + " fiyatı: 6.89tl", "Mesafe:2.2km", "Petrol Ofisi Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda yıkama makinesi, hava ve su makinesi, tuvalet bulunmaktadır.", 41.047798, 28.968614));
            addStation.add(new StationModel(R.drawable.total_logo, R.drawable.total_total_photo, "TOTAL   TOTAL Petrol ", fuelType + " fiyatı: 6,87tl", "Mesafe:5.7km", "TOTAL Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda hava ve su makinesi, tuvalet bulunmaktadır.", 41.044693, 28.978260));
            addStation.add(new StationModel(R.drawable.energy_logo, R.drawable.energy_kesan_photo, "ENERGY  merkez KEŞAN Petrol", fuelType + " fiyatı: 6,08tl", "Mesafe:1.8km", "ENERGY Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet ve yağ değişimi bulunmaktadır.", 41.055653, 28.809547));

        }

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
                String infoStPrice = addStation.get(position).getStationPrice();

                int infoStPhoto = addStation.get(position).getStPhoto();

                double infoStPositionX = addStation.get(position).getStPositionX();
                double infoStPositionY = addStation.get(position).getStPositionY();


                Bundle stationInformations = new Bundle();
                stationInformations.putDouble("infoStPositionX",infoStPositionX);
                stationInformations.putDouble("infoStPositionY",infoStPositionY);
                stationInformations.putInt("infoStPhoto",infoStPhoto);
                stationInformations.putString("infoStName",infoStName);
                stationInformations.putString("infoStPrice",infoStPrice);
                stationInformations.putString("infoStation",infoStation);

                Intent stationInformation = new Intent(StationListActivity.this,InformationStationActivity.class);
                stationInformation.putExtras(stationInformations);
                startActivity(stationInformation);
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFindStation = new Intent(StationListActivity.this,FindStationActivity.class);
                startActivity(intentFindStation);
            }
        });
    }
}




