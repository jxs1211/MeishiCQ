package xianjie.shen.myapplication;

public class TopItemBean
{
  int imgId;
  String tvDistrict;
  String tvName;
  String tvZanCount;
  int zanId;

  public TopItemBean(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    this.imgId = paramInt1;
    this.zanId = paramInt2;
    this.tvName = paramString1;
    this.tvDistrict = paramString2;
    this.tvZanCount = paramString3;
  }

  public int getImgId()
  {
    return this.imgId;
  }

  public String getTvDistrict()
  {
    return this.tvDistrict;
  }

  public String getTvName()
  {
    return this.tvName;
  }

  public String getTvZanCount()
  {
    return this.tvZanCount;
  }

  public int getZanId()
  {
    return this.zanId;
  }

  public void setImgId(int paramInt)
  {
    this.imgId = paramInt;
  }

  public void setTvDistrict(String paramString)
  {
    this.tvDistrict = paramString;
  }

  public void setTvName(String paramString)
  {
    this.tvName = paramString;
  }

  public void setTvZanCount(String paramString)
  {
    this.tvZanCount = paramString;
  }

  public void setZanId(int paramInt)
  {
    this.zanId = paramInt;
  }
}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.TopItemBean
 * JD-Core Version:    0.6.2
 */