/*
 *
 * Camera2Activity.java
 * 
 * Created by Wuwang on 2017/3/6
 * Copyright © 2016年 深圳哎吖科技. All rights reserved.
 */
package com.example.myapp.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Size;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapp.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.filter.GrayFilter;
import com.example.myapp.utils.PermissionUtils;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import static android.hardware.camera2.CameraDevice.TEMPLATE_PREVIEW;


/**
 * Description:
 */
public class Camera2Activity extends BaseActivity implements FrameCallback {

    private SurfaceView mSurfaceView;
    private TextureController mController;
    private Renderer mRenderer;
    private int cameraId = 0;
    private BoomMenuButton boomMenuButton;
    private static int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtils.askPermission(this, new String[]{Manifest.permission.CAMERA, Manifest
            .permission.WRITE_EXTERNAL_STORAGE}, 10, initViewRunnable);
    }

    protected void onFilterSet(TextureController controller){
//        ZipPkmAnimationFilter mAniFilter=new ZipPkmAnimationFilter(getResources());
//        mAniFilter.setAnimation("assets/etczip/cc.zip");
//        controller.addFilter(mAniFilter);
    }

    protected void setContentView() {
        setContentView(R.layout.activity_camera2);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        boomMenuButton = (BoomMenuButton) findViewById(R.id.bmb);
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(Camera2Activity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                            switch (index){
                                case 0:
                                    Intent intent1= new Intent(Camera2Activity.this,Camera3Activity.class);
                                    startActivity(intent1);
                                    break;
                                case 1:
                                    Intent intent2= new Intent(Camera2Activity.this,BlurActivity.class);
                                    startActivity(intent2);

                                    break;
                                case 2:
                                    Intent intent3= new Intent(Camera2Activity.this,GrayActivity.class);
                                    startActivity(intent3);

                                    break;
                                case 3:
                                    Intent intent4= new Intent(Camera2Activity.this,PencilActivity.class);
                                    startActivity(intent4);

                                    break;
                                case  4:
                                    Intent intent5 =new Intent(Camera2Activity.this,WarmActivity.class);
                                    startActivity(intent5);
                                    break;
                                case  5:
                                    Intent intent6 =new Intent(Camera2Activity.this,RreliefActivity.class);
                                    startActivity(intent6);

                                    break;
                                case  6:
                                    Intent intent7 =new Intent(Camera2Activity.this,Vortex_Activity.class);
                                    startActivity(intent7);

                                    break;
                                case  7:
                                    Intent intent8 =new Intent(Camera2Activity.this,WaterActivity.class);
                                    startActivity(intent8);

                                    break;
                                default:
                            }
                        }
                    })
                    .normalImageRes(getImageResource())
                    .normalText(getext());
            boomMenuButton.addBuilder(builder);
        }
    }


    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];

    }
    private static String [] text = new String[]{"美颜","Blur",
        "Gray","Penci",",Warm","Rrelief","Vortex","Water"
    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.drawable.r1,
            R.drawable.r2,
            R.drawable.r3,
            R.drawable.r4,
            R.drawable.r5,
            R.drawable.r6,
            R.drawable.r7,
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter1:
               Intent intent1= new Intent(Camera2Activity.this,Camera3Activity.class);
                startActivity(intent1);
                break;
            case R.id.filter2:
                Intent intent2= new Intent(Camera2Activity.this,BlurActivity.class);
                startActivity(intent2);

                break;
            case R.id.filter3:
                Intent intent3= new Intent(Camera2Activity.this,GrayActivity.class);
                startActivity(intent3);

                break;
            case R.id.filter4:
                Intent intent4= new Intent(Camera2Activity.this,PencilActivity.class);
                startActivity(intent4);

                break;
            case  R.id.filter5:
                Intent intent5 =new Intent(Camera2Activity.this,WarmActivity.class);
                startActivity(intent5);
                break;
            case  R.id.filter6:
                Intent intent6 =new Intent(Camera2Activity.this,RreliefActivity.class);
                startActivity(intent6);

                break;
            case  R.id.filter7:
                Intent intent7 =new Intent(Camera2Activity.this,Vortex_Activity.class);
                startActivity(intent7);

                break;
            case  R.id.filter8:
                Intent intent8 =new Intent(Camera2Activity.this,WaterActivity.class);
                startActivity(intent8);

                break;
                default:
        }
        return true;
    }

    private Runnable initViewRunnable = new Runnable() {
        @Override
        public void run() {

            //TODO 设置数据源
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mRenderer = new Camera2Renderer();
            }else{
                mRenderer = new Camera1Renderer();
            }
            setContentView();
            mSurfaceView = (SurfaceView)findViewById(R.id.mSurface);
            mController = new TextureController(Camera2Activity.this);
//            WaterMarkFilter filter=new WaterMarkFilter(getResources());
//            filter.setWaterMark(BitmapFactory.decodeResource(getResources(),R.mipmap.logo));
//            filter.setPosition(300,50,300,150);
//            mController.addFilter(filter);
            onFilterSet(mController);
            mController.setFrameCallback(720, 1280, Camera2Activity.this);
            mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    mController.surfaceCreated(holder);
                    mController.setRenderer(mRenderer);
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    mController.surfaceChanged(width, height);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    mController.surfaceDestroyed();
                }
            });

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode == 10, grantResults, initViewRunnable,
            new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Camera2Activity.this, "没有获得必要的权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.mShutter:
                mController.takePhoto();
                break;
            case R.id.change:
                if (cameraId==1){
                    cameraId=0;
//                    try {
//                        mRenderer.mCameraManager.openCamera(cameraId+"",mRenderer.stateCallback,mRenderer.mHandler);
//                    } catch (CameraAccessException e) {
//                        e.printStackTrace();
//                    }
                }else {
                    cameraId=1;
//                    try {
//                        mRenderer.mCameraManager.openCamera(cameraId+"",mRenderer.stateCallback,mRenderer.mHandler);
//                    } catch (CameraAccessException e) {
//                        e.printStackTrace();
//                    }
                }
                break;
                default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mController != null) {
            mController.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mController != null) {
            mController.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mController != null) {
            mController.destroy();
        }
    }

    @Override
    public void onFrame(final byte[] bytes, long time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=Bitmap.createBitmap(720,1280, Bitmap.Config.ARGB_8888);
                ByteBuffer b=ByteBuffer.wrap(bytes);
                bitmap.copyPixelsFromBuffer(b);
                saveBitmap(bitmap);
                bitmap.recycle();
            }
        }).start();
    }

    protected String getSD(){
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    //图片保存
    public void saveBitmap(Bitmap bmp){
//        String path =  getSD()+ "/OpenGLDemo/photo/";
//        File folder=new File(path);
//        if(!folder.exists()&&!folder.mkdirs()){
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(Camera2Activity.this, "无法保存照片", Toast.LENGTH_SHORT).show();
//                }
//            });
//            return;
//        }
//        long dataTake = System.currentTimeMillis();
//        final String jpegName=path+ dataTake +".jpg";
//        try {
//            FileOutputStream fout = new FileOutputStream(jpegName);
//            BufferedOutputStream bos = new BufferedOutputStream(fout);
//            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//            bos.flush();
//            bos.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(Camera2Activity.this, "保存成功->"+jpegName, Toast.LENGTH_SHORT).show();
//            }
//        });

        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "BianMin");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "sign_"+System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
// 其次把文件插入到系统图库
        String path = file.getAbsolutePath();
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(), path, fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }



    private class Camera1Renderer implements Renderer {

        private Camera mCamera;

        @Override
        public void onDestroy() {
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
            mCamera = Camera.open(cameraId);
            mController.setImageDirection(cameraId);
            Camera.Size size = mCamera.getParameters().getPreviewSize();
            mController.setDataSize(size.height, size.width);
            try {
                mCamera.setPreviewTexture(mController.getTexture());
                mController.getTexture().setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                    @Override
                    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                        mController.requestRender();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.startPreview();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class Camera2Renderer implements Renderer {

        CameraDevice mDevice;
        CameraManager mCameraManager;
        private HandlerThread mThread;
        public Handler mHandler;
        private Size mPreviewSize;
        Camera2Renderer() {
            mCameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
            mThread = new HandlerThread("camera2 ");
            mThread.start();
            mHandler = new Handler(mThread.getLooper());
        }

        @Override
        public void onDestroy() {
            if(mDevice!=null){
                mDevice.close();
                mDevice=null;
            }
        }


        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            try {
                if(mDevice!=null){
                    mDevice.close();
                    mDevice=null;
                }
                CameraCharacteristics c=mCameraManager.getCameraCharacteristics(cameraId+"");
                StreamConfigurationMap map=c.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                Size[] sizes=map.getOutputSizes(SurfaceHolder.class);
                //自定义规则，选个大小
                mPreviewSize=sizes[0];
                mController.setDataSize(mPreviewSize.getHeight(),mPreviewSize.getWidth());
                CameraDevice.StateCallback stateCallback=new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(CameraDevice camera) {
                        mDevice=camera;
                        try {
                            Surface surface=new Surface(mController
                                    .getTexture());
                            final CaptureRequest.Builder builder=mDevice.createCaptureRequest
                                    (TEMPLATE_PREVIEW);
                            builder.addTarget(surface);
                            mController.getTexture().setDefaultBufferSize(
                                    mPreviewSize.getWidth(),mPreviewSize.getHeight());
                            mDevice.createCaptureSession(Arrays.asList(surface), new
                                    CameraCaptureSession.StateCallback() {
                                        @Override
                                        public void onConfigured(CameraCaptureSession session) {
                                            try {
                                                session.setRepeatingRequest(builder.build(), new CameraCaptureSession.CaptureCallback() {
                                                    @Override
                                                    public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
                                                        super.onCaptureProgressed(session, request, partialResult);
                                                    }

                                                    @Override
                                                    public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                                                        super.onCaptureCompleted(session, request, result);
                                                        mController.requestRender();
                                                    }
                                                },mHandler);
                                            } catch (CameraAccessException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onConfigureFailed(CameraCaptureSession session) {

                                        }
                                    },mHandler);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onDisconnected(CameraDevice camera) {
                        mDevice=null;
                    }

                    @Override
                    public void onError(CameraDevice camera, int error) {

                    }
                };

                mCameraManager.openCamera(cameraId + "", stateCallback, mHandler);
            } catch (SecurityException | CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    }
}
