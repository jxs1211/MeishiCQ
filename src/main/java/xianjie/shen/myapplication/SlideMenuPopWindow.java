package xianjie.shen.myapplication;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.util.List;

public class SlideMenuPopWindow extends PopupWindow
    {
    private PopupWindowListViewAdapter mAdapter;
    private Context mContext;
    private View mConvertView;
    private List<?> mDatas;
    private int mHeight;
    private PopupWindowListViewAdapter.MyViewHolder mHolder;
    private OnItemClickListener mListener;
    private RecyclerView mRecyclerView;
    private int mWidth;

    public SlideMenuPopWindow(Context context, List<?> paramList)
        {
        calWidthAndHeight(context);
        this.mConvertView = LayoutInflater.from(context).inflate(R.layout.slide_menu_popup_window_main, null);
        this.mDatas = paramList;
        this.mContext = context;
        setContentView(this.mConvertView);
        setWidth(this.mWidth);
        setHeight(this.mHeight);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchInterceptor(new OnTouchListener()
            {
            public boolean onTouch(View view, MotionEvent motionEvent)
                {
//                view.getX();
                if (motionEvent.getAction() == 4)
                    {
                    SlideMenuPopWindow.this.dismiss();
                    return true;
                    }
                switch (motionEvent.getAction())
                    {
                    case 0:
                    case 1:
                    case 2:
                    }
                return false;
                }
            });
        initViews();
        initDatas();
        initEvents();
        }

    private void calWidthAndHeight(Context context)
        {
//    context = getDisplayMetrics(context);
        this.mWidth = ((int) (getDisplayMetrics(context).widthPixels * 0.7D));
        this.mHeight = getDisplayMetrics(context).heightPixels;
        }

    private DisplayMetrics getDisplayMetrics(Context context)
        {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics;
        }

    private void initDatas()
        {
        this.mAdapter = new PopupWindowListViewAdapter(this.mContext, this.mDatas, R.layout.slide_menu_item);
//        this.mHolder = this.mAdapter.getHolder();
        }

    private void initEvents()
        {
        this.mAdapter.setOnSlideMenuItemClickListener(new PopupWindowListViewAdapter.SlideMenuItemClickListener()
            {
            public void itemClick(View paramAnonymousView, int paramAnonymousInt, PopupWindowListViewAdapter.MyViewHolder paramAnonymousMyViewHolder)
                {
                if (SlideMenuPopWindow.this.mListener != null)
                    SlideMenuPopWindow.this.mListener.onItemClick(paramAnonymousView, paramAnonymousInt, paramAnonymousMyViewHolder);
                }

            public void itemLongClick(View paramAnonymousView, int paramAnonymousInt, PopupWindowListViewAdapter.MyViewHolder paramAnonymousMyViewHolder)
                {
                if (SlideMenuPopWindow.this.mListener != null)
                    SlideMenuPopWindow.this.mListener.onItemLongClick(paramAnonymousView, paramAnonymousInt, paramAnonymousMyViewHolder);
                }
            });
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        this.mRecyclerView.setLayoutManager(localLinearLayoutManager);
        this.mRecyclerView.setAdapter(this.mAdapter);
        }

    private void initViews()
        {
        this.mRecyclerView = ((RecyclerView) this.mConvertView.findViewById(R.id.rv_slide_menu));
        }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
        {
        this.mListener = paramOnItemClickListener;
        }

    public interface OnItemClickListener
        {
        public abstract void onItemClick(View paramView, int paramInt, PopupWindowListViewAdapter.MyViewHolder paramMyViewHolder);

        public abstract void onItemLongClick(View paramView, int paramInt, PopupWindowListViewAdapter.MyViewHolder paramMyViewHolder);
        }
    }

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.SlideMenuPopWindow
 * JD-Core Version:    0.6.2
 */