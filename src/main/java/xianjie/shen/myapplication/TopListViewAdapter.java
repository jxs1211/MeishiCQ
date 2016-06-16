package xianjie.shen.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TopListViewAdapter extends Adapter<TopListViewAdapter.MyViewHolder>
{
    private Context mContext;
    protected List<?> mDatas;
    private LayoutInflater mInflater;
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
    protected List<String> mStringDatas;

    public TopListViewAdapter(Context paramContext, List<?> paramList, int paramInt)
    {
        this.mContext = paramContext;
        this.mDatas = paramList;
        this.mLayoutId = paramInt;
        this.mInflater = LayoutInflater.from(paramContext);
    }

    private void setUpItemClickListener(final MyViewHolder paramMyViewHolder)
    {
        if (this.mOnItemClickListener != null)
        {
            paramMyViewHolder.itemView.setOnClickListener(new OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    int i = paramMyViewHolder.getLayoutPosition();
                    TopListViewAdapter.this.mOnItemClickListener.onItemClickListener(paramAnonymousView, i, paramMyViewHolder);
                }
            });
            paramMyViewHolder.itemView.setOnLongClickListener(new OnLongClickListener()
            {
                public boolean onLongClick(View paramAnonymousView)
                {
                    int i = paramMyViewHolder.getLayoutPosition();
                    TopListViewAdapter.this.mOnItemClickListener.onItemLongClickListener(paramAnonymousView, i, paramMyViewHolder);
                    return false;
                }
            });
        }
    }

    private void setUpItemClickListener(final MyViewHolder paramMyViewHolder, final int paramInt)
    {
        if (this.mOnItemClickListener != null)
        {
            paramMyViewHolder.itemView.setOnClickListener(new OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    TopListViewAdapter.this.mOnItemClickListener.onItemClickListener(paramAnonymousView, paramInt, paramMyViewHolder);
                }
            });
            paramMyViewHolder.itemView.setOnLongClickListener(new OnLongClickListener()
            {
                public boolean onLongClick(View paramAnonymousView)
                {
                    TopListViewAdapter.this.mOnItemClickListener.onItemLongClickListener(paramAnonymousView, paramInt, paramMyViewHolder);
                    return false;
                }
            });
        }
    }

    public void addItem(int paramInt)
    {
        notifyItemInserted(paramInt);
    }

    public void deleteItem(int paramInt)
    {
        this.mDatas.remove(paramInt);
        notifyItemRemoved(paramInt);
    }

    public int getItemCount()
    {
        return this.mDatas.size();
    }

    public void onBindViewHolder(MyViewHolder paramMyViewHolder, int paramInt)
    {
        paramMyViewHolder.ivImg.setImageResource(((TopItemBean) this.mDatas.get(paramInt)).getImgId());
        paramMyViewHolder.ivZan.setImageResource(((TopItemBean) this.mDatas.get(paramInt)).getZanId());
        paramMyViewHolder.tvName.setText(((TopItemBean) this.mDatas.get(paramInt)).getTvName());
        paramMyViewHolder.tvDistrict.setText(((TopItemBean) this.mDatas.get(paramInt)).getTvDistrict());
        paramMyViewHolder.tvZanCount.setText(((TopItemBean) this.mDatas.get(paramInt)).getTvZanCount());
        setUpItemClickListener(paramMyViewHolder);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
        return new MyViewHolder(this.mInflater.inflate(this.mLayoutId, paramViewGroup, false));
    }

    public void setOnItemCLickListener(OnItemClickListener paramOnItemClickListener)
    {
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView ivImg;
        ImageView ivZan;
        TextView tvDistrict;
        TextView tvName;
        TextView tvZanCount;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.ivImg = ((ImageView) itemView.findViewById(R.id.iv_img));
            this.ivZan = ((ImageView) itemView.findViewById(R.id.iv_zan));
            this.tvName = ((TextView) itemView.findViewById(R.id.tv_name));
            this.tvDistrict = ((TextView) itemView.findViewById(R.id.tv_district));
            this.tvZanCount = ((TextView) itemView.findViewById(R.id.tv_zan_count));
        }
    }

}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.TopListViewAdapter
 * JD-Core Version:    0.6.2
 */