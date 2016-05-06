package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

abstract class cd
  extends aj
{
  private static final String US = b.bt.toString();
  private static final String VQ = b.bu.toString();
  
  public cd(String paramString)
  {
    super(paramString, new String[] { US, VQ });
  }
  
  protected abstract boolean a(d.a parama1, d.a parama2, Map<String, d.a> paramMap);
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = paramMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((d.a)((Iterator)localObject).next() == di.ku()) {
        return di.r(Boolean.valueOf(false));
      }
    }
    localObject = (d.a)paramMap.get(US);
    d.a locala = (d.a)paramMap.get(VQ);
    if ((localObject == null) || (locala == null)) {}
    for (boolean bool = false;; bool = a((d.a)localObject, locala, paramMap)) {
      return di.r(Boolean.valueOf(bool));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\cd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */