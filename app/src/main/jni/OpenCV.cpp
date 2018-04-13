//
// Created by wanghongjun on 2018/4/13.
//
//-------------------【头文件、命名空间部分】---------------------
//      描述：包含程序所依赖的头文件和命名空间
//-------------------------------------------------------------
#include <jni.h>
#include <opencv2/opencv.hpp>

using namespace cv;

//----------------------【函数定义部分】---------------------------
//      描述：定义函数
//---------------------------------------------------------------
extern "C" {


    //测试函数
    JNIEXPORT jstring JNICALL
    Java_com_example_wanghongjun_ndk_1opencv_1demo_101_opencv_OpenCVHelper_test
            (JNIEnv *env, jclass obj) {

        jstring result=env->NewStringUTF("OpenCV");
        return result;

    }

    JNIEXPORT jintArray JNICALL
    Java_com_example_wanghongjun_ndk_1opencv_1demo_101_opencv_OpenCVHelper_image2Gray
            (JNIEnv *env, jclass obj, jint w, jint h,
             jintArray pix) {

        //【1】定义整形指针指向pix
        jint* pixPtr;
        pixPtr=env->GetIntArrayElements(pix,JNI_FALSE);
        if (NULL==pixPtr){
            return 0;
        }

        //【1】创建原始图像
        Mat src(h,w,CV_8UC4, (u_char*) pixPtr);

        //【2】绘制椭圆
        int thickness = 2;		//线宽
        int lineTpye = 8;		//线型 （8联通线型）

        ellipse(src,				//将椭圆绘制到图像img上
                Point(w/2,h/2),	//椭圆中心点
                Size(w/4,w/16),	//大小位于WINDOW_WIDTH / 4, WINDOW_WIDTH /16内
                90,				//椭圆旋转角度
                0,				//扩展的弧度：开始弧度
                360,			//扩展的弧度：结束弧度
                Scalar(255, 129, 0),	//图形颜色：蓝色
                thickness,			//线宽
                lineTpye			//线型
        );

        //【3】将绘制图像转换为intarray
        int size=h*w;
        int * dataPtr=(int*) src.data;

        jintArray result=env->NewIntArray(size);
        env->SetIntArrayRegion(result,0,size,dataPtr);
        return result;
    }

}

