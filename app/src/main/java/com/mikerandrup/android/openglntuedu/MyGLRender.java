package com.mikerandrup.android.openglntuedu;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

/**
 * Created by mike randrup on 9/16/2014.
 */
public class MyGLRender implements GLSurfaceView.Renderer {

    private Context ctx;
    private Pyramid pyramid;
    private FullCube fullCube;
    private MultifaceCube1 multifaceCube1;
    private MultifaceCube2 multifaceCube2;
    private TextureCube textureCube;

    private static float anglePyramid = 0;
    private static float angleCube = 0;
    private static float speedPyramid = 2.0f;
    private static float speedCube = -1.5f;

    public MyGLRender(Context context) {
        ctx = context;
        pyramid = new Pyramid();
        fullCube = new FullCube();
        multifaceCube1 = new MultifaceCube1();
        multifaceCube2 = new MultifaceCube2();
        textureCube = new TextureCube();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);

        try {
            textureCube.loadTexture(gl, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gl.glEnable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;
        float aspect = (float)width / height;

        gl.glViewport(0,0,width,height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.0f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        // TODO: display resizing

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(-6.0f, 0.0f, -12.0f);
        gl.glRotatef(anglePyramid, 0.1f, 1.0f, -0.1f);
        pyramid.draw(gl);

        gl.glLoadIdentity();
        gl.glTranslatef(-3.0f, 0.0f, -12.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
        fullCube.draw(gl);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -12.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
        multifaceCube1.draw(gl);

        gl.glLoadIdentity();
        gl.glTranslatef(3.0f, 0.0f, -12.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
        multifaceCube2.draw(gl);

        gl.glLoadIdentity();
        gl.glTranslatef(6.0f, 0.0f, -12.0f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
        textureCube.draw(gl);

        anglePyramid += speedPyramid;
        angleCube += speedCube;
    }
}
