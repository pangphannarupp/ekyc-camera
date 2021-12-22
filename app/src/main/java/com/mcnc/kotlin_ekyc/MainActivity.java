package com.mcnc.kotlin_ekyc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.innov8tif.facedetector.FaceDetectorSDK;
import com.innov8tif.facedetector.listener.FaceDetectorResultListener;
import com.innov8tif.facedetector.model.FaceAttr;
import com.innov8tif.facedetector.model.config.Config;
import com.innov8tif.okaycam.cam.OkayCamDoc;
import com.innov8tif.okaycam.config.OkayCamConfig;
import com.innov8tif.okaycam.config.OkaySelfieConfig;
import com.innov8tif.okaycam.selfie.OkayCamSelfie;
import com.innov8tif.okaycam.utils.BitmapUtils;

public class MainActivity extends AppCompatActivity {

    // This LICENSE KEY only uses for AppId com.mcnc.kotlin_ekyc
    private static String LICENSE_KEY = "BaFHqPuvKLymNYOl8R7kTHOIS0NmdEHQKBLtZNHw3OMwhAIrljwdkP_yuPDVuHN5";

    private Button btnTakeIdCard, btnTakeSelfie;
//                    btnOkayIdLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTakeIdCard = findViewById(R.id.btnTakeIdCard);
        btnTakeSelfie = findViewById(R.id.btnTakeSelfie);
        // btnOkayIdLite = findViewById(R.id.btnOkayIdLite);

        /*
        * @btnTakeIdCard   {Event} Click
        * @Return   {String} Will return image base 64 to Ionic
        * @Usage    Take Front and Back ID Card
        * @Feature  Crop Automatically
        * */
        btnTakeIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkayCamConfig config = OkayCamConfig.init(MainActivity.this);
                config.setCrop(true);

                OkayCamDoc.start(MainActivity.this, LICENSE_KEY, config, (isSuccess, image, e) -> {
                    System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
                    if(isSuccess) {
                        String result = BitmapUtils.INSTANCE.convertToBase64(image.get(0));
                        System.out.println("result: " + result);
//                        startCardDetector(image.get(0));
                    } else {

                    }

                    return null;
                });
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
                OkaySelfieConfig config = OkaySelfieConfig.init(MainActivity.this);
                OkayCamSelfie.start(MainActivity.this, LICENSE_KEY, config, (Boolean isSuccess, String image, Exception e) -> {
                    System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
                    if(isSuccess) {
                        String result = BitmapUtils.INSTANCE.convertToBase64(image);
                        System.out.println("result: " + result);
//                        startFaceDetector(image);
                    } else {

                    }

                    return null;
                });
            }
        });

//        btnOkayIdLite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //By default it is true
//                Config config = new Config.Builder()
//                        .setExtractAddress(true)
//                        .setExtractReligion(true)
//                        .setExtractGender(true)
//                        .build();
//                OkayLiteActivity.startMyKadScanning(MainActivity.this, LICENSE_KEY, config, (isSuccess, errorCode, result) -> {
//                    System.out.println("isSuccess: " + isSuccess + " errorCode: " + errorCode + " result: " + result);
//                    if(errorCode == 1) {
//                        Toast.makeText(MainActivity.this, "The license key is invalid", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

    /*****************/
    /**** NOT USE ****/
    /*****************/
    /*
     * @startFaceDetector   {Function}
     * @Param               {String} Image base 64
     * @Return              {Void}
     * @Usage               AI will detect fake or real user that base on face and eyes
     * */
    private void startFaceDetector(String imagePath) {
        Config config = new Config.Builder()
                .setImagePath(imagePath)
                .setFaceThreshold(0.9f)
                .setEyeThreshold(0.5f)
                .build();

        FaceDetectorSDK.startDetection(this, LICENSE_KEY, config, new FaceDetectorResultListener() {
            @Override
            public void onResultReceived(boolean isSuccess, int errorCode, FaceAttr result) {
                System.out.println("isSuccess: " + isSuccess + " errorCode: " + errorCode + " result: " + result);
                if (isSuccess) {
                    // handle result
                    System.out.println("getFaceImage: " + result.getFaceImage());
                    System.out.println("isFaceDetected: " + result.isFaceDetected());
                    System.out.println("isEye1Detected: " + result.isEye1Detected());
                    System.out.println("isEye2Detected: " + result.isEye2Detected());
                    Toast.makeText(MainActivity.this, "Face is detected: " + result.isFaceDetected(), Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Eye 1 is detected: " + result.isEye1Detected(), Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Eye 2 is detected: " + result.isEye2Detected(), Toast.LENGTH_LONG).show();
                }
                else {
                    // handle error
                }
            }
        });
    }


    /*****************/
    /**** NOT USE ****/
    /*****************/
    /*
     * @startCardDetector   {Function}
     * @Param               {String} Image base 64
     * @Return              {Void}
     * @Usage               AI will detect ID Card
     * @Note                Vender does not allow this feature
     * */
//    private void startCardDetector(String imagePath) {
////        ImageConfig imageConfig = new ImageConfig.Builder()
////                .setMyKadThreshold(70)
////                .setLandmarkThreshold(10)
////                .setHologramThreshold(30)
////                .setColorDetection(false)
////                .setScreenDetection(false)
////                .setContentSubDetection(false)
////                .setFaceTamperDetection(false)
////                .setMicroprintDetection(false)
////                .build();
//
//        ImagePathConfig imagePathConfig = (ImagePathConfig) new ImagePathConfig.Builder()
//                .setPath(imagePath)
//                .setMyKadThreshold(70)
//                .setLandmarkThreshold(10)
//                .setHologramThreshold(30)
//                .setColorDetection(false)
//                .setScreenDetection(false)
//                .setContentSubDetection(false)
//                .setFaceTamperDetection(false)
//                .setMicroprintDetection(false)
//                .build();
//
//        MyKadDetectionActivity.startDetection(this, LICENSE_KEY,
//                MyKadDetectionManager.CameraType.IMAGE, imagePathConfig, (isSuccess, errorCode, result) -> {
//                    System.out.println("isSuccess: " + isSuccess + " errorCode: " + errorCode + " result: " + result);
//                    if(errorCode == 1) {
//                        Toast.makeText(MainActivity.this, "The license key is invalid", Toast.LENGTH_SHORT).show();
//                    }
//                    if (isSuccess) {
//                        Log.d("SAMPLE", "result => " + result);
//                    } else {
//                        Log.d("SAMPLE", "error => " + errorCode);
//                    }
//                });
//    }
}