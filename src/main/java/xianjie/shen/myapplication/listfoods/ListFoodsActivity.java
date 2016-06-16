package xianjie.shen.myapplication.listfoods;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xianjie.shen.myapplication.R;
import xianjie.shen.myapplication.fooddetail.FoodDetailActivity;

public class ListFoodsActivity extends Activity
{
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private ListFoodsAdapter mAdapter;
    private String[] mAddr;
    private List<ListFoodsItemBean> mDatas;
    private String[] mDialogItemTexts;
    private TypedArray mImgsId;
    private ListAdapter mListAdapter;
    private ListFoodsItemBean mListFoodsItemBean;
    private String[] mName;
    private String[] mNumber;
    private RecyclerView mRecyclerView;
    private TextView mTitle;
    private RelativeLayout mTopBar;
    private ImageView mTopBarLeft;
    private ImageView mTopBarRight;

    private void getListViewItemDatas(int img, int name, int address, int number)
    {
        this.mImgsId = getResources().obtainTypedArray(img);
        this.mName = getResources().getStringArray(name);
        this.mAddr = getResources().getStringArray(address);
        this.mNumber = getResources().getStringArray(number);
    }

    private void initDatas()
    {
        int fragmentItemId = getIntent().getExtras().getInt("fragmentItemId", 0);
        int fragmentId = getIntent().getExtras().getInt("fragmentId", 0);
        this.mTitle.setText(getIntent().getExtras().getString("itemText"));
        this.mDialogItemTexts = getResources().getStringArray(R.array.dialog_item_texts);
        switch (fragmentId)
        {
            case 0:
                getFragmentItemDatas(fragmentId, fragmentItemId);
                break;
            case 1:
                getFragmentItemDatas(fragmentId, fragmentItemId);
                break;
        }
        this.mDatas = new ArrayList();
        for (int i = 0; i < mName.length; i++)
        {
            this.mListFoodsItemBean = new ListFoodsItemBean();
            this.mListFoodsItemBean.setImgId(this.mImgsId.getResourceId(i, 0));
            this.mListFoodsItemBean.setName(this.mName[i]);
            this.mListFoodsItemBean.setAddress(getResources().getString(R.string.listview_item_tv_address) + this.mAddr[i]);
            this.mListFoodsItemBean.setNumber(getResources().getString(R.string.listview_item_tv_number) + this.mNumber[i]);
            this.mListFoodsItemBean.setImgCallId(R.drawable.ic_navigation_check);
            this.mDatas.add(this.mListFoodsItemBean);
        }
        this.mAdapter = new ListFoodsAdapter(this, mDatas, R.layout.list_foods_item);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void getFragmentItemDatas(int fragmentId, int fragmentItemId)
    {
        if (fragmentId == 0)
        {
            switch (fragmentItemId)
            {
                default:
                    break;
                case 0:
                    getListViewItemDatas(R.array.list_item_imgs_yzq, R.array.list_item_names_yzq, R.array.list_item_addresses_yzq, R.array.list_item_numbers_yzq);
                    break;
                case 1:
                    getListViewItemDatas(R.array.list_item_imgs_spb, R.array.list_item_names_spb, R.array.list_item_addresses_spb, R.array.list_item_numbers_spb);
                    break;
                case 2:
                    getListViewItemDatas(R.array.list_item_imgs_nananqu, R.array.list_item_names_nananqu, R.array.list_item_addresses_nananqu, R.array.list_item_numbers_nananqu);
                    break;
                case 3:
                    getListViewItemDatas(R.array.list_item_imgs_bananqu, R.array.list_item_names_banannqu, R.array.list_item_addresses_banannqu, R.array.list_item_numbers_banannqu);
                    break;
                case 4:
                    getListViewItemDatas(R.array.list_item_imgs_jiangbeiqu, R.array.list_item_names_jiangbeiqu, R.array.list_item_addresses_jiangbeiqu, R.array.list_item_numbers_jiangbeiqu);
                    break;
                case 5:
                    getListViewItemDatas(R.array.list_item_imgs_ybq, R.array.list_item_names_ybq, R.array.list_item_addresses_ybq, R.array.list_item_numbers_ybq);
                    break;
                case 6:
                    getListViewItemDatas(R.array.list_item_imgs_jiulongpoqu, R.array.list_item_names_jiulongpoqu, R.array.list_item_addresses_jiulongpoqu, R.array.list_item_numbers_jiulongpoqu);
                    break;
                case 7:
                    getListViewItemDatas(R.array.list_item_imgs_dadukouqu, R.array.list_item_names_dadukouqu, R.array.list_item_addresses_dadukouqu, R.array.list_item_numbers_dadukouqu);
                    break;
            }
        } else if (fragmentId == 1)
        {
            switch (fragmentItemId)
            {
                case 0:
                    getListViewItemDatas(R.array.list_item_imgs_yu, R.array.list_item_names_yu, R.array.list_item_addresses_yu, R.array.list_item_numbers_yu);
                    break;
                case 1:
                    getListViewItemDatas(R.array.list_item_imgs_ji, R.array.list_item_names_ji, R.array.list_item_addresses_ji, R.array.list_item_numbers_ji);
                    break;
                case 2:
                    getListViewItemDatas(R.array.list_item_imgs_huoguo, R.array.list_item_names_huoguo, R.array.list_item_addresses_huoguo, R.array.list_item_numbers_huoguo);
                    break;
            }
        }
    }

    private void initEvents()
    {
        this.mTopBarLeft.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                ListFoodsActivity.this.finish();
            }
        });
        this.mAdapter.setOnItemCLickListener(new ListFoodsAdapter.OnItemClickListener()
        {
            public void onItemClick(View view, int pos, ListFoodsAdapter.MyViewHolder holder)
            {
                openActivity(FoodDetailActivity.class, holder);
            }

            public void onItemLongClick(View view, int pos, ListFoodsAdapter.MyViewHolder holder)
            {
//                Toast.makeText(ListFoodsActivity.this, "ListViewActivity long click " + pos, Toast.LENGTH_SHORT).show();
                ListFoodsActivity.this.itemClickDialog2(holder).show();
            }
        });
    }

    private void initViews()
    {
        this.mRecyclerView = ((RecyclerView) findViewById(R.id.rv_fragment_main));
        this.mTopBar = ((RelativeLayout) findViewById(R.id.rl_topbar));
        this.mTitle = ((TextView) findViewById(R.id.tv_topbar_center_title));
        this.mTopBarLeft = ((ImageView) findViewById(R.id.iv_topbar_left));
        this.mTopBarRight = ((ImageView) findViewById(R.id.iv_topbar_right));
        this.mTopBarRight.setVisibility(View.GONE);
    }

    private AlertDialog itemClickDialog()
    {
        AlertDialog localAlertDialog = new Builder(this).setSingleChoiceItems(this.mDialogItemTexts, 0, new OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case 0://call
                        break;
                    case 1://navi
                        break;
                }
                while (true)
                {
                    dialog.dismiss();
                    Toast.makeText(ListFoodsActivity.this.getApplicationContext(), ListFoodsActivity.this.mDialogItemTexts[which], Toast.LENGTH_SHORT).show();
                    Toast.makeText(ListFoodsActivity.this.getApplicationContext(), ListFoodsActivity.this.mDialogItemTexts[which], Toast.LENGTH_SHORT).show();
                }
            }
        }).create();
        ListView localListView = localAlertDialog.getListView();
        int i = 0;
        while (i < localListView.getChildCount())
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            {
                ((CheckedTextView) localListView.getChildAt(i)).getCheckMarkDrawable().setVisible(true, false);
            }
        }
        return localAlertDialog;
    }

    private AlertDialog itemClickDialog2(final ListFoodsAdapter.MyViewHolder holder)
    {
        mListAdapter = new ArrayAdapter(getApplicationContext(), R.layout.single_selection_list_item, R.id.tv_dialog_item, this.mDialogItemTexts);
        AlertDialog dialog = new Builder(new ContextThemeWrapper(this, R.style.MyDialogTheme))
                .setSingleChoiceItems(mListAdapter, 0, new OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                            case 0:
                                mAdapter.clickCallDialog(holder).show();
                                dialog.dismiss();
                                break;
                            case 1:
                                //弹出选择导航地图的dialog
                                mAdapter.clickNaviDialog(holder).show();
                                dialog.dismiss();
                                break;
                        }
                    }
                }).create();
        return dialog;
    }

    private String searchAddress(int pos)
    {
        int i = this.mAddr[pos].indexOf("导航：");
        this.mAddr[pos].length();
        return this.mAddr[pos].substring(i + 3, this.mAddr[pos].length() - 1);
    }

    private String getSearchAddress(ListFoodsAdapter.MyViewHolder holder)
    {
        int pos = holder.getLayoutPosition();
        int i = mAddr[pos].indexOf("导航");
        return mAddr[pos].substring(i + 3, mAddr[pos].length() - 1);
    }

    private void openActivity(Class className)
    {
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }

    private void openActivity(Class className, ListFoodsAdapter.MyViewHolder holder)
    {
        int pos = holder.getLayoutPosition();
        Intent intent = new Intent(this, className);
        Bundle bundle = new Bundle();
        bundle.putInt("img", ((ListFoodsItemBean) mDatas.get(pos)).getImgId());
        bundle.putString("name", holder.tvName.getText().toString());
        bundle.putString("number", holder.tvNumber.getText().toString());
        bundle.putString("address", holder.tvAddress.getText().toString());
        intent.putExtras(bundle);
//        intent.putExtra("number", holder.tvNumber.getText().toString());
//        intent.putExtra("address", holder.tvAddress.getText().toString());
        startActivity(intent);
    }

    private void setActivityEnterAndExitAnimation()
    {
        TypedArray array = getTheme().obtainStyledAttributes(new int[]{R.style.MyActivityAnmiation});
        int i = array.getResourceId(0, 0);
        array.recycle();
        array = getTheme().obtainStyledAttributes(i, new int[]{16842938, 16842939});
        this.activityCloseEnterAnimation = array.getResourceId(0, 0);
        this.activityCloseExitAnimation = array.getResourceId(0, 1);
//        array.getResourceId(1, 0);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(this.activityCloseEnterAnimation, this.activityCloseExitAnimation);
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_foods_activity_main);
        initViews();
        initDatas();
        initEvents();
//        setActivityEnterAndExitAnimation();
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.mRecyclerView.setLayoutManager(lm);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        this.mRecyclerView.setItemAnimator(itemAnimator);
    }

}