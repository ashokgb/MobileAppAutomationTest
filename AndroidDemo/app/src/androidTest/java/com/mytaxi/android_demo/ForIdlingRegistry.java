package com.mytaxi.android_demo;

import android.support.test.espresso.IdlingResource;

public class ForIdlingRegistry implements IdlingResource {
    private boolean isIdle = true;
    private ResourceCallback callback;
    @Override
    public String getName() {
        return this.getClass().getName();
    }
    @Override
    public boolean isIdleNow() {
        return isIdle;
    }
    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
    public static ForIdlingRegistry getResource() {
        return new ForIdlingRegistry();
    }

}
