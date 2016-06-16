package xianjie.shen.myapplication.fooddetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.util.Arrays;
import java.util.List;

import xianjie.shen.myapplication.R;

/**
 * Created by Administrator on 2016/5/22.
 */
public class FoodDetailActivity extends Activity
{
    private ImageView mFoodPic;
    private TextView mFoodName;
    private RecyclerView mRecyclerView;
    private FoodDetailAdapter mAdapter;
    private ListAdapter mListAdapter;
    private List<?> mDatas;
    private String mNumber;
    private String mAddress;
    private int mImg;
    private String mName;
    private ImageView mTopbarLeft;
    private TextView mTopbarCenter;
    private ImageView mTopbarRight;
    private String[] mDialogItemTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.food_detail_main);

        mImg = getIntent().getExtras().getInt("img");
        mName = getIntent().getExtras().getString("name");
        mNumber = getIntent().getExtras().getString("number");
        mAddress = getIntent().getExtras().getString("address");

        initViews();
        initDatas();
        initEvents();
    }

    private void initEvents()
    {
        mTopbarLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FoodDetailActivity.this.finish();
            }
        });
        mAdapter.setOnItemClickListener(new FoodDetailAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int pos, FoodDetailAdapter.MyViewHolder holder)
            {
                Toast.makeText(FoodDetailActivity.this, "onItemClick pos: " + pos, Toast.LENGTH_SHORT).show();
                FoodDetailActivity.this.itemClickDialog(holder);
            }

            @Override
            public void onItemLongClick(View view, int pos, FoodDetailAdapter.MyViewHolder holder)
            {
                Toast.makeText(FoodDetailActivity.this, "onItemLongClick pos: " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void itemClickDialog(final FoodDetailAdapter.MyViewHolder holder)
    {
        int pos = holder.getLayoutPosition();
        switch (pos)
        {
            case 0:
                mAdapter.clickCallDialog(holder).show();
                break;
            case 1:
                mAdapter.clickNaviDialog(holder).show();
                break;
        }
    }


    private void initDatas()
    {
        mDialogItemTexts = getResources().getStringArray(R.array.dialog_item_texts);
//        mDatas=new ArrayList();
        mDatas = Arrays.asList(mNumber, mAddress);
//        mDatas.add((Object) mNumber);
//        mDatas.add((Object) mAddress);
        mAdapter = new FoodDetailAdapter(this, mDatas, R.layout.food_detail_item);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initViews()
    {
        mFoodPic = (ImageView) findViewById(R.id.iv_food_detail);
        mFoodName = (TextView) findViewById(R.id.tv_food_detail);
        mTopbarLeft = (ImageView) findViewById(R.id.iv_topbar_left);
        mTopbarRight = (ImageView) findViewById(R.id.iv_topbar_right);
        mTopbarCenter = (TextView) findViewById(R.id.tv_topbar_center_title);
        mFoodPic.setImageResource(mImg);
        mFoodName.setText(mName);
        mTopbarCenter.setText(getResources().getString(R.string.topbar_title_text));
//        mTopbarRight.setVisibility(View.GONE);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_food_detail);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lm);
//        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
//        mRecyclerView.setItemAnimator(animator);

        mTopbarRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                share();
            }
        });
    }

    private void share()
    {
//        UMImage image = new UMImage(this, "http://image.so.com/v?q=图片大全&src=360pic_strong&fromurl=http%3A%2F%2Fwww.wz6.org%2Fmeinv%2F1879.html#q=%E5%9B%BE%E7%89%87%E5%A4%A7%E5%85%A8&src=360pic_strong&fromurl=http%3A%2F%2Fwww.wz6.org%2Fmeinv%2F1879.html&lightboxindex=1&id=127eb9bfde8a62c370ab54e0402f517f&multiple=1&itemindex=0&dataindex=1");
//        UMImage image = new UMImage(this, "http://www.umeng.com/images/pic/social/integrated_3.png");
        UMImage image = new UMImage(this, BitmapFactory.decodeResource(getResources(), mImg));
        ShareAction action = new ShareAction(this);
        action.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
//                .withText(getResources().getString(R.string.app_name))
                .withMedia(image)
                .setCallback(umShareListener)
                .withTitle(mName)
                .open();
    }

    private UMShareListener umShareListener = new UMShareListener()
    {
        @Override
        public void onResult(SHARE_MEDIA platform)
        {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE"))
            {
                Toast.makeText(FoodDetailActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(FoodDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t)
        {
            Toast.makeText(FoodDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform)
        {
            Toast.makeText(FoodDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    public void clickItem(FoodDetailAdapter.MyViewHolder holder)
    {
        String itemText = holder.itemText.getText().toString();
//        openDialog(itemText);
        openNaviDialog(itemText);
    }

    private void openDialog(final String content)
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.open_dialog_title))
                .setMessage(content)
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        openNaviDialog(content);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.dismiss();
                    }
                }).create();
    }

    private void openNaviDialog(final String address)
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setSingleChoiceItems(getResources().getStringArray(R.array.dialog_item_maps), 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        openMap(i, address);
                    }
                }).create();
    }

    private void openMap(int pos, String address)
    {
        if (pos == 0)
        {
            openGaodeMap(address);
        } else
        {
            openBaiMap(address);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result", "onActivityResult");
    }

    private void openBaiMap(String address)
    {

    }

    private void openGaodeMap(String address)
    {
    }

}
