package com.sakuramomoko.searchinganimview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SearchingAnimView extends FrameLayout {


    final private int mDefaultSearchingAnimViewColor = 0xffff9e21;
    final private int mDefaultSearchingAnimViewAlphaColor = 0x33000000;
    final private int mDefaultLineCircleWith = 9;

    private int mSearchingAnimViewColor;
    private int mSearchingAnimViewColorWithAlpha;
    private Context mContext;
    private ImageView mIvLineCircle;
    private ImageView mIvRotateCircle;
    private ImageView mIvSmallCircle;
    private ImageView mIvMiddleCircle;
    private ImageView mIvLargeCircle;
    private AnimatorSet animatorSet;
    private DefaultAnimatorListener defaultAnimatorListener;
    private double lineCircleScale = 256.0 / 412;
    private double rotateCircleScale = 344.0 / 412;
    private double smallCircleScale = 344.0 / 412;
    private double middleCircleScale = 374.0 / 412;
    private int defaultViewWidthOrHeight =412;
    private boolean isStopSearchingAnim;


    public SearchingAnimView(Context context) {
        super(context);
        init(context, null);
    }

    public SearchingAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public SearchingAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * set the view's size
     * @param attrs
     */
    public void setViewSize(AttributeSet attrs) {
        if (attrs == null) {
            setViewSize(0, 0);
        } else {
            setLayoutParamsForViews(mIvLargeCircle, 1, attrs);
            setLayoutParamsForViews(mIvLineCircle, lineCircleScale, attrs);
            setLayoutParamsForViews(mIvRotateCircle, rotateCircleScale, attrs);
            setLayoutParamsForViews(mIvSmallCircle, smallCircleScale, attrs);
            setLayoutParamsForViews(mIvMiddleCircle, middleCircleScale, attrs);
        }
    }


    /**
     * set the view's size
     * @param parentWith
     * @param parentHeight
     */
    public void setViewSize(int parentWith, int parentHeight) {
        setLayoutParamsForViews(mIvLargeCircle, 1, parentWith, parentHeight);
        setLayoutParamsForViews(mIvLineCircle, lineCircleScale, parentWith, parentHeight);
        setLayoutParamsForViews(mIvRotateCircle, rotateCircleScale, parentWith, parentHeight);
        setLayoutParamsForViews(mIvSmallCircle, smallCircleScale, parentWith, parentHeight);
        setLayoutParamsForViews(mIvMiddleCircle, middleCircleScale, parentWith, parentHeight);
    }

    /**
     * stop the view's animations
     */
    public void stopAnimations() {
        if (animatorSet != null) {
            isStopSearchingAnim = true;
            animatorSet.end();
            animatorSet.cancel();
        }
    }

    /**
     * start the view's animations
     */
    public void startAnimations() {
        isStopSearchingAnim = false;
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(lineCircleAnim(), rotateCircleAnim(), smallCircleAnim(), largeCircleAnim());
        }
        animatorSet.start();
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.layout_search_animation_view, this);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchingAnimView,
                0, 0);
        mSearchingAnimViewColor = attributes.getColor(R.styleable.SearchingAnimView_search_color, mDefaultSearchingAnimViewColor);
        mSearchingAnimViewColorWithAlpha = mSearchingAnimViewColor & 0x00FFFFFF + mDefaultSearchingAnimViewAlphaColor;
        mContext = context;
        defaultAnimatorListener = new DefaultAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isStopSearchingAnim) {
                    animation.start();
                } else {
                    super.onAnimationEnd(animation);
                }
            }
        };
        isStopSearchingAnim = false;
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        mIvLineCircle = (ImageView) findViewById(R.id.search_circle_line);
        mIvRotateCircle = (ImageView) findViewById(R.id.search_circle_rotate);
        mIvSmallCircle = (ImageView) findViewById(R.id.search_circle_small);
        mIvMiddleCircle = (ImageView) findViewById(R.id.search_circle_middle);
        mIvLargeCircle = (ImageView) findViewById(R.id.search_circle_large);
        setViewStrokeColor(mIvLineCircle);
        setViewDrawableColor(mIvRotateCircle);
        setViewColor(mIvSmallCircle);
        setViewColor(mIvMiddleCircle);
        setViewColor(mIvLargeCircle);
        setViewSize(attrs);
    }

    private void setViewColor(View view) {
        GradientDrawable myGrad = (GradientDrawable) view.getBackground();
        myGrad.setColor(mSearchingAnimViewColorWithAlpha);
    }

    private void setViewDrawableColor(View view) {
        Drawable mDrawable = view.getBackground();
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(mSearchingAnimViewColor, PorterDuff.Mode.MULTIPLY));
    }

    private void setViewStrokeColor(View view) {
        GradientDrawable myGrad = (GradientDrawable) view.getBackground();
        myGrad.setStroke(mDefaultLineCircleWith, mSearchingAnimViewColor);
    }

    private void setLayoutParamsForViews(View view, double scale, AttributeSet attrs) {
        LayoutParams layoutParams = new LayoutParams(mContext, attrs);
        setDefaultLayoutParams(layoutParams);
        layoutParams.width = (int) (layoutParams.width * scale);
        layoutParams.height = (int) (layoutParams.height * scale);
        layoutParams.gravity = Gravity.CENTER;
        view.setLayoutParams(layoutParams);
    }

    private void setDefaultLayoutParams(LayoutParams layoutParams){
        int width=layoutParams.width;
        int height=layoutParams.height;
        if(width==LayoutParams.MATCH_PARENT||width==LayoutParams.WRAP_CONTENT||width==LayoutParams.FILL_PARENT){
            layoutParams.width = defaultViewWidthOrHeight;
        }
        if(height==LayoutParams.MATCH_PARENT||height==LayoutParams.WRAP_CONTENT||height==LayoutParams.FILL_PARENT){
            layoutParams.height = defaultViewWidthOrHeight;
        }
    }

    private void setLayoutParamsForViews(View view, double scale, double parentWith, double parentHeight) {
        LayoutParams layoutParams = new LayoutParams((int) (parentWith * scale), (int) (parentHeight * scale));
        layoutParams.gravity = Gravity.CENTER;
        view.setLayoutParams(layoutParams);
    }

    private void repeat(ObjectAnimator objectAnimator, int repeatMode) {
        objectAnimator.setRepeatMode(repeatMode);
        objectAnimator.setRepeatCount(Integer.MAX_VALUE);
    }

    private AnimatorSet lineCircleAnim() {
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(scaleSet(mIvLineCircle, 1.0f, 1.08f, 2000), scaleSet(mIvLineCircle, 1.08f, 1.0f, 2000));
        set.addListener(defaultAnimatorListener);
        return set;
    }

    private ObjectAnimator rotateCircleAnim() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIvRotateCircle, "rotation", 0F, 360F).setDuration(2000);
        oa.setInterpolator(new LinearInterpolator());
        repeat(oa, ObjectAnimator.INFINITE);
        return oa;
    }

    private AnimatorSet smallCircleAnim() {
        View view = mIvSmallCircle;
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(scaleSet(view, 1.0f, 1.0f, 800),
                scaleSet(view, 1.0f, 0.85f, 800),
                scaleSet(view, 0.85f, 0.85f, 400),
                scaleSet(view, 1f, 1f, 1200));
        set.addListener(defaultAnimatorListener);
        return set;
    }

    private AnimatorSet largeCircleAnim() {
        View view = mIvLargeCircle;
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(scaleSet(view, 0.65f, 1.0f, 300),
                scaleSet(view, 1.0f, 0.85f, 200),
                scaleSet(view, 0.85f, 0.95f, 500),
                scaleSet(view, 0.95f, 0.65f, 1000),
                scaleSet(view, 0.65f, 0.65f, 800));
        set.addListener(defaultAnimatorListener);
        return set;
    }

    private AnimatorSet scaleSet(View view, float start, float end, int ms) {
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", start, end).setDuration(ms);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleY", start, end).setDuration(ms);
        AnimatorSet scaleSet = new AnimatorSet();
        scaleSet.playTogether(oa1, oa2);
        return scaleSet;
    }
}

