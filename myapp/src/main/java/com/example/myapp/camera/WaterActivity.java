package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.GrayFilter;
import com.example.myapp.filter.WaterFilter;

public class WaterActivity extends Camera2Activity {
    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_water);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        WaterFilter waterFilter=new WaterFilter(getResources());
        controller.addFilter(waterFilter);
    }
}
