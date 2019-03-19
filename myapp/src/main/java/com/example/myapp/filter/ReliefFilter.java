package com.example.myapp.filter;

import android.content.res.Resources;

public class ReliefFilter extends AFilter {
//    private float[] xy=new float[2];


    public ReliefFilter(Resources mRes) {
        super(mRes);
    }
//    private  int uTexSizeLocation;//改变定位
//    protected final String TexSize="TexSize";

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/base_vertex.glsl",
                "shader/color/relief_fragment.glsl");
//        uTexSizeLocation=glGetUniformLocation(mProgram,TexSize);//1 .得到属性的location

    }

    @Override
    protected void onSizeChanged(int width, int height) {
//        xy[0]=(float)width;
//        xy[1]=(float) height;


    }

    @Override
    protected void onSetExpandData() {
        super.onSetExpandData();
//        GLES20.glUniform2fv(uTexSizeLocation,0,xy,0);

    }
}
