package xianjie.shen.myapplication;

//import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity
{
    private static final int COLOR_TEXT_NORMAL = 2013265919;
    private static final int COLOR_TEXT_SELECTED = -1;
    private static final int DEFAULT_ANCHORED_GRAVITY = 8388611;
    public static final int FIRST_FRAGMENT = 0;
    public static final int SECOND_FRAGMENT = 1;
    public static final int GRAVITY = 3;
    private static final int GRID_LAYOUT = 1;
    public static final String INDICATOR_BACKGROUND_COLOR = "#ffdadada";
    private static final int LISTVIEW_LAYOUT = 2;
    public static final int RECT_INDICATOR = 1;
    private static final int STAGGERED_GRID_LAYOUT = 0;
    private static final int TAB_VISIALE_COUNT = 2;
    public static final int THIRD_FRAGMENT = 2;
    public static final String TOPBAR_TITLE = "#ff000000";
    public static final int TRIANGLE_INDICATOR = 0;
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private FragmentPagerAdapter mAdapter;
    private List<ViewPagerFragment> mContents = new ArrayList();
    private List<List> mDatas3;
    private List<DistrictFragmentItemBean> mDistrictDatas;
    private TypedArray mDistrictImgs;
    private String[] mDistrictNames;
    private long mExitTime = 0L;
    private String[] mFoodTypeNames;
    private List<DistrictFragmentItemBean> mFoodTypeDatas;
    private TypedArray mFoodTypeImgs;
    private ViewPagerFragment mFragment;
    private ViewPagerIndicator mIndicator;
    private List<SlideMenuItemBean> mSlideMenuItemBeans;
    private String[] mSlideMenuItemDescs;
    private int[] mSlideMenuItemImgs;
    private int[] mSlideMenuItemTags;
    private String[] mSlideMenuItemTitles;
    private SlideMenuPopWindow mSlideMenuPopWindow;
    private List<String> mTitles;
    private String[] mTopAddresses;
    private ImageView mTopBarLeft;
    private ImageView mTopBarRight;
    private List<TopItemBean> mTopDatas;
    private TypedArray mTopImgs;
    private String[] mTopNames;
    private String[] mTopZanCount;
    private RelativeLayout mTopbar;
    private TextView mTopbarTitle;
    private ViewPager mViewPager;

    private boolean continueClickExitApp(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getAction() == KeyEvent.ACTION_DOWN))
        {
            if (System.currentTimeMillis() - this.mExitTime > 2000L)
            {//大于2s弹出toast
                Toast.makeText(getApplicationContext(), R.string.exit_tips_one_more_time, Toast.LENGTH_SHORT).show();
                this.mExitTime = System.currentTimeMillis();
            }
            return true;
        } else//2s内连按2次就退出app
        {
            finish();
            System.exit(0);
            return false;
        }
    }

    private void continueClickExitApp()
    {
        if (System.currentTimeMillis() - this.mExitTime > 2000L)
        {//大于2s弹出toast
            Toast.makeText(getApplicationContext(), R.string.exit_tips_one_more_time, Toast.LENGTH_SHORT).show();
            this.mExitTime = System.currentTimeMillis();// 记录下这个点击的时间点
        } else//2s内连按2次就退出app
        {
            finish();
            System.exit(0);
        }
    }

    private void initDatas()
    {
        this.mDistrictDatas = new ArrayList();
        this.mFoodTypeDatas = new ArrayList();
        this.mTopDatas = new ArrayList();
        this.mDatas3 = new ArrayList();
        this.mSlideMenuItemBeans = new ArrayList();
        this.mDistrictImgs = getResources().obtainTypedArray(R.array.district_item_imgs);
        this.mDistrictNames = getResources().getStringArray(R.array.district_names);
        this.mFoodTypeNames = getResources().getStringArray(R.array.foodtype_names);
        this.mFoodTypeImgs = getResources().obtainTypedArray(R.array.foodtype_imgs);
        this.mTopImgs = getResources().obtainTypedArray(R.array.top_imgs);
        this.mTopNames = getResources().getStringArray(R.array.top_names);
        this.mTopAddresses = getResources().getStringArray(R.array.top_addresses);
        this.mTopZanCount = getResources().getStringArray(R.array.top_zan_count);

        for (int i = 0; i < this.mDistrictNames.length; i++)
        {
            DistrictFragmentItemBean bean = new DistrictFragmentItemBean(this.mDistrictImgs.getResourceId(i, 0), this.mDistrictNames[i]);
            this.mDistrictDatas.add(bean);
        }
        for (int i = 0; i < this.mFoodTypeNames.length; i++)
        {
            DistrictFragmentItemBean bean = new DistrictFragmentItemBean(this.mFoodTypeImgs.getResourceId(i, 0), this.mFoodTypeNames[i]);
            this.mFoodTypeDatas.add(bean);
        }
        for (int i = 0; i < this.mTopNames.length; i++)
        {
            TopItemBean bean = new TopItemBean(this.mTopImgs.getResourceId(i, 0), R.drawable.map_zan, this.mTopNames[i], this.mDistrictNames[i], this.mTopZanCount[i]);
            this.mTopDatas.add(bean);
        }
        this.mDatas3.add(0, this.mDistrictDatas);
        this.mDatas3.add(1, this.mFoodTypeDatas);
        this.mDatas3.add(2, this.mTopDatas);
        ViewPagerFragment fragment0 = ViewPagerFragment.newInstance(this.mDistrictDatas, R.layout.district_main, FIRST_FRAGMENT);
        ViewPagerFragment fragment1 = ViewPagerFragment.newInstance(this.mFoodTypeDatas, R.layout.district_main, SECOND_FRAGMENT);
        this.mContents.add(fragment0);
        this.mContents.add(fragment1);
        this.mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            public int getCount()
            {
                return mContents.size();
            }

            public Fragment getItem(int position)
            {
                return (Fragment) mContents.get(position);
            }
        };
        this.mViewPager.setAdapter(this.mAdapter);
        //SlideMenu数据源设置
        this.mSlideMenuItemTags = getResources().getIntArray(R.array.slide_menu_item_tags);
        this.mSlideMenuItemImgs = getResources().getIntArray(R.array.slide_menu_item_imgs);
        this.mSlideMenuItemTitles = getResources().getStringArray(R.array.slide_menu_item_titles);
        this.mSlideMenuItemDescs = getResources().getStringArray(R.array.slide_menu_item_descs);

        for (int i = 0; i < mSlideMenuItemTags.length; i++)
        {
            SlideMenuItemBean bean = new SlideMenuItemBean(this.mSlideMenuItemTags[i], this.mSlideMenuItemImgs[i], this.mSlideMenuItemTitles[i], this.mSlideMenuItemDescs[i]);
            this.mSlideMenuItemBeans.add(bean);
        }
    }

    private void initEvents()
    {
        this.mIndicator.setViewPager(this.mViewPager, 0);
        this.mTopBarLeft.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, GetSignActivity.class);
                startActivity(intent);
