package xianjie.shen.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ViewPagerIndicator extends LinearLayout
{
    private static final int COLOR_TEXT_NORMAL = 2013265919;
    private static final int COLOR_TEXT_SELECTED = -1;
    private static int DEFAULT_VISIABLE_TAB_COUNT = 0;
    public static final String PAINT_COLOR = "#ffffffff";
    public static final int RADIO_RECT_HEIGHT = 40;
    private static final float RADIO_RECT_WIDTH = 0.5F;
    private static final float RADIO_TRIANGLE_WIDTH = 0.125F;
    public static final int RECT_INDICATOR = 1;
    public static final int TRIANGLE_INDICATOR = 0;
    private final int TRIANGLE_WIDTH_MAX = (int) (getScreenWidth() / 3 * 0.125F);
    private int mCustomIndicatorId;
    private int mCustomIndicatorTranslationX;
    private int mInitCustomIndicatorTranslationX;
    private PageChangedListener mListener;
    private ViewPager mPager;
    private Paint mPaint;
    private Path mPath;
    private int mRectHeight;
    private int mRectWidth;
    private int mTabVisiableCount;
    private int mTabWidth;
    private List<String> mTitles;
    private int mTriangleHeight;
    private int mTriangleWidth;

    public ViewPagerIndicator(Context paramContext)
    {
        this(paramContext, null);
        Log.e("shen", "ViewPagerIndicator(context)");
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Log.e("shen", "ViewPagerIndicator(context, attrs)");
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.indicator);
        for (int i = 0; i < a.length(); i++)
        {
            this.mTabVisiableCount = a.getInteger(0, DEFAULT_VISIABLE_TAB_COUNT);
        }
        a.recycle();
        if (this.mTabVisiableCount == 0)
            this.mTabVisiableCount = DEFAULT_VISIABLE_TAB_COUNT;
        //the paint used to draw indicator
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getResources().getColor(R.color.indicator_highlight_color));
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setPathEffect(new CornerPathEffect(5.0F));
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int mCustomIndicatorId)
    {
        super(context, attrs);
        Log.e("shen", "ViewPagerIndicator(context, attrs)");
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.indicator);
        for (int i = 0; i < a.length(); i++)
        {
            this.mTabVisiableCount = a.getInteger(0, DEFAULT_VISIABLE_TAB_COUNT);

        }
        a.recycle();
        if (this.mTabVisiableCount == 0)
            this.mTabVisiableCount = DEFAULT_VISIABLE_TAB_COUNT;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getResources().getColor(R.color.indicator_highlight_color));
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setPathEffect(new CornerPathEffect(5.0F));
        this.mCustomIndicatorId = mCustomIndicatorId;
    }

    private View generateTextView(String text)
    {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = (getScreenWidth() / this.mTabVisiableCount);
        textView.setLayoutParams(lp);
        textView.setText(text);
        textView.setGravity(17);
        textView.setTextColor(getResources().getColor(R.color.text_color_gray));
        textView.setTextSize(2, 16.0F);
        return textView;
    }

    private int getScreenWidth()
    {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void initRect()
    {
        this.mRectHeight = (this.mRectWidth / 40);
        this.mPath = new Path();
        this.mPath.moveTo(0.0F, 0.0F);
        this.mPath.lineTo(this.mRectWidth, 0.0F);
        this.mPath.lineTo(this.mRectWidth, -this.mRectHeight);
        this.mPath.lineTo(0.0F, -this.mRectHeight);
        this.mPath.close();
    }

    private void initTriangle()
    {
        Log.e("shen", "initTriangle");
        this.mTriangleHeight = (this.mTriangleWidth / 2);
        this.mPath = new Path();
        this.mPath.moveTo(0.0F, 0.0F);
        this.mPath.lineTo(this.mTriangleWidth, 0.0F);
        this.mPath.lineTo(this.mTriangleWidth / 2, -this.mTriangleHeight);
        this.mPath.close();
    }

    private void resetAllTabView()
    {
        for (int j = 0; j < getChildCount(); j++)
        {
            ((TextView) getChildAt(j)).setTextColor(getResources().getColor(R.color.text_color_gray));
        }
    }

    private void setTabVisibleCount(int visibleCount)
    {
        this.mTabVisiableCount = visibleCount;
    }

    private void updateUI()
    {
        if (Looper.getMainLooper() == Looper.myLooper())
        {
            invalidate();
            return;
        }
        postInvalidate();
    }

    protected void dispatchDraw(Canvas canvas)
    {
        Log.e("shen", "dispatchDraw");
        Log.e("shen", "canvas.getHeight():" + canvas.getHeight() + " " + "canvas.getWidth():" + canvas.getWidth());
        canvas.save();
        canvas.translate(this.mInitCustomIndicatorTranslationX + this.mCustomIndicatorTranslationX, getHeight() + 2);
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    protected void onDraw(Canvas paramCanvas)
    {
        Log.e("shen", "onDraw");
        super.onDraw(paramCanvas);
    }

    protected void onFinishInflate()
    {
        Log.e("shen", "onFinishInflate");
        super.onFinishInflate();
        int j = getChildCount();
        Log.e("shen", "getChildCount：" + getChildCount());
        if (j == 0)
            return;
        int i = 0;
        while (i < j)
        {
            View localView = getChildAt(i);
            LayoutParams localLayoutParams = (LayoutParams) localView.getLayoutParams();
            localLayoutParams.weight = 0.0F;
            localLayoutParams.width = (getScreenWidth() / this.mTabVisiableCount);
            localView.setLayoutParams(localLayoutParams);
            i += 1;
        }
        setTabViewOnClickEvent();
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        Log.e("shen", "indicator onLayout");
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
        Log.e("shen", "Indicator onMeasure");
        super.onMeasure(paramInt1, paramInt2);
    }

    protected void onRestoreInstanceState(Parcelable paramParcelable)
    {
        Log.e("shen", "Indicator onRestoreInstanceState");
        super.onRestoreInstanceState(paramParcelable);
    }

    protected Parcelable onSaveInstanceState()
    {
        Log.e("shen", "Indicator onSaveInstanceState");
        return super.onSaveInstanceState();
    }

    /**
     * int w, int h, int oldw, int oldh
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        Log.e("shen", "onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        switch (this.mCustomIndicatorId)
        {
            default:
                return;
            case 0:
                this.mTriangleWidth = ((int) (w / this.mTabVisiableCount * 0.125F));
                this.mTriangleWidth = Math.min(this.mTriangleWidth, this.TRIANGLE_WIDTH_MAX);
                this.mInitCustomIndicatorTranslationX = (w / this.mTabVisiableCount / 2 - this.mTriangleWidth / 2);
                initTriangle();
                return;
            case 1:
        }
        this.mRectWidth = ((int) (w / this.mTabVisiableCount * 0.5F));
        this.mInitCustomIndicatorTranslationX = (w / this.mTabVisiableCount / 2 - this.mRectWidth / 2);
        initRect();
    }

    public void scroll(int paramInt, float paramFloat)
    {
        Log.e("shen", "Indicator scroll");
        this.mTabWidth = (getWidth() / this.mTabVisiableCount);
        Log.e("shen", "Indicator mTabWidth:" + this.mTabWidth);
        this.mCustomIndicatorTranslationX = ((int) (this.mTabWidth * paramFloat + this.mTabWidth * paramInt));
        if ((paramInt - (this.mTabVisiableCount - 2) >= 0) && (paramFloat > 0.0F) && (this.mTabVisiableCount < getChildCount()))
        {
            if (this.mTabVisiableCount == 1)
//        break label148;
                scrollTo((paramInt - (this.mTabVisiableCount - 2)) * this.mTabWidth + (int) (this.mTabWidth * paramFloat), 0);
        }
        while (true)
        {
            updateUI();
            Log.e("shen", "Indicator updateUI");
            return;
//      label148: scrollTo(this.mTabWidth * paramInt + (int)(this.mTabWidth * paramFloat), 0);
        }
    }

    public void setPageChangedListener(PageChangedListener paramPageChangedListener)
    {
        this.mListener = paramPageChangedListener;
    }

    public void setTabItem(List<String> itemTexts, int visibleCount, int customIndicatorId)
    {
        Log.e("shen", " setTabItem");
        this.mCustomIndicatorId = customIndicatorId;
        if ((itemTexts != null) && (itemTexts.size() > 0))
        {
            if (visibleCount <= 0)
                setTabVisibleCount(DEFAULT_VISIABLE_TAB_COUNT);
            else
            {
                removeAllViews();
                mTitles = itemTexts;
                for (int i = 0; i < mTitles.size(); i++)
                {
                    addView(generateTextView(mTitles.get(i)));
                }
                setTabVisibleCount(visibleCount);
            }
            setTabViewOnClickEvent();
        }
    }

    public void setTabViewOnClickEvent()
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            final int pos = i;
            getChildAt(i).setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    setTabViewSelected(pos);
                    mPager.setCurrentItem(pos);
                }
            });
        }
    }

    public void setTabViewSelected(int pos)
    {
        resetAllTabView();
        ((TextView) getChildAt(pos)).setTextColor(getResources().getColor(R.color.indicator_highlight_color));
    }

    public void setViewPager(ViewPager paramViewPager, int paramInt)
    {
        this.mPager = paramViewPager;
        this.mPager.setOnPageChangeListener(new OnPageChangeListener()
        {
            public void onPageScrollStateChanged(int state)
            {
                if (ViewPagerIndicator.this.mListener != null)
                    ViewPagerIndicator.this.mListener.onPageScrollStateChanged(state);
            }

            public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
            {
                Log.e("shen", " onPageScrolled");
                Log.e("shen", " position：" + paramAnonymousInt1 + " " + "positionOffset:" + paramAnonymousFloat);
                ViewPagerIndicator.this.scroll(paramAnonymousInt1, paramAnonymousFloat);
                if (ViewPagerIndicator.this.mListener != null)
                    ViewPagerIndicator.this.mListener.onPageScrolled(paramAnonymousInt1, paramAnonymousFloat, paramAnonymousInt2);
            }

            public void onPageSelected(int paramAnonymousInt)
            {
                Log.e("shen", " onPageSelected");
                Log.e("shen", " position：" + paramAnonymousInt);
                ViewPagerIndicator.this.setTabViewSelected(paramAnonymousInt);
                if (ViewPagerIndicator.this.mListener != null)
                    ViewPagerIndicator.this.mListener.onPageSelected(paramAnonymousInt);
            }
        });
        this.mPager.setCurrentItem(paramInt);
    }

    public interface PageChangedListener
    {
        void onPageScrollStateChanged(int paramInt);

        void onPageScrolled(int pos, float offSet, int offSetPixels);

        void onPageSelected(int pos);
    }
}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.ViewPagerIndicator
 * JD-Core Version:    0.6.2
 */