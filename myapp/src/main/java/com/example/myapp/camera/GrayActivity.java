package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.GrayFilter;

public class GrayActivity extends Camera2Activity {

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_gray);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        GrayFilter grayFilter=new GrayFilter(getResources());
        controller.addFilter(grayFilter);
    }
}
