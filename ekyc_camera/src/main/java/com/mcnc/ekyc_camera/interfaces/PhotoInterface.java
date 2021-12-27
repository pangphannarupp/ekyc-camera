package com.mcnc.ekyc_camera.interfaces;

import org.json.JSONObject;

public interface PhotoInterface {
    void onCompleted(JSONObject result);
    void onError(JSONObject error);
}
