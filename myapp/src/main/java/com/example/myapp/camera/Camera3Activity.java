package com.example.myapp.camera;

import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.widget.SeekBar;

import com.example.myapp.R;
import com.example.myapp.filter.BarrelBlurFilter;
import com.example.myapp.filter.Beauty;
import com.example.myapp.filter.BlurFilter;
import com.example.myapp.filter.FogFilter;
import com.example.myapp.filter.HDR_blow;
import com.example.myapp.filter.LookupFilter;
import com.example.myapp.filter.PencilFilter;
import com.example.myapp.filter.ReliefFilter;
import com.example.myapp.filter.VortexFilter;
import com.example.myapp.filter.WarmFilter;


/**
 * Created by aiya on 2017/6/8.
 */

public class Camera3Activity extends Camera2Activity {

    private AppCompatSeekBar mSeek;
    private LookupFilter mLookupFilter;
    private Beauty mBeautyFilter;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_camera3);
        mSeek=(AppCompatSeekBar) findViewById(R.id.mSeek);
        mSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("wuwang","process:"+progress);
                mLookupFilter.setIntensity(progress/100f);
                mBeautyFilter.setFlag(progress/20+1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onFilterSet(TextureController controller) {
        mLookupFilter=new LookupFilter(getResources());
        mLookupFilter.setMaskImage("lookup/purity.png");
        mLookupFilter.setIntensity(0.0f);
        controller.addFilter(mLookupFilter);
        mBeautyFilter=new Beauty(getResources());
        controller.addFilter(mBeautyFilter);
//        ReliefFilter reliefFilter=new ReliefFilter(getResources());
//        controller.addFilter(reliefFilter);
//        BlurFilter blurFilter=new BlurFilter(getResources());
//        controller.addFilter(blurFilter);
//        VortexFilter vortexFilter=new VortexFilter(getResources());
//        controller.addFilter(vortexFilter);
//        FogFilter fogFilter=new FogFilter(getResources());
//        controller.addFilter(fogFilter);
//        PencilFilter pencilFilter=new PencilFilter(getResources());
//        controller.addFilter(pencilFilter);
//        HDR_blow hdr_blow=new HDR_blow(getResources());
//        controller.addFilter(hdr_blow);
//        WarmFilter warmFilter=new WarmFilter(getResources());
//        controller.addFilter(warmFilter);
//        BarrelBlurFilter barrelBlurFilter=new BarrelBlurFilter(getResources());
//        controller.addFilter(barrelBlurFilter);
    }
}
