package xianjie.shen.myapplication.fooddetail;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/5/22.
 */
public class FoodDetailItemBean
{
    ImageView itemIcon;
    ImageView itemArrow;
    TextView itemText;

    public ImageView getItemIcon()
    {
        return itemIcon;
    }

    public void setItemIcon(ImageView itemIcon)
    {
        this.itemIcon = itemIcon;
    }

    public ImageView getItemArrow()
    {
        return itemArrow;
    }

    public void setItemArrow(ImageView itemArrow)
    {
        this.itemArrow = itemArrow;
    }

    public TextView getItemText()
    {
        return itemText;
    }

    public void setItemText(TextView itemText)
    {
        this.itemText = itemText;
    }
}
