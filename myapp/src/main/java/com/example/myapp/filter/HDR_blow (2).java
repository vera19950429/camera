package com.example.myapp.filter;

import android.content.res.Resources;
import android.opengl.GLES20;

public class HDR_blow extends AFilter {

    String frag="precision mediump float;     \n"  +
"varying vec2 textureCoordinate;     \n"+
        "uniform sampler2D vTexture; \n"+
        "uniform float k;                     \n"+
        "vec4 xposure(vec4 _color, float gray, float ex)  \n"+
        "{                            \n"+
        "   float b = (4.0*ex - 1.0);     \n"+
        "   float a = 1.0 - b;          \n"+
        "   float f = gray*(a*gray + b); \n"+
        "   return f*_color;          \n"+
        "}                            \n"+
        "void main()                  \n"+
        "{                            \n"+
        "   vec4 _dsColor = texture2D(vTexture, v_texCoord); \n"+
        "   float _lum = 0.3*_dsColor.x + 0.59*_dsColor.y;    \n"+
        "   vec4 _fColor = texture2D(vTexture, textureCoordinate);  \n"+
        "   gl_FragColor = xposure(_fColor, _lum, k);         \n"+
        "}                                                    \n"; 

    float k=1.6f;
    private  int uKLocation;//改变定位

    public HDR_blow(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgram(uRes(mRes,"shader/base_vertex.glsl"),frag);
        uKLocation= GLES20.glGetUniformLocation(mProgram,"k");
    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }

    @Override
    protected void onSetExpandData() {
        super.onSetExpandData();
        GLES20.glUniform1f(uKLocation,k);
    }
}