//                if ((MainActivity.this.mSlideMenuPopWindow != null) && (MainActivity.this.mSlideMenuPopWindow.isShowing()))
//                {
//                    MainActivity.this.mSlideMenuPopWindow.dismiss();
//                    MainActivity.this.changeLightBrightness(0.3F);
//                    return;
//                }
            }
        });
    }


    private void initSlideMenuPopWindow()
    {
        this.mSlideMenuPopWindow = new SlideMenuPopWindow(this, this.mSlideMenuItemBeans);
        this.mSlideMenuPopWindow.setAnimationStyle(R.style.PopupWindowAnim);
        this.mSlideMenuPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            public void onDismiss()
            {
                MainActivity.this.changeLightBrightness(1.0F);
            }
        });
        this.mSlideMenuPopWindow.setOnItemClickListener(new SlideMenuPopWindow.OnItemClickListener()
        {
            public void onItemClick(View view, int pos, PopupWindowListViewAdapter.MyViewHolder holder)
            {
                Toast.makeText(MainActivity.this, "onItemClick: " + pos, Toast.LENGTH_SHORT).show();
                MainActivity.this.mSlideMenuPopWindow.dismiss();
            }

            public void onItemLongClick(View view, int pos, PopupWindowListViewAdapter.MyViewHolder holder)
            {
                Toast.makeText(MainActivity.this, "onItemLongClick: " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews()
    {
        this.mTitles = Arrays.asList(getResources().getStringArray(R.array.tab_titles));
        this.mViewPager = ((ViewPager) findViewById(R.id.vp_content));
        this.mIndicator = ((ViewPagerIndicator) findViewById(R.id.vp_indicator));
        this.mTopbar = ((RelativeLayout) findViewById(R.id.rl_topbar));
        this.mIndicator.setTabItem(this.mTitles, 2, 0);
        this.mTopbarTitle = ((TextView) findViewById(R.id.tv_topbar_center_title));
        this.mTopbarTitle.setText(R.string.app_name);
        this.mTopbarTitle.setTextColor(getResources().getColor(R.color.text_color_black));
        this.mTopBarLeft = ((ImageView) findViewById(R.id.iv_topbar_left));
        this.mTopBarLeft.setImageResource(R.drawable.ic_menu_add);
        this.mTopBarRight = ((ImageView) findViewById(R.id.iv_topbar_right));
        this.mTopBarRight.setImageResource(R.drawable.ic_menu_delete);
        mTopBarLeft.setVisibility(View.GONE);
        mTopBarRight.setVisibility(View.GONE);

    }

    private void setTabViewAndViewPagerSelected(int paramInt)
    {
        this.mIndicator.setTabViewSelected(paramInt);
        this.mViewPager.setCurrentItem(paramInt, false);
    }

    private void showExitAppTip()
    {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.exit_dialog_title).setMessage(R.string.exit_dialog_message).setPositiveButton(R.string.exit_dialog_positive_button, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                MainActivity.this.finish();
                System.exit(0);
            }
        }).setNegativeButton(R.string.exit_dialog_negative_button, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.dismiss();
            }
        }).show();
    }

    public void changeLightBrightness(float alpha)
    {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.alpha = alpha;
        getWindow().setAttributes(localLayoutParams);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(this.activityCloseEnterAnimation, this.activityCloseExitAnimation);
    }

    public void onBackPressed()
    {

    }

    protected void onCreate(Bundle paramBundle)
    {
        Log.e("shen", "Activity onCreate");
        super.onCreate(paramBundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_without_textview);
        Config.dialogSwitch = true;
//        Log.e("shen", "setContentView");
        initViews();
        initDatas();
        initEvents();
        setTabViewAndViewPagerSelected(0);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (event.getAction() == KeyEvent.ACTION_DOWN))
        {
            continueClickExitApp();
        }
        return super.onKeyDown(keyCode, event);
    }
}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     xianjie.MainActivity
 * JD-Core Version:    0.6.2
 */