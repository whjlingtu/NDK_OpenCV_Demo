package com.example.wanghongjun.ndk_opencv_demo_01.opencv;

/**
 * Created by wanghongjun on 2018/4/13.
 */

public class OpenCVHelper {

    public static  void loadLib(){
        System.loadLibrary("OpenCV");
    }

    public static native String test();

    public static native int[] image2Gray(int w,int h,
                                          int pix[]);


}
