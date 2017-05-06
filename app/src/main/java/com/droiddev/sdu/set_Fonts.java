package com.droiddev.sdu;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class set_Fonts extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
