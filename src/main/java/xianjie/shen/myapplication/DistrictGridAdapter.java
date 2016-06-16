package xianjie.shen.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DistrictGridAdapter extends Adapter<DistrictGridAdapter.MyViewHolder>
{
    public static final String TEXT_COLOR = "#88000000";
    private Context mContext;
    protected List<?> mDatas;
    private ArrayList<Integer> mHeights;
    private LayoutInflater mInflater;
    private boolean mIsStaggered;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClickListener(View view, int pos, MyViewHolder holder);

        void onItemLongClickListener(View view, int pos, MyViewHolder holder);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    public DistrictGridAdapter(Context context, List<?> datas, int layoutId, boolean isStaggered)
    {
        this.mContext = context;
        this.mIsStaggered = isStaggered;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
        if (isStaggered)
        {//true为瀑布流
            mHeights = new ArrayList();
            for (int i = 0; i < datas.size(); i++)
            {
                mHeights.add(Integer.valueOf((int) (200.0D + Math.random() * 300.0D)));
            }
        }
    }

    private void setStaggeredGridAdapter(ViewHolder holder, int pos)
    {
        LayoutParams lp = holder.itemView.getLayoutParams();
        holder.getLayoutPosition();
        lp.height = ((Integer) mHeights.get(pos)).intValue();
        holder.itemView.setLayoutParams(lp);
    }

    private void setUpItemClickListener(final MyViewHolder holder)
    {
        if (this.mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new OnClickListener()
            {
                public void onClick(View view)
                {
                    int i = holder.getLayoutPosition();
                    DistrictGridAdapter.this.mOnItemClickListener.onItemClickListener(view, i, holder);
                }
            });
            holder.itemView.setOnLongClickListener(new OnLongClickListener()
            {
                public boolean onLongClick(View view)
                {
                    int i = holder.getLayoutPosition();
                    DistrictGridAdapter.this.mOnItemClickListener.onItemLongClickListener(view, i, holder);
                    return false;
                }
            });
        }
    }

    public int getItemCount()
    {
        return this.mDatas.size();
    }

    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.iv.setImageResource(((DistrictFragmentItemBean) this.mDatas.get(position)).getImgId());
        holder.tv.setText(((DistrictFragmentItemBean) this.mDatas.get(position)).getName());
        setUpItemClickListener(holder);
        if (this.mIsStaggered)
            setStaggeredGridAdapter(holder, position);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    public void setOnItemCLickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.iv = ((ImageView) itemView.findViewById(R.id.iv_img));
            this.tv = ((TextView) itemView.findViewById(R.id.tv_district));
        }
    }
}