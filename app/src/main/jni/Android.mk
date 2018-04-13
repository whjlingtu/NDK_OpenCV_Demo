LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE :=STATIC

ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include I:\Android\OpenCV-android-sdk\sdk\native\jni\OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_MODULE := OpenCV

LOCAL_C_INCLUDE :=I:\Android\OpenCV-android-sdk\sdk\native\jni\include\opencv2\opencv.hpp

LOCAL_SRC_FILES :=OpenCV.cpp

LOCAL_LDLIBS +=  -lm -llog

include $(BUILD_SHARED_LIBRARY)