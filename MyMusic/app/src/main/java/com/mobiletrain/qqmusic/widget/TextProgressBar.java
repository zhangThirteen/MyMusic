package com.mobiletrain.qqmusic.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.mobiletrain.qqmusic.util.MyBitmapUtil;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class TextProgressBar extends View {
    String text = "";
    private Paint bgPaint;
    private int textSize = 40;
    private Paint fgPaint;

    int progress;

    public void setText(String text) {
        this.text = text;
    }

    public void setProgress(int progress) {
        if (progress < 1) {
            this.progress = 1;
        } else if (progress > 99) {
            this.progress = 99;
        } else {
            this.progress = progress;
        }
    }

    public void setBgPaint(Paint bgPaint) {
        this.bgPaint = bgPaint;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setFgPaint(Paint fgPaint) {
        this.fgPaint = fgPaint;
    }

    public TextProgressBar(Context context) {
        this(context, null);
    }

    public TextProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setTextSize(textSize);

        fgPaint = new Paint();
        fgPaint.setAntiAlias(true);

        fgPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if ("".equals(text)) {

            return;
        }

        Bitmap textBitmap = MyBitmapUtil.createTextBitmap(text, textSize, Color.GREEN, 1);
        darwTextCenter(canvas, textBitmap);


        int width = (int) (textBitmap.getWidth() * (progress / 100f));
        Bitmap progressBitmap = Bitmap.createBitmap(textBitmap, 0, 0, width > textBitmap.getWidth() ? textBitmap.getWidth() : width, textBitmap.getHeight());
        fgPaint.setColor(Color.GREEN);
        //   Log.e("test", "onDraw: "+ (getWidth() / 2 - textBitmap.getWidth() / 2)+"/"+(getHeight() / 2 - textBitmap.getHeight() / 2 + 5));
        canvas.drawBitmap(progressBitmap,
                getWidth() / 2 - textBitmap.getWidth() / 2,
                (getHeight() / 2 - textBitmap.getHeight() / 2)+5,
                fgPaint);


    }

    private void darwTextCenter(Canvas canvas, Bitmap textBitmap) {
        float textWidth = bgPaint.measureText(this.text);
        Rect textBoundRect = new Rect();
        bgPaint.getTextBounds(text, 0, text.length(), textBoundRect);
        bgPaint.setColor(Color.WHITE);
        //     Log.e("test", "darwTextCenter: "+ (getWidth() / 2 - textBitmap.getWidth() / 2)+"/"+(getHeight() / 2 - textBitmap.getHeight() / 2 + 5));
        canvas.drawText(text, getWidth() / 2 - textBitmap.getWidth() / 2,
                getHeight() / 2 + textBitmap.getHeight() / 2, bgPaint);
    }
}
