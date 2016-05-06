package com.joanzapata.pdfview.model;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class PagePart
{
  private int cacheOrder;
  private float height;
  private int page;
  private RectF pageRelativeBounds;
  private Bitmap renderedBitmap;
  private boolean thumbnail;
  private int userPage;
  private float width;
  
  public PagePart(int paramInt1, int paramInt2, Bitmap paramBitmap, float paramFloat1, float paramFloat2, RectF paramRectF, boolean paramBoolean, int paramInt3)
  {
    this.userPage = paramInt1;
    this.page = paramInt2;
    this.renderedBitmap = paramBitmap;
    this.pageRelativeBounds = paramRectF;
    this.thumbnail = paramBoolean;
    this.cacheOrder = paramInt3;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PagePart)) {}
    do
    {
      return false;
      paramObject = (PagePart)paramObject;
    } while ((((PagePart)paramObject).getPage() != this.page) || (((PagePart)paramObject).getUserPage() != this.userPage) || (((PagePart)paramObject).getWidth() != this.width) || (((PagePart)paramObject).getHeight() != this.height) || (((PagePart)paramObject).getPageRelativeBounds().left != this.pageRelativeBounds.left) || (((PagePart)paramObject).getPageRelativeBounds().right != this.pageRelativeBounds.right) || (((PagePart)paramObject).getPageRelativeBounds().top != this.pageRelativeBounds.top) || (((PagePart)paramObject).getPageRelativeBounds().bottom != this.pageRelativeBounds.bottom));
    return true;
  }
  
  public int getCacheOrder()
  {
    return this.cacheOrder;
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  public int getPage()
  {
    return this.page;
  }
  
  public RectF getPageRelativeBounds()
  {
    return this.pageRelativeBounds;
  }
  
  public Bitmap getRenderedBitmap()
  {
    return this.renderedBitmap;
  }
  
  public int getUserPage()
  {
    return this.userPage;
  }
  
  public float getWidth()
  {
    return this.width;
  }
  
  public boolean isThumbnail()
  {
    return this.thumbnail;
  }
  
  public void setCacheOrder(int paramInt)
  {
    this.cacheOrder = paramInt;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\model\PagePart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */