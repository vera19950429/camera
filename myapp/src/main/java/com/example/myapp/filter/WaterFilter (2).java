package com.example.myapp.filter;

import android.content.res.Resources;
import android.opengl.GLES20;

public class WaterFilter extends AFilter {

    String frag="precision mediump float;    \n"  +
"varying vec2 textureCoordinate;    \n"+
        "uniform sampler2D vTexture;  \n"+
        "uniform vec2 TexSize;       \n"+
        "float _waterPower = 40.0;     \n"+
        "float _quatLevel = 5.0;       \n"+
        "vec4 quant(vec4 _cl, float n)  \n"+
        "{                            \n"+
        "   _cl.x = floor(_cl.x*255.0/n)*n/255.0;  \n"+
        "   _cl.y = floor(_cl.y*255.0/n)*n/255.0;  \n"+
        "   _cl.z = floor(_cl.z*255.0/n)*n/255.0;  \n"+
        "   return _cl;                            \n"+
        "}                                         \n"+
        "void main()                               \n"+
        "{                                         \n"+
        "   vec4 noiseColor = _waterPower*texture2D(vTexture,textureCoordinate);           \n"+
        "   vec2 newUV =vec2 (textureCoordinate.x + noiseColor.x/TexSize.x,textureCoordinate.y + noiseColor.y/TexSize.y);  \n"+
        "   vec4 _fColor = texture2D(vTexture,newUV);                 \n"+
        "   gl_FragColor = quant(_fColor, 255.0/pow(2,_quatLevel));   \n"+
        "}                                                \n";

    float[] xy=new float[2];
    private  int uTexSizeLocation;//改变定位

    public WaterFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgram(uRes(mRes,"shader/base_vertex.glsl"),frag);
        uTexSizeLocation= GLES20.glGetUniformLocation(mProgram,"TexSize");
    }

    @Override
    protected void onSizeChanged(int width, int height) {
        xy[0]=width;
        xy[1]=height;

    }

    @Override
    protected void onSetExpandData() {
        super.onSetExpandData();
        GLES20.glUniform2fv(uTexSizeLocation,1,xy,0);
    }
}
