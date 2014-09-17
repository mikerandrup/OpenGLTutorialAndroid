package com.mikerandrup.android.openglntuedu;

import android.app.Activity;
import android.os.Bundle;
import android.opengl.GLSurfaceView;

public class MyGLActivity extends Activity {

    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glView = new GLSurfaceView(this);
        glView.setRenderer(new MyGLRender(this));
        this.setContentView(glView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }

}
