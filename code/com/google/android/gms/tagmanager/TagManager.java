package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager
{
  private static TagManager XB;
  private final DataLayer TN;
  private final r Wj;
  private final ConcurrentMap<n, Boolean> XA;
  private final a Xz;
  private final Context mContext;
  
  TagManager(Context paramContext, a parama, DataLayer paramDataLayer)
  {
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    this.mContext = paramContext.getApplicationContext();
    this.Xz = parama;
    this.XA = new ConcurrentHashMap();
    this.TN = paramDataLayer;
    this.TN.a(new DataLayer.b()
    {
      public void v(Map<String, Object> paramAnonymousMap)
      {
        paramAnonymousMap = paramAnonymousMap.get("event");
        if (paramAnonymousMap != null) {
          TagManager.a(TagManager.this, paramAnonymousMap.toString());
        }
      }
    });
    this.TN.a(new d(this.mContext));
    this.Wj = new r();
  }
  
  private void bE(String paramString)
  {
    Iterator localIterator = this.XA.keySet().iterator();
    while (localIterator.hasNext()) {
      ((n)localIterator.next()).ba(paramString);
    }
  }
  
  public static TagManager getInstance(Context paramContext)
  {
    try
    {
      if (XB != null) {
        break label65;
      }
      if (paramContext == null)
      {
        bh.t("TagManager.getInstance requires non-null context.");
        throw new NullPointerException();
      }
    }
    finally {}
    XB = new TagManager(paramContext, new a()new DataLayernew v
    {
      public o a(Context paramAnonymousContext, TagManager paramAnonymousTagManager, Looper paramAnonymousLooper, String paramAnonymousString, int paramAnonymousInt, r paramAnonymousr)
      {
        return new o(paramAnonymousContext, paramAnonymousTagManager, paramAnonymousLooper, paramAnonymousString, paramAnonymousInt, paramAnonymousr);
      }
    }, new DataLayer(new v(paramContext)));
    label65:
    paramContext = XB;
    return paramContext;
  }
  
  void a(n paramn)
  {
    this.XA.put(paramn, Boolean.valueOf(true));
  }
  
  boolean b(n paramn)
  {
    return this.XA.remove(paramn) != null;
  }
  
  boolean f(Uri paramUri)
  {
    for (;;)
    {
      boolean bool;
      Object localObject2;
      try
      {
        localObject1 = ce.ju();
        if (!((ce)localObject1).f(paramUri)) {
          break label229;
        }
        paramUri = ((ce)localObject1).getContainerId();
        int i = 3.XD[localObject1.jv().ordinal()];
        switch (i)
        {
        default: 
          bool = true;
          return bool;
        }
      }
      finally {}
      Object localObject1 = this.XA.keySet().iterator();
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (n)((Iterator)localObject1).next();
        if (((n)localObject2).getContainerId().equals(paramUri))
        {
          ((n)localObject2).bc(null);
          ((n)localObject2).refresh();
        }
      }
      else
      {
        continue;
        localObject2 = this.XA.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          n localn = (n)((Iterator)localObject2).next();
          if (localn.getContainerId().equals(paramUri))
          {
            localn.bc(((ce)localObject1).jw());
            localn.refresh();
          }
          else if (localn.iF() != null)
          {
            localn.bc(null);
            localn.refresh();
          }
        }
        continue;
        label229:
        bool = false;
      }
    }
  }
  
  public DataLayer getDataLayer()
  {
    return this.TN;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, int paramInt)
  {
    paramString = this.Xz.a(this.mContext, this, null, paramString, paramInt, this.Wj);
    paramString.iI();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, int paramInt, Handler paramHandler)
  {
    paramString = this.Xz.a(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.Wj);
    paramString.iI();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, int paramInt)
  {
    paramString = this.Xz.a(this.mContext, this, null, paramString, paramInt, this.Wj);
    paramString.iK();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, int paramInt, Handler paramHandler)
  {
    paramString = this.Xz.a(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.Wj);
    paramString.iK();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, int paramInt)
  {
    paramString = this.Xz.a(this.mContext, this, null, paramString, paramInt, this.Wj);
    paramString.iJ();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, int paramInt, Handler paramHandler)
  {
    paramString = this.Xz.a(this.mContext, this, paramHandler.getLooper(), paramString, paramInt, this.Wj);
    paramString.iJ();
    return paramString;
  }
  
  public void setVerboseLoggingEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2;; i = 5)
    {
      bh.setLogLevel(i);
      return;
    }
  }
  
  static abstract interface a
  {
    public abstract o a(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, r paramr);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\TagManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */