/**
 * ProgressView.java
 * <p>
 * A widget to used to draw the progress view in horizontal.
 *
 * @author Rajkumar.N
 */
package com.raj.progressview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;


/**
 * A widget to used to draw the progress view in horizontal.
 */
public class ProgressView extends View {

    private int progressColor = Color.GREEN;
    private int progressFillColor = Color.YELLOW;

    private int stage;
    private int progress;
    private int numberOfStages;
    private float radius;
    private float lineDepth;
    private float startY = 10;

    private Context mContext;

    private Paint circlePaint = new Paint();
    private Paint linePaint = new Paint();
    private Paint fillLinePaint = new Paint();

    public ProgressView(Context context) {
        super(context);
        mContext = context;
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getNumberOfStages() {
        return numberOfStages;
    }

    public void setNumberOfStages(int numberOfStages) {
        this.numberOfStages = numberOfStages;
    }

    /**
     * Loads the initial resource for the View.
     *
     * @param context  - The Context associated with the view.
     * @param attrs    - The AttributeSet associated with the view.
     * @param defStyle - The style associated with the view.
     */
    public void init(Context context, AttributeSet attrs, int defStyle) {
//        if (!isInEditMode())
        {
            mContext = context;
            TypedArray typedArray = getContext().getTheme()
                    .obtainStyledAttributes(attrs,
                            R.styleable.ProgressView, defStyle, 0);

            stage = typedArray.getInteger(R.styleable.ProgressView_stage, 0);
            progress = typedArray.getInteger(R.styleable.ProgressView_progress, 0);
            numberOfStages = typedArray.getInteger(R.styleable.ProgressView_count, 0);

            // Load defaults from the resources
            final Resources res = getResources();

            // Get the radius and line depth from dimens.
            radius = typedArray.getDimension(R.styleable.ProgressView_circleRadius, res.getDimension(R.dimen.default_progress_circle_radius));
            lineDepth = typedArray.getDimension(R.styleable.ProgressView_lineDepth, res.getDimension(R.dimen.default_progress_line_depth));


            // Set the progress and fill colors
            progressColor = typedArray.getColor(R.styleable.ProgressView_progressColor, ContextCompat.getColor(mContext, R.color.default_progress_color));
            progressFillColor = typedArray.getColor(R.styleable.ProgressView_progressFillColor, ContextCompat.getColor(mContext, R.color.default_progress_fill_color));

            startY = radius;

            // Update the line stroke
            fillLinePaint.setStrokeWidth(lineDepth);
            linePaint.setStrokeWidth(lineDepth);

            // Update the paint with colors
            linePaint.setColor(progressColor);
            fillLinePaint.setColor(progressFillColor);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (getWidth() == 0 || getHeight() == 0 || numberOfStages < 2) {
            return;
        }

        float stagePoint = (getWidth() - (2 * radius)) / (numberOfStages - 1);

        // Draw progress and fill lines with cap
        float cap = radius / 4;
        for (int i = 1; i < numberOfStages; i++) {
            // Calculate line start and end
            float fillStart = (stagePoint * (i - 1)) + (radius * 2) + cap;
            float fillEnd = (stagePoint * i) - cap;
            // Draw the progress line
            canvas.drawLine(fillStart, startY, fillEnd, startY, linePaint);

            // Draw the filled line only progress stages.
            if (i <= stage) {
                if (i == stage) {
                    float difference = fillEnd - fillStart;
                    fillEnd = fillStart + (difference * ((float) progress / 100));
                }
                canvas.drawLine(fillStart, startY, fillEnd, startY, fillLinePaint);
            }
        }

        float stageX = 0 + radius;
        for (int i = 0; i < numberOfStages; i++) {
            if (i < stage || (i == stage && progress == 100)) {
                circlePaint.setColor(progressFillColor);
            } else {
                circlePaint.setColor(progressColor);
            }
            canvas.drawCircle(stageX, startY, radius, circlePaint);
            stageX = stageX + stagePoint;
        }
    }
}

