package com.example.myapp.filter;

import android.content.res.Resources;

public class BarrelBlurFilter extends AFilter {
    public BarrelBlurFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/base_vertex.glsl",
                "shader/color/barrelBlur_fragment.glsl");

    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }
}
