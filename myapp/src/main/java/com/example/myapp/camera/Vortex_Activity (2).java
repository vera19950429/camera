package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.GrayFilter;
import com.example.myapp.filter.VortexFilter;

public class Vortex_Activity extends Camera2Activity {

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_vortex_);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        VortexFilter vortexFilter=new VortexFilter(getResources());
        controller.addFilter(vortexFilter);
    }
}
