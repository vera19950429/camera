package com.example.myapp.filter;

import android.content.res.Resources;

public class VortexFilter extends AFilter {
    public VortexFilter(Resources mRes) {
        super(mRes);
    }

    @Override
    protected void onCreate() {
        createProgramByAssetsFile("shader/base_vertex.glsl",
                "shader/color/vortex_fragment.glsl");

    }

    @Override
    protected void onSizeChanged(int width, int height) {

    }
}
