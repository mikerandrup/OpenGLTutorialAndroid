package com.mikerandrup.android.openglntuedu;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by mike randrup on 9/16/2014.
 */
public class Square {
    private FloatBuffer vertexBuffer;  // Buffer for vertex-array

    private float[] vertices = {
            -1.0f, -1.0f,  0.0f,
            1.0f, -1.0f,  0.0f,
            -1.0f,  1.0f,  0.0f,
            1.0f,  1.0f,  0.0f
    };

    public Square() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void draw(GL10 gl) {

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        int error = gl.glGetError();
        if(error != GL10.GL_NO_ERROR) {
            Log.e("square", "glError: " + error);
        }
    }
}
