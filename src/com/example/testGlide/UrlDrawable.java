package com.example.testGlide;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;


public class UrlDrawable extends Drawable {
    public GlideDrawable drawable;

    public UrlDrawable() {
        super();
    }

    @Override
    public void setAlpha(int alpha) {
        if (drawable != null) {
            drawable.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (drawable != null) {
            drawable.setColorFilter(cf);
        }
    }

    @Override
    public int getOpacity() {
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {
// override the draw to facilitate refresh function later
        if (drawable != null) {
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            canvas.drawRect(drawable.getBounds(), p);
            drawable.draw(canvas);

            if (!drawable.isRunning()) {
                drawable.start();
            }
        }
    }
}