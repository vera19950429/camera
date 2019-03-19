package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.BlurFilter;

public class BlurActivity extends Camera2Activity{

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_blur);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        BlurFilter blurFilter=new BlurFilter(getResources());
        controller.addFilter(blurFilter);
    }

}
