package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class di
{
  private static final Object XI = null;
  private static Long XJ = new Long(0L);
  private static Double XK = new Double(0.0D);
  private static dh XL = dh.v(0L);
  private static String XM = new String("");
  private static Boolean XN = new Boolean(false);
  private static List<Object> XO = new ArrayList(0);
  private static Map<Object, Object> XP = new HashMap();
  private static d.a XQ = r(XM);
  
  public static d.a bI(String paramString)
  {
    d.a locala = new d.a();
    locala.type = 5;
    locala.gd = paramString;
    return locala;
  }
  
  private static dh bJ(String paramString)
  {
    try
    {
      dh localdh = dh.bH(paramString);
      return localdh;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      bh.t("Failed to convert '" + paramString + "' to a number.");
    }
    return XL;
  }
  
  private static Long bK(String paramString)
  {
    paramString = bJ(paramString);
    if (paramString == XL) {
      return XJ;
    }
    return Long.valueOf(paramString.longValue());
  }
  
  private static Double bL(String paramString)
  {
    paramString = bJ(paramString);
    if (paramString == XL) {
      return XK;
    }
    return Double.valueOf(paramString.doubleValue());
  }
  
  private static Boolean bM(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return Boolean.FALSE;
    }
    return XN;
  }
  
  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).doubleValue();
    }
    bh.t("getDouble received non-Number");
    return 0.0D;
  }
  
  public static String j(d.a parama)
  {
    return m(o(parama));
  }
  
  public static dh k(d.a parama)
  {
    return n(o(parama));
  }
  
  public static Object ko()
  {
    return XI;
  }
  
  public static Long kp()
  {
    return XJ;
  }
  
  public static Double kq()
  {
    return XK;
  }
  
  public static Boolean kr()
  {
    return XN;
  }
  
  public static dh ks()
  {
    return XL;
  }
  
  public static String kt()
  {
    return XM;
  }
  
  public static d.a ku()
  {
    return XQ;
  }
  
  public static Long l(d.a parama)
  {
    return o(o(parama));
  }
  
  public static Double m(d.a parama)
  {
    return p(o(parama));
  }
  
  public static String m(Object paramObject)
  {
    if (paramObject == null) {
      return XM;
    }
    return paramObject.toString();
  }
  
  public static dh n(Object paramObject)
  {
    if ((paramObject instanceof dh)) {
      return (dh)paramObject;
    }
    if (t(paramObject)) {
      return dh.v(u(paramObject));
    }
    if (s(paramObject)) {
      return dh.a(Double.valueOf(getDouble(paramObject)));
    }
    return bJ(m(paramObject));
  }
  
  public static Boolean n(d.a parama)
  {
    return q(o(parama));
  }
  
  public static Long o(Object paramObject)
  {
    if (t(paramObject)) {
      return Long.valueOf(u(paramObject));
    }
    return bK(m(paramObject));
  }
  
  public static Object o(d.a parama)
  {
    int k = 0;
    int j = 0;
    int i = 0;
    if (parama == null) {
      return XI;
    }
    Object localObject1;
    Object localObject2;
    switch (parama.type)
    {
    default: 
      bh.t("Failed to convert a value of type: " + parama.type);
      return XI;
    case 1: 
      return parama.fY;
    case 2: 
      localObject1 = new ArrayList(parama.fZ.length);
      parama = parama.fZ;
      j = parama.length;
      while (i < j)
      {
        localObject2 = o(parama[i]);
        if (localObject2 == XI) {
          return XI;
        }
        ((ArrayList)localObject1).add(localObject2);
        i += 1;
      }
      return localObject1;
    case 3: 
      if (parama.ga.length != parama.gb.length)
      {
        bh.t("Converting an invalid value to object: " + parama.toString());
        return XI;
      }
      localObject1 = new HashMap(parama.gb.length);
      i = k;
      while (i < parama.ga.length)
      {
        localObject2 = o(parama.ga[i]);
        Object localObject3 = o(parama.gb[i]);
        if ((localObject2 == XI) || (localObject3 == XI)) {
          return XI;
        }
        ((Map)localObject1).put(localObject2, localObject3);
        i += 1;
      }
      return localObject1;
    case 4: 
      bh.t("Trying to convert a macro reference to object");
      return XI;
    case 5: 
      bh.t("Trying to convert a function id to object");
      return XI;
    case 6: 
      return Long.valueOf(parama.ge);
    case 7: 
      localObject1 = new StringBuffer();
      parama = parama.gg;
      k = parama.length;
      i = j;
      while (i < k)
      {
        localObject2 = j(parama[i]);
        if (localObject2 == XM) {
          return XI;
        }
        ((StringBuffer)localObject1).append((String)localObject2);
        i += 1;
      }
      return ((StringBuffer)localObject1).toString();
    }
    return Boolean.valueOf(parama.gf);
  }
  
  public static Double p(Object paramObject)
  {
    if (s(paramObject)) {
      return Double.valueOf(getDouble(paramObject));
    }
    return bL(m(paramObject));
  }
  
  public static Boolean q(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return (Boolean)paramObject;
    }
    return bM(m(paramObject));
  }
  
  public static d.a r(Object paramObject)
  {
    boolean bool = false;
    Object localObject1 = new d.a();
    if ((paramObject instanceof d.a)) {
      return (d.a)paramObject;
    }
    if ((paramObject instanceof String))
    {
      ((d.a)localObject1).type = 1;
      ((d.a)localObject1).fY = ((String)paramObject);
    }
    for (;;)
    {
      ((d.a)localObject1).gi = bool;
      return (d.a)localObject1;
      Object localObject2;
      Object localObject3;
      if ((paramObject instanceof List))
      {
        ((d.a)localObject1).type = 2;
        localObject2 = (List)paramObject;
        paramObject = new ArrayList(((List)localObject2).size());
        localObject2 = ((List)localObject2).iterator();
        bool = false;
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = r(((Iterator)localObject2).next());
          if (localObject3 == XQ) {
            return XQ;
          }
          if ((bool) || (((d.a)localObject3).gi)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localObject3);
            break;
          }
        }
        ((d.a)localObject1).fZ = ((d.a[])((List)paramObject).toArray(new d.a[0]));
      }
      else if ((paramObject instanceof Map))
      {
        ((d.a)localObject1).type = 3;
        localObject3 = ((Map)paramObject).entrySet();
        paramObject = new ArrayList(((Set)localObject3).size());
        localObject2 = new ArrayList(((Set)localObject3).size());
        localObject3 = ((Set)localObject3).iterator();
        bool = false;
        if (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = (Map.Entry)((Iterator)localObject3).next();
          d.a locala = r(((Map.Entry)localObject4).getKey());
          localObject4 = r(((Map.Entry)localObject4).getValue());
          if ((locala == XQ) || (localObject4 == XQ)) {
            return XQ;
          }
          if ((bool) || (locala.gi) || (((d.a)localObject4).gi)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(locala);
            ((List)localObject2).add(localObject4);
            break;
          }
        }
        ((d.a)localObject1).ga = ((d.a[])((List)paramObject).toArray(new d.a[0]));
        ((d.a)localObject1).gb = ((d.a[])((List)localObject2).toArray(new d.a[0]));
      }
      else if (s(paramObject))
      {
        ((d.a)localObject1).type = 1;
        ((d.a)localObject1).fY = paramObject.toString();
      }
      else if (t(paramObject))
      {
        ((d.a)localObject1).type = 6;
        ((d.a)localObject1).ge = u(paramObject);
      }
      else
      {
        if (!(paramObject instanceof Boolean)) {
          break;
        }
        ((d.a)localObject1).type = 8;
        ((d.a)localObject1).gf = ((Boolean)paramObject).booleanValue();
      }
    }
    localObject1 = new StringBuilder().append("Converting to Value from unknown object type: ");
    if (paramObject == null) {}
    for (paramObject = "null";; paramObject = paramObject.getClass().toString())
    {
      bh.t((String)paramObject);
      return XQ;
    }
  }
  
  private static boolean s(Object paramObject)
  {
    return ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof dh)) && (((dh)paramObject).kj()));
  }
  
  private static boolean t(Object paramObject)
  {
    return ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof dh)) && (((dh)paramObject).kk()));
  }
  
  private static long u(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).longValue();
    }
    bh.t("getInt64 received non-Number");
    return 0L;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */