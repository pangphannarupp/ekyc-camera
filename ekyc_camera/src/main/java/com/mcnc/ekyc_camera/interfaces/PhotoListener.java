package com.mcnc.ekyc_camera.interfaces;

import org.json.JSONObject;

public interface PhotoListener {
    void onCompleted(JSONObject result);
    void onError(JSONObject error);
}
