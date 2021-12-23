package com.mcnc.ekyc_cam;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.innov8tif.okaycam.cam.OkayCamDoc;
import com.innov8tif.okaycam.config.CameraFacing;
import com.innov8tif.okaycam.config.CaptureConfigPair;
import com.innov8tif.okaycam.config.OkayCamBtnConfig;
import com.innov8tif.okaycam.config.OkayCamCaptureConfig;
import com.innov8tif.okaycam.config.OkayCamConfig;
import com.innov8tif.okaycam.config.OkayCamFrameConfig;
import com.innov8tif.okaycam.config.OkayCamLabelConfig;
import com.innov8tif.okaycam.config.OkayCamTimerConfig;
import com.innov8tif.okaycam.config.OkaySelfieConfig;
import com.innov8tif.okaycam.config.OkaySelfieLabelConfig;
import com.innov8tif.okaycam.config.OkaySelfieSwichBtnConfig;
import com.innov8tif.okaycam.selfie.OkayCamSelfie;
import com.innov8tif.okaycam.utils.BitmapUtils;

import org.json.JSONObject;

public class EkycCam {
    // This LICENSE KEY only uses for AppId com.mcnc.kotlin_ekyc
    private static String LICENSE_KEY = "BaFHqPuvKLymNYOl8R7kTHOIS0NmdEHQKBLtZNHw3OMwhAIrljwdkP_yuPDVuHN5";
    private Context mContext;

    public EkycCam(Context context) {
        mContext = context;
    }

    public JSONObject takeIdCard() {
        int time = 0;
        OkayCamConfig config = OkayCamConfig.init(mContext);
        //Crop photo after taking
        config.setCrop(true);
        //config.setWidth(100);
        //Show flash button
        config.setTorchBtnEnabled(true);
        //Quality of photo 0.0f-1.0f
        config.setImageQuality(1.0f);
        //Top Label
        config.setTopLabel(new OkayCamLabelConfig(
                "សួស្តី 안녕하세요 你好 こんにちは PPCBank",//text
                Color.parseColor("#ffffff"),//color
                14//font size
        ));
        //Bottom Label
        config.setBottomLabel(new OkayCamLabelConfig(
                "សួស្តី 안녕하세요 你好 こんにちは PPCBank",//text
                Color.parseColor("#ffffff"),//color
                14//font size
        ));
        //Color of Timer
        config.setTimer(new OkayCamTimerConfig(
                Color.parseColor("#ffffff"),//background
                Color.parseColor("#000000")//text
        ));
        //Frame
        config.setFrame(new OkayCamFrameConfig(
                null, //new Size(100, 50),with and height
                Color.parseColor("#ffff46"),//color
                null//path of guide
        ));
        //Show overlay background
        config.setShowOverlay(true);
        //Delay, Flash, PathPhoto
        config.setCaptureConfig(new CaptureConfigPair(
                new OkayCamCaptureConfig(
                        0,//delay
                        false,//flash
                        null//pathPhoto
                ),
                new OkayCamCaptureConfig(
                        time,//delay
                        false,//flash
                        null//pathPhoto
                )
        ));
        //Color of capture button
        config.setCaptureBtnColor(Color.parseColor("#ffff46"));
        //Color of confirm button
        config.setConfirmBtnConfig(new OkayCamBtnConfig(
                Color.parseColor("#ffff46"),//background
                Color.parseColor("#ffffff")//icon
        ));
        //Color of retake button
        config.setRetakeBtnConfig(new OkayCamBtnConfig(
                Color.parseColor("#ff8941"),//background
                Color.parseColor("#ffffff")//icon
        ));

        OkayCamDoc.start((Activity) mContext, LICENSE_KEY, config, (isSuccess, image, e) -> {
            System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
            if(isSuccess) {
                String result = BitmapUtils.INSTANCE.convertToBase64(image.get(0));
                System.out.println("result: " + result);
//                        startCardDetector(image.get(0));
            } else {

            }

            return null;
        });

        return new JSONObject();
    }

    public JSONObject takeSelfie() {
        OkaySelfieConfig config = OkaySelfieConfig.init(mContext);
//                config.setWidth(20);
//                config.setImageQuality(300);
//                config.setOutputPath(null);
        //Default camera {Front/Back}
        config.setDefaultCameraFacing(CameraFacing.FRONT);
        //Top Label
        config.setTopLabel(new OkaySelfieLabelConfig(
                "សួស្តី 안녕하세요 你好 こんにちは PPCBank",//text
                Color.parseColor("#ffffff"),//color
                14//font size
        ));
        //Color of bottom panel
        config.setBottomFrameColor(Color.parseColor("#ffffff"));
        //Color of capture button
        config.setCaptureBtnColor(Color.parseColor("#ff365778"));
        //Color of switch camera button
        config.setSwitchBtnConfig(new OkaySelfieSwichBtnConfig(
                Color.parseColor("#ff830521"),//color
                true//show/hide
        ));
        //Color of confirm button
        config.setConfirmBtnConfig(new OkayCamBtnConfig(
                Color.parseColor("#ffff46"),//background
                Color.parseColor("#ffffff")//icon
        ));
        //Color of retake button
        config.setRetakeBtnConfig(new OkayCamBtnConfig(
                Color.parseColor("#ff8941"),//background
                Color.parseColor("#ffffff")//icon
        ));


        OkayCamSelfie.start((Activity) mContext, LICENSE_KEY, config, (Boolean isSuccess, String image, Exception e) -> {
            System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
            if(isSuccess) {
                String result = BitmapUtils.INSTANCE.convertToBase64(image);
                System.out.println("result: " + result);
//                        startFaceDetector(image);
            } else {

            }

            return null;
        });

        return new JSONObject();
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
//    private void startFaceDetector(String imagePath) {
//        Config config = new Config.Builder()
//                .setImagePath(imagePath)
//                .setFaceThreshold(0.9f)
//                .setEyeThreshold(0.5f)
//                .build();
//
//        FaceDetectorSDK.startDetection(this, LICENSE_KEY, config, new FaceDetectorResultListener() {
//            @Override
//            public void onResultReceived(boolean isSuccess, int errorCode, FaceAttr result) {
//                System.out.println("isSuccess: " + isSuccess + " errorCode: " + errorCode + " result: " + result);
//                if (isSuccess) {
//                    // handle result
//                    System.out.println("getFaceImage: " + result.getFaceImage());
//                    System.out.println("isFaceDetected: " + result.isFaceDetected());
//                    System.out.println("isEye1Detected: " + result.isEye1Detected());
//                    System.out.println("isEye2Detected: " + result.isEye2Detected());
//                    Toast.makeText(MainActivity.this, "Face is detected: " + result.isFaceDetected(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(MainActivity.this, "Eye 1 is detected: " + result.isEye1Detected(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(MainActivity.this, "Eye 2 is detected: " + result.isEye2Detected(), Toast.LENGTH_LONG).show();
//                }
//                else {
//                    // handle error
//                }
//            }
//        });
//    }


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
