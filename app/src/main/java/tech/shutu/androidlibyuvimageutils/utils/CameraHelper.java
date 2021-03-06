package tech.shutu.androidlibyuvimageutils.utils;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lake on 16-3-16.
 * Modified by raomengyang on 17-3-31
 * src : search librestreaming at GitHub
 */
public class CameraHelper {
    private static final String TAG = CameraHelper.class.getSimpleName();

    public static final int PREVIEW_WIDTH = 1280;
    public static final int PREVIEW_HEIGHT = 720;
    public static final int DST_WIDTH = 504;
    public static final int DST_HEIGHT = 896;

    public static boolean configCamera(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes != null) {
            if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            } else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            } else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_FIXED)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);
            }
        }
        parameters.setPreviewFormat(ImageFormat.YV12);
        parameters.setPreviewSize(PREVIEW_WIDTH, PREVIEW_HEIGHT);
        try {
            camera.setParameters(parameters);
        } catch (Exception e) {
            camera.release();
            return false;
        }
        return true;
    }
}
