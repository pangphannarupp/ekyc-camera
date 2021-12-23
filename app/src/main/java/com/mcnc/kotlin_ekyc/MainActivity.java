package com.mcnc.kotlin_ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mcnc.ekyc_cam.EkycCam;

public class MainActivity extends AppCompatActivity {

    // This LICENSE KEY only uses for AppId com.mcnc.kotlin_ekyc
    private static String LICENSE_KEY = "BaFHqPuvKLymNYOl8R7kTHOIS0NmdEHQKBLtZNHw3OMwhAIrljwdkP_yuPDVuHN5";

    private Button btnTakeIdCard, btnTakeSelfie;

    private EkycCam ekycCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakeIdCard = findViewById(R.id.btnTakeIdCard);
        btnTakeSelfie = findViewById(R.id.btnTakeSelfie);

        ekycCam = new EkycCam(MainActivity.this);

        /*
        * @btnTakeIdCard   {Event} Click
        * @Return   {String} Will return image base 64 to Ionic
        * @Usage    Take Front and Back ID Card
        * @Feature  Crop Automatically
        * */
        btnTakeIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ekycCam.takeIdCard();
            }
        });

        /*
         * @btnTakeSelfie   {Event} Click
         * @Return          {String} Will return image base 64 to Ionic
         * @Usage           Take Selfie
         * */
        btnTakeSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ekycCam.takeSelfie();
            }
        });
    }
}