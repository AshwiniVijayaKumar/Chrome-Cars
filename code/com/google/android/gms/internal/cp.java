package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public final class cp
{
  private static final Object op = new Object();
  public static final String pu;
  private static cq pv = new cq(pu);
  private static BigInteger pw = BigInteger.ONE;
  private static HashSet<co> px = new HashSet();
  private static HashMap<String, cr> py = new HashMap();
  
  static
  {
    Object localObject1 = UUID.randomUUID();
    byte[] arrayOfByte1 = BigInteger.valueOf(((UUID)localObject1).getLeastSignificantBits()).toByteArray();
    byte[] arrayOfByte2 = BigInteger.valueOf(((UUID)localObject1).getMostSignificantBits()).toByteArray();
    localObject1 = new BigInteger(1, arrayOfByte1).toString();
    int i = 0;
    while (i < 2)
    {
      try
      {
        Object localObject2 = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject2).update(arrayOfByte1);
        ((MessageDigest)localObject2).update(arrayOfByte2);
        byte[] arrayOfByte3 = new byte[8];
        System.arraycopy(((MessageDigest)localObject2).digest(), 0, arrayOfByte3, 0, 8);
        localObject2 = new BigInteger(1, arrayOfByte3).toString();
        localObject1 = localObject2;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
      i += 1;
    }
    pu = (String)localObject1;
  }
  
  public static Bundle a(v.a parama, String paramString, Context paramContext)
  {
    Bundle localBundle;
    synchronized (op)
    {
      localBundle = new Bundle();
      localBundle.putBundle("app", pv.b(paramString, paramContext));
      paramString = new Bundle();
      paramContext = py.keySet().iterator();
      if (paramContext.hasNext())
      {
        String str = (String)paramContext.next();
        paramString.putBundle(str, ((cr)py.get(str)).toBundle());
      }
    }
    localBundle.putBundle("slots", paramString);
    paramString = new ArrayList();
    paramContext = px.iterator();
    while (paramContext.hasNext()) {
      paramString.add(((co)paramContext.next()).toBundle());
    }
    localBundle.putParcelableArrayList("ads", paramString);
    parama.a(px);
    px = new HashSet();
    return localBundle;
  }
  
  public static void a(co paramco)
  {
    synchronized (op)
    {
      px.add(paramco);
      return;
    }
  }
  
  public static void a(v.a parama)
  {
    synchronized (op)
    {
      px.addAll(parama.ah());
      return;
    }
  }
  
  public static void a(String paramString, cr paramcr)
  {
    synchronized (op)
    {
      py.put(paramString, paramcr);
      return;
    }
  }
  
  public static String aP()
  {
    synchronized (op)
    {
      String str = pw.toString();
      pw = pw.add(BigInteger.ONE);
      return str;
    }
  }
  
  public static cq aQ()
  {
    synchronized (op)
    {
      cq localcq = pv;
      return localcq;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */