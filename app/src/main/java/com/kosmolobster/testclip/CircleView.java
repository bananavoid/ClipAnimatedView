package com.kosmolobster.testclip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CircleView extends RelativeLayout {
    private int ANIMATION_DURATION = 1000;

    private LinearLayout layoutTop;
    private LinearLayout layoutBottom;
    private Button bottomBtn;
    private Button topBtn;

    public CircleView(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
        setWillNotDraw(false);

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_circle_view, this);

        layoutTop = (LinearLayout)this.findViewById(R.id.halves_top);
        layoutBottom = (LinearLayout)this.findViewById(R.id.halves_bottom);

        topBtn = (Button) this.findViewById(R.id.button_fragment_top_halves);
        bottomBtn = (Button) this.findViewById(R.id.button_fragment_top_halves);
    }

    public void animateHalfCircleMenus(boolean closeCircleAnimation) {

        TranslateAnimation introtop, introbottom;

        float moveAmountFloat = 1f;

        if (closeCircleAnimation) { //close

            introtop = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -moveAmountFloat,
                    Animation.RELATIVE_TO_SELF, 0.0f);


            introbottom = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, moveAmountFloat,
                    Animation.RELATIVE_TO_SELF, 0.0f);

        } else { //open

        introtop = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -moveAmountFloat);

        introbottom = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, moveAmountFloat);
        }

        layoutTop.startAnimation(createAnimSet(introtop, 0.7f));
        layoutBottom.startAnimation(createAnimSet(introbottom, 0.5f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centreX = this.getX() + this.getWidth()  / 2;
        float centreY = this.getY() + this.getHeight() / 2;

        Path path = new Path();
        path.addCircle(centreX, centreY, this.getHeight() / 2, Path.Direction.CW);
        canvas.clipPath(path);

        invalidate();
    }

    private AnimationSet createAnimSet(TranslateAnimation translateAnimation, float alphaEnd) {
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, alphaEnd);
        int moveDuration = ANIMATION_DURATION;

        set.addAnimation(translateAnimation);
        set.addAnimation(alphaAnimation);
        set.setDuration(moveDuration);
        set.setFillEnabled(true);
        set.setFillAfter(true);

        return set;
    }
}
