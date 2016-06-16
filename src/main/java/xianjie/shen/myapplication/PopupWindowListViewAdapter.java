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
import java.util.concurrent.Semaphore;

public class PopupWindowListViewAdapter extends RecyclerView.Adapter<PopupWindowListViewAdapter.MyViewHolder>
{
    private Context mContext;
    private List<?> mDatas;
    private MyViewHolder mHolder;
    private Semaphore mHolderSemphone = new Semaphore(0);
    private LayoutInflater mInflater;
    private int mLayoutId;
    private SlideMenuItemClickListener mSlideMenuItemClickListener;

    public interface SlideMenuItemClickListener
    {
        void itemClick(View view, int pos, MyViewHolder holder);

        void itemLongClick(View view, int pos, MyViewHolder holder);
    }

    public void setOnSlideMenuItemClickListener(SlideMenuItemClickListener listener)
    {
        this.mSlideMenuItemClickListener = listener;
    }

    public PopupWindowListViewAdapter(Context paramContext, List<?> paramList, int paramInt)
    {
        this.mContext = paramContext;
        this.mDatas = paramList;
        this.mInflater = LayoutInflater.from(paramContext);
        this.mLayoutId = paramInt;
    }

    private void setupItemEvents(final MyViewHolder holder, final int pos)
    {
        holder.itemView.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
                if (PopupWindowListViewAdapter.this.mSlideMenuItemClickListener != null)
                    PopupWindowListViewAdapter.this.mSlideMenuItemClickListener.itemClick(view, pos, holder);
            }
        });
        holder.itemView.setOnLongClickListener(new OnLongClickListener()
        {
            public boolean onLongClick(View view)
            {
                if (PopupWindowListViewAdapter.this.mSlideMenuItemClickListener != null)
                    PopupWindowListViewAdapter.this.mSlideMenuItemClickListener.itemLongClick(view, pos, holder);
                return false;
            }
        });
    }

//    public MyViewHolder getHolder()
//    {
//        if (this.mHolder == null) ;
//        try
//        {
//            this.mHolderSemphone.acquire();
////            label14:
//            mHolder = new MyViewHolder();
//            return this.mHolder;
//        } catch (InterruptedException localInterruptedException)
//        {
////            break label14;
//        }
//    }

    public int getItemCount()
    {
        return this.mDatas.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int pos)
    {
        holder.viewTag.setBackgroundColor(((SlideMenuItemBean) this.mDatas.get(pos)).getSlideMenuTag());
        holder.iv_Img.setImageResource(((SlideMenuItemBean) this.mDatas.get(pos)).getImg());
        holder.tv_Title.setText(((SlideMenuItemBean) this.mDatas.get(pos)).getTitle());
        holder.tv_Desc.setText(((SlideMenuItemBean) this.mDatas.get(pos)).getDesc());
        setupItemEvents(holder, pos);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int paramInt)
    {
        View view = this.mInflater.inflate(this.mLayoutId, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
//        this.mHolderSemphone.release();
        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_Img;
        TextView tv_Desc;
        TextView tv_Title;
        View viewTag;

        public MyViewHolder(View itemView)
        {
            super(itemView);
//            this.viewTag = itemView.findViewById(2131361903);
//            this.iv_Img = ((ImageView) viewTag.findViewById(2131361904));
//            this.tv_Title = ((TextView) viewTag.findViewById(2131361905));
//            this.tv_Desc = ((TextView) viewTag.findViewById(2131361906));
//            }
        }
    }
}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.PopupWindowListViewAdapter
 * JD-Core Version:    0.6.2
 */