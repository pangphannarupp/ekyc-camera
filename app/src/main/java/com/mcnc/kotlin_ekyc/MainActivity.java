package com.mcnc.kotlin_ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mcnc.ekyc_cam.EkycCam;

import org.json.JSONException;
import org.json.JSONObject;

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
        JSONObject cameraTakeIdCardOption = new JSONObject();
        JSONObject cameraTakeSelfieOption = new JSONObject();

        //Take ID Card Option
        try {
            cameraTakeIdCardOption.put("licenseKey", LICENSE_KEY);
            cameraTakeIdCardOption.put("isCropped", true);
            cameraTakeIdCardOption.put("width", null);
            cameraTakeIdCardOption.put("showFlashButton", true);
            cameraTakeIdCardOption.put("imageQuality", 1.0);
            cameraTakeIdCardOption.put("showOverlay", true);
            cameraTakeIdCardOption.put("captureButtonColor", "#ff3783");
            JSONObject topLabelOption = new JSONObject();
            topLabelOption.put("text", "សួស្តី 안녕하세요 你好 こんにちは PPCBank");
            topLabelOption.put("color", "#ffffff");
            topLabelOption.put("size", 14);
            cameraTakeIdCardOption.put("topLabelOption", topLabelOption);
            JSONObject bottomLabelOption = new JSONObject();
            bottomLabelOption.put("text", "Bottom Label");
            bottomLabelOption.put("color", "#fff842");
            bottomLabelOption.put("size", 14);
            cameraTakeIdCardOption.put("bottomLabelOption", bottomLabelOption);
            JSONObject timerOption = new JSONObject();
            timerOption.put("backgroundColor", "#ff8842");
            timerOption.put("numberColor", "#fff842");
            cameraTakeIdCardOption.put("timerOption", timerOption);
            JSONObject captureOption = new JSONObject();
            captureOption.put("delay", 3);
            captureOption.put("showFlash", false);
            captureOption.put("pathPhoto", null);
            cameraTakeIdCardOption.put("captureOption", captureOption);
            JSONObject frameOption = new JSONObject();
            frameOption.put("size", null);
            frameOption.put("color", "#ff1142");
            frameOption.put("pathGuide", null);
            cameraTakeIdCardOption.put("frameOption", frameOption);
            JSONObject confirmButtonOption = new JSONObject();
            confirmButtonOption.put("backgroundColor", "#ff8142");
            confirmButtonOption.put("iconColor", "#ffffff");
            cameraTakeIdCardOption.put("confirmButtonOption", confirmButtonOption);
            JSONObject retakeButtonOption = new JSONObject();
            retakeButtonOption.put("backgroundColor", "#ff1042");
            retakeButtonOption.put("iconColor", "#ffffff");
            cameraTakeIdCardOption.put("retakeButtonOption", retakeButtonOption);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Take Selfie Option
        try {
            cameraTakeSelfieOption.put("licenseKey", LICENSE_KEY);
            cameraTakeSelfieOption.put("width", null);
            cameraTakeSelfieOption.put("imageQuality", 1.0);
            cameraTakeSelfieOption.put("outputPath", null);
            cameraTakeSelfieOption.put("isFrontCamera", true);
            cameraTakeSelfieOption.put("bottomPanelColor", "#ff3783");
            cameraTakeSelfieOption.put("captureButtonColor", "#ff8842");
            JSONObject topLabelOption = new JSONObject();
            topLabelOption.put("text", "សួស្តី 안녕하세요 你好 こんにちは PPCBank");
            topLabelOption.put("color", "#ffffff");
            topLabelOption.put("size", 14);
            cameraTakeSelfieOption.put("topLabelOption", topLabelOption);
            JSONObject switchCameraButtonOption = new JSONObject();
            switchCameraButtonOption.put("color", "#ffffff");
            switchCameraButtonOption.put("isShow", true);
            cameraTakeSelfieOption.put("switchCameraButtonOption", switchCameraButtonOption);
            JSONObject confirmButtonOption = new JSONObject();
            confirmButtonOption.put("backgroundColor", "#ff8142");
            confirmButtonOption.put("iconColor", "#ffffff");
            cameraTakeSelfieOption.put("confirmButtonOption", confirmButtonOption);
            JSONObject retakeButtonOption = new JSONObject();
            retakeButtonOption.put("backgroundColor", "#ff1042");
            retakeButtonOption.put("iconColor", "#ffffff");
            cameraTakeSelfieOption.put("retakeButtonOption", retakeButtonOption);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        * @btnTakeIdCard   {Event} Click
        * @Return   {String} Will return image base 64 to Ionic
        * @Usage    Take Front and Back ID Card
        * @Feature  Crop Automatically
        * */
        btnTakeIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject resultObject = ekycCam.takeIdCard(cameraTakeIdCardOption);
                    System.out.println("resultObject: " + resultObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                try {
                    ekycCam.takeSelfie(cameraTakeSelfieOption);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}