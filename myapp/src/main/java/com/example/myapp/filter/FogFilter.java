package com.example.myapp.filter;

import android.content.res.Resources;

public class FogFilter extends AFilter {
    public FogFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/fog/vertex.glsl",
                "shader/fog/fragment.glsl");

    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }
}
