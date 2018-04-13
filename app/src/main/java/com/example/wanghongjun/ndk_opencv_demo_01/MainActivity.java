package com.example.wanghongjun.ndk_opencv_demo_01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanghongjun.ndk_opencv_demo_01.opencv.OpenCVHelper;

public class MainActivity extends AppCompatActivity {


    private TextView tv_test;
    private ImageView iv_test;
    private Button bt;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();


    }

    private void initEvent(){

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int w=bitmap.getWidth();
                int h=bitmap.getHeight();
                int[] pix=new int[w*h];
                bitmap.getPixels(pix,0,w,0,0,w,h);

                //调用本地方法
                int[] opencv_pix=OpenCVHelper.image2Gray(w,h,pix);
                if (opencv_pix!=null){
                    Bitmap test=IntArray2Bitmap(opencv_pix,w,h);
                    iv_test.setImageBitmap(test);
                }else{
                    tv_test.setText("调用Opencv 图像处理失败");
                }

            }
        });
    }

    /**
     * 将像素数组转换为Bitmap对象
     * @param pix
     * @param w
     * @param h
     * @return
     */
    public  Bitmap IntArray2Bitmap(int[] pix, int w, int h){
        Bitmap result=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        result.setPixels(pix,0,w,0,0,w,h);
        Log.d("TAG",""+pix[0]);
        return result;
    }

    private void initData(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.simple);
    }

    private void initView(){
        OpenCVHelper.loadLib(); //加载Opencv库

        tv_test = (TextView)findViewById(R.id.sample_text);
        iv_test = (ImageView)findViewById(R.id.iv_test);
        bt = (Button)findViewById(R.id.bt_onclick);

        tv_test.setText(OpenCVHelper.test());
    }


}
