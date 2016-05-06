package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class u
  extends aj
{
  private static final String ID = a.N.toString();
  private static final String NAME = b.dn.toString();
  private static final String UC = b.cm.toString();
  private final DataLayer TN;
  
  public u(DataLayer paramDataLayer)
  {
    super(ID, new String[] { NAME });
    this.TN = paramDataLayer;
  }
  
  public boolean iy()
  {
    return false;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = this.TN.get(di.j((d.a)paramMap.get(NAME)));
    if (localObject == null)
    {
      paramMap = (d.a)paramMap.get(UC);
      if (paramMap != null) {
        return paramMap;
      }
      return di.ku();
    }
    return di.r(localObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */