package xianjie.shen.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WelcomeActivity extends Activity
{
    private static final long DELAY_START_TIME = 1500L;
    private static final long DELAY_SHORT_TIME = 500L;
    private static final long DELAY_LONG_TIME = 3000L;
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private static boolean mIsFirstIn = true;
    private Handler mHandler = new Handler();
    private Runnable runnable;
    private Button mBtnBypass;

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);
        initViews();
        initEvents();

    }

    private void initEvents()
    {
        loadingDelayed(DELAY_LONG_TIME);
        mBtnBypass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loadingNow();
            }
        });
    }

    private void loadingNow()
    {
        openActivity(MainActivity.class);
        mHandler.removeCallbacks(runnable);
        finish();
    }

    private void initViews()
    {
        mBtnBypass = (Button) findViewById(R.id.btn_bypass);
    }

    private void loadingDelayed(long delayTime)
    {
        mHandler.postDelayed(runnable = new Runnable()
        {
            @Override
            public void run()
            {
                openActivity(MainActivity.class);
                finish();
            }
        }, delayTime);
    }

    private void openActivity(Class className)
    {
        startActivity(new Intent(this, className));
        overridePendingTransition(this.activityCloseEnterAnimation, this.activityCloseExitAnimation);
    }

    private void setActivityEnterAndExitAnimation()
    {
//        TypedArray localTypedArray = getTheme().obtainStyledAttributes(new int[]{16842926});
//        int i = localTypedArray.getResourceId(0, 0);
//        localTypedArray.recycle();
//        localTypedArray = getTheme().obtainStyledAttributes(i, new int[]{16842938, 16842939});
//        this.activityCloseEnterAnimation = localTypedArray.getResourceId(0, 0);
//        this.activityCloseExitAnimation = localTypedArray.getResourceId(1, 0);
//        localTypedArray.recycle();
    }

    public void finish()
    {
        super.finish();
//        overridePendingTransition(this.activityCloseEnterAnimation, this.activityCloseExitAnimation);
    }

    /**
     * 如果点击welcome界面，就直接进入主界面
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
//        if (event.getAction() == MotionEvent.ACTION_UP)
//            openActivity(MainActivity.class);
//
//        mHandler.removeCallbacks(runnable);

        return super.onTouchEvent(event);
    }
}
