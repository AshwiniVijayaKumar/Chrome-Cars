package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class ey
  implements SafeParcelable, fb.b<String, Integer>
{
  public static final ez CREATOR = new ez();
  private final HashMap<String, Integer> Cp;
  private final HashMap<Integer, String> Cq;
  private final ArrayList<a> Cr;
  private final int wj;
  
  public ey()
  {
    this.wj = 1;
    this.Cp = new HashMap();
    this.Cq = new HashMap();
    this.Cr = null;
  }
  
  ey(int paramInt, ArrayList<a> paramArrayList)
  {
    this.wj = paramInt;
    this.Cp = new HashMap();
    this.Cq = new HashMap();
    this.Cr = null;
    a(paramArrayList);
  }
  
  private void a(ArrayList<a> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      a locala = (a)paramArrayList.next();
      f(locala.Cs, locala.Ct);
    }
  }
  
  public String a(Integer paramInteger)
  {
    String str = (String)this.Cq.get(paramInteger);
    paramInteger = str;
    if (str == null)
    {
      paramInteger = str;
      if (this.Cp.containsKey("gms_unknown")) {
        paramInteger = "gms_unknown";
      }
    }
    return paramInteger;
  }
  
  public int describeContents()
  {
    ez localez = CREATOR;
    return 0;
  }
  
  ArrayList<a> ek()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.Cp.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new a(str, ((Integer)this.Cp.get(str)).intValue()));
    }
    return localArrayList;
  }
  
  public int el()
  {
    return 7;
  }
  
  public int em()
  {
    return 0;
  }
  
  public ey f(String paramString, int paramInt)
  {
    this.Cp.put(paramString, Integer.valueOf(paramInt));
    this.Cq.put(Integer.valueOf(paramInt), paramString);
    return this;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ez localez = CREATOR;
    ez.a(this, paramParcel, paramInt);
  }
  
  public static final class a
    implements SafeParcelable
  {
    public static final fa CREATOR = new fa();
    final String Cs;
    final int Ct;
    final int versionCode;
    
    a(int paramInt1, String paramString, int paramInt2)
    {
      this.versionCode = paramInt1;
      this.Cs = paramString;
      this.Ct = paramInt2;
    }
    
    a(String paramString, int paramInt)
    {
      this.versionCode = 1;
      this.Cs = paramString;
      this.Ct = paramInt;
    }
    
    public int describeContents()
    {
      fa localfa = CREATOR;
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      fa localfa = CREATOR;
      fa.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */