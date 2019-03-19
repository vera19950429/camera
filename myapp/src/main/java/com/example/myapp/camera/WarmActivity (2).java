package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.WarmFilter;

public class WarmActivity extends Camera2Activity {

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_warm);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        WarmFilter warmFilter=new WarmFilter(getResources());
        controller.addFilter(warmFilter);
    }
}
