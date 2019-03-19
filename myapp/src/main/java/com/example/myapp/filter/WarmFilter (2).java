package com.example.myapp.filter;

import android.content.res.Resources;
import android.opengl.GLES20;

import static android.opengl.GLES20.glGetUniformLocation;

public class WarmFilter extends AFilter {
    public WarmFilter(Resources mRes) {
        super(mRes);
    }
    //0.添加数组
    //暖色的颜色。是加强R/G来完成。这里注意的是颜色值在[0,1]之间
    float[] warmFilterColorData = {0.1f, 0.1f, 0.0f};
    private  int uChangeColorLocation;//改变定位
    protected final String U_CHANGE_COLOR="u_ChangeColor";
    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/base_vertex.glsl",
                "shader/color/warm_fragment.glsl");
        uChangeColorLocation=glGetUniformLocation(mProgram,U_CHANGE_COLOR);//1 .得到属性的location

    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }

    @Override
    protected void onSetExpandData() {
        super.onSetExpandData();
        //2. 将颜色数组传入着色器程序
        GLES20.glUniform3fv(uChangeColorLocation, 1, warmFilterColorData,0);

    }
}
