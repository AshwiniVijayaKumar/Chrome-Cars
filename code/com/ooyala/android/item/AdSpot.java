package com.ooyala.android.item;

public abstract class AdSpot
  implements Comparable<AdSpot>
{
  public int compareTo(AdSpot paramAdSpot)
  {
    int j = getTime() - paramAdSpot.getTime();
    int i = j;
    if (j == 0) {
      i = paramAdSpot.getPriority() - getPriority();
    }
    return i;
  }
  
  public int getPriority()
  {
    return 0;
  }
  
  public abstract int getTime();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\AdSpot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */