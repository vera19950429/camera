package com.example.myapp.camera;

/**
 * Description:
 */
public interface FrameCallback {

    void onFrame(byte[] bytes, long time);

}
