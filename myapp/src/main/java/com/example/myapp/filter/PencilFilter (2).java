package com.example.myapp.filter;

import android.content.res.Resources;
import android.opengl.GLES20;

public class PencilFilter extends AFilter {

    String frag="precision mediump float;                              \n"+
            "vec4 dip_filter(mat3 _filter, sampler2D _image, vec2 _xy, vec2 texSize)               \n"+
            "{                                                                                    \n"+
            "   mat3 _filter_pos_delta_x=mat3(vec3(-1.0, 0.0, 1.0), vec3(0.0, 0.0 ,1.0) ,vec3(1.0,0.0,1.0));            \n"+
            "   mat3 _filter_pos_delta_y=mat3(vec3(-1.0,-1.0,-1.0),vec3(-1.0,0.0,0.0),vec3(-1.0,1.0,1.0));              \n"+
            "   vec4 final_color = vec4(0.0, 0.0, 0.0, 0.0);                                      \n"+
            "   for(int i = 0; i<3; i++)                                                          \n"+
            "   {                                                                                 \n"+
            "       for(int j = 0; j<3; j++)                                                      \n"+
            "       {                                                                             \n"+
            "           vec2 _xy_new = vec2(_xy.x + _filter_pos_delta_x[i][j], _xy.y + _filter_pos_delta_y[i][j]); \n"+
            "           vec2 _uv_new = vec2(_xy_new.x/texSize.x, _xy_new.y/texSize.y);            \n"+
            "           final_color += texture2D(_image,_uv_new) * _filter[i][j];                 \n"+
            "       }                                                                             \n"+
            "   }                                                                                 \n"+
            "   return final_color;                                                               \n"+
            "}                                                                                    \n"+
            "varying vec2 textureCoordinate;                                                             \n"+
            "uniform vec2 TexSize;                                                               \n"+
            "uniform sampler2D vTexture;                                                         \n"+
            "void main()                                                                          \n"+
            "{                                                                                    \n"+
            "   vec2 intXY = vec2(textureCoordinate.x * TexSize.x, textureCoordinate.y * TexSize.y);            \n"+
            "   mat3 _smooth_fil = mat3(-0.5,-1.0,0.0,                                        \n"+
            "                           -1.0,0.0,1.0,                                         \n"+
            "                            0.0,1.0,0.5);                                        \n"+
            "   vec4 delColor = dip_filter(_smooth_fil, vTexture, intXY, TexSize);           \n"+
            "   float deltaGray = 0.3*delColor.x + 0.59*delColor.y + 0.11*delColor.z;          \n"+
            "   if(deltaGray < 0.0) deltaGray = -1.0 * deltaGray;                             \n"+
            "   deltaGray = 1.0 - deltaGray;                                                  \n"+
            "   gl_FragColor = vec4(deltaGray,deltaGray,deltaGray,1.0);                        \n"+
            "}                                                                                    \n";

    float[] xy=new float[2];
    private  int uTexSizeLocation;//改变定位

    public PencilFilter(Resources mRes) {
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
