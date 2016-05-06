package com.ooyala.android.item;

import com.ooyala.android.util.DebugMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdSpotManager<T extends AdSpot>
{
  private static final String TAG = "AdSpotManager";
  private List<T> _ads = new ArrayList();
  private Set<T> _playedAds = new HashSet();
  private int _timeAlignment = 0;
  
  private int alignedAdTime(int paramInt)
  {
    int i = paramInt;
    if (this._timeAlignment > 0) {
      i = (this._timeAlignment / 2 + paramInt) / this._timeAlignment * this._timeAlignment;
    }
    return i;
  }
  
  public T adBeforeTime(int paramInt)
  {
    Object localObject = null;
    int i = 0;
    Iterator localIterator = this._ads.iterator();
    for (;;)
    {
      AdSpot localAdSpot;
      int k;
      if (localIterator.hasNext())
      {
        localAdSpot = (AdSpot)localIterator.next();
        k = alignedAdTime(localAdSpot.getTime());
        if (paramInt >= k) {}
      }
      else
      {
        return (T)localObject;
      }
      int j = i;
      if (k > i)
      {
        j = k;
        localObject = null;
      }
      i = j;
      if (!this._playedAds.contains(localAdSpot))
      {
        localObject = localAdSpot;
        i = j;
      }
    }
  }
  
  public void clear()
  {
    this._playedAds.clear();
    this._ads.clear();
    this._timeAlignment = 0;
  }
  
  public int getAlignment()
  {
    return this._timeAlignment;
  }
  
  public Set<Integer> getCuePointsInMilliSeconds()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this._ads.iterator();
    while (localIterator.hasNext())
    {
      AdSpot localAdSpot = (AdSpot)localIterator.next();
      if ((localAdSpot.getTime() > 0) && (!this._playedAds.contains(localAdSpot))) {
        localHashSet.add(Integer.valueOf(alignedAdTime(localAdSpot.getTime())));
      }
    }
    return localHashSet;
  }
  
  public void insertAd(T paramT)
  {
    if (paramT == null)
    {
      DebugMode.assertFail("AdSpotManager", "try to insert a null ad");
      return;
    }
    if (this._ads.contains(paramT))
    {
      DebugMode.assertFail("AdSpotManager", paramT.toString() + " already exist");
      return;
    }
    this._ads.add(paramT);
    Collections.sort(this._ads);
  }
  
  public void insertAds(List<T> paramList)
  {
    this._ads.addAll(paramList);
    Collections.sort(this._ads);
  }
  
  public void markAsPlayed(T paramT)
  {
    if (paramT == null)
    {
      DebugMode.assertFail("AdSpotManager", "try to mark a null adspot");
      return;
    }
    this._playedAds.add(paramT);
  }
  
  public void resetAds()
  {
    this._playedAds.clear();
  }
  
  public void setAlignment(int paramInt)
  {
    this._timeAlignment = paramInt;
  }
  
  public int size()
  {
    return this._ads.size();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\AdSpotManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */