package xianjie.shen.myapplication;

public class SlideMenuItemBean
{
  private String desc;
  private int img;
  private int slideMenuTag;
  private String title;

  public SlideMenuItemBean(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    this.slideMenuTag = paramInt1;
    this.img = paramInt2;
    this.title = paramString1;
    this.desc = paramString2;
  }

  public String getDesc()
  {
    return this.desc;
  }

  public int getImg()
  {
    return this.img;
  }

  public int getSlideMenuTag()
  {
    return this.slideMenuTag;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setDesc(String paramString)
  {
    this.desc = paramString;
  }

  public void setImg(int paramInt)
  {
    this.img = paramInt;
  }

  public void setSlideMenuTag(int paramInt)
  {
    this.slideMenuTag = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           E:\tools\apktool\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     shen.xianjie.myapplication.SlideMenuItemBean
 * JD-Core Version:    0.6.2
 */