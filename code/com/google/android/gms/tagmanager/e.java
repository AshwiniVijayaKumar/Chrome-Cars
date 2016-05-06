package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class e
  extends aj
{
  private static final String ID = a.ah.toString();
  private static final String TD = b.bS.toString();
  private static final String TE = b.bV.toString();
  private final Context kL;
  
  public e(Context paramContext)
  {
    super(ID, new String[] { TE });
    this.kL = paramContext;
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(TE);
    if (localObject == null) {
      return di.ku();
    }
    localObject = di.j((d.a)localObject);
    paramMap = (d.a)paramMap.get(TD);
    if (paramMap != null) {}
    for (paramMap = di.j(paramMap);; paramMap = null)
    {
      paramMap = ay.e(this.kL, (String)localObject, paramMap);
      if (paramMap == null) {
        break;
      }
      return di.r(paramMap);
    }
    return di.ku();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */