package com.joanzapata.pdfview;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.joanzapata.pdfview.model.PagePart;
import com.joanzapata.pdfview.util.Constants.Cache;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;

class CacheManager
{
  private PriorityQueue<PagePart> activeCache = new PriorityQueue(Constants.Cache.CACHE_SIZE, new PagePartComparator());
  private PriorityQueue<PagePart> passiveCache = new PriorityQueue(Constants.Cache.CACHE_SIZE, new PagePartComparator());
  private Vector<PagePart> thumbnails = new Vector();
  
  private PagePart find(PriorityQueue<PagePart> paramPriorityQueue, PagePart paramPagePart)
  {
    paramPriorityQueue = paramPriorityQueue.iterator();
    while (paramPriorityQueue.hasNext())
    {
      PagePart localPagePart = (PagePart)paramPriorityQueue.next();
      if (localPagePart.equals(paramPagePart)) {
        return localPagePart;
      }
    }
    return null;
  }
  
  private void makeAFreeSpace()
  {
    while ((this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE) && (!this.passiveCache.isEmpty())) {
      ((PagePart)this.passiveCache.poll()).getRenderedBitmap().recycle();
    }
    while ((this.activeCache.size() + this.passiveCache.size() >= Constants.Cache.CACHE_SIZE) && (!this.activeCache.isEmpty())) {
      ((PagePart)this.activeCache.poll()).getRenderedBitmap().recycle();
    }
  }
  
  public void cachePart(PagePart paramPagePart)
  {
    makeAFreeSpace();
    this.activeCache.offer(paramPagePart);
  }
  
  public void cacheThumbnail(PagePart paramPagePart)
  {
    if (this.thumbnails.size() >= 4) {
      ((PagePart)this.thumbnails.remove(0)).getRenderedBitmap().recycle();
    }
    this.thumbnails.add(paramPagePart);
  }
  
  public boolean containsThumbnail(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, RectF paramRectF)
  {
    paramRectF = new PagePart(paramInt1, paramInt2, null, paramFloat1, paramFloat2, paramRectF, true, 0);
    Iterator localIterator = this.thumbnails.iterator();
    while (localIterator.hasNext()) {
      if (((PagePart)localIterator.next()).equals(paramRectF)) {
        return true;
      }
    }
    return false;
  }
  
  public Vector<PagePart> getPageParts()
  {
    Vector localVector = new Vector(this.passiveCache);
    localVector.addAll(this.activeCache);
    return localVector;
  }
  
  public Vector<PagePart> getThumbnails()
  {
    return this.thumbnails;
  }
  
  public void makeANewSet()
  {
    this.passiveCache.addAll(this.activeCache);
    this.activeCache.clear();
  }
  
  public void recycle()
  {
    Iterator localIterator = this.activeCache.iterator();
    while (localIterator.hasNext()) {
      ((PagePart)localIterator.next()).getRenderedBitmap().recycle();
    }
    localIterator = this.activeCache.iterator();
    while (localIterator.hasNext()) {
      ((PagePart)localIterator.next()).getRenderedBitmap().recycle();
    }
    localIterator = this.thumbnails.iterator();
    while (localIterator.hasNext()) {
      ((PagePart)localIterator.next()).getRenderedBitmap().recycle();
    }
    this.passiveCache.clear();
    this.activeCache.clear();
    this.thumbnails.clear();
  }
  
  public boolean upPartIfContained(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, RectF paramRectF, int paramInt3)
  {
    paramRectF = new PagePart(paramInt1, paramInt2, null, paramFloat1, paramFloat2, paramRectF, false, 0);
    PagePart localPagePart = find(this.passiveCache, paramRectF);
    if (localPagePart != null)
    {
      this.passiveCache.remove(localPagePart);
      localPagePart.setCacheOrder(paramInt3);
      this.activeCache.offer(localPagePart);
      return true;
    }
    return find(this.activeCache, paramRectF) != null;
  }
  
  class PagePartComparator
    implements Comparator<PagePart>
  {
    PagePartComparator() {}
    
    public int compare(PagePart paramPagePart1, PagePart paramPagePart2)
    {
      if (paramPagePart1.getCacheOrder() == paramPagePart2.getCacheOrder()) {
        return 0;
      }
      if (paramPagePart1.getCacheOrder() > paramPagePart2.getCacheOrder()) {
        return 1;
      }
      return -1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\CacheManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */