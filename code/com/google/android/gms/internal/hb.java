package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;
import java.util.Set;

public final class hb
{
  private static final String[] IH = { "requestId", "outcome" };
  private final HashMap<String, Integer> II;
  private final int yJ;
  
  private hb(int paramInt, HashMap<String, Integer> paramHashMap)
  {
    this.yJ = paramInt;
    this.II = paramHashMap;
  }
  
  public static hb H(DataHolder paramDataHolder)
  {
    a locala = new a();
    locala.aZ(paramDataHolder.getStatusCode());
    int j = paramDataHolder.getCount();
    int i = 0;
    while (i < j)
    {
      int k = paramDataHolder.I(i);
      locala.p(paramDataHolder.getString("requestId", i, k), paramDataHolder.getInteger("outcome", i, k));
      i += 1;
    }
    return locala.fV();
  }
  
  public Set<String> getRequestIds()
  {
    return this.II.keySet();
  }
  
  public int getRequestOutcome(String paramString)
  {
    er.b(this.II.containsKey(paramString), "Request " + paramString + " was not part of the update operation!");
    return ((Integer)this.II.get(paramString)).intValue();
  }
  
  public static final class a
  {
    private HashMap<String, Integer> II = new HashMap();
    private int yJ = 0;
    
    public a aZ(int paramInt)
    {
      this.yJ = paramInt;
      return this;
    }
    
    public hb fV()
    {
      return new hb(this.yJ, this.II, null);
    }
    
    public a p(String paramString, int paramInt)
    {
      if (gt.isValid(paramInt)) {
        this.II.put(paramString, Integer.valueOf(paramInt));
      }
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */