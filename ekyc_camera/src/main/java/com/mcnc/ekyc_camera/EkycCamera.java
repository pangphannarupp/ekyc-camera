package com.mcnc.ekyc_camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Size;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

public class EkycCamera {

    // This LICENSE KEY only uses for AppId com.mcnc.kotlin_ekyc
    private static String LICENSE_KEY = "BaFHqPuvKLymNYOl8R7kTHOIS0NmdEHQKBLtZNHw3OMwhAIrljwdkP_yuPDVuHN5";
    private String licenseKey = "";
    private Context mContext;

    public EkycCamera(Context context) {
        mContext = context;
    }

    //Old
    public void takeIdCardTest() {
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
                //startCardDetector(image.get(0));
            } else {

            }
            return null;
        });
    }

    //New
    public JSONObject takeIdCard(JSONObject option) throws JSONException {
        OkayCamConfig config = OkayCamConfig.init(mContext);

        //License Key
        final String KEY = "licenseKey";
        if(option.has(KEY) && !option.isNull(KEY)) {
            licenseKey = option.getString(KEY);
        } else {

        }

        //Crop photo after taking
        final String IS_CROPPED = "isCropped";
        if(option.has(IS_CROPPED) && !option.isNull(IS_CROPPED)) {
            config.setCrop(option.getBoolean(IS_CROPPED));
        }

        //width
        final String WIDTH = "width";
        if(option.has(WIDTH) && !option.isNull(WIDTH)) {
            config.setWidth(option.getInt(WIDTH));
        }

        //Show flash button
        final String SHOW_FLASH_BUTTON = "showFlashButton";
        if(option.has(SHOW_FLASH_BUTTON) && !option.isNull(SHOW_FLASH_BUTTON)) {
            config.setTorchBtnEnabled(option.getBoolean(SHOW_FLASH_BUTTON));
        }

        //Quality of photo 0.0f-1.0f
        final String IMAGE_QUALITY = "imageQuality";
        if(option.has(IMAGE_QUALITY) && !option.isNull(IMAGE_QUALITY)) {
            config.setImageQuality(Float.parseFloat(option.get(IMAGE_QUALITY).toString()));
        }

        //Show overlay background
        final String SHOW_OVERLAY = "showOverlay";
        if(option.has(SHOW_OVERLAY) && !option.isNull(SHOW_OVERLAY)) {
            config.setShowOverlay(option.getBoolean("showOverlay"));
        }

        //Color of capture button
        final String CAPTURE_BUTTON_COLOR = "captureButtonColor";
        if(option.has(CAPTURE_BUTTON_COLOR) && !option.isNull(CAPTURE_BUTTON_COLOR)) {
            config.setCaptureBtnColor(Color.parseColor(option.getString("captureButtonColor")));
        }

        //Top Label
        final String TOP_LABEL_OPTION = "topLabelOption";
        if(option.has(TOP_LABEL_OPTION) && !option.isNull(TOP_LABEL_OPTION)) {
            String text = option.getJSONObject(TOP_LABEL_OPTION).has("text") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("text") ?
                            option.getJSONObject(TOP_LABEL_OPTION).getString("text") : "";
            String color = option.getJSONObject(TOP_LABEL_OPTION).has("color") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("color") ?
                            option.getJSONObject(TOP_LABEL_OPTION).getString("color") : "#ffffff";
            int size = option.getJSONObject(TOP_LABEL_OPTION).has("size") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("size") ?
                            option.getJSONObject(TOP_LABEL_OPTION).getInt("size") : 24;
            config.setTopLabel(new OkayCamLabelConfig(
                    text,//text
                    Color.parseColor(color),//color
                    size//font size
            ));
        }

        //Bottom Label
        final String BOTTOM_LABEL_OPTION = "bottomLabelOption";
        if(option.has(BOTTOM_LABEL_OPTION) && !option.isNull(BOTTOM_LABEL_OPTION)) {
            String text = option.getJSONObject(BOTTOM_LABEL_OPTION).has("text") && !option.getJSONObject(BOTTOM_LABEL_OPTION).isNull("text") ?
                            option.getJSONObject(BOTTOM_LABEL_OPTION).getString("text") : "";
            String color = option.getJSONObject(BOTTOM_LABEL_OPTION).has("color") && !option.getJSONObject(BOTTOM_LABEL_OPTION).isNull("color") ?
                            option.getJSONObject(BOTTOM_LABEL_OPTION).getString("color") : "#ffffff";
            int size = option.getJSONObject(BOTTOM_LABEL_OPTION).has("size") && !option.getJSONObject(BOTTOM_LABEL_OPTION).isNull("size") ?
                            option.getJSONObject(BOTTOM_LABEL_OPTION).getInt("size") : 24;
            config.setBottomLabel(new OkayCamLabelConfig(
                    text,//text
                    Color.parseColor(color),//color
                    size//font size
            ));
        }

        //Color of Timer
        final String TIMER_OPTION = "timerOption";
        if(option.has(TIMER_OPTION) && !option.isNull(TIMER_OPTION)) {
            String backgroundColor = option.getJSONObject(TIMER_OPTION).has("backgroundColor") && !option.getJSONObject(TIMER_OPTION).isNull("backgroundColor") ?
                            option.getJSONObject(TIMER_OPTION).getString("backgroundColor") : "#662196F3";
            String numberColor = option.getJSONObject(TIMER_OPTION).has("numberColor") && !option.getJSONObject(TIMER_OPTION).isNull("numberColor") ?
                            option.getJSONObject(TIMER_OPTION).getString("numberColor") : "#ffffff";
            config.setTimer(new OkayCamTimerConfig(
                    Color.parseColor(backgroundColor),//background
                    Color.parseColor(numberColor)//text
            ));
        }

        //Frame
        final String FRAME_OPTION = "frameOption";
        if(option.has(FRAME_OPTION) && !option.isNull(FRAME_OPTION)) {
            Size sze = option.getJSONObject(FRAME_OPTION).has("size") && !option.getJSONObject(FRAME_OPTION).isNull("size") ?
                new Size(
                        option.getJSONObject(FRAME_OPTION).getJSONObject("size").has("width") && !option.getJSONObject(FRAME_OPTION).getJSONObject("size").isNull("width") ?
                                option.getJSONObject(FRAME_OPTION).getJSONObject("size").getInt("width") : 0,
                        option.getJSONObject(FRAME_OPTION).getJSONObject("size").has("height") && !option.getJSONObject(FRAME_OPTION).getJSONObject("size").isNull("height") ?
                                option.getJSONObject(FRAME_OPTION).getJSONObject("size").getInt("height") : 0
                ): null;
            String color = option.getJSONObject(FRAME_OPTION).has("color") && !option.getJSONObject(FRAME_OPTION).isNull("color") ?
                        option.getJSONObject(FRAME_OPTION).getString("color") : "#ffffff";
            String content = option.getJSONObject(FRAME_OPTION).has("content") && !option.getJSONObject(FRAME_OPTION).isNull("content") ?
                        option.getJSONObject(FRAME_OPTION).getString("content") : null;
            config.setFrame(new OkayCamFrameConfig(
                    sze, //new Size(100, 50),with and height
                    Color.parseColor(color),//color
                    content//path of guide
            ));
        }

        //Delay, Flash, PathPhoto
        final String CAPTURE_OPTION = "captureOption";
        if(option.has(CAPTURE_OPTION) && !option.isNull(CAPTURE_OPTION)) {
            int delay = option.getJSONObject(CAPTURE_OPTION).has("delay") && !option.getJSONObject(CAPTURE_OPTION).isNull("delay") ?
                        option.getJSONObject(CAPTURE_OPTION).getInt("delay") : 5;
            boolean showFlash = option.getJSONObject(CAPTURE_OPTION).has("showFlash") && !option.getJSONObject(CAPTURE_OPTION).isNull("showFlash") ?
                        option.getJSONObject(CAPTURE_OPTION).getBoolean("showFlash") : true;
            config.setCaptureConfig(new CaptureConfigPair(
                    new OkayCamCaptureConfig(
                            0,//delay
                            false,//flash
                            null//pathPhoto
                    ),
                    new OkayCamCaptureConfig(
                            delay,//delay
                            showFlash,//flash
                            null//pathPhoto
                    )
            ));
        }

        //Color of confirm button
        final String CONFIRM_BUTTON_OPTION = "confirmButtonOption";
        if(option.has(CONFIRM_BUTTON_OPTION) && !option.isNull(CONFIRM_BUTTON_OPTION)) {
            String backgroundColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("backgroundColor") ?
                        option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("backgroundColor") : "#EB144C";
            String iconColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("iconColor") ?
                        option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("iconColor") : "#ffffff";
            config.setConfirmBtnConfig(new OkayCamBtnConfig(
                    Color.parseColor(backgroundColor),//background
                    Color.parseColor(iconColor)//icon
            ));
        }
        //Color of retake button
        final String RETAKE_BUTTON_OPTION = "retakeButtonOption";
        if(option.has(RETAKE_BUTTON_OPTION) && !option.isNull(RETAKE_BUTTON_OPTION)) {
            String backgroundColor = option.getJSONObject(RETAKE_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("backgroundColor") ?
                        option.getJSONObject(RETAKE_BUTTON_OPTION).getString("backgroundColor") : "#EB144C";
            String iconColor = option.getJSONObject(RETAKE_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("iconColor") ?
                        option.getJSONObject(RETAKE_BUTTON_OPTION).getString("iconColor") : "#ffffff";
            config.setRetakeBtnConfig(new OkayCamBtnConfig(
                    Color.parseColor(backgroundColor),//background
                    Color.parseColor(iconColor)//icon
            ));
        }

        JSONObject resultObject = new JSONObject();
        resultObject.put("imageUrl", "");

        OkayCamDoc.start((Activity) mContext, licenseKey, config, (isSuccess, image, e) -> {
            System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
            if(isSuccess) {
                String result = BitmapUtils.INSTANCE.convertToBase64(image.get(0));
                System.out.println("result: " + result);
                //startCardDetector(image.get(0));
            } else {

            }
            return null;
        });

        return resultObject;
    }

    //Old
    public void takeSelfieTest() {
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
    }

    //New
    public JSONObject takeSelfie(JSONObject option) throws JSONException {
        OkaySelfieConfig config = OkaySelfieConfig.init(mContext);

        //License Key
        final String KEY = "licenseKey";
        if(option.has(KEY) && !option.isNull(KEY)) {
            licenseKey = option.getString(KEY);
        } else {

        }

        //width
        final String WIDTH = "width";
        if(option.has(WIDTH) && !option.isNull(WIDTH)) {
            config.setWidth(option.getInt(WIDTH));
        }

        //Quality of image
        final String IMAGE_QUALITY = "imageQuality";
        if(option.has(IMAGE_QUALITY) && !option.isNull(IMAGE_QUALITY)) {
            config.setImageQuality(Float.parseFloat(option.get(IMAGE_QUALITY).toString()));
        }

        //Path of output image
        final String OUTPUT_PATH = "outputPath";
        if(option.has(OUTPUT_PATH) && !option.isNull(OUTPUT_PATH)) {
            config.setOutputPath(option.getString(OUTPUT_PATH));
        }

        //Default camera {Front/Back}
        final String IS_FRONT_CAMERA = "isFrontCamera";
        if(option.has(IS_FRONT_CAMERA) && !option.isNull(IS_FRONT_CAMERA)) {
            config.setDefaultCameraFacing(option.getBoolean(IS_FRONT_CAMERA) ? CameraFacing.FRONT : CameraFacing.BACK);
        }

        //Color of bottom panel
        final String BOTTOM_PANEL_COLOR = "bottomPanelColor";
        if(option.has(BOTTOM_PANEL_COLOR) && !option.isNull(BOTTOM_PANEL_COLOR)) {
            config.setBottomFrameColor(Color.parseColor(option.getString(BOTTOM_PANEL_COLOR)));
        }

        //Color of capture button
        final String CAPTURE_BUTTON_COLOR = "captureButtonColor";
        if(option.has(CAPTURE_BUTTON_COLOR) && !option.isNull(CAPTURE_BUTTON_COLOR)) {
            config.setCaptureBtnColor(Color.parseColor(option.getString(CAPTURE_BUTTON_COLOR)));
        }

        //Top Label
        final String TOP_LABEL_OPTION = "topLabelOption";
        if(option.has(TOP_LABEL_OPTION) && !option.isNull(TOP_LABEL_OPTION)) {
            String text = option.getJSONObject(TOP_LABEL_OPTION).has("text") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("text") ?
                    option.getJSONObject(TOP_LABEL_OPTION).getString("text"): "Please align your face within the frame";
            String color = option.getJSONObject(TOP_LABEL_OPTION).has("color") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("color") ?
                    option.getJSONObject(TOP_LABEL_OPTION).getString("color"): "#ffffff";
            int size = option.getJSONObject(TOP_LABEL_OPTION).has("size") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("size") ?
                    option.getJSONObject(TOP_LABEL_OPTION).getInt("size"): 24;
            config.setTopLabel(new OkaySelfieLabelConfig(
                    text,//text
                    Color.parseColor(color),//color
                    size//font size
            ));
        }

        //Color of switch camera button
        final String SWITCH_CAMERA_BUTTON_OPTION = "switchCameraButtonOption";
        if(option.has(SWITCH_CAMERA_BUTTON_OPTION) && !option.isNull(SWITCH_CAMERA_BUTTON_OPTION)) {
            String color = option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).has("color") && !option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).isNull("color") ?
                    option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).getString("color") : "#ffffff";
            boolean isShow = option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).has("isShow") && !option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).isNull("isShow") ?
                    option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).getBoolean("isShow") : true;
            config.setSwitchBtnConfig(new OkaySelfieSwichBtnConfig(
                    Color.parseColor(color),//color
                    isShow//show/hide
            ));
        }

        //Color of confirm button
        final String CONFIRM_BUTTON_OPTION = "confirmButtonOption";
        if(option.has(CONFIRM_BUTTON_OPTION) && !option.isNull(CONFIRM_BUTTON_OPTION)) {
            String backgroundColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("backgroundColor") ?
                    option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("backgroundColor") : "#EB144C";
            String iconColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("iconColor") ?
                    option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("iconColor") : "#ffffff";

            config.setConfirmBtnConfig(new OkayCamBtnConfig(
                    Color.parseColor(backgroundColor),//background
                    Color.parseColor(iconColor)//icon
            ));
        }

        //Color of retake button
        final String RETAKE_BUTTON_OPTION = "retakeButtonOption";
        if(option.has(RETAKE_BUTTON_OPTION) && !option.isNull(RETAKE_BUTTON_OPTION)) {
            String backgroundColor = option.getJSONObject(RETAKE_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("backgroundColor") ?
                    option.getJSONObject(RETAKE_BUTTON_OPTION).getString("backgroundColor") : "#EB144C";
            String iconColor = option.getJSONObject(RETAKE_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("iconColor") ?
                    option.getJSONObject(RETAKE_BUTTON_OPTION).getString("iconColor") : "#ffffff";
            config.setRetakeBtnConfig(new OkayCamBtnConfig(
                    Color.parseColor(backgroundColor),//background
                    Color.parseColor(iconColor)//icon
            ));
        }

        OkayCamSelfie.start((Activity) mContext, licenseKey, config, (Boolean isSuccess, String image, Exception e) -> {
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
