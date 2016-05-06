package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class io
  extends fb
  implements SafeParcelable, Moment
{
  public static final ip CREATOR = new ip();
  private static final HashMap<String, fb.a<?, ?>> RL = new HashMap();
  private String Oc;
  private final Set<Integer> RM;
  private im SH;
  private im SI;
  private String Sz;
  private String uS;
  private final int wj;
  
  static
  {
    RL.put("id", fb.a.j("id", 2));
    RL.put("result", fb.a.a("result", 4, im.class));
    RL.put("startDate", fb.a.j("startDate", 5));
    RL.put("target", fb.a.a("target", 6, im.class));
    RL.put("type", fb.a.j("type", 7));
  }
  
  public io()
  {
    this.wj = 1;
    this.RM = new HashSet();
  }
  
  io(Set<Integer> paramSet, int paramInt, String paramString1, im paramim1, String paramString2, im paramim2, String paramString3)
  {
    this.RM = paramSet;
    this.wj = paramInt;
    this.uS = paramString1;
    this.SH = paramim1;
    this.Sz = paramString2;
    this.SI = paramim2;
    this.Oc = paramString3;
  }
  
  public io(Set<Integer> paramSet, String paramString1, im paramim1, String paramString2, im paramim2, String paramString3)
  {
    this.RM = paramSet;
    this.wj = 1;
    this.uS = paramString1;
    this.SH = paramim1;
    this.Sz = paramString2;
    this.SI = paramim2;
    this.Oc = paramString3;
  }
  
  protected boolean a(fb.a parama)
  {
    return this.RM.contains(Integer.valueOf(parama.eu()));
  }
  
  protected Object ak(String paramString)
  {
    return null;
  }
  
  protected boolean al(String paramString)
  {
    return false;
  }
  
  protected Object b(fb.a parama)
  {
    switch (parama.eu())
    {
    case 3: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + parama.eu());
    case 2: 
      return this.uS;
    case 4: 
      return this.SH;
    case 5: 
      return this.Sz;
    case 6: 
      return this.SI;
    }
    return this.Oc;
  }
  
  public int describeContents()
  {
    ip localip = CREATOR;
    return 0;
  }
  
  public HashMap<String, fb.a<?, ?>> en()
  {
    return RL;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof io)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (io)paramObject;
    Iterator localIterator = RL.values().iterator();
    while (localIterator.hasNext())
    {
      fb.a locala = (fb.a)localIterator.next();
      if (a(locala))
      {
        if (((io)paramObject).a(locala))
        {
          if (!b(locala).equals(((io)paramObject).b(locala))) {
            return false;
          }
        }
        else {
          return false;
        }
      }
      else if (((io)paramObject).a(locala)) {
        return false;
      }
    }
    return true;
  }
  
  public String getId()
  {
    return this.uS;
  }
  
  public ItemScope getResult()
  {
    return this.SH;
  }
  
  public String getStartDate()
  {
    return this.Sz;
  }
  
  public ItemScope getTarget()
  {
    return this.SI;
  }
  
  public String getType()
  {
    return this.Oc;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  Set<Integer> hB()
  {
    return this.RM;
  }
  
  im hS()
  {
    return this.SH;
  }
  
  im hT()
  {
    return this.SI;
  }
  
  public io hU()
  {
    return this;
  }
  
  public boolean hasId()
  {
    return this.RM.contains(Integer.valueOf(2));
  }
  
  public boolean hasResult()
  {
    return this.RM.contains(Integer.valueOf(4));
  }
  
  public boolean hasStartDate()
  {
    return this.RM.contains(Integer.valueOf(5));
  }
  
  public boolean hasTarget()
  {
    return this.RM.contains(Integer.valueOf(6));
  }
  
  public boolean hasType()
  {
    return this.RM.contains(Integer.valueOf(7));
  }
  
  public int hashCode()
  {
    Iterator localIterator = RL.values().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      fb.a locala = (fb.a)localIterator.next();
      if (!a(locala)) {
        break label68;
      }
      int j = locala.eu();
      i = b(locala).hashCode() + (i + j);
    }
    label68:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ip localip = CREATOR;
    ip.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\io.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */