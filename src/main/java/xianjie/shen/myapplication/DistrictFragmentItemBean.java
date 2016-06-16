package xianjie.shen.myapplication;

public class DistrictFragmentItemBean
{
    private int imgId;
    private String name;

    public DistrictFragmentItemBean(int imgId, String text)
    {
        this.imgId = imgId;
        this.name = text;
    }

    public int getImgId()
    {
        return this.imgId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setImgId(int paramInt)
    {
        this.imgId = paramInt;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }
}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.DistrictFragmentItemBean
 * JD-Core Version:    0.6.2
 */