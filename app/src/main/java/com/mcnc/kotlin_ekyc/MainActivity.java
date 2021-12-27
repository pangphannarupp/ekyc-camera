package com.mcnc.kotlin_ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mcnc.ekyc_camera.EkycCamera;
import com.mcnc.ekyc_camera.interfaces.PhotoListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // This LICENSE KEY only uses for AppId com.mcnc.kotlin_ekyc
    private static String LICENSE_KEY = "BaFHqPuvKLymNYOl8R7kTHOIS0NmdEHQKBLtZNHw3OMwhAIrljwdkP_yuPDVuHN5";

    private Button btnTakeIdCard, btnTakeSelfie;

    private EkycCamera ekycCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakeIdCard = findViewById(R.id.btnTakeIdCard);
        btnTakeSelfie = findViewById(R.id.btnTakeSelfie);

        ekycCamera = new EkycCamera(MainActivity.this);
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
            cameraTakeIdCardOption.put("captureButtonColor", "#136784");
            JSONObject topLabelOption = new JSONObject();
            topLabelOption.put("text", "សួស្តី 안녕하세요 你好 こんにちは PPCBank");
            topLabelOption.put("color", "#936183");
            topLabelOption.put("size", 14);
            cameraTakeIdCardOption.put("topLabelOption", topLabelOption);
            JSONObject bottomLabelOption = new JSONObject();
            bottomLabelOption.put("text", null);
            bottomLabelOption.put("color", "#fff842");
            bottomLabelOption.put("size", 14);
            cameraTakeIdCardOption.put("bottomLabelOption", bottomLabelOption);
            JSONObject timerOption = new JSONObject();
            timerOption.put("backgroundColor", "#ff8842");
            timerOption.put("numberColor", "#fff842");
            cameraTakeIdCardOption.put("timerOption", timerOption);
            JSONObject captureOption = new JSONObject();
            captureOption.put("delay", 0);
            captureOption.put("showFlash", false);
            captureOption.put("pathPhoto", null);
            cameraTakeIdCardOption.put("captureOption", captureOption);
            JSONObject frameOption = new JSONObject();
            JSONObject frameSizeOption = new JSONObject();
            frameSizeOption.put("width", 400);
            frameSizeOption.put("height", 100);
            frameOption.put("size", null);
            frameOption.put("color", "#ff1142");
            frameOption.put("content", null);
            cameraTakeIdCardOption.put("frameOption", frameOption);
            JSONObject confirmButtonOption = new JSONObject();
            confirmButtonOption.put("backgroundColor", "#ff8142");
            confirmButtonOption.put("iconColor", "#11ffff");
            cameraTakeIdCardOption.put("confirmButtonOption", confirmButtonOption);
            JSONObject retakeButtonOption = new JSONObject();
            retakeButtonOption.put("backgroundColor", "#701042");
            retakeButtonOption.put("iconColor", "#00ffff");
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
            cameraTakeSelfieOption.put("bottomPanelColor", "#003783");
            cameraTakeSelfieOption.put("captureButtonColor", "#ff8842");
            JSONObject topLabelOption = new JSONObject();
            topLabelOption.put("text", "សួស្តី 안녕하세요 你好 こんにちは PPCBank");
            topLabelOption.put("color", "#975fff");
            topLabelOption.put("size", 14);
            cameraTakeSelfieOption.put("topLabelOption", topLabelOption);
            JSONObject switchCameraButtonOption = new JSONObject();
            switchCameraButtonOption.put("color", "#ff87ff");
            switchCameraButtonOption.put("isShow", true);
            cameraTakeSelfieOption.put("switchCameraButtonOption", switchCameraButtonOption);
            JSONObject confirmButtonOption = new JSONObject();
            confirmButtonOption.put("backgroundColor", "#ff8142");
            confirmButtonOption.put("iconColor", "#87ffff");
            cameraTakeSelfieOption.put("confirmButtonOption", confirmButtonOption);
            JSONObject retakeButtonOption = new JSONObject();
            retakeButtonOption.put("backgroundColor", "#001042");
            retakeButtonOption.put("iconColor", "#46ffff");
            cameraTakeSelfieOption.put("retakeButtonOption", retakeButtonOption);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnTakeIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ekycCamera.takeIdCard(cameraTakeIdCardOption, new PhotoListener() {
                        @Override
                        public void onCompleted(JSONObject result) {
                            System.out.println("onCompleted: " + result);
                        }

                        @Override
                        public void onError(JSONObject error) {
                            System.out.println("onError: " + error);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("onError: " + e);
                }
            }
        });

        btnTakeSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ekycCamera.takeSelfie(cameraTakeSelfieOption, new PhotoListener() {
                        @Override
                        public void onCompleted(JSONObject result) {
                            System.out.println("onCompleted: " + result);
                        }

                        @Override
                        public void onError(JSONObject error) {
                            System.out.println("onError: " + error);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("onError: " + e);
                }
            }
        });
    }
}