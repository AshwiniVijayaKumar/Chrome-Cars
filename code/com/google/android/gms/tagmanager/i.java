package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class i
  extends dg
{
  private static final String ID = a.aA.toString();
  private static final String TF = b.bi.toString();
  private static final String TG = b.ey.toString();
  static final String TH = "gtm_" + ID + "_unrepeatable";
  private static final Set<String> TI = new HashSet();
  private static final String URL = b.ez.toString();
  private final a TJ;
  private final Context mContext;
  
  public i(Context paramContext)
  {
    this(paramContext, new a()
    {
      public aq iz()
      {
        return y.F(i.this);
      }
    });
  }
  
  i(Context paramContext, a parama)
  {
    super(ID, new String[] { URL });
    this.TJ = parama;
    this.mContext = paramContext;
  }
  
  private boolean aU(String paramString)
  {
    boolean bool1 = true;
    for (;;)
    {
      try
      {
        boolean bool2 = aW(paramString);
        if (bool2) {
          return bool1;
        }
        if (aV(paramString)) {
          TI.add(paramString);
        } else {
          bool1 = false;
        }
      }
      finally {}
    }
  }
  
  boolean aV(String paramString)
  {
    return this.mContext.getSharedPreferences(TH, 0).contains(paramString);
  }
  
  boolean aW(String paramString)
  {
    return TI.contains(paramString);
  }
  
  public void w(Map<String, d.a> paramMap)
  {
    String str;
    if (paramMap.get(TG) != null)
    {
      str = di.j((d.a)paramMap.get(TG));
      if ((str == null) || (!aU(str))) {
        break label46;
      }
    }
    label46:
    do
    {
      return;
      str = null;
      break;
      Uri.Builder localBuilder = Uri.parse(di.j((d.a)paramMap.get(URL))).buildUpon();
      paramMap = (d.a)paramMap.get(TF);
      if (paramMap != null)
      {
        paramMap = di.o(paramMap);
        if (!(paramMap instanceof List))
        {
          bh.t("ArbitraryPixel: additional params not a list: not sending partial hit: " + localBuilder.build().toString());
          return;
        }
        paramMap = ((List)paramMap).iterator();
        while (paramMap.hasNext())
        {
          Object localObject = paramMap.next();
          if (!(localObject instanceof Map))
          {
            bh.t("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + localBuilder.build().toString());
            return;
          }
          localObject = ((Map)localObject).entrySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
            localBuilder.appendQueryParameter(localEntry.getKey().toString(), localEntry.getValue().toString());
          }
        }
      }
      paramMap = localBuilder.build().toString();
      this.TJ.iz().bk(paramMap);
      bh.v("ArbitraryPixel: url = " + paramMap);
    } while (str == null);
    try
    {
      TI.add(str);
      cz.a(this.mContext, TH, str, "true");
      return;
    }
    finally {}
  }
  
  public static abstract interface a
  {
    public abstract aq iz();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */