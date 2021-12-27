package com.mcnc.ekyc_camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Size;

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
import com.mcnc.ekyc_camera.interfaces.PhotoInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class EkycCamera {

    // This LICENSE KEY only uses for AppId com.mcnc.kotlin_ekyc
//    private static String LICENSE_KEY = "BaFHqPuvKLymNYOl8R7kTHOIS0NmdEHQKBLtZNHw3OMwhAIrljwdkP_yuPDVuHN5";
    private String licenseKey = "";
    private Context mContext;
    OkaySelfieConfig okaySelfieConfig;

    public EkycCamera(Context context) {
        mContext = context;
    }

//    //Old
//    public void takeIdCardTest() {
//        int time = 0;
//        OkayCamConfig config = OkayCamConfig.init(mContext);
//        //Crop photo after taking
//        config.setCrop(true);
//        //config.setWidth(100);
//        //Show flash button
//        config.setTorchBtnEnabled(true);
//        //Quality of photo 0.0f-1.0f
//        config.setImageQuality(1.0f);
//        //Top Label
//        config.setTopLabel(new OkayCamLabelConfig(
//                "សួស្តី 안녕하세요 你好 こんにちは PPCBank",//text
//                Color.parseColor("#ffffff"),//color
//                14//font size
//        ));
//        //Bottom Label
//        config.setBottomLabel(new OkayCamLabelConfig(
//                "សួស្តី 안녕하세요 你好 こんにちは PPCBank",//text
//                Color.parseColor("#ffffff"),//color
//                14//font size
//        ));
//        //Color of Timer
//        config.setTimer(new OkayCamTimerConfig(
//                Color.parseColor("#ffffff"),//background
//                Color.parseColor("#000000")//text
//        ));
//        //Frame
//        config.setFrame(new OkayCamFrameConfig(
//                null, //new Size(100, 50),with and height
//                Color.parseColor("#ffff46"),//color
//                null//path of guide
//        ));
//        //Show overlay background
//        config.setShowOverlay(true);
//        //Delay, Flash, PathPhoto
//        config.setCaptureConfig(new CaptureConfigPair(
//                new OkayCamCaptureConfig(
//                        0,//delay
//                        false,//flash
//                        null//pathPhoto
//                ),
//                new OkayCamCaptureConfig(
//                        time,//delay
//                        false,//flash
//                        null//pathPhoto
//                )
//        ));
//        //Color of capture button
//        config.setCaptureBtnColor(Color.parseColor("#ffff46"));
//        //Color of confirm button
//        config.setConfirmBtnConfig(new OkayCamBtnConfig(
//                Color.parseColor("#ffff46"),//background
//                Color.parseColor("#ffffff")//icon
//        ));
//        //Color of retake button
//        config.setRetakeBtnConfig(new OkayCamBtnConfig(
//                Color.parseColor("#ff8941"),//background
//                Color.parseColor("#ffffff")//icon
//        ));
//
//        OkayCamDoc.start((Activity) mContext, LICENSE_KEY, config, (isSuccess, image, e) -> {
//            System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
//            if(isSuccess) {
//                String result = BitmapUtils.INSTANCE.convertToBase64(image.get(0));
//                System.out.println("result: " + result);
//                //startCardDetector(image.get(0));
//            } else {
//
//            }
//            return null;
//        });
//    }
//
//    //Old
//    public void takeSelfieTest() {
//        OkaySelfieConfig config = OkaySelfieConfig.init(mContext);
////                config.setWidth(20);
////                config.setImageQuality(300);
////                config.setOutputPath(null);
//        //Default camera {Front/Back}
//        config.setDefaultCameraFacing(CameraFacing.FRONT);
//        //Top Label
//        config.setTopLabel(new OkaySelfieLabelConfig(
//                "សួស្តី 안녕하세요 你好 こんにちは PPCBank",//text
//                Color.parseColor("#ffffff"),//color
//                14//font size
//        ));
//        //Color of bottom panel
//        config.setBottomFrameColor(Color.parseColor("#ffffff"));
//        //Color of capture button
//        config.setCaptureBtnColor(Color.parseColor("#ff365778"));
//        //Color of switch camera button
//        config.setSwitchBtnConfig(new OkaySelfieSwichBtnConfig(
//                Color.parseColor("#ff830521"),//color
//                true//show/hide
//        ));
//        //Color of confirm button
//        config.setConfirmBtnConfig(new OkayCamBtnConfig(
//                Color.parseColor("#ffff46"),//background
//                Color.parseColor("#ffffff")//icon
//        ));
//        //Color of retake button
//        config.setRetakeBtnConfig(new OkayCamBtnConfig(
//                Color.parseColor("#ff8941"),//background
//                Color.parseColor("#ffffff")//icon
//        ));
//
//
//        OkayCamSelfie.start((Activity) mContext, LICENSE_KEY, config, (Boolean isSuccess, String image, Exception e) -> {
//            System.out.println("isSuccess: " + isSuccess + " image: " + image + " e: " + e);
//            if(isSuccess) {
//                String result = BitmapUtils.INSTANCE.convertToBase64(image);
//                System.out.println("result: " + result);
////                        startFaceDetector(image);
//            } else {
//
//            }
//
//            return null;
//        });
//    }

    //New
    public void takeIdCard(JSONObject option, PhotoInterface callback) throws JSONException {
        OkayCamConfig config = OkayCamConfig.init(mContext);

        //License Key
        final String KEY = "licenseKey";
        if(option.has(KEY) && !option.isNull(KEY)) {
            licenseKey = option.getString(KEY);
        } else {
            JSONObject returnError = new JSONObject();
            returnError.put("errorCode", -100);
            returnError.put("errorMessage", "Please add license key.");
            callback.onError(returnError);

            return;
        }

        //Crop photo after taking
        final String IS_CROPPED = "isCropped";
        if(option.has(IS_CROPPED) && !option.isNull(IS_CROPPED)) {
            if(option.get(IS_CROPPED) instanceof Boolean) {
                config.setCrop(option.getBoolean(IS_CROPPED));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + IS_CROPPED + "} is not a type of boolean.");
                callback.onError(returnError);

                return;
            }
        }

        //width
        final String WIDTH = "width";
        if(option.has(WIDTH) && !option.isNull(WIDTH)) {
            if(option.get(WIDTH) instanceof Integer) {
                config.setWidth(option.getInt(WIDTH));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + WIDTH + "} is not a type of integer.");
                callback.onError(returnError);

                return;
            }
        }

        //Show flash button
        final String SHOW_FLASH_BUTTON = "showFlashButton";
        if(option.has(SHOW_FLASH_BUTTON) && !option.isNull(SHOW_FLASH_BUTTON)) {
            if(option.get(SHOW_FLASH_BUTTON) instanceof Boolean) {
                config.setTorchBtnEnabled(option.getBoolean(SHOW_FLASH_BUTTON));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + SHOW_FLASH_BUTTON + "} is not a type of boolean.");
                callback.onError(returnError);

                return;
            }
        }

        //Quality of photo 0.0f-1.0f
        final String IMAGE_QUALITY = "imageQuality";
        if(option.has(IMAGE_QUALITY) && !option.isNull(IMAGE_QUALITY)) {
            if(option.get(IMAGE_QUALITY) instanceof Number) {
                config.setImageQuality(Float.parseFloat(option.get(IMAGE_QUALITY).toString()));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + IMAGE_QUALITY + "} is not a type of number.");
                callback.onError(returnError);

                return;
            }
        }

        //Show overlay background
        final String SHOW_OVERLAY = "showOverlay";
        if(option.has(SHOW_OVERLAY) && !option.isNull(SHOW_OVERLAY)) {
            if(option.get(SHOW_OVERLAY) instanceof Boolean) {
                config.setShowOverlay(option.getBoolean(SHOW_OVERLAY));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + SHOW_OVERLAY + "} is not a type of boolean.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of capture button
        final String CAPTURE_BUTTON_COLOR = "captureButtonColor";
        if(option.has(CAPTURE_BUTTON_COLOR) && !option.isNull(CAPTURE_BUTTON_COLOR)) {
            if(option.get(CAPTURE_BUTTON_COLOR) instanceof String) {
                config.setCaptureBtnColor(Color.parseColor(option.getString(CAPTURE_BUTTON_COLOR)));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + SHOW_OVERLAY + "} is not a type of boolean.");
                callback.onError(returnError);

                return;
            }
        }

        //Top Label
        final String TOP_LABEL_OPTION = "topLabelOption";
        if(option.has(TOP_LABEL_OPTION) && !option.isNull(TOP_LABEL_OPTION)) {
            if(option.get(TOP_LABEL_OPTION) instanceof JSONObject) {
                String text = "";
                if(option.getJSONObject(TOP_LABEL_OPTION).has("text") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("text")) {
                    if(option.getJSONObject(TOP_LABEL_OPTION).get("text") instanceof String) {
                        text = option.getJSONObject(TOP_LABEL_OPTION).getString("text");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION +".text} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String color = "#ffffff";
                if(option.getJSONObject(TOP_LABEL_OPTION).has("color") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("color")) {
                    if(option.getJSONObject(TOP_LABEL_OPTION).get("color") instanceof String) {
                        color = option.getJSONObject(TOP_LABEL_OPTION).getString("color");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION +".color} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                int size = 24;
                if(option.getJSONObject(TOP_LABEL_OPTION).has("size") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("size")) {
                    if(option.getJSONObject(TOP_LABEL_OPTION).get("size") instanceof Integer) {
                        size = option.getJSONObject(TOP_LABEL_OPTION).getInt("size");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION +".size} is not a type of integer.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setTopLabel(new OkayCamLabelConfig(
                        text,//text
                        Color.parseColor(color),//color
                        size//font size
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Bottom Label
        final String BOTTOM_LABEL_OPTION = "bottomLabelOption";
        if(option.has(BOTTOM_LABEL_OPTION) && !option.isNull(BOTTOM_LABEL_OPTION)) {
            if(option.get(BOTTOM_LABEL_OPTION) instanceof JSONObject) {

                String text = "";
                if(option.getJSONObject(BOTTOM_LABEL_OPTION).has("text") && !option.getJSONObject(BOTTOM_LABEL_OPTION).isNull("text")) {
                    if(option.getJSONObject(BOTTOM_LABEL_OPTION).get("text") instanceof String) {
                        text = option.getJSONObject(BOTTOM_LABEL_OPTION).getString("text");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + BOTTOM_LABEL_OPTION +".text} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String color = "#ffffff";
                if(option.getJSONObject(BOTTOM_LABEL_OPTION).has("color") && !option.getJSONObject(BOTTOM_LABEL_OPTION).isNull("color")) {
                    if(option.getJSONObject(BOTTOM_LABEL_OPTION).get("color") instanceof String) {
                        color = option.getJSONObject(BOTTOM_LABEL_OPTION).getString("color");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + BOTTOM_LABEL_OPTION +".color} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                int size = 24;
                if(option.getJSONObject(BOTTOM_LABEL_OPTION).has("size") && !option.getJSONObject(BOTTOM_LABEL_OPTION).isNull("size")) {
                    if(option.getJSONObject(BOTTOM_LABEL_OPTION).get("size") instanceof Integer) {
                        size = option.getJSONObject(BOTTOM_LABEL_OPTION).getInt("size");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + BOTTOM_LABEL_OPTION +".size} is not a type of integer.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setBottomLabel(new OkayCamLabelConfig(
                        text,//text
                        Color.parseColor(color),//color
                        size//font size
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + BOTTOM_LABEL_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of Timer
        final String TIMER_OPTION = "timerOption";
        if(option.has(TIMER_OPTION) && !option.isNull(TIMER_OPTION)) {
            if(option.get(TIMER_OPTION) instanceof JSONObject) {
                String backgroundColor = "#662196F3";
                if(option.getJSONObject(TIMER_OPTION).has("backgroundColor") && !option.getJSONObject(TIMER_OPTION).isNull("backgroundColor")) {
                    if(option.getJSONObject(TIMER_OPTION).get("backgroundColor") instanceof String) {
                        backgroundColor = option.getJSONObject(TIMER_OPTION).getString("backgroundColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TIMER_OPTION + ".backgroundColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }

                }

                String numberColor = "#ffffff";
                if(option.getJSONObject(TIMER_OPTION).has("numberColor") && !option.getJSONObject(TIMER_OPTION).isNull("numberColor")) {
                    if(option.getJSONObject(TIMER_OPTION).get("numberColor") instanceof String) {
                        numberColor = option.getJSONObject(TIMER_OPTION).getString("numberColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TIMER_OPTION + ".numberColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setTimer(new OkayCamTimerConfig(
                        Color.parseColor(backgroundColor),//background
                        Color.parseColor(numberColor)//text
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + TIMER_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Frame
        final String FRAME_OPTION = "frameOption";
        if(option.has(FRAME_OPTION) && !option.isNull(FRAME_OPTION)) {
            if(option.get(FRAME_OPTION) instanceof JSONObject) {
                Size size = null;
                int width = 0;
                int height = 0;
                if(option.getJSONObject(FRAME_OPTION).has("size") && !option.getJSONObject(FRAME_OPTION).isNull("size")) {
                    if(option.getJSONObject(FRAME_OPTION).get("size") instanceof JSONObject) {
                        if(option.getJSONObject(FRAME_OPTION).getJSONObject("size").has("width") && !option.getJSONObject(FRAME_OPTION).getJSONObject("size").isNull("width")) {
                            if(option.getJSONObject(FRAME_OPTION).getJSONObject("size").get("width") instanceof Integer) {
                                width = option.getJSONObject(FRAME_OPTION).getJSONObject("size").getInt("width");
                            } else {
                                JSONObject returnError = new JSONObject();
                                returnError.put("errorCode", -102);
                                returnError.put("errorMessage", "Parameter {" + FRAME_OPTION + ".size.width} is not a type of integer.");
                                callback.onError(returnError);

                                return;
                            }
                        } else {
                            size = null;
                        }

                        if(option.getJSONObject(FRAME_OPTION).getJSONObject("size").has("height") && !option.getJSONObject(FRAME_OPTION).getJSONObject("size").isNull("height")) {
                            if(option.getJSONObject(FRAME_OPTION).getJSONObject("size").get("height") instanceof Integer) {
                                height = option.getJSONObject(FRAME_OPTION).getJSONObject("size").getInt("height");
                            } else {
                                JSONObject returnError = new JSONObject();
                                returnError.put("errorCode", -102);
                                returnError.put("errorMessage", "Parameter {" + FRAME_OPTION + ".size.height} is not a type of integer.");
                                callback.onError(returnError);

                                return;
                            }
                        } else {
                            size = null;
                        }

                        size = new Size(width, height);
                    } else {

                    }
                }

                String color = "#ffffff";
                if(option.getJSONObject(FRAME_OPTION).has("color") && !option.getJSONObject(FRAME_OPTION).isNull("color")) {
                    if(option.getJSONObject(FRAME_OPTION).get("color") instanceof String) {
                        color = option.getJSONObject(FRAME_OPTION).getString("color");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + FRAME_OPTION + ".color} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String content = null;
                if(option.getJSONObject(FRAME_OPTION).has("content") && !option.getJSONObject(FRAME_OPTION).isNull("content")) {
                    if(option.getJSONObject(FRAME_OPTION).get("content") instanceof String) {
                        content = option.getJSONObject(FRAME_OPTION).getString("content");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + FRAME_OPTION + ".content} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setFrame(new OkayCamFrameConfig(
                        size, //new Size(100, 50),with and height
                        Color.parseColor(color),//color
                        content//path of guide
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + FRAME_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Delay, Flash, PathPhoto
        final String CAPTURE_OPTION = "captureOption";
        if(option.has(CAPTURE_OPTION) && !option.isNull(CAPTURE_OPTION)) {
            if(option.get(CAPTURE_OPTION) instanceof JSONObject) {
                int delay = 5;
                if(option.getJSONObject(CAPTURE_OPTION).has("delay") && !option.getJSONObject(CAPTURE_OPTION).isNull("delay")) {
                    if(option.getJSONObject(CAPTURE_OPTION).get("delay") instanceof Integer) {
                        delay = option.getJSONObject(CAPTURE_OPTION).getInt("delay");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CAPTURE_OPTION + ".delay} is not a type of integer.");
                        callback.onError(returnError);

                        return;
                    }
                }

                boolean showFlash = true;
                if(option.getJSONObject(CAPTURE_OPTION).has("showFlash") && !option.getJSONObject(CAPTURE_OPTION).isNull("showFlash")) {
                    if(option.getJSONObject(CAPTURE_OPTION).get("showFlash") instanceof Boolean) {
                        showFlash = option.getJSONObject(CAPTURE_OPTION).getBoolean("showFlash");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CAPTURE_OPTION + ".showFlash} is not a type of boolean.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String pathPhoto = null;
                if(option.getJSONObject(CAPTURE_OPTION).has("pathPhoto") && !option.getJSONObject(CAPTURE_OPTION).isNull("pathPhoto")) {
                    if(option.getJSONObject(CAPTURE_OPTION).get("pathPhoto") instanceof String) {
                        pathPhoto = option.getJSONObject(CAPTURE_OPTION).getString("pathPhoto");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CAPTURE_OPTION + ".pathPhoto} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setCaptureConfig(new CaptureConfigPair(
                        new OkayCamCaptureConfig(
                                0,//delay
                                false,//flash
                                null//pathPhoto
                        ),
                        new OkayCamCaptureConfig(
                                delay,//delay
                                showFlash,//flash
                                pathPhoto//pathPhoto
                        )
                ));
            }
        }

        //Color of confirm button
        final String CONFIRM_BUTTON_OPTION = "confirmButtonOption";
        if(option.has(CONFIRM_BUTTON_OPTION) && !option.isNull(CONFIRM_BUTTON_OPTION)) {
            if(option.get(CONFIRM_BUTTON_OPTION) instanceof JSONObject) {
                String backgroundColor = "#EB144C";
                if(option.getJSONObject(CONFIRM_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("backgroundColor")) {
                    if(option.getJSONObject(CONFIRM_BUTTON_OPTION).get("backgroundColor") instanceof String) {
                        backgroundColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("backgroundColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CONFIRM_BUTTON_OPTION + ".backgroundColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String iconColor = "#ffffff";
                if(option.getJSONObject(CONFIRM_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("iconColor")) {
                    if(option.getJSONObject(CONFIRM_BUTTON_OPTION).get("iconColor") instanceof String) {
                        iconColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("iconColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CONFIRM_BUTTON_OPTION + ".iconColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setConfirmBtnConfig(new OkayCamBtnConfig(
                        Color.parseColor(backgroundColor),//background
                        Color.parseColor(iconColor)//icon
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + CONFIRM_BUTTON_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }
        //Color of retake button
        final String RETAKE_BUTTON_OPTION = "retakeButtonOption";
        if(option.has(RETAKE_BUTTON_OPTION) && !option.isNull(RETAKE_BUTTON_OPTION)) {
            if(option.get(RETAKE_BUTTON_OPTION) instanceof JSONObject) {
                String backgroundColor = "#EB144C";
                if(option.getJSONObject(RETAKE_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("backgroundColor")) {
                    if(option.getJSONObject(RETAKE_BUTTON_OPTION).get("backgroundColor") instanceof String) {
                        backgroundColor = option.getJSONObject(RETAKE_BUTTON_OPTION).getString("backgroundColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + RETAKE_BUTTON_OPTION + ".backgroundColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String iconColor = "#ffffff";
                if(option.getJSONObject(RETAKE_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("iconColor")) {
                    if(option.getJSONObject(RETAKE_BUTTON_OPTION).get("iconColor") instanceof String) {
                        iconColor = option.getJSONObject(RETAKE_BUTTON_OPTION).getString("iconColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + RETAKE_BUTTON_OPTION + ".iconColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                config.setRetakeBtnConfig(new OkayCamBtnConfig(
                        Color.parseColor(backgroundColor),//background
                        Color.parseColor(iconColor)//icon
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + RETAKE_BUTTON_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        OkayCamDoc.start((Activity) mContext, licenseKey, config, (isSuccess, image, e) -> {
            if(isSuccess) {
                JSONObject returnResult = new JSONObject();
                String result = BitmapUtils.INSTANCE.convertToBase64(image.get(0));
                try {
                    returnResult.put("message", "Images are captured successfully.");
                    returnResult.put("image", result);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                callback.onCompleted(returnResult);

                //startFaceDetector(image);
            } else {
                JSONObject returnError = new JSONObject();
                try {
                    int errorCode = 0;
                    String errorMessage = "";
                    if(e.getMessage().contains("Invalid license key")) {
                        errorCode = -101;
                        errorMessage = e.getMessage();
                    } else if(e.getMessage().contains("User refused to grant permission")) {
                        errorCode = -103;
                        errorMessage = e.getMessage();
                    } else if(e.getMessage().contains("User cancelled the activity")) {
                        errorCode = -104;
                        errorMessage = e.getMessage();
                    }

                    returnError.put("errorCode", errorCode);
                    returnError.put("errorMessage", errorMessage);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                callback.onError(returnError);
            }
            return null;
        });
    }

    //New
    public void takeSelfie(JSONObject option, PhotoInterface callback) throws JSONException {
        okaySelfieConfig = OkaySelfieConfig.init(mContext);

        //License Key
        final String KEY = "licenseKey";
        if(option.has(KEY) && !option.isNull(KEY)) {
            licenseKey = option.getString(KEY);
        } else {
            JSONObject returnError = new JSONObject();
            returnError.put("errorCode", -100);
            returnError.put("errorMessage", "Please add license key.");
            callback.onError(returnError);

            return;
        }

        //width
        final String WIDTH = "width";
        if(option.has(WIDTH) && !option.isNull(WIDTH)) {
            if(option.get(WIDTH) instanceof Integer) {
                okaySelfieConfig.setWidth(option.getInt(WIDTH));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + WIDTH + "} is not a type of integer.");
                callback.onError(returnError);

                return;
            }

        }

        //Quality of image
        final String IMAGE_QUALITY = "imageQuality";
        if(option.has(IMAGE_QUALITY) && !option.isNull(IMAGE_QUALITY)) {
            if(option.get(IMAGE_QUALITY) instanceof Number) {
                okaySelfieConfig.setImageQuality(Float.parseFloat(option.get(IMAGE_QUALITY).toString()));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + IMAGE_QUALITY + "} is not a type of number.");
                callback.onError(returnError);

                return;
            }
        }

        //Path of output image
        final String OUTPUT_PATH = "outputPath";
        if(option.has(OUTPUT_PATH) && !option.isNull(OUTPUT_PATH)) {
            if(option.get(OUTPUT_PATH) instanceof String) {
                okaySelfieConfig.setOutputPath(option.getString(OUTPUT_PATH));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + OUTPUT_PATH + "} is not a type of string.");
                callback.onError(returnError);

                return;
            }

        }

        //Default camera {Front/Back}
        final String IS_FRONT_CAMERA = "isFrontCamera";
        if(option.has(IS_FRONT_CAMERA) && !option.isNull(IS_FRONT_CAMERA)) {
            if(option.get(IS_FRONT_CAMERA) instanceof Boolean) {
                okaySelfieConfig.setDefaultCameraFacing(option.getBoolean(IS_FRONT_CAMERA) ? CameraFacing.FRONT : CameraFacing.BACK);
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + IS_FRONT_CAMERA + "} is not a type of boolean.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of bottom panel
        final String BOTTOM_PANEL_COLOR = "bottomPanelColor";
        if(option.has(BOTTOM_PANEL_COLOR) && !option.isNull(BOTTOM_PANEL_COLOR)) {
            if(option.get(BOTTOM_PANEL_COLOR) instanceof String) {
                okaySelfieConfig.setBottomFrameColor(Color.parseColor(option.getString(BOTTOM_PANEL_COLOR)));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + BOTTOM_PANEL_COLOR + "} is not a type of string.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of capture button
        final String CAPTURE_BUTTON_COLOR = "captureButtonColor";
        if(option.has(CAPTURE_BUTTON_COLOR) && !option.isNull(CAPTURE_BUTTON_COLOR)) {
            if(option.get(CAPTURE_BUTTON_COLOR) instanceof String) {
                okaySelfieConfig.setCaptureBtnColor(Color.parseColor(option.getString(CAPTURE_BUTTON_COLOR)));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + CAPTURE_BUTTON_COLOR + "} is not a type of string.");
                callback.onError(returnError);

                return;
            }
        }

        //Top Label
        final String TOP_LABEL_OPTION = "topLabelOption";
        if(option.has(TOP_LABEL_OPTION) && !option.isNull(TOP_LABEL_OPTION)) {
            if(option.get(TOP_LABEL_OPTION) instanceof JSONObject) {
                String text = "Please align your face within the frame";
                if(option.getJSONObject(TOP_LABEL_OPTION).has("text") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("text")) {
                    if(option.getJSONObject(TOP_LABEL_OPTION).get("text") instanceof String) {
                        text = option.getJSONObject(TOP_LABEL_OPTION).getString("text");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION + ".text} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String color = "#ffffff";
                if(option.getJSONObject(TOP_LABEL_OPTION).has("color") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("color")) {
                    if(option.getJSONObject(TOP_LABEL_OPTION).get("color") instanceof String) {
                        color = option.getJSONObject(TOP_LABEL_OPTION).getString("color");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION + ".color} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                int size = 24;
                if(option.getJSONObject(TOP_LABEL_OPTION).has("size") && !option.getJSONObject(TOP_LABEL_OPTION).isNull("size")) {
                    if(option.getJSONObject(TOP_LABEL_OPTION).get("size") instanceof Integer) {
                        size = option.getJSONObject(TOP_LABEL_OPTION).getInt("size");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION + ".size} is not a type of integer.");
                        callback.onError(returnError);

                        return;
                    }
                }


                okaySelfieConfig.setTopLabel(new OkaySelfieLabelConfig(
                        text,//text
                        Color.parseColor(color),//color
                        size//font size
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + TOP_LABEL_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of switch camera button
        final String SWITCH_CAMERA_BUTTON_OPTION = "switchCameraButtonOption";
        if(option.has(SWITCH_CAMERA_BUTTON_OPTION) && !option.isNull(SWITCH_CAMERA_BUTTON_OPTION)) {
            if(option.get(SWITCH_CAMERA_BUTTON_OPTION) instanceof JSONObject) {
                String color = "#ffffff";
                if(option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).has("color") && !option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).isNull("color")) {
                    if(option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).get("color") instanceof String) {
                        color = option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).getString("color");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + SWITCH_CAMERA_BUTTON_OPTION + ".color} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                boolean isShow = true;
                if(option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).has("isShow") && !option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).isNull("isShow")) {
                    if(option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).get("isShow") instanceof Boolean) {
                        isShow = option.getJSONObject(SWITCH_CAMERA_BUTTON_OPTION).getBoolean("isShow");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + SWITCH_CAMERA_BUTTON_OPTION + ".isShow} is not a type of boolean.");
                        callback.onError(returnError);

                        return;
                    }
                }

                okaySelfieConfig.setSwitchBtnConfig(new OkaySelfieSwichBtnConfig(
                        Color.parseColor(color),//color
                        isShow//show/hide
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + SWITCH_CAMERA_BUTTON_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of confirm button
        final String CONFIRM_BUTTON_OPTION = "confirmButtonOption";
        if(option.has(CONFIRM_BUTTON_OPTION) && !option.isNull(CONFIRM_BUTTON_OPTION)) {
            if(option.get(CONFIRM_BUTTON_OPTION) instanceof JSONObject) {
                String backgroundColor = "#EB144C";
                if(option.getJSONObject(CONFIRM_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("backgroundColor")) {
                    if(option.getJSONObject(CONFIRM_BUTTON_OPTION).get("backgroundColor") instanceof String) {
                        backgroundColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("backgroundColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CONFIRM_BUTTON_OPTION + ".backgroundColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String iconColor = "#ffffff";
                if(option.getJSONObject(CONFIRM_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(CONFIRM_BUTTON_OPTION).isNull("iconColor")) {
                    if(option.getJSONObject(CONFIRM_BUTTON_OPTION).get("iconColor") instanceof String) {
                        iconColor = option.getJSONObject(CONFIRM_BUTTON_OPTION).getString("iconColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + CONFIRM_BUTTON_OPTION + ".iconColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                okaySelfieConfig.setConfirmBtnConfig(new OkayCamBtnConfig(
                        Color.parseColor(backgroundColor),//background
                        Color.parseColor(iconColor)//icon
                ));
            } else {
                JSONObject returnError = new JSONObject();
                returnError.put("errorCode", -102);
                returnError.put("errorMessage", "Parameter {" + CONFIRM_BUTTON_OPTION + "} is not a type of json object.");
                callback.onError(returnError);

                return;
            }
        }

        //Color of retake button
        final String RETAKE_BUTTON_OPTION = "retakeButtonOption";
        if(option.has(RETAKE_BUTTON_OPTION) && !option.isNull(RETAKE_BUTTON_OPTION)) {
            if(option.get(RETAKE_BUTTON_OPTION) instanceof JSONObject) {
                String backgroundColor = "#EB144C";
                if(option.getJSONObject(RETAKE_BUTTON_OPTION).has("backgroundColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("backgroundColor")) {
                    if(option.getJSONObject(RETAKE_BUTTON_OPTION).get("backgroundColor") instanceof String) {
                        backgroundColor = option.getJSONObject(RETAKE_BUTTON_OPTION).getString("backgroundColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + RETAKE_BUTTON_OPTION + ".backgroundColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                String iconColor = "#ffffff";
                if(option.getJSONObject(RETAKE_BUTTON_OPTION).has("iconColor") && !option.getJSONObject(RETAKE_BUTTON_OPTION).isNull("iconColor")) {
                    if(option.getJSONObject(RETAKE_BUTTON_OPTION).get("iconColor") instanceof String) {
                        iconColor = option.getJSONObject(RETAKE_BUTTON_OPTION).getString("iconColor");
                    } else {
                        JSONObject returnError = new JSONObject();
                        returnError.put("errorCode", -102);
                        returnError.put("errorMessage", "Parameter {" + RETAKE_BUTTON_OPTION + ".iconColor} is not a type of string.");
                        callback.onError(returnError);

                        return;
                    }
                }

                okaySelfieConfig.setRetakeBtnConfig(new OkayCamBtnConfig(
                        Color.parseColor(backgroundColor),//background
                        Color.parseColor(iconColor)//icon
                ));
            } else {

            }
        }

        OkayCamSelfie.start((Activity) mContext, licenseKey, okaySelfieConfig, (Boolean isSuccess, String image, Exception e) -> {
            if(isSuccess) {
                JSONObject returnResult = new JSONObject();
                String result = BitmapUtils.INSTANCE.convertToBase64(image);
                try {
                    returnResult.put("message", "Image is captured successfully.");
                    returnResult.put("image", result);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                callback.onCompleted(returnResult);

                //startFaceDetector(image);
            } else {
                JSONObject returnError = new JSONObject();
                try {
                    int errorCode = 0;
                    String errorMessage = "";
                    if(e.getMessage().contains("Invalid license key")) {
                        errorCode = -101;
                        errorMessage = e.getMessage();
                    } else if(e.getMessage().contains("User refused to grant permission")) {
                        errorCode = -103;
                        errorMessage = e.getMessage();
                    } else if(e.getMessage().contains("User cancelled the activity")) {
                        errorCode = -104;
                        errorMessage = e.getMessage();
                    }

                    returnError.put("errorCode", errorCode);
                    returnError.put("errorMessage", errorMessage);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                callback.onError(returnError);
            }

            return null;
        });
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
