package xianjie.shen.myapplication.fooddetail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import xianjie.shen.myapplication.R;

/**
 * Created by Administrator on 2016/5/22.
 */
public class FoodDetailAdapter extends RecyclerView.Adapter<FoodDetailAdapter.MyViewHolder>
{

    private Context mContext;
    protected List<?> mDatas;
    private LayoutInflater mInflater;
    private int mLayoutId;
    private String[] mDialogItemText;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int pos, MyViewHolder holder);

        void onItemLongClick(View view, int pos, MyViewHolder holder);
    }

    public FoodDetailAdapter(Context context, List<?> datas, int layoutId)
    {
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(mLayoutId, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
//        String itemText = ((FoodDetailItemBean) mDatas.get(position)).getItemText().toString();
        //重置一下imageView的状态，以免错乱
        holder.itemIcon.setImageResource(R.drawable.white_box);
        holder.itemArrow.setImageResource(R.drawable.white_box);

        String itemText = mDatas.get(position).toString();
        if (position % 2 == 0)
        {
            holder.itemIcon.setImageResource(R.drawable.ic_menu_call);
        } else
        {
            holder.itemIcon.setImageResource(R.drawable.ic_menu_mapmode);
        }
        holder.itemArrow.setImageResource(R.drawable.tuan_triangle);
        holder.itemText.setText(itemText);

        setupItemEvent(holder);
    }

    private void setupItemEvent(final MyViewHolder holder)
    {
        if (mOnItemClickListener != null)
        {
            final int pos = holder.getLayoutPosition();
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mOnItemClickListener.onItemClick(view, pos, holder);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    mOnItemClickListener.onItemLongClick(view, pos, holder);
                    return true;//处理并拦截
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView itemIcon;
        ImageView itemArrow;
        TextView itemText;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            itemIcon = (ImageView) itemView.findViewById(R.id.iv_food_detail_item_icon);
            itemArrow = (ImageView) itemView.findViewById(R.id.iv_food_detail_arrow);
            itemText = (TextView) itemView.findViewById(R.id.tv_food_detail_item_text);
        }
    }

    public AlertDialog clickCallDialog(final MyViewHolder holder)
    {
        final String number = (String) holder.itemText.getText();
        int i = number.indexOf("电话：");
        AlertDialog dialog = null;
        if ((number.substring(i + 3).equals("无")) || (number.substring(i + 3).equals("老板不留电话")))
        {//商家无电话
            dialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.MyDialogTheme))
                    .setTitle("联系方式")
                    .setMessage(number.substring(i + 3))
                    .setPositiveButton("取消", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    }).create();
            return dialog;
        } else
        {//商家有电话
            dialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.MyDialogTheme))
                    .setTitle(mContext.getResources().getString(R.string.call_dialog_title))
                    .setMessage(number.substring(i + 3))
                    .setPositiveButton("拨打", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + number));
                            mContext.startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    }).create();
            return dialog;
        }
    }

    public AlertDialog clickNaviDialog(final MyViewHolder holder)
    {
        mDialogItemText = mContext.getResources().getStringArray(R.array.dialog_item_maps);
        ListAdapter adapter = new ArrayAdapter(mContext, R.layout.single_selection_list_item, R.id.tv_dialog_item, mDialogItemText);

        AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.MyDialogTheme))
                .setTitle(mContext.getResources().getString(R.string.navi_dialog_title))
                .setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        naviAddress(searchAddress(holder), which);
                        Toast.makeText(mContext, searchAddress(holder), Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                }).create();
        return dialog;
    }

    private String searchAddress(MyViewHolder holder)
    {
        int i = holder.itemText.getText().toString().indexOf("导航：");
        int length = holder.itemText.getText().toString().length();
        return holder.itemText.getText().toString().substring(i + 3, length - 1);
    }

    public void naviAddress(String address, int which)
    {
        switch (which)
        {
            case 0://调起百度地图
                try
                {
                    if (isAlreadyInstalled("com.baidu.BaiduMap"))
                    {
                        Intent intent = new Intent("android.intent.action.VIEW");
//                        intent.setData(Uri.parse("intent://map/geocoder?address=\" + address + \"&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end"));
//                        intent.setData(Uri.parse("intent://map/geocoder?address=" + address + "&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end"));
                        intent = Intent.getIntent("intent://map/geocoder?address=" + address + "&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//                        intent.setPackage("com.baidu.BaiduMap");
                        mContext.startActivity(intent); //启动调用
                    } else
                    {//防止因为没有安装地图客户端，直接会导致程序挂掉
                        Toast.makeText(mContext, "没有安装百度地图客户端", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e)
                {
                }
                break;
            case 1://调起高德地图
                try
                {
                    if (isAlreadyInstalled("com.autonavi.minimap"))
                    {
                        Intent intent = new Intent("android.intent.action.VIEW",
                                Uri.parse("androidamap://viewGeo?sourceApplication=softname&addr=" + address));
                        intent.setPackage("com.autonavi.minimap");
                        mContext.startActivity(intent); //启动调用
//                        Log.i("GasStation", "高德地图客户端已经安装");
                    } else
                    {
                        Toast.makeText(mContext, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e)
                {
                }
                break;
        }
    }

    private boolean isAlreadyInstalled(String packageName)
    {
        return new File("/data/data/" + packageName).exists();
    }
}
