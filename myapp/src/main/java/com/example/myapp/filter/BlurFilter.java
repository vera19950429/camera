package com.example.myapp.filter;

import android.content.res.Resources;

public class BlurFilter extends AFilter {
    public BlurFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/base_vertex.glsl",
                "shader/color/blur_fragment.glsl");

    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }
}
