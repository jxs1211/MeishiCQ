package xianjie.shen.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import xianjie.shen.myapplication.listfoods.ListFoodsActivity;

@SuppressLint("ValidFragment")
public class ViewPagerFragment extends Fragment
{
    public static final int SPAN_COUNT = 2;
    private Adapter mAdapter;
    private List<?> mDatas;
    private List<String> mDatas1;
    private List<String> mDatas2;
    private int mFragmentId;
    private int mLayoutId;
    private int mLayoutManagerId;
    private RecyclerView mRecyclerView;
    private String mTitle;

    public ViewPagerFragment()
    {
    }

    public ViewPagerFragment(String paramString)
    {
        this.mTitle = paramString;
    }

    public ViewPagerFragment(List<?> datas)
    {
        this.mDatas = datas;
    }

    public ViewPagerFragment(List<?> datas, int layoutId)
    {
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    public ViewPagerFragment(List<?> datas, int layoutId, int fragmentId)
    {
        this.mDatas = datas;
        this.mLayoutId = layoutId;
        this.mFragmentId = fragmentId;
    }

    private void clickFragmentItem(int pos, String itemText)
    {
        openActivityForResult(ListFoodsActivity.class, pos, getFragmentId(), itemText);
    }

    private void initDatas()
    {
        if (getFragmentId() == 0)
        {
            this.mAdapter = new DistrictGridAdapter(getActivity(), this.mDatas, R.layout.district_item, false);
        } else if (getFragmentId() == 1)
        {
            this.mAdapter = new FoodTypeListViewAdapter(getActivity(), this.mDatas, R.layout.foodtype_main_item2);
        } else if (getFragmentId() == 2)
        {
            this.mAdapter = new TopListViewAdapter(getActivity(), this.mDatas, R.layout.top_main_item);
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void initEvents()
    {
        setupAdapterItemEvent();
    }

    private void initViews(View view)
    {
        this.mRecyclerView = ((RecyclerView) view.findViewById(R.id.rv_fragment_main));
        RecyclerView.LayoutManager lm = null;
        switch (getFragmentId())
        {
            case 0:
                lm = new GridLayoutManager(getActivity(), SPAN_COUNT);
            case 1:
                lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            case 2:
                lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }
        mRecyclerView.setLayoutManager(lm);
    }

    public static ViewPagerFragment newInstance(List<?> datas)
    {
        return new ViewPagerFragment(datas);
    }

    public static ViewPagerFragment newInstance(List<?> datas, int layoutId)
    {
        return new ViewPagerFragment(datas, layoutId);
    }

    public static ViewPagerFragment newInstance(List<?> datas, int layoutId, int fragmentId)
    {
        return new ViewPagerFragment(datas, layoutId, fragmentId);
    }

    private void openActivityForResult(Class className, int itemId, int fragmentId, String itemText)
    {
        Intent intent = new Intent(getActivity(), className);
        Bundle localBundle = new Bundle();
        localBundle.putInt("fragmentItemId", itemId);
        localBundle.putString("itemText", itemText);
        intent.putExtras(localBundle);
        localBundle.putInt("fragmentId", fragmentId);
        startActivityForResult(intent, 1211);
    }

    private void setupAdapterItemEvent()
    {
        if (getFragmentId() == 0)
        {
            ((DistrictGridAdapter) this.mAdapter).setOnItemCLickListener(new DistrictGridAdapter.OnItemClickListener()
            {
                public void onItemClickListener(View view, int pos, DistrictGridAdapter.MyViewHolder holder)
                {
                    clickFragmentItem(pos, (String) holder.tv.getText());
                }

                public void onItemLongClickListener(View view, int pos, DistrictGridAdapter.MyViewHolder holder)
                {
                    Toast.makeText(ViewPagerFragment.this.getActivity(), "DistrictGridAdapter onItemLongClick " + pos, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (getFragmentId() == 1)
        {
            ((FoodTypeListViewAdapter) this.mAdapter).setOnItemCLickListener(new FoodTypeListViewAdapter.OnItemClickListener()
            {
                public void onItemClickListener(View view, int pos, FoodTypeListViewAdapter.MyViewHolder holder)
                {
                    clickFragmentItem(pos, (String) holder.tvName.getText());
                }

                public void onItemLongClickListener(View view, int pos, FoodTypeListViewAdapter.MyViewHolder holder)
                {
                    Toast.makeText(ViewPagerFragment.this.getActivity(), "DistrictGridAdapter onItemLongClick " + pos, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (getFragmentId() == 2)
        {
            ((TopListViewAdapter) this.mAdapter).setOnItemCLickListener(new TopListViewAdapter.OnItemClickListener()
            {
                public void onItemClickListener(View view, int pos, TopListViewAdapter.MyViewHolder holder)
                {
                    clickFragmentItem(pos, (String) holder.tvName.getText());
                }

                public void onItemLongClickListener(View view, int pos, TopListViewAdapter.MyViewHolder holder)
                {
                    Toast.makeText(ViewPagerFragment.this.getActivity(), "DistrictGridAdapter onItemLongClick " + pos, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View view = inflater.inflate(mLayoutId, parent, false);
        initViews(view);
        initDatas();
        initEvents();
        return view;
    }

    public void setFragmentId(int fragmentId)
    {
        this.mFragmentId = fragmentId;
    }

    public void setLayoutManagerId(int layoutManagerId)
    {
        this.mLayoutManagerId = layoutManagerId;
    }

    public int getFragmentId()
    {
        return this.mFragmentId;
    }

    public int getLayoutManagerId()
    {
        return this.mLayoutManagerId;
    }
}