package com.example.myapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.filter.PencilFilter;

public class PencilActivity extends Camera2Activity {

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_pencil);
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        PencilFilter pencilFilter=new PencilFilter(getResources());
        controller.addFilter(pencilFilter);
    }
}
