package vip.frendy.opencv;

import android.graphics.Bitmap;

import vip.frendy.opencv.utils.ShapeHelper;

/**
 * Created by frendy on 2018/7/6.
 */

public class OpenCVManager {

    private static OpenCVManager manager;

    public static OpenCVManager getInstance() {
        if(manager == null) {
            manager = new OpenCVManager();
        }
        return manager;
    }

    private OpenCVManager() {
        System.loadLibrary("CVDroid");
    }

    //黑白
    public native Bitmap toBW(Bitmap bitmap);

    //TODO: 背景虚化，目前通过指定的遮罩来实现，后续可添加边缘检测抠出遮罩
    public native Bitmap toBokeh(Bitmap bitmap, int x, int y, int w, int h, int blurSize, int type);
    public native Bitmap toBokehWithCircle(Bitmap bitmap, int r, int blurSize, int type);

    //局部放大
    public native int[] toEnlarge(int[] buffer, int width, int heigth, int centerX, int centerY, int radius, float multiple);
    public Bitmap toEnlarge(Bitmap bitmap, int x, int y, int radius, int strength) {
        return ShapeHelper.enlarge(bitmap, x, y, radius, strength);
    }

    //变形
    public native Bitmap toStretch(Bitmap bitmap);
    public native Bitmap toCylinder(Bitmap bitmap);

    //分类
    public native int classifier(Bitmap bitmap, String cascadePath);

    //手势识别
    public native int getFingerCount(Bitmap bitmap, int threshold, float widthScale, float heightScale);
}
