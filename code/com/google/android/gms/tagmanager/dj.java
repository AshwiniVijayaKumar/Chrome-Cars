package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class dj
  extends dg
{
  private static final String ID = a.aQ.toString();
  private static final String XR = b.bg.toString();
  private static final String XS = b.bp.toString();
  private static final String XT = b.bo.toString();
  private static final String XU = b.er.toString();
  private static final String XV = b.et.toString();
  private static final String XW = b.ev.toString();
  private static Map<String, String> XX;
  private static Map<String, String> XY;
  private final DataLayer TN;
  private final Set<String> XZ;
  private final df Ya;
  
  public dj(Context paramContext, DataLayer paramDataLayer)
  {
    this(paramContext, paramDataLayer, new df(paramContext));
  }
  
  dj(Context paramContext, DataLayer paramDataLayer, df paramdf)
  {
    super(ID, new String[0]);
    this.TN = paramDataLayer;
    this.Ya = paramdf;
    this.XZ = new HashSet();
    this.XZ.add("");
    this.XZ.add("0");
    this.XZ.add("false");
  }
  
  private Map<String, String> E(Map<String, d.a> paramMap)
  {
    paramMap = (d.a)paramMap.get(XV);
    if (paramMap != null) {
      return c(paramMap);
    }
    if (XX == null)
    {
      paramMap = new HashMap();
      paramMap.put("transactionId", "&ti");
      paramMap.put("transactionAffiliation", "&ta");
      paramMap.put("transactionTax", "&tt");
      paramMap.put("transactionShipping", "&ts");
      paramMap.put("transactionTotal", "&tr");
      paramMap.put("transactionCurrency", "&cu");
      XX = paramMap;
    }
    return XX;
  }
  
  private Map<String, String> F(Map<String, d.a> paramMap)
  {
    paramMap = (d.a)paramMap.get(XW);
    if (paramMap != null) {
      return c(paramMap);
    }
    if (XY == null)
    {
      paramMap = new HashMap();
      paramMap.put("name", "&in");
      paramMap.put("sku", "&ic");
      paramMap.put("category", "&iv");
      paramMap.put("price", "&ip");
      paramMap.put("quantity", "&iq");
      paramMap.put("currency", "&cu");
      XY = paramMap;
    }
    return XY;
  }
  
  private void a(Tracker paramTracker, Map<String, d.a> paramMap)
  {
    String str = bN("transactionId");
    if (str == null) {
      bh.t("Cannot find transactionId in data layer.");
    }
    for (;;)
    {
      return;
      LinkedList localLinkedList = new LinkedList();
      Object localObject2;
      Object localObject3;
      try
      {
        localObject1 = p((d.a)paramMap.get(XT));
        ((Map)localObject1).put("&t", "transaction");
        localObject2 = E(paramMap).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          b((Map)localObject1, (String)((Map.Entry)localObject3).getValue(), bN((String)((Map.Entry)localObject3).getKey()));
        }
        localLinkedList.add(localObject1);
      }
      catch (IllegalArgumentException paramTracker)
      {
        bh.c("Unable to send transaction", paramTracker);
        return;
      }
      Object localObject1 = kv();
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map)((Iterator)localObject1).next();
          if (((Map)localObject2).get("name") == null)
          {
            bh.t("Unable to send transaction item hit due to missing 'name' field.");
            return;
          }
          localObject3 = p((d.a)paramMap.get(XT));
          ((Map)localObject3).put("&t", "item");
          ((Map)localObject3).put("&ti", str);
          Iterator localIterator = F(paramMap).entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            b((Map)localObject3, (String)localEntry.getValue(), (String)((Map)localObject2).get(localEntry.getKey()));
          }
          localLinkedList.add(localObject3);
        }
      }
      paramMap = localLinkedList.iterator();
      while (paramMap.hasNext()) {
        paramTracker.send((Map)paramMap.next());
      }
    }
  }
  
  private void b(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  private String bN(String paramString)
  {
    paramString = this.TN.get(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.toString();
  }
  
  private Map<String, String> c(d.a parama)
  {
    parama = di.o(parama);
    if (!(parama instanceof Map)) {
      return null;
    }
    Object localObject = (Map)parama;
    parama = new LinkedHashMap();
    localObject = ((Map)localObject).entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      parama.put(localEntry.getKey().toString(), localEntry.getValue().toString());
    }
    return parama;
  }
  
  private boolean d(Map<String, d.a> paramMap, String paramString)
  {
    paramMap = (d.a)paramMap.get(paramString);
    if (paramMap == null) {
      return false;
    }
    return di.n(paramMap).booleanValue();
  }
  
  private List<Map<String, String>> kv()
  {
    Object localObject = this.TN.get("transactionProducts");
    if (localObject == null) {
      return null;
    }
    if (!(localObject instanceof List)) {
      throw new IllegalArgumentException("transactionProducts should be of type List.");
    }
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext()) {
      if (!(localIterator.next() instanceof Map)) {
        throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
      }
    }
    return (List)localObject;
  }
  
  private Map<String, String> p(d.a parama)
  {
    if (parama == null) {
      return new HashMap();
    }
    parama = c(parama);
    if (parama == null) {
      return new HashMap();
    }
    String str = (String)parama.get("&aip");
    if ((str != null) && (this.XZ.contains(str.toLowerCase()))) {
      parama.remove("&aip");
    }
    return parama;
  }
  
  public void w(Map<String, d.a> paramMap)
  {
    Tracker localTracker = this.Ya.bF("_GTM_DEFAULT_TRACKER_");
    if (d(paramMap, XS))
    {
      localTracker.send(p((d.a)paramMap.get(XT)));
      return;
    }
    if (d(paramMap, XU))
    {
      a(localTracker, paramMap);
      return;
    }
    bh.w("Ignoring unknown tag.");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\dj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */