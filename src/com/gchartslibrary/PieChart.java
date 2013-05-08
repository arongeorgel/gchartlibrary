package com.gchartslibrary;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.RectF;

import com.gchartslibrary.chart.Chart;
import com.gchartslibrary.model.ChartDataset;
import com.gchartslibrary.renderer.Renderer;

public class PieChart extends Chart {

    private int mCenterX;
    private int mCenterY;

    public PieChart(ChartDataset dataset, Renderer renderer) {
        super(dataset, renderer);
        mCenterX = 0;
        mCenterY = 0;
    }

    private int colorToDarker(int color, float value) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= value;
        return Color.HSVToColor(hsv);
    }

    @Override
    public void draw(Canvas canvas, int x, int y, int width, int height, Paint paint) {
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);

        int legendSize = getLegendSize(mRenderer, height / 5, 0);
        int left = x;
        int top = y;
        int right = x + width;
        int sLength = mDataset.getItemCount();
        double total = 0;
        String[] titles = new String[sLength];
        for (int i = 0; i < sLength; i++) {
            total += mDataset.getValue(i);
            titles[i] = mDataset.getCategory(i);
        }
        if (mRenderer.isFitLegend()) {
            legendSize = drawLegend(canvas, mRenderer, titles, left, right, y, width, height, legendSize, paint, true);
        }
        int bottom = y + height - legendSize;

        drawBackground(mRenderer, canvas, x, y, width, height, paint, false, Renderer.NO_COLOR);
        float currentAngle = mRenderer.getStartAngle();
        float labelCurrentAngle = mRenderer.getStartAngle();
        int mRadius = Math.min(Math.abs(right - left), Math.abs(bottom - top));
        int radius = (int) (mRadius * 0.35) + 50;

        if (mCenterX == 0) {
            mCenterX = (left + right) / 2;
        }
        if (mCenterY == 0) {
            mCenterY = (bottom + top) / 2;
        }

        // Hook in clip detection after center has been calculated
        // mPieMapper.setDimensions(radius, mCenterX, mCenterY);
        // boolean loadPieCfg = !mPieMapper.areAllSegmentPresent(sLength);
        // if (loadPieCfg) {
        // mPieMapper.clearPieSegments();
        // }

        float shortRadius = radius * 0.9f;
        float longRadius = radius * 1.1f;

        RectF oval = new RectF(mCenterX - radius, mCenterY - radius, mCenterX + radius, mCenterY + radius);

        for (int i = 0; i < sLength; i++) {
            float value = (float) mDataset.getValue(i);
            float angle = (float) (value / total * 360);
            int color = mRenderer.getRenderers().get(i).getColor();
            Paint paint3 = new Paint();
            paint3.setStrokeWidth(1);
            paint3.setStrokeCap(Paint.Cap.ROUND);
            paint3.setStyle(Paint.Style.FILL);

            int[] colors = { color, colorToDarker(color, 0.95f) };
            RadialGradient gradient3 = new RadialGradient(mCenterX, mCenterY, radius, colors, null, android.graphics.Shader.TileMode.CLAMP);
            paint3.setShader(gradient3);
            paint3.setAntiAlias(true);

            canvas.drawArc(oval, currentAngle, angle, true, paint3);
            currentAngle += angle;
        }
    }

    @Override
    public Renderer getRenderer() {
        // TODO Auto-generated method stub
        return null;
    }

}
