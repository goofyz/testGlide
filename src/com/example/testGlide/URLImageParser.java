package com.example.testGlide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;


public class URLImageParser implements Html.ImageGetter {
    private TextView container;

    public URLImageParser(TextView v) {
        this.container = v;
    }

    @Override
    public Drawable getDrawable(final String url) {
        final UrlDrawable urlDrawable = new UrlDrawable();
        final String source = url;

        debug("Url is " + url);
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) container.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        final float dpi = (int) metrics.density;

        Drawable.Callback callback = new Drawable.Callback() {

            @Override
            public void invalidateDrawable(Drawable drawable) {
                container.invalidate();
            }

            @Override
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long l) {
            }

            @Override
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {

            }
        };
        container.setTag(R.id.callback_id, callback);

        Glide.with(container.getContext()).load(source)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> glideDrawableTarget, boolean b) {
                        debug("Error in Glide listener");
                        if (e != null) {
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> glideDrawableTarget, boolean b, boolean b2) {
                        return false;
                    }
                }).
                into(new ViewTarget<TextView, GlideDrawable>(container) {
                    @Override
                    public void onResourceReady(GlideDrawable d, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        int width = (int) (d.getIntrinsicWidth() * dpi);
                        int height = (int) (d.getIntrinsicHeight() * dpi);
                        d.setBounds(0, 0, width, height);
                        d.setVisible(true, true);

                        urlDrawable.setBounds(0, 0, width, height);
                        urlDrawable.setDrawable(d);
                        urlDrawable.setCallback((Drawable.Callback) container.getTag(R.id.callback_id));
                        debug("Lisnter ended " + width + ", " + height + ", source: " + source + ", animated? " + d.isAnimated() + ", " + d.getClass().getSimpleName());

                        if (d instanceof GifDrawable) {
                            debug("Gif drawable ! animated? " + d.isAnimated() + ", " + (d.getCallback() == null));
                            GifDrawable a = (GifDrawable) d;
                            d.setLoopCount(GlideDrawable.LOOP_FOREVER);
                            d.start();
                        }
                    }

                });
        return urlDrawable;
    }

    private void debug(String msg) {
        Log.d("AAA", msg);
    }
}