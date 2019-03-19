package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.GrayFilter;
import com.example.myapp.filter.ReliefFilter;

public class RreliefActivity extends Camera2Activity {

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_rrelief);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        ReliefFilter reliefFilter=new ReliefFilter(getResources());
        controller.addFilter(reliefFilter);
    }
}
