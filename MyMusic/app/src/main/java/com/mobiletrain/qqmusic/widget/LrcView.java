package com.mobiletrain.qqmusic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.mobiletrain.qqmusic.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public class LrcView extends View {

    List<Map<String, Object>> data;

    private Paint paint;
    private Paint hPaint;

    private long currentMillis;
    private int currentLinePosition;
    private int width;
    private int height;

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public void setCurrentMillis(long currentMillis) {
        this.currentMillis = currentMillis;
    }

    public LrcView(Context context) {
        this(context, null);
    }

    public LrcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LrcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setTextSize(35);
        paint.setColor(Color.WHITE);

        hPaint = new Paint();
        hPaint.setAntiAlias(true);//抗锯齿
        hPaint.setTextSize(35);
        hPaint.setColor(getResources().getColor(R.color.toolbarGreen));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (width == 0 || height == 0) {
            width = getWidth();
            height = getHeight();
        }

        if (data == null) {
            drawTextCenter(canvas, "暂无歌词", hPaint);
            return;
        }

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> map = data.get(i);
            long start = (long) map.get("start");
            long end = (long) map.get("end");

            if (currentMillis >= start && currentMillis <= end) {
                currentLinePosition = i;
            }
        }


        drawOtherLines(canvas);

        drawCurrentLine(canvas);

    }

    private void drawCurrentLine(Canvas canvas) {
        String line = (String) data.get(currentLinePosition).get("text");
        long start = (long) data.get(currentLinePosition).get("start");
        long end = (long) data.get(currentLinePosition).get("end");
        // drawTextCenter(canvas, line, paint);
        //    Log.e("test", "drawCurrentLine: "+line+"/"+start );
        if (listener != null) {
            listener.onLyricChanged(start, end, currentMillis, line);
        //    drawTextCenter(canvas, line, hPaint);
        } else {
            drawTextCenter(canvas, line, hPaint);
        }
    }

    private void drawOtherLines(Canvas canvas) {
        for (int i = currentLinePosition + 1; i < currentLinePosition + 11; i++) {
            if (i > -1 && i < data.size()) {
                String text = (String) data.get(i).get("text");
                Rect rect = new Rect();
                hPaint.getTextBounds(text, 0, text.length(), rect);
                canvas.drawText(text, width / 2 - rect.width() / 2, height / 2 + rect.height() / 2 + 60 * (i - currentLinePosition), paint);
            }
        }
        for (int i = currentLinePosition - 1; i > currentLinePosition - 11; i--) {
            if (i > -1 && i < data.size()) {
                String text = (String) data.get(i).get("text");
                Rect rect = new Rect();
                hPaint.getTextBounds(text, 0, text.length(), rect);
                canvas.drawText(text, width / 2 - rect.width() / 2, height / 2 + rect.height() / 2 - 60 * (currentLinePosition - i), paint);
            }
        }
    }

    private void drawTextCenter(Canvas canvas, String text, Paint p) {
        Rect rect = new Rect();
        hPaint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text, width / 2 - rect.width() / 2, height / 2 + rect.height() / 2, p);
    }

    OnLyricChangedListener listener;

    public void setListener(OnLyricChangedListener listener) {
        this.listener = listener;
    }

    public interface OnLyricChangedListener {
        void onLyricChanged(long start, long end, long currentMillis, String text);
    }

}
